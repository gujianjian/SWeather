package com.example.joy.sweather.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joy on 2018/5/13.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.entity
 * 简介
 */

public class Update {


    /**
     * loc : 2017-10-26 17:29当地时间
     * utc : 2017-10-26 09:29UTC时间
     */
    @SerializedName("loc")
    private String locTime;
    private String utc;

    public String getLocTime() {
        return locTime;
    }

    public void setLocTime(String locTime) {
        this.locTime = locTime;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
