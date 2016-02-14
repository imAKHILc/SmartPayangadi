package com.smartpazhayangadi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestAd extends AppCompatActivity {

    Activity context;
    ProgressDialog pd;
    String text=null;
    Button submit;
    EditText name,email,message,phone;
    String n,e,p,m;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_ad);
        context=this;
        submit = (Button) findViewById(R.id.ad_submit);
        name = (EditText) findViewById(R.id.ad_name);
        email = (EditText) findViewById(R.id.ad_email);
        phone = (EditText) findViewById(R.id.ad_phone);
        message = (EditText) findViewById(R.id.ad_message);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                n = name.getText().toString();
                e = email.getText().toString();
                p = phone.getText().toString();
                m = message.getText().toString();
                n = n.replaceAll(" ", "_");
                e = e.replaceAll(" ", "");
                p = p.replaceAll(" ", "");
                m = m.replaceAll(" ", "_");
                if (n.length() < 4) {
                    Toast.makeText(RequestAd.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                } else if (!e.toLowerCase().contains("@")) {
                    Toast.makeText(RequestAd.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }else if (p.length() < 10) {
                    Toast.makeText(RequestAd.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (m.length() < 10) {
                    Toast.makeText(RequestAd.this, "Message Should Contain At Least 10 Characters", Toast.LENGTH_SHORT).show();
                } else {
                    send(n, e, p, m);
                }
            }
        });
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(), AboutUs.class);
            i.putExtra("status",text);
            startActivity(i);
            finish();
        }
    };

    public void send(String n, String e, String p, String m){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute("http://teczoz.com/form/requestad.php?name="+n+"&email="+e+"&phone="+p+"&message="+m+"&submit=SEND");
    }

    public Activity getContext() {
        return context;
    }

    private class BackTask extends AsyncTask<String,Integer,Void> {
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
                if(pd!=null) {
                    pd.dismiss();
                    mHandler.postDelayed(mUpdateTimeTask, 100);
                }
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            if (pd != null) {
                pd.dismiss();
                mHandler.postDelayed(mUpdateTimeTask, 100);
            }
        }
    }
}