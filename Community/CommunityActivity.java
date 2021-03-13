package com.fanxiaoyudemo.magicalwardrobe.Community;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fanxiaoyudemo.magicalwardrobe.R;

public class CommunityActivity extends Activity {
    private ImageView C_RETURN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        C_RETURN = (ImageView)findViewById(R.id.c_return);
        C_RETURN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
