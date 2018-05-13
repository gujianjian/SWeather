package com.example.joy.sweather.Constract.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.joy.sweather.Constract.view.IWeatherInfoView;
import com.example.joy.sweather.base.BasePresenter;
import com.example.joy.sweather.entity.Weather;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.Common;
import com.example.joy.sweather.utils.NetUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Locale;


/**
 * Created by joy on 2018/5/12.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.Constract.presenter
 * 简介
 */

public class WeatherInfoPresenter extends BasePresenter<IWeatherInfoView> {

    private Handler mHanlder;

    public WeatherInfoPresenter() {
        mHanlder = new Handler(Looper.getMainLooper());

    }

    //显示天气信息
    public void resume(String weatherId){
        String params = "key=" + Canstants.WEATHER_KEY + "&location=" + weatherId;
        String address = String.format(Locale.CHINA, Canstants.WEATHER_NOW_ADDRESS, params);
        NetUtil.sendOkhttpClient(address, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mView.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s=response.body().string();
                final Weather weather = Common.parseGson(s);
                mHanlder.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.showInfo(weather);
                        mView.downRefresh();
                    }
                });

            }
        });

        loadPic();
    }


    //加载bing图片

    public void loadPic(){
        NetUtil.sendOkhttpClient(Canstants.BING_PIC_ADDRESS, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mView.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String s=response.body().string();

                mHanlder.post(new Runnable() {
                    @Override
                    public void run() {
                        mView.loadBingPic(s);
                    }
                });
            }
        });
    }

}
