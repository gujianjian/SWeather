package com.example.joy.sweather.entity;

/**
 * Created by joy on 2018/5/13.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.entity
 * 简介   实况天气类
 */

import com.google.gson.annotations.SerializedName;

/**
  "Now": {
 "cond_code": "101",         天气状态码
 "cond_txt": "多云",         天气状况
 "fl": "16",                体感温度，默认单位：摄氏度
 "hum": "73",               相对湿度73%
 "pcpn": "0",               降水量，毫米
 "pres": "1017",            大气压强
 "tmp": "14",               温度，默认单位：摄氏度
 "vis": "1",                能见度，默认单位：公里
 "wind_deg": "11",          风向360角度
 "wind_dir": "北风",         风向
 "wind_sc": "微风",          风力
 "wind_spd": "6"            风速公里/小时
 }
 */
public class Now {

    @SerializedName("cond_code")
    public String imageCode;

    @SerializedName("cond_txt")
    public String weather_info;

    @SerializedName("fl")
    public String body_tmp;

    public String hum;

    @SerializedName("pcpn")
    public String water;

    public String pres;
    public String tmp;
    public String vis;
    public String wind_deg;
    public String wind_dir;
    public String wind_sc;
    public String wind_spd;
    public String cloud;

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getWeather_info() {
        return weather_info;
    }

    public void setWeather_info(String weather_info) {
        this.weather_info = weather_info;
    }

    public String getBody_tmp() {
        return body_tmp;
    }

    public void setBody_tmp(String body_tmp) {
        this.body_tmp = body_tmp;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(String wind_deg) {
        this.wind_deg = wind_deg;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getWind_sc() {
        return wind_sc;
    }

    public void setWind_sc(String wind_sc) {
        this.wind_sc = wind_sc;
    }

    public String getWind_spd() {
        return wind_spd;
    }

    public void setWind_spd(String wind_spd) {
        this.wind_spd = wind_spd;
    }
}
