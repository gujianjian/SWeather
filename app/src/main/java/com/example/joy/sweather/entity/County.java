package com.example.joy.sweather.entity;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.entity
 * 简介
 */


//链接http://guolin.tech/api/china/17/129

//        {
//        "id": 1017,
//        "name": "宁波",
//        "weather_id": "CN101210401"
//        }

import org.litepal.crud.DataSupport;

/**
 * 县
 */
public class County extends DataSupport {

    private String cityName;//name
    private String weatherId;//weather_id;
    private int cityCode;//city类的cityCode


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }
}
