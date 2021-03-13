package com.fanxiaoyudemo.magicalwardrobe.Home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fanxiaoyudemo.magicalwardrobe.Adapter.LinearAdapter;
import com.fanxiaoyudemo.magicalwardrobe.Adapter.MyPagerAdapter;
import com.fanxiaoyudemo.magicalwardrobe.Community.CommunityActivity;
import com.fanxiaoyudemo.magicalwardrobe.ModelEdit.ModelEditActivity;
import com.fanxiaoyudemo.magicalwardrobe.MyWardrobe.MyWardrobeActivity;
import com.fanxiaoyudemo.magicalwardrobe.R;
import com.fanxiaoyudemo.magicalwardrobe.Tool.UserData;
import com.fanxiaoyudemo.magicalwardrobe.UserInformation.UserInformationAcyivity;
import com.fanxiaoyudemo.magicalwardrobe.WeatherForcast.WeatherForcastActivity;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity implements View.OnTouchListener, ViewPager.OnPageChangeListener {
    private ImageView VIRTUAL_FIT, WEATHER_FORECAS, COMMUNITY, MY_WARDROBE, MESSAGE, MY_COLLECTION, USER_INFORMATION, VIEW_SWITCH;
    private RecyclerView HOME_RECYCLER_VIEW;

    public static final int VIEW_PAGER_DELAY = 2000;
    private MyPagerAdapter myAdapter;
    private List<ImageView> myItems;
    private ImageView[] myBottomImages;
    private LinearLayout myBottomLinear;
    private ViewPager myViewPager;

    private int currentViewPagerItem;
    //是否自动播放
    private boolean isAutoPlay;

    private MyHandler myHandler;
    private Thread myThread;

    UserData USER_DATA;
    int code=-1;
    int code_2=-1;
    int code_get_1=-1;
    int code_get_2=-1;
    int code_get_3=-1;
    int code_get_4=-1;
    int code_get_5=-1;
    int code_get_0=-1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        USER_DATA=(UserData)getApplication();

        ImageView VIRTUAL_FIT = (ImageView) findViewById(R.id.virtual_fit);
        ImageView WEATHER_FORECAST = (ImageView) findViewById(R.id.weather_forecast);
        ImageView COMMUNITY = (ImageView) findViewById(R.id.community);
        ImageView MY_WARDROBE = (ImageView) findViewById(R.id.my_wardrobe);
        ImageView MESSAGE = (ImageView) findViewById(R.id.message);
        ImageView MY_COLLECTION = (ImageView) findViewById(R.id.my_collection);
        final ImageView USER_INFORMATION = (ImageView) findViewById(R.id.user_information);
        ImageView VIEW_SWITCH = (ImageView) findViewById(R.id.view_switch);


        VIRTUAL_FIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ModelEditActivity.class);
                startActivity(intent);
            }
        });
        WEATHER_FORECAST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, WeatherForcastActivity.class);
                startActivity(intent);
            }
        });
        COMMUNITY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CommunityActivity.class);
                startActivity(intent);
            }
        });
        MY_WARDROBE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    public void run() {
                        Looper.prepare();
                        try {
                            code_get_0 = USER_DATA.get_cloth_function(USER_DATA.getPhoneNum(), "0");
                            code_get_1 = USER_DATA.get_cloth_function(USER_DATA.getPhoneNum(), "1");
                            code_get_2 = USER_DATA.get_cloth_function(USER_DATA.getPhoneNum(), "2");
                            code_get_3 = USER_DATA.get_cloth_function(USER_DATA.getPhoneNum(), "3");
                            code_get_4 = USER_DATA.get_cloth_function(USER_DATA.getPhoneNum(), "4");
                            code_get_5 = USER_DATA.get_cloth_function(USER_DATA.getPhoneNum(), "5");
                            USER_DATA.setCurrentItem(0);
                            USER_DATA.setClassifyCode("0");
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if((code_get_0==0||code_get_0==2)&&(code_get_1==0||code_get_1==2)&&(code_get_2==0||code_get_2==2)&&(code_get_3==0||code_get_3==2)&&(code_get_4==0||code_get_4==2)&&(code_get_5==0||code_get_5==2))
                        {
                            Intent intent = new Intent(HomeActivity.this, MyWardrobeActivity.class);Toast.makeText(getApplicationContext(),"获取成功!",Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }else
                        {
                            Toast.makeText(getApplicationContext(),"查询失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }.start();


            }
        });
        MESSAGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "我的信息", Toast.LENGTH_SHORT).show();
            }
        });
        MY_COLLECTION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
            }
        });
        USER_INFORMATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        try {
                            code = USER_DATA.get_information_function(USER_DATA.getPhoneNum());
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                if (code == 0) {
                    Intent intent = new Intent(HomeActivity.this, UserInformationAcyivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "查询成功", Toast.LENGTH_SHORT).show();
                } else if (code == 1) {
                    Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
        VIEW_SWITCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity2.class);
                startActivity(intent);
            }
        });


//        RecyclerView
        HOME_RECYCLER_VIEW = (RecyclerView) findViewById(R.id.home_recycler_view);
        HOME_RECYCLER_VIEW.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        HOME_RECYCLER_VIEW.addItemDecoration(new MyDecoration());
        HOME_RECYCLER_VIEW.setAdapter(new LinearAdapter(HomeActivity.this, new LinearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(HomeActivity.this, "click:" + pos, Toast.LENGTH_SHORT).show();
            }
        }));
        myHandler = new MyHandler(this);
        //配置轮播图ViewPager
        myViewPager = (ViewPager) findViewById(R.id.viewpager_ad);
        myItems = new ArrayList<>();
        myAdapter = new MyPagerAdapter(myItems, this);
        myViewPager.setAdapter(myAdapter);

        myViewPager.setOnTouchListener(this);
        myViewPager.addOnPageChangeListener(this);
        isAutoPlay = true;

        addImageView();
        myAdapter.notifyDataSetChanged();
        //设置底部4个小点
        setBottomIndicator();
    }

    private void addImageView() {

        ImageView view1 = new ImageView(this);
        view1.setImageResource(R.drawable.ad);
        ImageView view2 = new ImageView(this);
        view2.setImageResource(R.drawable.ad2);
        ImageView view3 = new ImageView(this);
        view3.setImageResource(R.drawable.ad3);
        ImageView view4 = new ImageView(this);
        view4.setImageResource(R.drawable.ad4);

        view1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view4.setScaleType(ImageView.ScaleType.CENTER_CROP);

        myItems.add(view1);
        myItems.add(view2);
        myItems.add(view3);
        myItems.add(view4);

    }
    private void setBottomIndicator() {
        //获取指示器(下面三个小点)
        myBottomLinear = (LinearLayout) findViewById(R.id.circle_indicater);
        //右下方小圆点
        myBottomImages = new ImageView[myItems.size()];
        for (int i = 0; i < myBottomImages.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);
            //如果当前是第一个 设置为选中状态
            if (i == 0) {
                imageView.setImageResource(R.drawable.indicator_select);
            } else {
                imageView.setImageResource(R.drawable.indicator_no_select);
            }
            myBottomImages[i] = imageView;
            //添加到父容器
            myBottomLinear.addView(imageView);
        }

        //让其在最大值的中间开始滑动, 一定要在 mBottomImages初始化之前完成
        int mid = MyPagerAdapter.MAX_SCROLL_VALUE / 2;
        myViewPager.setCurrentItem(mid);
        currentViewPagerItem = mid;

        //定时发送消息
        myThread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    myHandler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(HomeActivity.VIEW_PAGER_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        myThread.start();
    }
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    public void onPageSelected(int position) {

        currentViewPagerItem = position;
        if (myItems != null) {
            position %= myBottomImages.length;
            int total = myBottomImages.length;

            for (int i = 0; i < total; i++) {
                if (i == position) {
                    myBottomImages[i].setImageResource(R.drawable.indicator_select);
                } else {
                    myBottomImages[i].setImageResource(R.drawable.indicator_no_select);
                }
            }
        }
    }
    public void onPageScrollStateChanged(int state) {

    }
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isAutoPlay = false;
                break;
            case MotionEvent.ACTION_UP:
                isAutoPlay = true;
                break;
        }
        return false;
    }
    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(getResources().getDimensionPixelOffset(R.dimen.dividerHeight), getResources().getDimensionPixelOffset(R.dimen.dividerHeight), getResources().getDimensionPixelOffset(R.dimen.dividerHeight), getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
    private static class MyHandler extends Handler {
        private WeakReference<HomeActivity> myWeakReference;

        public MyHandler(HomeActivity activity) {
            myWeakReference = new WeakReference<HomeActivity>(activity);
        }
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    HomeActivity activity = myWeakReference.get();
                    if (activity.isAutoPlay) {

                        activity.myViewPager.setCurrentItem(++activity.currentViewPagerItem);
                    }

                    break;
            }

        }
    }
}
