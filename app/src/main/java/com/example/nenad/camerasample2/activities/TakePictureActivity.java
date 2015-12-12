package com.example.nenad.camerasample2.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.nenad.camerasample2.CameraSampleApplication;
import com.example.nenad.camerasample2.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;

import butterknife.ButterKnife;

public class TakePictureActivity extends AppCompatActivity {


    private Camera mCamera;
    private CameraPreviewActivity mCameraPreview;

    @Bind(R.id.camera_preview)
    FrameLayout preview;

    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom__camera);
;
        ButterKnife.bind(this);

        final Context context = this;

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();


                mCamera = getCameraInstance();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCameraPreview = new CameraPreviewActivity(context, mCamera);
                        preview.addView(mCameraPreview);
                    }
                });
            }
        };

        thread.start();

        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            // cannot get camera or does not exist
            Log.e(getString(R.string.app_name), getString(R.string.failed_open_camera));
        }
        return camera;
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFile = getOutputMediaFile();
            if (pictureFile == null) {
                return;
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                Intent intent = ProcessPhotoActivity.getIntent(context, pictureFile.getAbsolutePath());
                startActivity(intent);
                finish();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {
            }
        }
    };

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                CameraSampleApplication.getInstance().getString(R.string.app_name));
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(CameraSampleApplication.getInstance().getString(R.string.app_name),
                        CameraSampleApplication.getInstance().getString(R.string.failed_create_directory));
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }
}

