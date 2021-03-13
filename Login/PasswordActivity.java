package com.fanxiaoyudemo.magicalwardrobe.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;

import java.io.IOException;

public class PasswordActivity extends Activity {
    private Button PASSWORD_NEXT;
    private EditText PHONE_NUM;
    UserData USER_DATA;
    int code=-1;
    String phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        USER_DATA=(UserData)this.getApplication();

        PHONE_NUM= (EditText) findViewById(R.id.phoneNum);
        PASSWORD_NEXT = (Button) findViewById(R.id.password_next);


        PASSWORD_NEXT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        try {
                            phoneNum=PHONE_NUM.getText().toString();
                            code = USER_DATA.find_password_function(phoneNum);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                if(code==0)
                {
                    Intent intent = new Intent(PasswordActivity.this,PasswordActivity_2.class);
                    USER_DATA.setPhoneNum(phoneNum);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "查询成功", Toast.LENGTH_SHORT).show();
                }else if(code==1)
                {
                    Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
