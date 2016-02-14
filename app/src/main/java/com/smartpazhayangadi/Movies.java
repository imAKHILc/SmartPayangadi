package com.smartpazhayangadi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Movies extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String s[] = new String[100];
    Button theater[]=new Button[100];
    Button m1,m2,m3,m4;
    JSONArray jArray;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        context=this;
        s[0]="";
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        m1 = (Button) findViewById(R.id.m1);
        m2 = (Button) findViewById(R.id.m2);
        m3 = (Button) findViewById(R.id.m3);
        m4 = (Button) findViewById(R.id.m4);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            m1.setStateListAnimator(null);
            m2.setStateListAnimator(null);
            m3.setStateListAnimator(null);
            m4.setStateListAnimator(null);
        }
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-smartpz.rhcloud.com/script/movies.php");
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public Activity getContext() {
        return context;
    }

    private class BackTask extends AsyncTask<String,Integer,Void> {
        String text="";
        protected void onPreExecute(){
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.show();
        }

        protected Void doInBackground(String...params){
            URL url;
            try {
                url = new URL(params[0]);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                InputStream is=con.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line;
                while((line=br.readLine())!=null){
                    text+=line;
                }

                br.close();

            }catch (Exception e) {
                e.printStackTrace();
                if(pd!=null) pd.dismiss();
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if (pd != null)
                pd.dismiss();

            try {
                jArray = new JSONArray(text);

                LinearLayout layout_pz = (LinearLayout) findViewById(R.id.films_pz);
                LinearLayout layout_py = (LinearLayout) findViewById(R.id.films_py);
                LinearLayout layout_kn = (LinearLayout) findViewById(R.id.films_kn);
                LinearLayout layout_tp = (LinearLayout) findViewById(R.id.films_tp);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, dpToPx(10));

                for(int i=0; i<jArray.length();i++){
                    JSONObject json = jArray.getJSONObject(i);
                    s[i] =  json.getString("Name")+"\n"+
                            "Film: "+json.getString("Film")+"\n"+
                            json.getString("Details");

                    theater[i] = new Button(Movies.this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        theater[i].setStateListAnimator(null);
                    }
                    theater[i].setClickable(false);
                    theater[i].setBackgroundResource(R.drawable.box);
                    theater[i].setTextColor(Color.parseColor("#3c3c3c"));
                    theater[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    theater[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                    theater[i].setPadding(dpToPx(20), dpToPx(20), dpToPx(20), dpToPx(20));
                    //Typeface menu = Typeface.createFromAsset(getAssets(), "fonts/title_n.ttf");
                    //theater[i].setTypeface(menu);

                    if(json.getString("Name").contains("PYD")) {
                        s[i] = s[i].replaceAll("PYD", "");
                        theater[i].setText(s[i].toUpperCase());
                        layout_pz.addView(theater[i], lp);
                    }if(json.getString("Name").contains("PNR")) {
                        s[i] = s[i].replaceAll("PNR", "");
                        theater[i].setText(s[i].toUpperCase());
                        layout_py.addView(theater[i], lp);
                    }if(json.getString("Name").contains("KNR")){
                        s[i] = s[i].replaceAll("KNR", "");
                        theater[i].setText(s[i].toUpperCase());
                        layout_kn.addView(theater[i], lp);
                    }if(json.getString("Name").contains("TPBA")){
                        s[i] = s[i].replaceAll("TPBA", "");
                        theater[i].setText(s[i].toUpperCase());
                        layout_tp.addView(theater[i], lp);
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(s[0]=="") {
                    Toast.makeText(Movies.this, "No Internet Access", Toast.LENGTH_SHORT).show();
                }
            }finally {

            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}