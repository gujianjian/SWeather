package com.example.joy.sweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;

import com.example.joy.sweather.entity.Weather;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.Common;
import com.example.joy.sweather.utils.L;
import com.example.joy.sweather.utils.NetUtil;
import com.example.joy.sweather.utils.SpUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Locale;

public class AutoUpdateService extends Service {

    private AlarmManager manager;
    private PendingIntent pi;

    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        L.d("开始自动更新");
        updateWeather();
        updateBingPic();
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //每8个小时进行一次更新
        long updatetime = SystemClock.elapsedRealtime() + 1000 * 60 * 60 * 8;
        Intent i = new Intent(this, AutoUpdateService.class);
        pi = PendingIntent.getService(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, updatetime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateBingPic() {
        String address=Canstants.BING_PIC_ADDRESS;
        NetUtil.sendOkhttpClient(address, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                L.d(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String bing_pic = response.body().string();
                if (!TextUtils.isEmpty(bing_pic)) {
                    SpUtils.getInstance().putString(Canstants.SP_BING_PIC_KEY,bing_pic);
                }
            }
        });
    }

    private void updateWeather() {
        String weatherStr = SpUtils.getInstance().getString(Canstants.SP_WEATHER_KEY, "");
        if (!TextUtils.isEmpty(weatherStr)) {
            Weather weather = Common.parseGson(weatherStr);
            String weather_id = weather.basic.weather_id;
            String params = "key=" + Canstants.WEATHER_KEY + "&location=" + weather_id;
            String address = String.format(Locale.CHINA, Canstants.WEATHER_NOW_ADDRESS, params);

            NetUtil.sendOkhttpClient(address, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    L.d(e.getMessage());
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String weather = response.body().string();
                    SpUtils.getInstance().putString(Canstants.SP_WEATHER_KEY,weather);
                }
            });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消定时任务
        manager.cancel(pi);
    }
}
