package com.smartpazhayangadi;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Traintime extends AppCompatActivity implements View.OnClickListener {

    Button b1t,b2t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traintime);

        b1t = (Button) findViewById(R.id.b_m);
        b2t = (Button) findViewById(R.id.b_s);
        Typeface menu = Typeface.createFromAsset(getAssets(), "fonts/title_n.ttf");
        b1t.setTypeface(menu);
        b2t.setTypeface(menu);

        try {
            b1t.setOnClickListener(this);
            b2t.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_m:
                Intent intent1 = new Intent(getApplicationContext(), Train_m.class);
                startActivity(intent1);
                break;
            case R.id.b_s:
                Intent intent2 = new Intent(getApplicationContext(), Train_s.class);
                startActivity(intent2);
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
