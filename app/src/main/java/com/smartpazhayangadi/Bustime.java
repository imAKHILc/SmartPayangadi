package com.smartpazhayangadi;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bustime extends AppCompatActivity implements View.OnClickListener {

    Button b_fr,b_to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bustime);

        b_fr = (Button) findViewById(R.id.b_fr);
        b_to = (Button) findViewById(R.id.b_to);
        Typeface menu = Typeface.createFromAsset(getAssets(), "fonts/title_n.ttf");
        b_fr.setTypeface(menu);
        b_to.setTypeface(menu);

        try {
            b_fr.setOnClickListener(this);
            b_to.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_fr:
                Intent intent1 = new Intent(getApplicationContext(), BusFrom.class);
                startActivity(intent1);
                break;
            case R.id.b_to:
                Intent intent2 = new Intent(getApplicationContext(), BusTo.class);
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
