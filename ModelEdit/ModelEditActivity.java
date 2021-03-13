package com.fanxiaoyudemo.magicalwardrobe.ModelEdit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;
//在这个activity中没有写线程不知道运行情况如何
public class ModelEditActivity extends Activity {
    private Button GET_MODEL,VIRTUAL_FIT;
    UserData USER_DATA;
    int code=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_edit);
        USER_DATA=(UserData)getApplication();

        GET_MODEL=(Button)findViewById(R.id.get_model);
        VIRTUAL_FIT=(Button)findViewById(R.id.virtual_fit);

        GET_MODEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModelEditActivity.this,ModelGetAndSaveActivity.class);
                startActivity(intent);
            }
        });

        VIRTUAL_FIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum=USER_DATA.getPhoneNum();
                String modelURL=USER_DATA.getModelURL();
                try {
                    new Thread() {

                        public void run() {
                            Looper.prepare();
                            try {
                                code=USER_DATA.virtual_fit_function(USER_DATA.getPhoneNum());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }if(code==0)
                            {
                                Intent intent=new Intent(ModelEditActivity.this,VirtualFitActivity.class);
                                startActivity(intent);
                                if(USER_DATA.getModelURL().equals("https://www.baidu.com/"))
                                    Toast.makeText(getApplicationContext(),"请先获取",Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(getApplicationContext(),"获取成功",Toast.LENGTH_SHORT).show();
                            }
                            else if(code==1)
                                Toast.makeText(getApplicationContext(),"用户不存在",Toast.LENGTH_SHORT).show();
                            else if(code==2)
                                Toast.makeText(getApplicationContext(),"获取失败",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(),"无法获取到正常值",Toast.LENGTH_SHORT).show();

                        }

                    }.start(); Looper.loop();
                     } catch (Exception e) {
                    e.printStackTrace();
                }
                            }
        });


    }
}