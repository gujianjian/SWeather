package com.example.joy.sweather.entity;

/**
 * Created by joy on 2018/5/13.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.entity
 * 简介 天气基础信息类
 */


import com.google.gson.annotations.SerializedName;

/**
 *
 "basic": {
 "cid": "CN101010100",
 "location": "北京",
 "parent_city": "北京",
 "admin_area": "北京",
 "cnty": "中国",
 "lat": "39.90498734",
 "lon": "116.40528870",
 "tz": "8.0"
 }
 */
public class Basic {

    @SerializedName("location")
    public String cityName;

    @SerializedName("cid")
    public String weather_id;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }
}
