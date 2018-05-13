package com.example.joy.sweather.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joy.sweather.R;


/**
 * Created by joy on 2018/5/13.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.ui.common
 * 简介
 */

public class WeatherInfoCommonView extends LinearLayout {

    private TextView tv_title;
    private TextView tv_content;
    private String title;
    private String content;

    public WeatherInfoCommonView(Context context) {
        super(context);
        init(context);
    }


    public WeatherInfoCommonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WeatherInfoCommonView);
        title = a.getString(R.styleable.WeatherInfoCommonView_weather_title);
        content = a.getString(R.styleable.WeatherInfoCommonView_weather_content);

        setWeatherInfoTitle(title);
        setWeatherInfoContent(content);
        a.recycle();



    }

    public WeatherInfoCommonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_weatherinfo_common_view, this);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);

    }

    public void setWeatherInfoContent(String text){
        tv_content.setText(text);

    }

    public void setWeatherInfoTitle(String text){
        tv_title.setText(text);

    }
}
