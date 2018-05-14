package com.example.joy.sweather.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
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

    private  int titleColor;
    private  float contentSize;
    private float titleSize;
    private TextView tv_title;
    private TextView tv_content;
    private String title;
    private String content;
    private int contentColor;
    private Context mContext;


    public WeatherInfoCommonView(Context context) {
        super(context);
        init(context);
    }


    public WeatherInfoCommonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        mContext=context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WeatherInfoCommonView);
        title = a.getString(R.styleable.WeatherInfoCommonView_my_title);
        content = a.getString(R.styleable.WeatherInfoCommonView_my_content);
        titleSize = a.getDimensionPixelSize(R.styleable.WeatherInfoCommonView_my_titleSize,48);
        contentSize = a.getDimensionPixelSize(R.styleable.WeatherInfoCommonView_my_contentSize, 90);
        titleColor = a.getColor(R.styleable.WeatherInfoCommonView_my_title_color, Color.GRAY);
        contentColor = a.getColor(R.styleable.WeatherInfoCommonView_my_content_color, Color.WHITE);


        setInfoTitle(title);
        setInfoTitleTextSize(titleSize);
        setInfoTitleColor(titleColor);

        setInfoContent(content);
        setInfoContentTextSize(contentSize);
        setInfoContentColor(contentColor);


        a.recycle();



    }
    public WeatherInfoCommonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //设置描述信息字体颜色
    public void setInfoContentColor(int contentColor) {
        tv_content.setTextColor(contentColor);
    }

    //设置标题字体颜色
    public void setInfoTitleColor(int titleColor) {
        tv_title.setTextColor(titleColor);
    }

    //设置描述信息字体大小
    private void setInfoContentTextSize(float contentSize) {
        tv_content.setTextSize(px2dp(mContext,contentSize));
    }

    //设置标题字体大小
    public void setInfoTitleTextSize(float titleSize) {
        tv_title.setTextSize(px2dp(mContext,titleSize));
    }




    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_weatherinfo_common_view, this);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);

    }

    //设置说明信息
    public void setInfoContent(String text){
        tv_content.setText(text);

    }

    //设置标题信息
    public void setInfoTitle(String text){
        tv_title.setText(text);

    }

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }


    public static int px2dp(Context context, float pxVal) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal / scale + 0.5f);
    }



}
