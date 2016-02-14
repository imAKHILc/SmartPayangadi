package com.smartpazhayangadi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TrainStatus extends AppCompatActivity {

    WebView status;
    String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_status);
        status = (WebView) findViewById(R.id.status);
        status.getSettings().setJavaScriptEnabled(true);
        status.getSettings().setUserAgentString("AndroidWebView");
        status.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                status.loadUrl("javascript:document.getElementById(\"eta\").setAttribute(\"style\",\"width:100%;\");");
                status.loadUrl("javascript:document.getElementById(\"google_ads_frame1\").setAttribute(\"style\",\"display:none;\");");
            }
        });
        status.loadUrl("http://spoturtrain.com/status.php?tno="+num+"&date=0");
    }
}
