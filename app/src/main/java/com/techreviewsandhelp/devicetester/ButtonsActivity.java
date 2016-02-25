package com.techreviewsandhelp.devicetester;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonsActivity extends AppCompatActivity {
    TextView keyPressedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        keyPressedTextView = (TextView)findViewById(R.id.keyPressedTextView);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Toast.makeText(this, "Push every single button.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch(keyCode){
            case KeyEvent.KEYCODE_HOME:{
                keyPressedTextView.setText("Home Button");
                break;
            }
            case KeyEvent.KEYCODE_BACK:{
                keyPressedTextView.setText("Back Button");
                break;
            }
            case KeyEvent.KEYCODE_MENU:{
                keyPressedTextView.setText("Menu Button");
                break;
            }
            case KeyEvent.KEYCODE_VOLUME_DOWN:{
                keyPressedTextView.setText("Volume Down Button");
                break;
            }
            case KeyEvent.KEYCODE_VOLUME_UP:{
                keyPressedTextView.setText("Volume Up Button");
                break;
            }
            case KeyEvent.KEYCODE_POWER:{
                keyPressedTextView.setText("Power Button");
                break;
            }
            case KeyEvent.KEYCODE_SEARCH:{
                keyPressedTextView.setText("Search Button");
                break;
            }

            default: keyPressedTextView.setText("Pressed button code: "+keyCode);
        }
    return true;
    }
}
