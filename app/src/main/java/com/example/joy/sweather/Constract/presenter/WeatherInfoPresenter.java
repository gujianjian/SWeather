package com.example.joy.sweather.Constract.presenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.joy.sweather.Constract.view.IWeatherInfoView;
import com.example.joy.sweather.base.BasePresenter;
import com.example.joy.sweather.entity.Weather;
import com.example.joy.sweather.service.AutoUpdateService;
import com.example.joy.sweather.ui.SettingActivity;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.Common;
import com.example.joy.sweather.utils.NetUtil;
import com.example.joy.sweather.utils.SpUtils;
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


    public void resume(Weather weather) {
        mView.showInfo(weather);
        loadPic();
    }

    //显示天气信息
    public void resume(String weatherId) {


        String params = "key=" + Canstants.WEATHER_KEY + "&location=" + weatherId;
        String address = String.format(Locale.CHINA, Canstants.WEATHER_NOW_ADDRESS, params);
        NetUtil.sendOkhttpClient(address, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mView.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String weatherStr = response.body().string();
                if (!TextUtils.isEmpty(weatherStr)) {
                    final Weather weather = Common.parseGson(weatherStr);
                    if (weather.status.equals("ok")) {

                        //保存天气json数据到缓存中
                        SpUtils.getInstance().putString("weather", weatherStr);
                        mHanlder.post(new Runnable() {
                            @Override
                            public void run() {
                                mView.showInfo(weather);
                                mView.downRefresh();
                            }
                        });
                    }

                }


            }
        });

        isStartAutoUpdateService();
        loadPic();
    }


    //是否启动自动更新
    private void isStartAutoUpdateService() {
        boolean is_auto_update = SpUtils.getInstance().getBoolean(Canstants.IS_AUTO_UPDATE, false);
        mView.isStartUpdateService(is_auto_update);
    }


    //加载bing图片
    public void loadPic() {
        String bingPicUrl = SpUtils.getInstance().getString(Canstants.SP_BING_PIC_KEY, "");
        //如果有缓存则直接调用有缓存的
        if (!TextUtils.isEmpty(bingPicUrl)) {
            mView.loadBingPic(bingPicUrl);

        }else {
            NetUtil.sendOkhttpClient(Canstants.BING_PIC_ADDRESS, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    mView.onFailure(e.getMessage());
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    final String bing_pic = response.body().string();

                    if (!TextUtils.isEmpty(bing_pic)) {
                        SpUtils.getInstance().putString("bing_pic", bing_pic);
                        mHanlder.post(new Runnable() {
                            @Override
                            public void run() {
                                mView.loadBingPic(bing_pic);
                            }
                        });
                    }


                }
            });
        }
    }

}
