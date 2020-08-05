package com.burhanrashid52.photoeditor.frame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.burhanrashid52.photoeditor.R;

import java.io.ByteArrayOutputStream;

public class FrameActivity extends AppCompatActivity {

    Bitmap image;
    Bitmap originalImage;
    ImageView imageView;
    ImageView frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        initData();

        Button done = findViewById(R.id.frame_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("frame_image", convertBitmapToByteArraye(getImage()));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        Button cancel = findViewById(R.id.frame_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("frame_image", convertBitmapToByteArraye(originalImage));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
    private void initData() {
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView = findViewById(R.id.frame_img);
        imageView.setImageBitmap(image);
        originalImage = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

    Bitmap getImage() {
        ImageView imageView = findViewById(R.id.frame_img);
        return ((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

    byte[] convertBitmapToByteArraye(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, 10, 10, null);
        return result;
    }


    }