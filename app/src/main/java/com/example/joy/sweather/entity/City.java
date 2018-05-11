package com.example.joy.sweather.entity;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.entity
 * 简介
 */

//链接http://guolin.tech/api/china/17        其中后面的17为省的code
//      {
//        "id": 126,
//        "name": "杭州"
//        }


import org.litepal.crud.DataSupport;

/**
 * 市
  */
public class City extends DataSupport{
    private int cityCode;//id
    private String cityName;//name
    private int provinceCode;//province类的provinceCode

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
