package com.fanxiaoyudemo.magicalwardrobe.Tool;

import android.webkit.JavascriptInterface;

/**
 * Created by fanxi on 2020/3/10.
 */

public class AndroidtoJs extends Object {
    UserData USER_DATA;
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void save(String modelURL) throws Exception {
//        Log.d("","123123123123");
//        USER_DATA.get_and_save_model_function(USER_DATA.getPhoneNum(),modelURL);
    }
}
