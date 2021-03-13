package com.fanxiaoyudemo.magicalwardrobe.Login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.Home.HomeActivity;
import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;

import java.io.IOException;
//读写权限

public class LoginActivity extends AppCompatActivity {
    private TextView LOGIN,REGISTER,FIND_PASSWPRD;
    private EditText PHONE_NUM,PASSWORD;
    UserData USER_DATA;
    String phoneNum,password;
    int code=-1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE};    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LOGIN=(TextView) findViewById(R.id.login);
        REGISTER=(TextView) findViewById(R.id.register);
        FIND_PASSWPRD=(TextView) findViewById(R.id.findPassword);
        PHONE_NUM=(EditText) findViewById(R.id.phoneNum);
        PASSWORD=(EditText)findViewById(R.id.password);
        USER_DATA=(UserData)this.getApplication();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginActivity.this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }



        LOGIN.setOnClickListener(new View.OnClickListener() {
              public void onClick(View v) {
                  new Thread() {
                      public void run() {
                          try {
                              phoneNum=PHONE_NUM.getText().toString();
                              password=PASSWORD.getText().toString();
                              code = USER_DATA.login_function(phoneNum, password);
                          } catch (IOException e) {
                              e.printStackTrace();
                          } catch (Exception e) {
                              e.printStackTrace();
                          }

                      }
                  }.start();
                  if(code==0)
                  {
                      Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                      USER_DATA.setPhoneNum(phoneNum);
                      USER_DATA.setPassword(password);
                      startActivity(intent);
                      Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                  }else if(code==1)
                  {
                      onStart();
                      Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                  }
                  else if(code==2)
                  {
                      onStart();
                      Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
                  }
              }
        });


        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        FIND_PASSWPRD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,PasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}


