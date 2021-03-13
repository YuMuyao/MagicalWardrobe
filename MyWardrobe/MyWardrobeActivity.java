package com.fanxiaoyudemo.magicalwardrobe.MyWardrobe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.Cloth;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;

import java.io.File;
import java.util.List;

import static com.fanxiaoyudemo.magicalwardrobe.R.id.cloth;

public class MyWardrobeActivity extends Activity implements View.OnClickListener {
    private ImageView MW_RETURN,SHIRT_1,JACKET_1,SHORTS_1,DRESS_1,SHOE_1,OTHERS_1,BT_PREVIOUS,BT_NEXT,CLOTH,LABLE_GET;
    private Button BUTTON_ADD,BUTTON_DELETE;
    private TextView SHIRT_2,SHIRT_3,JACKET_2,JACKET_3,SHORTS_2,SHORTS_3,DRESS_2,DRESS_3,SHOE_2,SHOE_3,OTHERS_2,OTHERS_3;
    UserData USER_DATA;
    int code_del=-1;
    int code_get_1=-1;
    int code_get_2=-1;
    int code_get_3=-1;
    int code_get_4=-1;
    int code_get_5=-1;
    int code_get_0=-1;
    List<Cloth> clothListTem=null;
    File clothPic;
    Cloth clothTem;
    int itemTem;
    int sizeTem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wardrobe);
        USER_DATA=(UserData)getApplication();

        CLOTH=(ImageView) findViewById(cloth);

        BUTTON_ADD=(Button) findViewById(R.id.button_add);
        BUTTON_ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyWardrobeActivity.this,PhotoActivity.class);
                startActivity(intent);
            }
        });

        BUTTON_DELETE=(Button) findViewById(R.id.button_delete);
        BUTTON_DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(MyWardrobeActivity.this,RubbishActivity.class);
                    startActivity(intent);
                }
        });

        MW_RETURN = (ImageView)findViewById(R.id.mw_return);
        MW_RETURN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BT_PREVIOUS=(ImageView) findViewById(R.id.bt_previous);
        BT_NEXT=(ImageView) findViewById(R.id.bt_next);

        SHIRT_1=(ImageView)findViewById(R.id.shirt_1);
        JACKET_1=(ImageView)findViewById(R.id.jacket_1);
        SHORTS_1=(ImageView)findViewById(R.id.shorts_1);
        DRESS_1=(ImageView)findViewById(R.id.dress_1);
        SHOE_1=(ImageView)findViewById(R.id.shoe_1);
        OTHERS_1=(ImageView)findViewById(R.id.others_1);

        SHIRT_2=(TextView) findViewById(R.id.shirt_2);
        JACKET_2=(TextView)findViewById(R.id.jacket_2);
        SHORTS_2=(TextView)findViewById(R.id.shorts_2);
        DRESS_2=(TextView) findViewById(R.id.dress_2);
        SHOE_2=(TextView) findViewById(R.id.shoe_2);
        OTHERS_2=(TextView) findViewById(R.id.others_2);

        SHIRT_3=(TextView) findViewById(R.id.shirt_3);
        JACKET_3=(TextView) findViewById(R.id.jacket_3);
        SHORTS_3=(TextView) findViewById(R.id.shorts_3);
        DRESS_3=(TextView) findViewById(R.id.dress_3);
        SHOE_3=(TextView) findViewById(R.id.shoe_3);
        OTHERS_3=(TextView) findViewById(R.id.others_3);

        LABLE_GET=(ImageView)findViewById(R.id.labelGet);
        setListerners();

    }

    private void setListerners() {
        BT_PREVIOUS.setOnClickListener(this);
        BT_NEXT.setOnClickListener(this);

        SHIRT_1.setOnClickListener(this);
        SHIRT_2.setOnClickListener(this);
        SHIRT_3.setOnClickListener(this);

        JACKET_1.setOnClickListener(this);
        JACKET_2.setOnClickListener(this);
        JACKET_3.setOnClickListener(this);

        SHORTS_1.setOnClickListener(this);
        SHORTS_2.setOnClickListener(this);
        SHORTS_3.setOnClickListener(this);

        DRESS_1.setOnClickListener(this);
        DRESS_2.setOnClickListener(this);
        DRESS_3.setOnClickListener(this);

        SHOE_1.setOnClickListener(this);
        SHOE_2.setOnClickListener(this);
        SHOE_3.setOnClickListener(this);

        OTHERS_1.setOnClickListener(this);
        OTHERS_2.setOnClickListener(this);
        OTHERS_3.setOnClickListener(this);
        LABLE_GET.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_previous:
                itemTem=USER_DATA.getCurrentItem();
                if(itemTem!=0)
                {
                    itemTem-=1;
                    USER_DATA.setCurrentItem(itemTem);
                    switch (Integer.parseInt(USER_DATA.getClassifyCode()))
                    {
                        case 0:
                            clothTem= (Cloth) USER_DATA.getClothList0().get(itemTem);
                            break;
                        case 1:
                            clothTem= (Cloth) USER_DATA.getClothList1().get(itemTem);
                            break;
                        case 2:
                            clothTem= (Cloth) USER_DATA.getClothList2().get(itemTem);
                            break;
                        case 3:
                            clothTem= (Cloth) USER_DATA.getClothList3().get(itemTem);
                            break;
                        case 4:
                            clothTem= (Cloth) USER_DATA.getClothList4().get(itemTem);
                            break;
                        case 5:
                            clothTem= (Cloth) USER_DATA.getClothList5().get(itemTem);
                            break;
                    }
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                }
                else
                    Toast.makeText(getApplicationContext(),"已经是第一页啦",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_next:
                switch (Integer.parseInt(USER_DATA.getClassifyCode()))
                {
                    case 0:
                        sizeTem=USER_DATA.getClothList0().size();
                        break;
                    case 1:
                        sizeTem=USER_DATA.getClothList1().size();
                        break;
                    case 2:
                        sizeTem=USER_DATA.getClothList2().size();
                        break;
                    case 3:
                        sizeTem=USER_DATA.getClothList3().size();
                        break;
                    case 4:
                        sizeTem=USER_DATA.getClothList4().size();
                        break;
                    case 5:
                        sizeTem=USER_DATA.getClothList5().size();
                        break;
                }
            if(sizeTem>(itemTem+1)){
                    itemTem+=1;
                    USER_DATA.setCurrentItem(itemTem);
                switch (Integer.parseInt(USER_DATA.getClassifyCode()))
                {
                    case 0:
                        clothTem= (Cloth) USER_DATA.getClothList0().get(itemTem);
                        break;
                    case 1:
                        clothTem= (Cloth) USER_DATA.getClothList1().get(itemTem);
                        break;
                    case 2:
                        clothTem= (Cloth) USER_DATA.getClothList2().get(itemTem);
                        break;
                    case 3:
                        clothTem= (Cloth) USER_DATA.getClothList3().get(itemTem);
                        break;
                    case 4:
                        clothTem= (Cloth) USER_DATA.getClothList4().get(itemTem);
                        break;
                    case 5:
                        clothTem= (Cloth) USER_DATA.getClothList5().get(itemTem);
                        break;
                }
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                }
                else
                    Toast.makeText(getApplicationContext(),"已经到最后啦",Toast.LENGTH_SHORT).show();
                break;
            case R.id.shirt_1:
            case R.id.shirt_2:
            case R.id.shirt_3:
                USER_DATA.setClassifyCode("0");USER_DATA.setCurrentItem(0);
                if(USER_DATA.getClothList0().size()>0)
                {
                    clothTem= (Cloth) USER_DATA.getClothList0().get(0);
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                } else
                {
                    CLOTH.setImageDrawable(getDrawable(R.drawable.nothing));
                    Toast.makeText(getApplicationContext(),"没有啦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.jacket_1:
            case R.id.jacket_2:
            case R.id.jacket_3:
                USER_DATA.setClassifyCode("1");USER_DATA.setCurrentItem(0);
                if(USER_DATA.getClothList1().size()>0)
                {



                    clothTem= (Cloth) USER_DATA.getClothList1().get(0);
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                } else
                {
                    CLOTH.setImageDrawable(getDrawable(R.drawable.nothing));
                    Toast.makeText(getApplicationContext(),"没有啦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.shorts_1:
            case R.id.shorts_2:
            case R.id.shorts_3:
                USER_DATA.setClassifyCode("2");USER_DATA.setCurrentItem(0);
                if(USER_DATA.getClothList2().size()>0)
                {



                    clothTem= (Cloth) USER_DATA.getClothList2().get(0);
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                } else
                {
                    CLOTH.setImageDrawable(getDrawable(R.drawable.nothing));
                    Toast.makeText(getApplicationContext(),"没有啦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dress_1:
            case R.id.dress_2:
            case R.id.dress_3:
                USER_DATA.setClassifyCode("3");USER_DATA.setCurrentItem(0);
                if(USER_DATA.getClothList3().size()>0)
                {



                    clothTem= (Cloth) USER_DATA.getClothList3().get(0);
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                } else
                {
                    CLOTH.setImageDrawable(getDrawable(R.drawable.nothing));
                    Toast.makeText(getApplicationContext(),"没有啦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.shoe_1:
            case R.id.shoe_2:
            case R.id.shoe_3:
                USER_DATA.setClassifyCode("4");USER_DATA.setCurrentItem(0);
                if(USER_DATA.getClothList4().size()>0)
                {



                    clothTem= (Cloth) USER_DATA.getClothList4().get(0);
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                }else
            {
                CLOTH.setImageDrawable(getDrawable(R.drawable.nothing));
                Toast.makeText(getApplicationContext(),"没有啦",Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.others_1:
            case R.id.others_2:
            case R.id.others_3:
                USER_DATA.setClassifyCode("5");USER_DATA.setCurrentItem(0);
                if(USER_DATA.getClothList5().size()>0)
                {



                    clothTem= (Cloth) USER_DATA.getClothList5().get(0);
                    clothPic=clothTem.getClothFile();
                    Bitmap bitmap= BitmapFactory.decodeFile(String.valueOf(clothPic));
                    CLOTH.setImageBitmap(bitmap);
                } else
                {
                    CLOTH.setImageDrawable(getDrawable(R.drawable.nothing));
                    Toast.makeText(getApplicationContext(),"没有啦",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.labelGet:
                Toast.makeText(getApplicationContext(),"我的衣橱",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
