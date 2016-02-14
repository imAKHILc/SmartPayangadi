package com.smartpazhayangadi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Notification extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String title[] = new String[1000];
    String message[] = new String[1000];
    String date[] = new String[1000];
    Button title_b[]=new Button[1000];
    Button message_b[]=new Button[1000];
    Button date_b[]=new Button[1000];
    JSONArray jArray;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        context=this;
        title[0]="";
        message[0]="";
        date[0]="";
    }

    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://db-smartpz.rhcloud.com/script/notifications.php");
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
                    title[i] = json.getString("Title");
                    message[i] = json.getString("Message");
                    date[i] = json.getString("Date");
                }

                LinearLayout layout = (LinearLayout) findViewById(R.id.not_content);
                LinearLayout.LayoutParams lp_t = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lp_m = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lp_d = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp_d.setMargins(0, 0, 0, dpToPx(20));

                int i;
                for(i=jArray.length()-1;i>=0; i--) {
                    title_b[i] = new Button(Notification.this);
                    title_b[i].setBackgroundResource(R.drawable.title_box);
                    title_b[i].setText(title[i]);
                    title_b[i].setTypeface(Typeface.DEFAULT_BOLD);
                    title_b[i].setTextColor(Color.parseColor("#ffffff"));
                    title_b[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    title_b[i].setGravity(Gravity.CENTER);
                    title_b[i].setPadding(0, dpToPx(20), 0, dpToPx(20));
                    layout.addView(title_b[i], lp_t);

                    message_b[i] = new Button(Notification.this);
                    message_b[i].setBackgroundResource(R.drawable.message_box);
                    message_b[i].setText(message[i]);
                    message_b[i].setTextColor(Color.parseColor("#3c3c3c"));
                    message_b[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    message_b[i].setGravity(Gravity.LEFT | Gravity.CENTER);
                    message_b[i].setPadding(dpToPx(20), dpToPx(60), dpToPx(20), dpToPx(60));
                    layout.addView(message_b[i], lp_m);

                    date_b[i] = new Button(Notification.this);
                    date_b[i].setBackgroundResource(R.drawable.date_box);
                    date_b[i].setText(date[i]);
                    date_b[i].setTypeface(Typeface.DEFAULT_BOLD);
                    date_b[i].setTextColor(Color.parseColor("#ffffff"));
                    date_b[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    date_b[i].setGravity(Gravity.RIGHT | Gravity.CENTER);
                    date_b[i].setPadding(dpToPx(20), dpToPx(20), dpToPx(20), dpToPx(20));
                    layout.addView(date_b[i], lp_d);
                }

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error Parsing Data " + e.toString());
                if(title[0]=="") {
                    Toast.makeText(Notification.this, "No Internet Access", Toast.LENGTH_SHORT).show();
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