package com.smartpazhayangadi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BusTo extends AppCompatActivity implements View.OnClickListener {

    Button f_1,f_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_to);
        f_1 = (Button) findViewById(R.id.from_1);
        f_2 = (Button) findViewById(R.id.from_2);

        Typeface menu = Typeface.createFromAsset(getAssets(), "fonts/title_n.ttf");
        f_1.setTypeface(menu);
        f_2.setTypeface(menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            f_1.setStateListAnimator(null);
            f_2.setStateListAnimator(null);
        }

        try {
            f_1.setOnClickListener(this);
            f_2.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.from_1:
                Intent intent1 = new Intent(getApplicationContext(), FromPayyannur.class);
                startActivity(intent1);
                break;
            case R.id.from_2:
                Intent intent2 = new Intent(getApplicationContext(), FromKannur.class);
                startActivity(intent2);
                break;
        }
    }
}
