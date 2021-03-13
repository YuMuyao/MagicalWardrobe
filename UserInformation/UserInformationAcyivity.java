package com.fanxiaoyudemo.magicalwardrobe.UserInformation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;



public class UserInformationAcyivity extends Activity {
    UserData USER_DATA;
    private ImageView UI_RETURN;
    private TextView PHONE_NUM,USER_NAME,SEX,STYLE;
    HeadPortrait AVATER;
    Button EDIT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information_acyivity);
        USER_DATA=(UserData)getApplication();
        UI_RETURN = (ImageView) findViewById(R.id.ui_return);
        PHONE_NUM=(TextView)findViewById(R.id.phoneNum);
        USER_NAME=(TextView)findViewById(R.id.userName);
        SEX=(TextView)findViewById(R.id.sex);
        STYLE=(TextView)findViewById(R.id.style);
        AVATER=(HeadPortrait)findViewById(R.id.avater);
        EDIT=(Button)findViewById(R.id.edit);
        try {
            Bitmap bitmap=BitmapFactory.decodeFile((USER_DATA.getAvater()).toString());
            AVATER.setImageBitmap(bitmap);
            PHONE_NUM.setText(USER_DATA.getPhoneNum());
            USER_NAME.setText(USER_DATA.getUserName());
            if(USER_DATA.getSex()=="1")
                SEX.setText("女生");
            else
                SEX.setText("男生");
            STYLE.setText((CharSequence) USER_DATA.getStyle().get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        UI_RETURN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(UserInformationAcyivity.this,EditUserInformationActivity.class);
                startActivity(intent);
            }
        });
    }

}

