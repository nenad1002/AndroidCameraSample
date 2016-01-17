package com.example.nenad.camerasample2.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.nenad.camerasample2.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProcessPhotoActivity extends AppCompatActivity {


    public static Intent getIntent(Context context, String picturePath) {
        Intent intent = new Intent(context, ProcessPhotoActivity.class);

        intent.putExtra(PICTURE_PATH_EXTRA, picturePath);

        return intent;
    }


    private static final String PICTURE_PATH_EXTRA = "PICTURE_PATH_EXTRA";

    private static final int RESIZE_RATIO = 5;

    private static final int PICTURE_SIDE_LENGTH = 500;


    @Bind(R.id.preview_view)
    ImageView previewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_photo);

        ButterKnife.bind(this);

        String path = getIntent().getStringExtra(PICTURE_PATH_EXTRA);

        loadPicture(path);

    }


    private void loadPicture(String picturePath) {


            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = RESIZE_RATIO;

            final Bitmap tmpBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(picturePath, options)
                    , PICTURE_SIDE_LENGTH, PICTURE_SIDE_LENGTH, false);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    final Bitmap rotatedBitmap = mirrorBitmap(rotateBitmap(tmpBitmap));
                    iterateBitmap(rotatedBitmap);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            previewView.setImageBitmap(rotatedBitmap);
                        }
                    });
                }
            });

            thread.start();

    }

    private Bitmap rotateBitmap(Bitmap bitmap) {
        Bitmap resBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {
                resBitmap.setPixel(j, i, bitmap.getPixel(i, j));
            }
        }


        return resBitmap;
    }

    private Bitmap mirrorBitmap(Bitmap bitmap) {
        Bitmap resBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        int n = bitmap.getHeight(), m = bitmap.getWidth();

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m; j++) {
                resBitmap.setPixel(i, j, bitmap.getPixel(n - i - 1, j));
                resBitmap.setPixel(n - i - 1, j, bitmap.getPixel(i, j));
            }
        }


        return resBitmap;
    }

    private void iterateBitmap(Bitmap bitmap) {
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int color = bitmap.getPixel(i, j);
                // do something with color -> i am going to use this in neural network to see
                // each perticular color
            }
        }
    }
}
