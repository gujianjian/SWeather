package com.example.joy.sweather.utils;

import android.text.TextUtils;

import com.example.joy.sweather.entity.City;
import com.example.joy.sweather.entity.County;
import com.example.joy.sweather.entity.Province;
import com.example.joy.sweather.entity.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.utils
 * 简介   常用类
 */

public class Common {


    /**
     * 解析省的json数据，并将数据存入数据库
     * @return
     */
    public static boolean handleProvinceJson(String json){
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                if (jsonArray.length() > 0) {
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        Province province=new Province();
                        province.setProvinceCode(id);
                        province.setProvinceName(name);
                        province.save();
                    }
                    return true;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析市的json数据，而且省的id保存入数据库
     * @param json
     * @param provinceCode
     * @return
     */
    public static boolean handleCityJson(String json,int provinceCode) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                if (jsonArray.length() > 0) {
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        City city=new City();
                        city.setCityCode(id);
                        city.setCityName(name);
                        city.setProvinceCode(provinceCode);
                        city.save();

                    }
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }


    /**
     * 解析县的json数据，而且县的id保存入数据库
     * @param json
     * @param cityCode
     * @return
     */
    public static boolean handleCountyJson(String json, int cityCode) {
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                if (jsonArray.length() > 0) {
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String weather_id = jsonObject.getString("weather_id");
                        County county=new County();
                        county.setCityCode(cityCode);
                        county.setCityName(name);
                        county.setWeatherId(weather_id);
                        county.save();
                    }
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    /**
     * 解析weather json数据
     * @param response
     * @return
     */
    public static Weather parseGson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
            JSONObject weatherObject = jsonArray.getJSONObject(0);
            String weatherStr=weatherObject.toString();
            return new Gson().fromJson(weatherStr,new TypeToken<Weather>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
