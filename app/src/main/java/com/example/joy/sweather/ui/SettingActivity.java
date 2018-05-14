package com.example.joy.sweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.example.joy.sweather.Constract.presenter.SettingPresenter;
import com.example.joy.sweather.Constract.view.ISettingView;
import com.example.joy.sweather.R;
import com.example.joy.sweather.base.BaseActivity;
import com.example.joy.sweather.service.AutoUpdateService;
import com.example.joy.sweather.ui.common.TitleCommonView;

import java.util.Set;

/**
 * Created by joy on 2018/5/14.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.ui
 * 简介
 */

public class SettingActivity extends BaseActivity<ISettingView, SettingPresenter> implements ISettingView, View.OnClickListener {


    private TitleCommonView titleCommonView;
    private Switch mSwitch;
    private Intent serviceIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);

        initView();
    }

    @Override
    protected SettingPresenter initPresenter() {
        return new SettingPresenter();
    }


    @Override
    protected void initView() {
        titleCommonView = findViewById(R.id.title_bar);
        titleCommonView.setTitleText("设置");
        mSwitch=findViewById(R.id.switch_update_service);
        mSwitch.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void setStartUpdateService() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //启动停止自动更新天气服务
            case R.id.switch_update_service:
                serviceIntent = new Intent(SettingActivity.this, AutoUpdateService.class);
                boolean isChecked = mSwitch.isChecked();
                if (isChecked) {
                    startService(serviceIntent);
                }else{
                    stopService(serviceIntent);
                }

                break;
        }
    }
}
