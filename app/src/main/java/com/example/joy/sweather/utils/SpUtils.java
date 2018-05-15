package com.example.joy.sweather.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.joy.sweather.application.BaseApplication;

/**
 * Created by joy on 2018/5/14.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.utils
 * 简介
 */

public class SpUtils {

   public static SpUtils mInstance;
    private final SharedPreferences sp;

    public static SpUtils getInstance(){
        if (mInstance == null) {
            synchronized (SpUtils.class) {
                if (mInstance == null) {
                    mInstance=new SpUtils();
                }
            }
        }
        return mInstance;
    }

    private SpUtils() {
        sp = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());

    }


    public void putString(String key,String value) {
        sp.edit().putString(key,value).apply();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key,value).apply();
    }

    public boolean getBoolean(String key, boolean defVal) {
        return sp.getBoolean(key, defVal);
    }
}
