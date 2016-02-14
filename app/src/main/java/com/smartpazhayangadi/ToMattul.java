package com.smartpazhayangadi;

import android.app.Activity;
import android.app.ProgressDialog;
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

public class ToMattul extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    EditText bus_e;
    Button bus_b;
    String s[] = new String[100];
    Button timing[]=new Button[100];
    JSONArray jArray;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_mattul);
        context=this;
        s[0]="";
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-smartpz.rhcloud.com/script/tomattul.php");
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

                for(int i=0; i<jArray.length();i++){
                    JSONObject json = jArray.getJSONObject(i);
                    s[i] =  json.getString("Name")+"\n"+
                            "Time: "+json.getString("Time");
                }

                LinearLayout layout = (LinearLayout) findViewById(R.id.bus_content);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, 0, dpToPx(10));

                int i;
                for(i=0;i<jArray.length(); i++) {
                    timing[i] = new Button(ToMattul.this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        timing[i].setStateListAnimator(null);
                    }
                    timing[i].setBackgroundResource(R.drawable.box);
                    timing[i].setText(s[i].toUpperCase());
                    timing[i].setTextColor(Color.parseColor("#3c3c3c"));
                    timing[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    timing[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                    timing[i].setPadding(dpToPx(20), dpToPx(20), dpToPx(20), dpToPx(20));
                    layout.addView(timing[i], lp);
                }

                bus_e = (EditText) findViewById(R.id.bus_e);
                bus_b = (Button) findViewById(R.id.bus_b);
                bus_b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        String term;
                        term = bus_e.getText().toString();
                        for(int i=0;i<jArray.length(); i++) {
                            if(s[i].toLowerCase().contains(term.toLowerCase()))
                                timing[i].setVisibility(View.VISIBLE);
                            else
                                timing[i].setVisibility(View.GONE);
                        }
                    }
                });

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(s[0]=="") {
                    Toast.makeText(ToMattul.this, "No Internet Access", Toast.LENGTH_SHORT).show();
                }
            }finally {

            }
        }
    }
}