package com.techreviewsandhelp.devicetester;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.security.Policy;

public class MainActivity extends Activity {
    private Camera camera;

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
        Button buttons = (Button)findViewById(R.id.buttons);
        Button cam = (Button)findViewById(R.id.cam);







        final Context c = this;
        //Screen Burnout
        screen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test Screen Burn/Stuck Pixels");

                // set dialog message
                alertDialogBuilder
                        .setMessage("This test is to allow you to see if there is any burnt in images. Once you click I understand, please look very close at the screen. You're looking for a ghost of an image or something (Screen Burn). Then click anywhere to come back to this page.")
                        .setCancelable(false)
                        //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                startActivity(new Intent(c, Screen.class));
                                finish();
                            }
                        })
                        //set left button
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }



        });

            //light
        light.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                boolean hasFlash;


                hasFlash = getApplicationContext().getPackageManager()
                        .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

                if (!hasFlash) {
                    AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                            .create();
                    alert.setTitle("ERROR!!!");
                    alert.setMessage("Your device doesn't support flashlight");
                    alert.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    alert.show();
                } else {

                                            //ON
                    camera = Camera.open();
                    Camera.Parameters p = camera.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    camera.startPreview();

                        AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                                    .create();
                                alert.setTitle("Light");
                                alert.setMessage("Your LED is on in the back and should be lighting up.");
                                alert.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                //Off
                                final Camera.Parameters p = camera.getParameters();
                                p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                                camera.setParameters(p);
                                camera.stopPreview();
                                camera.release();
                                camera = null;


                            }
                        });
                        alert.show();

                    }}});

            //speaker
        speaker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

            //call
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //need to make a thing saying what the person should do to test the call
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"));
                startActivity(intent);
            }
        });

            //vibrate
        vibrate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

            //gps
        gps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

            //nfc
        nfc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

            //bluetooth
        bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

            //accelerometer
        accelerometer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

            //Butttons
        buttons.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

            //Camera
        cam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }
    //Back Button
    final Context c = this;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(c, MainActivity.class));
        finish();
    }}
