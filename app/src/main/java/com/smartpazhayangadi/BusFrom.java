package com.smartpazhayangadi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BusFrom extends AppCompatActivity implements View.OnClickListener {

    Button f_1,f_2,f_3,f_4,f_5,f_6,f_7,f_8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_from);
        f_1 = (Button) findViewById(R.id.from_1);
        f_2 = (Button) findViewById(R.id.from_2);
        f_3 = (Button) findViewById(R.id.from_3);
        f_4 = (Button) findViewById(R.id.from_4);
        f_5 = (Button) findViewById(R.id.from_5);
        f_6 = (Button) findViewById(R.id.from_6);
        f_7 = (Button) findViewById(R.id.from_7);
        f_8 = (Button) findViewById(R.id.from_8);
        Typeface menu = Typeface.createFromAsset(getAssets(), "fonts/title_n.ttf");
        f_1.setTypeface(menu);
        f_2.setTypeface(menu);
        f_3.setTypeface(menu);
        f_4.setTypeface(menu);
        f_5.setTypeface(menu);
        f_6.setTypeface(menu);
        f_7.setTypeface(menu);
        f_8.setTypeface(menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            f_1.setStateListAnimator(null);
            f_2.setStateListAnimator(null);
            f_3.setStateListAnimator(null);
            f_4.setStateListAnimator(null);
            f_5.setStateListAnimator(null);
            f_6.setStateListAnimator(null);
            f_7.setStateListAnimator(null);
            f_8.setStateListAnimator(null);
        }

        try {
            f_1.setOnClickListener(this);
            f_2.setOnClickListener(this);
            f_3.setOnClickListener(this);
            f_4.setOnClickListener(this);
            f_5.setOnClickListener(this);
            f_6.setOnClickListener(this);
            f_7.setOnClickListener(this);
            f_8.setOnClickListener(this);
        } catch (Exception e) {

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.from_1:
                Intent intent1 = new Intent(getApplicationContext(), ToPayyannur.class);
                startActivity(intent1);
                break;
            case R.id.from_2:
                Intent intent2 = new Intent(getApplicationContext(), ToKannur.class);
                startActivity(intent2);
                break;
            case R.id.from_3:
                Intent intent3 = new Intent(getApplicationContext(), ToThaliparamba.class);
                startActivity(intent3);
                break;
            case R.id.from_4:
                Intent intent4 = new Intent(getApplicationContext(), ToMattul.class);
                startActivity(intent4);
                break;
            case R.id.from_5:
                Intent intent5 = new Intent(getApplicationContext(), ToPappinisseri.class);
                startActivity(intent5);
                break;
            case R.id.from_6:
                Intent intent6 = new Intent(getApplicationContext(), ToMuttam.class);
                startActivity(intent6);
                break;
            case R.id.from_7:
                Intent intent7 = new Intent(getApplicationContext(), ToPuthiyangadi.class);
                startActivity(intent7);
                break;
            case R.id.from_8:
                Intent intent8 = new Intent(getApplicationContext(), ToMoolakkeel.class);
                startActivity(intent8);
                break;
        }
    }
}
