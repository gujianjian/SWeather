package com.example.joy.sweather.Constract.view;

import com.example.joy.sweather.entity.Weather;

/**
 * Created by joy on 2018/5/12.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.Constract.view
 * 简介
 */

public interface IWeatherInfoView {

    //显示天气信息
    void showInfo(Weather weather);

    //失败错误信息
    void onFailure(String msg);

    //加载bing背景图片
    void loadBingPic(String url);

    //设置停止更新
    void downRefresh();

}
