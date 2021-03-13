package com.fanxiaoyudemo.magicalwardrobe.Home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.Adapter.GridAdapter;
import com.fanxiaoyudemo.magicalwardrobe.Community.CommunityActivity;
import com.fanxiaoyudemo.magicalwardrobe.MyWardrobe.MyWardrobeActivity;
import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.UserInformation.UserInformationAcyivity;
import com.fanxiaoyudemo.magicalwardrobe.ModelEdit.ModelEditActivity;
import com.fanxiaoyudemo.magicalwardrobe.WeatherForcast.WeatherForcastActivity;

public class HomeActivity2 extends Activity {
    private ImageView VIRTUAL_FIT2, WEATHER_FORECAST2, COMMUNITY2, MY_WARDROBE2, MESSAGE2, MY_COLLECTION2, USER_INFORMATION2,VIEW_SWITCH_COPY2;
    private RecyclerView HOME_RECYCLER_VIEW2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home2);


        ImageView VIRTUAL_FIT2 = (ImageView) findViewById(R.id.virtual_fit2);
        ImageView WEATHER_FORECAST2 = (ImageView) findViewById(R.id.weather_forecast2);
        ImageView COMMUNITY2 = (ImageView) findViewById(R.id.community2);
        ImageView MY_WARDROBE2 = (ImageView) findViewById(R.id.my_wardrobe2);
        ImageView MESSAGE2 = (ImageView) findViewById(R.id.message2);
        ImageView MY_COLLECTION2 = (ImageView) findViewById(R.id.my_collection2);
        ImageView USER_INFORMATION2 = (ImageView) findViewById(R.id.user_information2);
        ImageView VIEW_SWITCH_COPY2 = (ImageView)findViewById(R.id.view_switch2);


        VIRTUAL_FIT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity2.this, ModelEditActivity.class);
                startActivity(intent);
            }
        });
        WEATHER_FORECAST2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity2.this, WeatherForcastActivity.class);
                startActivity(intent);
            }
        });
        COMMUNITY2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity2.this, CommunityActivity.class);
                startActivity(intent);
            }
        });
        MY_WARDROBE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity2.this, MyWardrobeActivity.class);
                startActivity(intent);
            }
        });
        MESSAGE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity2.this, "我的信息", Toast.LENGTH_SHORT).show();
            }
        });
        MY_COLLECTION2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity2.this, "我的收藏", Toast.LENGTH_SHORT).show();
            }
        });
        USER_INFORMATION2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity2.this, UserInformationAcyivity.class);
                startActivity(intent);
            }
        });
        VIEW_SWITCH_COPY2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        RecyclerView
        HOME_RECYCLER_VIEW2 = (RecyclerView) findViewById(R.id.home_recycler_view2);
        HOME_RECYCLER_VIEW2.setLayoutManager(new GridLayoutManager(HomeActivity2.this, 2));
        HOME_RECYCLER_VIEW2.addItemDecoration(new MyDecoration());
        HOME_RECYCLER_VIEW2.setAdapter(new GridAdapter(HomeActivity2.this, new GridAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(HomeActivity2.this, "click: " + pos, Toast.LENGTH_SHORT).show();
            }
        }));
    }
    class MyDecoration extends RecyclerView.ItemDecoration

    {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(getResources().getDimensionPixelOffset(R.dimen.dividerHeight), getResources().getDimensionPixelOffset(R.dimen.dividerHeight), getResources().getDimensionPixelOffset(R.dimen.dividerHeight), getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}


