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

public class PasswordActivity_2 extends Activity {
    private Button PASSWORD_FINISH,PASSWORD;
    private EditText REQUESTION,ANSWER;
    String answer;
    UserData USER_DATA;
    int code=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_2);


        USER_DATA=(UserData)this.getApplication();
        REQUESTION=(EditText)findViewById(R.id.requestion);
        REQUESTION.setText(USER_DATA.getRequestion());
        ANSWER=(EditText)findViewById(R.id.answer);
        PASSWORD=(Button)findViewById(R.id.password);
        REQUESTION.setText(USER_DATA.getRequestion());
        PASSWORD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_password_2);
                Toast.makeText(getApplicationContext(),"已显示",Toast.LENGTH_SHORT).show();
            }
        });


        PASSWORD_FINISH = (Button)findViewById(R.id.password_finish);


        PASSWORD_FINISH.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        try {
                            answer=ANSWER.getText().toString();
                            code = USER_DATA.find_password_function_2(USER_DATA.getPhoneNum(),answer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
                if(code==0)
                {
                    USER_DATA.setAnswer(answer);
                    Intent intent = new Intent(PasswordActivity_2.this,LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"您的密码是："+USER_DATA.getPassword(),Toast.LENGTH_LONG).show();
                }else if(code==1)
                {
                    Toast.makeText(getApplicationContext(), "用户不存在", Toast.LENGTH_SHORT).show();
                }else if(code==2)
                {
                    Toast.makeText(getApplicationContext(), "答案错误", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
