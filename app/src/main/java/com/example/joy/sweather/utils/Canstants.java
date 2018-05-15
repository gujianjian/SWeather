package com.example.joy.sweather.utils;

/**
 * Created by joy on 2018/5/11.
 * 项目名  SWeather
 * 类名   com.example.joy.sweather.utils
 * 简介   常量类
 */

public class Canstants {


    //省链接
    public final static String PROVINCE_ADDRESS ="http://guolin.tech/api/china";
    //市链接
    public final static String CITY_ADDRESS ="http://guolin.tech/api/china/%d";
    //县链接
    public final static String COUNTY_ADDRESS ="http://guolin.tech/api/china/%d/%d";

    //实况天气GET weather/Now   string.format(WEATHER_NOW_ADDRESS,"key=ea0791533462468d9304948e3307963b&location=CN101010100")
    public final static String WEATHER_NOW_ADDRESS="https://free-api.heweather.com/s6/weather/now?%s";

    //WEATHER_KEY
    public final static String WEATHER_KEY="ea0791533462468d9304948e3307963b";

    //每天变换的bing图片
    public final static String BING_PIC_ADDRESS="http://guolin.tech/api/bing_pic";


    //是否自动更新
    public final static String IS_AUTO_UPDATE="is_auto_update";

    //天气now 的缓存key
    public final static String SP_WEATHER_KEY ="weather";

    //bing图片的  缓存key
    public final static String SP_BING_PIC_KEY ="bing_pic";
}
