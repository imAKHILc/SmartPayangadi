package com.smartpazhayangadi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ImpNos extends AppCompatActivity implements View.OnClickListener {

    ImageButton call_1, call_2, call_3, call_4, call_5, call_6, call_7, call_8, call_9, call_10, call_11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_nos);

        call_1 = (ImageButton) findViewById(R.id.call_1);
        call_2 = (ImageButton) findViewById(R.id.call_2);
        call_3 = (ImageButton) findViewById(R.id.call_3);
        call_4 = (ImageButton) findViewById(R.id.call_4);
        call_5 = (ImageButton) findViewById(R.id.call_5);
        call_6 = (ImageButton) findViewById(R.id.call_6);
        call_7 = (ImageButton) findViewById(R.id.call_7);
        call_8 = (ImageButton) findViewById(R.id.call_8);
        call_9 = (ImageButton) findViewById(R.id.call_9);
        call_10 = (ImageButton) findViewById(R.id.call_10);
        call_11 = (ImageButton) findViewById(R.id.call_11);

        try {
            call_1.setOnClickListener(this);
            call_2.setOnClickListener(this);
            call_3.setOnClickListener(this);
            call_4.setOnClickListener(this);
            call_5.setOnClickListener(this);
            call_6.setOnClickListener(this);
            call_7.setOnClickListener(this);
            call_8.setOnClickListener(this);
            call_9.setOnClickListener(this);
            call_10.setOnClickListener(this);
            call_11.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_1:
                Intent callIntent1 = new Intent(Intent.ACTION_CALL);
                callIntent1.setData(Uri.parse("tel:04972870233"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent1);
                break;
            case R.id.call_2:
                Intent callIntent2 = new Intent(Intent.ACTION_CALL);
                callIntent2.setData(Uri.parse("tel:8943341416"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent2);
                break;
            case R.id.call_3:
                Intent callIntent3 = new Intent(Intent.ACTION_CALL);
                callIntent3.setData(Uri.parse("tel:04972870245"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent3);
                break;
            case R.id.call_4:
                Intent callIntent4 = new Intent(Intent.ACTION_CALL);
                callIntent4.setData(Uri.parse("tel:04972870278"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent4);
                break;
            case R.id.call_5:
                Intent callIntent5 = new Intent(Intent.ACTION_CALL);
                callIntent5.setData(Uri.parse("tel:04972808080"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent5);
                break;
            case R.id.call_6:
                Intent callIntent6 = new Intent(Intent.ACTION_CALL);
                callIntent6.setData(Uri.parse("tel:04972715335"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent6);
                break;
            case R.id.call_7:
                Intent callIntent7 = new Intent(Intent.ACTION_CALL);
                callIntent7.setData(Uri.parse("tel:04972870220"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent7);
                break;
            case R.id.call_8:
                Intent callIntent8 = new Intent(Intent.ACTION_CALL);
                callIntent8.setData(Uri.parse("tel:04972870100"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent8);
                break;
            case R.id.call_9:
                Intent callIntent9 = new Intent(Intent.ACTION_CALL);
                callIntent9.setData(Uri.parse("tel:04985201268"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent9);
                break;
            case R.id.call_10:
                Intent callIntent10 = new Intent(Intent.ACTION_CALL);
                callIntent10.setData(Uri.parse("tel:04972870243"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent10);
                break;
            case R.id.call_11:
                Intent callIntent11 = new Intent(Intent.ACTION_CALL);
                callIntent11.setData(Uri.parse("tel:8129471329"));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent11);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
