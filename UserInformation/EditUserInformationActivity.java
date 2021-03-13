package com.fanxiaoyudemo.magicalwardrobe.UserInformation;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.Home.HomeActivity;
import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


    public class EditUserInformationActivity extends Activity {
        private Button EDIT_FINISH,EDIT_CHECK,AVATER;
        private EditText PHONE_NUM, USER_NAME, PASSWORD, PASSWORD_CHECK, REQUESTION, ANSWER;
        private RadioButton FEMALE;
        private String phoneNum;
        private String userName;
        private String password, passwordCheck;
        private String sex="0";
        private String requestion;
        private String answer;
        int code=-1;
        UserData USER_DATA;
        File edit_avater;
        public CheckBox STYLE_0, STYLE_1, STYLE_2, STYLE_3, STYLE_4, STYLE_5, STYLE_6, STYLE_7, STYLE_8, STYLE_9, STYLE_10, STYLE_11, STYLE_12, STYLE_13;
        int judge=0;
        int flag=0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_user_information);
            USER_DATA=(UserData)this.getApplication();
            List<String> STYLE = new ArrayList<String>();
            FEMALE = (RadioButton) findViewById(R.id.female);
            PHONE_NUM = (EditText) findViewById(R.id.phoneNum);
            USER_NAME = (EditText) findViewById(R.id.userName);
            REQUESTION = (EditText) findViewById(R.id.requestion);
            ANSWER = (EditText) findViewById(R.id.answer);
            PASSWORD = (EditText) findViewById(R.id.password);
            PASSWORD_CHECK = (EditText) findViewById(R.id.passwordCheck);
            EDIT_CHECK=(Button) findViewById(R.id.edit_check);
            EDIT_FINISH=(Button)findViewById(R.id.edit_finish);
            AVATER=(Button) findViewById(R.id.avater);


            STYLE_0 = (CheckBox) findViewById(R.id.style_0);
            STYLE_1 = (CheckBox) findViewById(R.id.style_1);
            STYLE_2 = (CheckBox) findViewById(R.id.style_2);
            STYLE_3 = (CheckBox) findViewById(R.id.style_3);
            STYLE_4 = (CheckBox) findViewById(R.id.style_4);
            STYLE_5 = (CheckBox) findViewById(R.id.style_5);
            STYLE_6 = (CheckBox) findViewById(R.id.style_6);
            STYLE_7 = (CheckBox) findViewById(R.id.style_7);
            STYLE_8 = (CheckBox) findViewById(R.id.style_8);
            STYLE_9 = (CheckBox) findViewById(R.id.style_9);
            STYLE_10 = (CheckBox) findViewById(R.id.style_10);
            STYLE_11 = (CheckBox) findViewById(R.id.style_11);
            STYLE_12 = (CheckBox) findViewById(R.id.style_12);
            STYLE_13 = (CheckBox) findViewById(R.id.style_13);
            AVATER.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //使用Intent  意图
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
            });
            EDIT_CHECK.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    phoneNum=PHONE_NUM.getText().toString();
                    if(phoneNum.equals(""))
                        flag=1;
                    password=PASSWORD.getText().toString();
                    passwordCheck=PASSWORD_CHECK.getText().toString();
                    userName=USER_NAME.getText().toString();
                    requestion=REQUESTION.getText().toString();
                    answer=ANSWER.getText().toString();
                    if(!(passwordCheck.equals(password))) {
                        PASSWORD_CHECK.setText("");
                        Toast.makeText(getApplicationContext(),"两次输入的密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(FEMALE.isChecked())
                        sex="1";
                    USER_DATA.setStyle(new ArrayList<String>());
                    if(STYLE_0.isChecked())
                        USER_DATA.getStyle().add("嘻哈");
                    if(STYLE_1.isChecked())
                        USER_DATA.getStyle().add("学院");
                    if(STYLE_2.isChecked())
                        USER_DATA.getStyle().add("淑女");
                    if(STYLE_3.isChecked())
                        USER_DATA.getStyle().add("田园");
                    if(STYLE_4.isChecked())
                        USER_DATA.getStyle().add("欧美");
                    if(STYLE_5.isChecked())
                        USER_DATA.getStyle().add("日韩");
                    if(STYLE_6.isChecked())
                        USER_DATA.getStyle().add("国朝");
                    if(STYLE_7.isChecked())
                        USER_DATA.getStyle().add("萝莉");
                    if(STYLE_8.isChecked())
                        USER_DATA.getStyle().add("简约");
                    if(STYLE_9.isChecked())
                        USER_DATA.getStyle().add("休闲");
                    if(STYLE_10.isChecked())
                        USER_DATA.getStyle().add("街头");
                    if(STYLE_11.isChecked())
                        USER_DATA.getStyle().add("运动");
                    if(STYLE_12.isChecked())
                        USER_DATA.getStyle().add("古风");
                    if(STYLE_13.isChecked())
                        USER_DATA.getStyle().add("成熟");
                    judge=1;
                }
            } );
            EDIT_FINISH.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(judge==1||flag==1)
                    {
                        new Thread() {
                            public void run() {
                                try {;
                                    code = USER_DATA.edit_info_function(phoneNum,password,userName,sex,USER_DATA.getStyle(),requestion,answer, edit_avater);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        if (code == 0) {
                            USER_DATA.setPhoneNum(phoneNum);
                            finish();
                            Intent intent = new Intent(EditUserInformationActivity.this, HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        } else if (code == 1) {
                            Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                        }else if (code == 2) {
                            Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "请完成信息确认", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            //RADIO_GROUP=(RadioGroup)findViewById(R.id.radioGroup);
            // FEMALE_BUTTON=(RadioButton)findViewById(R.id.radioButton_female);
            //MALE_BUTTON=(RadioButton)findViewById(R.id.radioButton_male);
        }
        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 2) {
                // 从相册返回的数据
                if (data != null) {
                    // 得到图片的全路径
                    Uri uri = data.getData();
                    String path=getRealPathFromURI(uri);
                    edit_avater=new File(path);
                }
            }
        }

        private String getRealPathFromURI(Uri contentUri) { //传入图片uri地址
            String[] proj = { MediaStore.Images.Media.DATA };
            CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
            Cursor cursor = loader.loadInBackground();
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
    }

