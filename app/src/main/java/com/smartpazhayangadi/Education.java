package com.smartpazhayangadi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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

public class Education extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String s[] = new String[100];
    Button educate[]=new Button[100];
    Button m1,m2;
    JSONArray jArray;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        context=this;
        s[0]="";
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        m1 = (Button) findViewById(R.id.m1);
        m2 = (Button) findViewById(R.id.m2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            m1.setStateListAnimator(null);
            m2.setStateListAnimator(null);
        }
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-smartpz.rhcloud.com/script/education.php");
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
            pd.setMessage("please wait...");
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

                LinearLayout scl_content = (LinearLayout) findViewById(R.id.scl_content);
                LinearLayout clg_content = (LinearLayout) findViewById(R.id.clg_content);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, dpToPx(10));

                for(int i=0; i<jArray.length();i++){
                    JSONObject json = jArray.getJSONObject(i);
                    s[i] =  json.getString("Name")+"\n"+
                            "Location: "+json.getString("Place")+"\n"+
                            "Courses: "+json.getString("Courses")+"\n"+
                            "Phone: "+json.getString("Phone");

                    educate[i] = new Button(Education.this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        educate[i].setStateListAnimator(null);
                    }
                    educate[i].setClickable(false);
                    educate[i].setBackgroundResource(R.drawable.box);
                    educate[i].setTextColor(Color.parseColor("#3c3c3c"));
                    educate[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    educate[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                    educate[i].setPadding(dpToPx(20),dpToPx(20),dpToPx(20),dpToPx(20));

                    if(json.getString("Name").contains("SCL")) {
                        s[i] = s[i].replaceAll("SCL", "");
                        s[i] = s[i].replaceAll("Courses: \n", "");
                        educate[i].setText(s[i].toUpperCase());
                        scl_content.addView(educate[i], lp);
                    }if(json.getString("Name").contains("CLG")) {
                        s[i] = s[i].replaceAll("CLG", "");
                        educate[i].setText(s[i].toUpperCase());
                        clg_content.addView(educate[i], lp);
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(s[0]=="") {
                    Toast.makeText(Education.this, "No Internet Access", Toast.LENGTH_SHORT).show();
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