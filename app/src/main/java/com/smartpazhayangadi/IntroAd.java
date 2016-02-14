package com.smartpazhayangadi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class IntroAd extends AppCompatActivity {

    ImageView intro_ad;
    Button skip,req_ad;
    Bitmap bitmap;
    ProgressDialog pDialog;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_ad);
        text = "http://db-smartpz.rhcloud.com/pics/intro_ad_pic.png";
        intro_ad = (ImageView)findViewById(R.id.intro_ad);
        new LoadImage().execute(text);
        skip = (Button) findViewById(R.id.skip);
        req_ad = (Button) findViewById(R.id.req_ad);
        skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("showit", text);
                startActivity(intent);
                finish();
            }
        });

        req_ad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), RequestAd.class);
                startActivity(intent);
            }
        });
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(IntroAd.this);
            pDialog.setMessage("Checking connection...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                intro_ad.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(IntroAd.this, "No Internet Access", Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public void onBackPressed() {
    }
}
