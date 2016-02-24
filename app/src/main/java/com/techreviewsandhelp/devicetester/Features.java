package com.techreviewsandhelp.devicetester;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.Calendar;

public class Features{
    public static final int SCREEN_BURNOUT = 0;
    public static final int LIGHT = 1;
    public static final int SPEAKER = 2;
    public static final int CALL = 3;
    public static final int VIBRATE = 4;
    public static final int GPS = 5;
    public static final int NFC = 6;
    public static final int BLUETOOTH = 7;
    public static final int ACCELEROMETER = 8;
    public static final int BUTTONS = 9;
    public static final int CAMERA = 10;

    Context mContext;
    private Camera camera;
    private Vibrator myVib;
    Resources resources;

    public Features(Context context){
        mContext = context;
        resources = context.getResources();
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
                .setMessage(resources.getString(R.string.burnout_message))
                .setCancelable(false)
                        //set right button
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        mContext.startActivity(new Intent(mContext, Screen.class));
                        Toast.makeText(mContext, "Look for screen burn. Then tap the screen.", Toast.LENGTH_SHORT).show();
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
                .setMessage(resources.getString(R.string.speaker_message))
                .setCancelable(false)
                        //set right button
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Intent intent = new Intent(mContext,SpeakerActivity.class);
                        Intent intent = new Intent( MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                        mContext.startActivity(intent);
                        Toast.makeText(mContext, "Record your voice, then listen to it.", Toast.LENGTH_SHORT).show();
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
                .setMessage(resources.getString(R.string.call_message))
                .setCancelable(false)
                        //set right button
                .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // This will take the person to the calling app.

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"));
                        mContext.startActivity(intent);
                        Toast.makeText(mContext, "Call to someone.", Toast.LENGTH_SHORT).show();
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
                .setMessage(resources.getString(R.string.vibrate_message))
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
                    .setMessage(resources.getString(R.string.gps_message))
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
                    .setMessage(resources.getString(R.string.nfc_message))
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
                        .setMessage(resources.getString(R.string.bluetooth_message))
                        .setCancelable(false)
                                //set right button
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(mContext,"Bluetooth is OK.",Toast.LENGTH_LONG).show();
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
                .setMessage(resources.getString(R.string.accelerometer_message))
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
                .setMessage(R.string.buttons_message)
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
                .setMessage(resources.getString(R.string.camera_message))
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
