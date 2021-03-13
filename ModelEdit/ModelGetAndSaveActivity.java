package com.fanxiaoyudemo.magicalwardrobe.ModelEdit;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;

public class ModelGetAndSaveActivity extends AppCompatActivity {
    private WebView WEB_GET_MODEL;
    UserData USER_DATA;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_get_and_save);
        USER_DATA=(UserData)getApplication();
        WEB_GET_MODEL = (WebView) findViewById(R.id.webview_model_get_and_save);

        WEB_GET_MODEL.loadUrl("http://114.55.255.62:8080/?PhoneNum="+USER_DATA.getPhoneNum());
        WEB_GET_MODEL.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = WEB_GET_MODEL.getSettings();
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
