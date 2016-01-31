package com.techreviewsandhelp.devicetester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ID
        Button screen = (Button)findViewById(R.id.screen);
        Button light = (Button)findViewById(R.id.light);
        Button speaker = (Button)findViewById(R.id.speaker);
        Button call = (Button)findViewById(R.id.call);
        Button vibrate = (Button)findViewById(R.id.vibrate);
        Button gps = (Button)findViewById(R.id.gps);
        Button nfc = (Button)findViewById(R.id.nfc);
        Button bluetooth = (Button)findViewById(R.id.bluetooth);
        Button accelerometer = (Button)findViewById(R.id.accelerometer);



        final Context c = this;
        //Screen Burnout
        screen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /**
                 * Maybe a good idea to have something come up to tell the person what to look for.
                 * Something that the person has to say OK to so they have enough time to read it.
                 */


                startActivity(new Intent(c, Screen.class));
                finish();
            }
        });

            //light
        light.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

            //speaker
        speaker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

            //call
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

            //vibrate
        vibrate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

            //gps
        gps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

            //nfc
        nfc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

            //bluetooth
        bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });

            //accelerometer
        accelerometer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {}
        });


    }
}
