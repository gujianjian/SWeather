package com.example.joy.sweather.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.joy.sweather.R;
import com.example.joy.sweather.entity.Weather;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.Common;
import com.example.joy.sweather.utils.NetUtil;
import com.example.joy.sweather.utils.SpUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String weatherStr = SpUtils.getInstance().getString("weather", "");
        if (!TextUtils.isEmpty(weatherStr)) {
            Weather weather = Common.parseGson(weatherStr);
            String weather_id = weather.basic.weather_id;
            Bundle bundle = new Bundle();
            bundle.putString("weatherId",weather_id);
            WeatherInfoActivity.createIntent(this,bundle);
        }

    }

}
