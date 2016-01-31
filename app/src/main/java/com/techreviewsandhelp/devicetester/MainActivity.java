package com.techreviewsandhelp.devicetester;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Vibrator;
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
import java.util.Locale;

public class MainActivity extends Activity {
    private Camera camera;
    private Vibrator myVib;

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

        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);


        /**
         * things to work on
         * Speaker and mic
         * NFC
         * Bluetooth
         * accelerometer
         * buttons (the thing will tell you what buttons you're pressing
         * camera (it will just open the camera. that away the person can see if the last owner messed that up)
         *
         * I added a basic AlertDialog on each one, but they have to be edited.
         *
         *
         * Another thing that needs to be worked on is the UGI.
         */




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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the speaker and mic function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the maps app.


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

            //call
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //need to make a thing saying what the person should do to test the call
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the call");

                // set dialog message
                alertDialogBuilder
                        .setMessage("In this test you will simply need to call someone. This will test if the radio works in the phone, the quality of the call, and how well you can hear it. During this test, please remember to test both the normal and the speaker quality. By pressing I understand, you will be taken to the calling app. When you're satisfy with the test. Please remember to come back to this app to finish the test.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the calling app.

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:"));
                                startActivity(intent);
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

            //vibrate
        vibrate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the vibrate function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("By pressing I understand, your phone should vibrate one time real quick. If this doesn't work, then it means your vibrate function on the phone may not work.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will vibrate for a few seconds.

                                myVib.vibrate(500);
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

            //gps
        gps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the GPS function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the maps app.

                                String s ="https://www.google.com/maps";

                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                                startActivity(browserIntent);
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

            //nfc
        nfc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the GPS function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the maps app.


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

            //bluetooth
        bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the GPS function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the maps app.


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

            //accelerometer
        accelerometer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the GPS function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the maps app.


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

            //Butttons
        buttons.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the GPS function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the maps app.


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

            //Camera
        cam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        c);

                // set title
                alertDialogBuilder.setTitle("Test the GPS function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // This will take the person to the maps app.


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
    }
    //Back Button
    final Context c = this;
    @Override
    public void onBackPressed() {

    }}
