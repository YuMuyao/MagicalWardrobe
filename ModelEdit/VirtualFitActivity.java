package com.fanxiaoyudemo.magicalwardrobe.ModelEdit;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;

public class VirtualFitActivity extends AppCompatActivity {
    private WebView WEB_VIRTUAL_FIT;
    UserData USER_DATA;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_fit);
        USER_DATA=(UserData)getApplication();
        try {
            USER_DATA.virtual_fit_function(USER_DATA.getPhoneNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        WEB_VIRTUAL_FIT = (WebView) findViewById(R.id.webview_virtual_fit);
        WEB_VIRTUAL_FIT.loadUrl(USER_DATA.getModelURL());
        WEB_VIRTUAL_FIT.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                WEB_VIRTUAL_FIT.setWebChromeClient(new WebChromeClient());
                view.loadUrl(USER_DATA.getModelURL());
                return true;
            }
        });
        WebSettings webSettings = WEB_VIRTUAL_FIT.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs (true);

    }
}
