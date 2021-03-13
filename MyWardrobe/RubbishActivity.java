package com.fanxiaoyudemo.magicalwardrobe.MyWardrobe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.Home.HomeActivity;
import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.Cloth;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;

public class RubbishActivity extends AppCompatActivity {
    Button DELETE_CHECK;
    int code_del=-1;
    ImageView GOBACK;
    UserData USER_DATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rubbish);
        USER_DATA=(UserData)getApplication();
        GOBACK=(ImageView) findViewById(R.id.return_ad);
        GOBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(RubbishActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        DELETE_CHECK=(Button)findViewById(R.id.delete_check);
        DELETE_CHECK.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
            new Thread(){
                    @Override
                    public void run() {
                        Looper.prepare();
                            Cloth clothTem=null;
                        int flag=0;
                            switch (Integer.parseInt(USER_DATA.getClassifyCode()))
                            {
                                case 0:
                                    if(USER_DATA.getClothList0().size()!=0)
                                    {
                                        clothTem= (Cloth) USER_DATA.getClothList0().get(USER_DATA.getCurrentItem());
                                        flag=1;
                                    }

                                    break;
                                case 1:
                                    if(USER_DATA.getClothList1().size()!=0)
                                    {
                                        clothTem= (Cloth) USER_DATA.getClothList1().get(USER_DATA.getCurrentItem());
                                        flag=1;
                                    }

                                    break;
                                case 2:
                                    if(USER_DATA.getClothList2().size()!=0)
                                    {
                                        clothTem= (Cloth) USER_DATA.getClothList2().get(USER_DATA.getCurrentItem());
                                        flag=1;
                                    }
                                    break;
                                case 3:
                                    if(USER_DATA.getClothList3().size()!=0)
                                    {
                                        clothTem= (Cloth) USER_DATA.getClothList3().get(USER_DATA.getCurrentItem());
                                        flag=1;
                                    }
                                     break;
                                case 4:
                                    if(USER_DATA.getClothList4().size()!=0)
                                    {
                                        clothTem= (Cloth) USER_DATA.getClothList4().get(USER_DATA.getCurrentItem());
                                        flag=1;
                                    }
                                     break;
                                case 5:
                                    if(USER_DATA.getClothList5().size()!=0)
                                    {
                                        clothTem= (Cloth) USER_DATA.getClothList5().get(USER_DATA.getCurrentItem());
                                        flag=1;
                                    }
                                     break;
                            }
                        if(flag==1) {
                            try {
                                code_del = USER_DATA.delete_cloth_function(clothTem.getClothNum());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (code_del == 0) {
                                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                            } else if (code_del == 1) {
                                Toast.makeText(getApplicationContext(), "衣服不存在", Toast.LENGTH_SHORT).show();
                            } else if (code_del == 2) {
                                Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "出错啦", Toast.LENGTH_SHORT).show();
                            }
                        } Looper.loop();
                    }

                }.start();

            }
        }

        );

    }
}
