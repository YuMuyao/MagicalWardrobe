package com.fanxiaoyudemo.magicalwardrobe.WeatherForcast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fanxiaoyudemo.magicalwardrobe.R;

public class WeatherForcastActivity extends Activity {
    private ImageView WF_RETURN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
        WF_RETURN = (ImageView)findViewById(R.id.wf_return);
        WF_RETURN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
