package com.example.joy.sweather.application;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather
 * 简介
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
