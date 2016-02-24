package com.techreviewsandhelp.devicetester;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class AccelerometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.trh_logo);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        setContentView(imageView);
        Toast.makeText(this, "Rotate the device.", Toast.LENGTH_SHORT).show();
    }
}
