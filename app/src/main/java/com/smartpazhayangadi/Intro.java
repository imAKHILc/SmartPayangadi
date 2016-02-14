package com.smartpazhayangadi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pushbots.push.Pushbots;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Intro extends AppCompatActivity {

    Activity context;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        context=this;
        Pushbots.sharedInstance().init(this);
        mHandler.postDelayed(mUpdateTimeTask, 3000);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(), IntroAd.class);
            startActivity(i);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
    }
}
