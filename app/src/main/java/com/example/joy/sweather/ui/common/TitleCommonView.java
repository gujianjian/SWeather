package com.example.joy.sweather.ui.common;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.joy.sweather.R;

/**
 * Created by joy on 2018/5/15.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.ui.common
 * 简介
 */

public class TitleCommonView extends RelativeLayout {

    private TextView tv_title;
    private Button btn_back;

    public TitleCommonView(Context context) {
        super(context);
    }

    public TitleCommonView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.layout_common_title, this);
        tv_title = findViewById(R.id.tv_title);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });


    }

    public TitleCommonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitleText(String text) {
        tv_title.setText(text);
    }
}
