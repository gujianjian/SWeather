package com.example.joy.sweather.utils;

/**
 * Created by joy on 2018/5/11.
 * 项目名  SWeather
 * 类名   com.example.joy.sweather.utils
 * 简介   常量类
 */

public class Canstants {

    //省链接
    public final static String provinceAddress="http://guolin.tech/api/china";
    //市链接
    public final static String cityAddress="http://guolin.tech/api/china/%d";
    //县链接
    public final static String countyAddress="http://guolin.tech/api/china/%d/%d";

    //实况天气GET weather/now   string.format(WEATHER_NOW_ADDRESS,"key=ea0791533462468d9304948e3307963b&location=北京")
    public final static String WEATHER_NOW_ADDRESS="https://free-api.heweather.com/s6/weather/now?%s";
}
