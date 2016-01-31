package com.techreviewsandhelp.devicetester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by crua9 on 1/30/2016.
 */
public class Screen extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        //Bright
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = 1.0f; // 0.0 - 1.0
        getWindow().setAttributes(lp);

        //ID
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.burnout);
        final Context c = this;

        //Layout button
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c, MainActivity.class));
                finish();
            }
        });

}
    //Back Button
    final Context c = this;
    @Override
        public void onBackPressed() {
        startActivity(new Intent(c, MainActivity.class));
        finish();
    }
}
