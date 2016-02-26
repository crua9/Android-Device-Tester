package com.techreviewsandhelp.devicetester;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerViewClickListener {

    private Features features;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        features = new Features(this);
        String[] featuresNames = getResources().getStringArray(R.array.features_names);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this,featuresNames,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);

        /**
         * things to work on
         * Speaker and mic
         * accelerometer
         * buttons (the thing will tell you what buttons you're pressing
         *
         * I added a basic AlertDialog on each one, but they have to be edited.
         *
         *
         * Another thing that needs to be worked on is the UGI.
         */
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        v.startAnimation(animation);
        features.runFeature(position);
    }
}
