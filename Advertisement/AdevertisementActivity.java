package com.fanxiaoyudemo.magicalwardrobe.Advertisement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.R;

public class AdevertisementActivity extends Activity {
    ImageView RETURN,COMMENT,PRAISE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adevertisement);
        RETURN=(ImageView)findViewById(R.id.return_ad);
        RETURN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       COMMENT=(ImageView)findViewById(R.id.comment);
        COMMENT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"评论！",Toast.LENGTH_SHORT).show();
            }
        });
        PRAISE=(ImageView)findViewById(R.id.ad_praise);
        PRAISE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"点赞！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
