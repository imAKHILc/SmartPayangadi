package com.smartpazhayangadi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,head;
    InterstitialAd mInterstitialAd;
    //Random r = new Random();
    //int showad = r.nextInt(3-1) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3459182657144294/1992589563");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b10 = (Button) findViewById(R.id.b10);
        b11 = (Button) findViewById(R.id.b11);
        b12 = (Button) findViewById(R.id.b12);
        b13 = (Button) findViewById(R.id.b13);
        b14 = (Button) findViewById(R.id.b14);
        head = (Button) findViewById(R.id.head);

        Typeface title = Typeface.createFromAsset(getAssets(), "fonts/title_b.ttf");
        Typeface menu = Typeface.createFromAsset(getAssets(), "fonts/title_n.ttf");
        head.setTypeface(title);
        b1.setTypeface(menu);
        b2.setTypeface(menu);
        b3.setTypeface(menu);
        b4.setTypeface(menu);
        b5.setTypeface(menu);
        b6.setTypeface(menu);
        b7.setTypeface(menu);
        b8.setTypeface(menu);
        b9.setTypeface(menu);
        b10.setTypeface(menu);
        b11.setTypeface(menu);
        b12.setTypeface(menu);
        b13.setTypeface(menu);
        b14.setTypeface(menu);

        try {
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
            b5.setOnClickListener(this);
            b6.setOnClickListener(this);
            b7.setOnClickListener(this);
            b8.setOnClickListener(this);
            b9.setOnClickListener(this);
            b10.setOnClickListener(this);
            b11.setOnClickListener(this);
            b12.setOnClickListener(this);
            b13.setOnClickListener(this);
            b14.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                Intent intent1 = new Intent(getApplicationContext(), Bustime.class);
                startActivity(intent1);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b2:
                Intent intent2 = new Intent(getApplicationContext(), Traintime.class);
                startActivity(intent2);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b3:
                Intent intent3 = new Intent(getApplicationContext(), Autotaxi.class);
                startActivity(intent3);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b4:
                Intent intent4 = new Intent(getApplicationContext(), Medical.class);
                startActivity(intent4);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b5:
                Intent intent5 = new Intent(getApplicationContext(), Education.class);
                startActivity(intent5);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b6:
                Intent intent6 = new Intent(getApplicationContext(), FoodStay.class);
                startActivity(intent6);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b7:
                Intent intent7 = new Intent(getApplicationContext(), Shops.class);
                startActivity(intent7);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b8:
                Intent intent8 = new Intent(getApplicationContext(), Banks.class);
                startActivity(intent8);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b9:
                Intent intent9 = new Intent(getApplicationContext(), Movies.class);
                startActivity(intent9);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b10:
                Intent intent10 = new Intent(getApplicationContext(), ImpNos.class);
                startActivity(intent10);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b11:
                Intent intent11 = new Intent(getApplicationContext(), Offices.class);
                startActivity(intent11);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b12:
                Intent intent12 = new Intent(getApplicationContext(), Tourism.class);
                startActivity(intent12);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b13:
                Intent intent13 = new Intent(getApplicationContext(), Notification.class);
                startActivity(intent13);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.b14:
                Intent intent14 = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(intent14);
                finish();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        Intent out = new Intent(getApplicationContext(), OutroAd.class);
        startActivity(out);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        finish();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
