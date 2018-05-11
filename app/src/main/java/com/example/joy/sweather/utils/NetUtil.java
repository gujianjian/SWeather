package com.example.joy.sweather.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.utils
 * 简介
 */

public class NetUtil {

    public static void sendOkhttpClient(String address, Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
