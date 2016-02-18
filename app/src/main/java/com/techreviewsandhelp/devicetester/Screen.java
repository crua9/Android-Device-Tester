package com.techreviewsandhelp.devicetester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by crua9 on 1/30/2016.
 */
public class Screen extends Activity {
    int counter = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        //Bright
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = 1.0f; // 0.0 - 1.0
        getWindow().setAttributes(lp);

        //ID
        final FrameLayout layout = (FrameLayout)findViewById(R.id.burnout);
        final Context c = this;

        //Layout button
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (counter){
                    case 0:{
                        layout.setBackgroundColor(getResources().getColor(R.color.clearRed));
                        break;
                    }
                    case 1:{
                        layout.setBackgroundColor(getResources().getColor(R.color.clearGreen));
                        break;
                    }
                    case 2:{
                        layout.setBackgroundColor(getResources().getColor(R.color.clearBlue));
                        break;
                    }
                    case 3:{
                        finish();
                        break;
                    }
                }
                counter++;
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
