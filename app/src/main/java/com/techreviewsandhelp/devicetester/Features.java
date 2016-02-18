package com.techreviewsandhelp.devicetester;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Calendar;

public class Features{
    public final int SCREEN_BURNOUT = 0;
    public final int LIGHT = 1;
    public final int SPEAKER = 2;
    public final int CALL = 3;
    public final int VIBRATE = 4;
    public final int GPS = 5;
    public final int NFC = 6;
    public final int BLUETOOTH = 7;
    public final int ACCELEROMETER = 8;
    public final int BUTTONS = 9;
    public final int CAMERA = 10;

    Context mContext;
    private Camera camera;
    private Vibrator myVib;

    public Features(Context context){
        mContext = context;
    }

    public void runFeature(int name){
        switch (name){
            case SCREEN_BURNOUT: {screenBurnout();break;}
            case LIGHT: {light();break;}
            case SPEAKER: {speaker();break;}
            case CALL: {call();break;}
            case VIBRATE: {vibrate();break;}
            case GPS: {gps();break;}
            case NFC: {nfc();break;}
            case BLUETOOTH: {bluetooth();break;}
            case ACCELEROMETER: {accelerometer();break;}
            case BUTTONS: {buttons();break;}
            case CAMERA: {camera();break;}
        }
    }

    private void screenBurnout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

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
                        mContext.startActivity(new Intent(mContext, Screen.class));
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

    private void light(){
        boolean hasFlash;


        hasFlash = mContext.getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            AlertDialog alert = new AlertDialog.Builder(mContext)
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

            AlertDialog alert = new AlertDialog.Builder(mContext)
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
        }
    }

    private void speaker(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set title
        alertDialogBuilder.setTitle("Test the speaker and mic function");

        // set dialog message
        alertDialogBuilder
                .setMessage("Speaker, mic")
                .setCancelable(false)
                        //set right button
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(mContext,SpeakerActivity.class);
                        mContext.startActivity(intent);
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

    private void call(){
        //need to make a thing saying what the person should do to test the call
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

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
                        mContext.startActivity(intent);
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

    private void vibrate(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        myVib = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
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

    private void gps() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    mContext);

            // set title
            alertDialogBuilder.setTitle("Test the GPS function");

            // set dialog message
            alertDialogBuilder
                    .setMessage("The app can see your GPS is on. Please make sure you have Google Maps installed on your phone. By pressing I understand, your device will open Google Maps. From here, you will be able to see if your GPS works at all, and how good is it.")
                    .setCancelable(false)
                            //set right button
                    .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // This will take the person to the maps app.

                            String s = "https://www.google.com/maps";

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                            mContext.startActivity(browserIntent);
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
        } else {
            Toast.makeText(mContext,
                    "Your GPS is off, or your device doesn't have it. Please make sure it's on by going into the device settings.", Toast.LENGTH_LONG).show();

        }
    }

    private void nfc(){
        NfcManager manager = (NfcManager) mContext.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            // adapter exists and is enabled.


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    mContext);

            // set title
            alertDialogBuilder.setTitle("Test the NFC function");

            // set dialog message
            alertDialogBuilder
                    .setMessage("This app can see that your NFC is enable. Please feel free to use another NFC device to see how well it works.")
                    .setCancelable(false)
                            //set right button
                    .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // This will take the person to the maps app.

                            dialog.cancel();
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
            alertDialog.show();}
        else{
            Toast.makeText(mContext,
                    "Your NFC is off, or your device doesn't have it. Please make sure it's on by going into the device settings.", Toast.LENGTH_LONG).show();
        }
    }

    private void bluetooth(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(mContext,
                    "Your device doesn't have Bluetooth. ", Toast.LENGTH_LONG).show();

        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                // Bluetooth is not enable
                Toast.makeText(mContext,
                        "Your Bluetooth is off, or your device doesn't have it. Please make sure it's on by going into the device settings.", Toast.LENGTH_LONG).show();

            }else {AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    mContext);

                // set title
                alertDialogBuilder.setTitle("Test the Bluetooth function");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bluetooth")
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
                alertDialog.show();}
        }
    }

    private void accelerometer(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set title
        alertDialogBuilder.setTitle("Test the accelerometer");

        // set dialog message
        alertDialogBuilder
                .setMessage("")
                .setCancelable(false)
                        //set right button
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(mContext,AccelerometerActivity.class);
                        mContext.startActivity(intent);
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

    private void buttons(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set title
        alertDialogBuilder.setTitle("Test buttons");

        // set dialog message
        alertDialogBuilder
                .setMessage("Buttons")
                .setCancelable(false)
                        //set right button
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(mContext,ButtonsActivity.class);
                        mContext.startActivity(intent);
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

    private void camera(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set title
        alertDialogBuilder.setTitle("Test the Camera");

        // set dialog message
        alertDialogBuilder
                .setMessage("In this test you're looking for any problems with the picture. Before you say the last owner scratched the lens, please make sure the lens is clean.")
                .setCancelable(false)
                        //set right button
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // This will take the person to the maps app.

                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        mContext.startActivity(intent);
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
}
