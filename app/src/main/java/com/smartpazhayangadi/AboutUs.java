package com.smartpazhayangadi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity implements View.OnClickListener {
    Button b1_a,b2_a,b3_a,b4_a;
    String status=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            status = extras.getString("status");
            if(status!=null) {
                status = status.replace("null","");
                Toast.makeText(AboutUs.this, status, Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(AboutUs.this, "Email Not Sent", Toast.LENGTH_SHORT).show();
        }
        b1_a = (Button) findViewById(R.id.b1_a);
        b2_a = (Button) findViewById(R.id.b2_a);
        b3_a = (Button) findViewById(R.id.b3_a);
        b4_a = (Button) findViewById(R.id.b4_a);


        Typeface menu = Typeface.createFromAsset(getAssets(), "fonts/title_n.ttf");
        b1_a.setTypeface(menu);
        b2_a.setTypeface(menu);
        b3_a.setTypeface(menu);
        b4_a.setTypeface(menu);

        try {
            b1_a.setOnClickListener(this);
            b2_a.setOnClickListener(this);
            b3_a.setOnClickListener(this);
            b4_a.setOnClickListener(this);
        } catch (Exception e) {

        }
    }
    private boolean MyStartActivity(Intent aIntent) {
        try
        {
            startActivity(aIntent);
            return true;
        }
        catch (ActivityNotFoundException e)
        {
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1_a:
                Intent intent1 = new Intent(getApplicationContext(), Feedback.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.b2_a:
                Intent intent2 = new Intent(getApplicationContext(), RequestAd.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.b3_a:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Try Google play
                intent.setData(Uri.parse("market://details?id=com.smartpazhayangadi"));
                if (!MyStartActivity(intent)) {
                    //Market (Google play) app seems not installed, let's try to open a webbrowser
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?com.smartpazhayangadi"));
                    if (!MyStartActivity(intent)) {
                        //Well if this also fails, we have run out of options, inform the user.
                        Toast.makeText(this, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
                break;
            case R.id.b4_a:
                String message = "Check this out! An app made for Pazhayangadi!!\nLink: https://goo.gl/w5NHNs";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(share, "Title of the dialog the system will open"));
                finish();
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
