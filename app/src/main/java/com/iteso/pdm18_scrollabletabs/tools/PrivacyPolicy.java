package com.iteso.pdm18_scrollabletabs.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.iteso.pdm18_scrollabletabs.R;

public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        WebView  mWebView = null;


        mWebView = findViewById(R.id.activity_privacy_webview);
        mWebView.loadUrl("file:///android_asset/PrivacyPolicy.html");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this),"Android");
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String  url) {
            if (Uri.parse(url).getHost().equals("http://www.iteso.com")){
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    public class WebAppInterface{
        Context mContext;
        WebAppInterface(Context c){
            mContext = c;
        }
        @JavascriptInterface
        public void showToast (String toast){
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }

}
