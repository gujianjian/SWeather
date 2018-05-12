package com.example.joy.sweather.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.joy.sweather.Constract.presenter.WeatherInfoPresenter;
import com.example.joy.sweather.Constract.view.IWeatherInfoView;
import com.example.joy.sweather.base.BaseActivity;

/**
 * Created by joy on 2018/5/12.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.ui
 * 简介
 */

public class WeatherInfoActivity extends BaseActivity<IWeatherInfoView,WeatherInfoPresenter> implements IWeatherInfoView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected WeatherInfoPresenter initPresenter() {
        return new WeatherInfoPresenter();
    }


    @Override
    public void showInfo() {

    }
}
