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

            final Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);

            final Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 350, 350, false);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    iterateBitmap(bitmap2);
                }
            });

            thread.start();


            previewView.setImageBitmap(bitmap2);
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
