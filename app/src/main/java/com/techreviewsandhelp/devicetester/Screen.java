package com.techreviewsandhelp.devicetester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by crua9 on 1/30/2016.
 */
public class Screen extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.burnout);
        final Context c = this;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c, MainActivity.class));
                finish();
            }
        });

}
    final Context c = this;
    @Override
        public void onBackPressed() {
        startActivity(new Intent(c, MainActivity.class));
        finish();
    }
}
