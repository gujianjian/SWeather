package com.example.joy.sweather.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.joy.sweather.Constract.presenter.WeatherInfoPresenter;
import com.example.joy.sweather.Constract.view.IWeatherInfoView;
import com.example.joy.sweather.R;
import com.example.joy.sweather.base.BaseActivity;
import com.example.joy.sweather.entity.Weather;
import com.example.joy.sweather.ui.common.WeatherInfoCommonView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by joy on 2018/5/12.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.ui
 * 简介
 */

public class WeatherInfoActivity extends BaseActivity<IWeatherInfoView,WeatherInfoPresenter> implements IWeatherInfoView{

    private WeatherInfoCommonView text_fl;
    private WeatherInfoCommonView text_wind_deg;
    private WeatherInfoCommonView text_wind_dir;
    private WeatherInfoCommonView text_wind_sc;
    private WeatherInfoCommonView text_wind_spd;
    private WeatherInfoCommonView text_hum;
    private WeatherInfoCommonView text_pcpn;
    private WeatherInfoCommonView text_vis;
    private WeatherInfoCommonView text_pres;
    private WeatherInfoCommonView text_cloud;
    private TextView text_city_title;
    private TextView tv_text_info;
    private ImageView iv_image_pic;
    private TextView tv_tmp;
    private LinearLayout ll_weather;

    private ImageView iv_bing_pic;

    private TextView tv_update_time;//更新时间

    private SwipeRefreshLayout srl_refresh;
    private String weatherId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherinfo);

        initView();


    }

    public static void createIntent(Context context, Bundle bundle) {
        Intent intent=new Intent(context, WeatherInfoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        //体感温度
        text_fl = findViewById(R.id.weather_item_fl);

        //风向360角度
        text_wind_deg = findViewById(R.id.weather_item_wind_deg);
        //风向
        text_wind_dir = findViewById(R.id.weather_item_wind_dir);
        //风力
        text_wind_sc = findViewById(R.id.weather_item_wind_sc);
        //风速
        text_wind_spd = findViewById(R.id.weather_item_wind_spd);
        //相对湿度
        text_hum = findViewById(R.id.weather_item_hum);
        //降水量
        text_pcpn = findViewById(R.id.weather_item_pcpn);
        //能见度
        text_vis = findViewById(R.id.weather_item_vis);
        //大气压强
        text_pres = findViewById(R.id.weather_item_pres);
        //云量
        text_cloud = findViewById(R.id.weather_item_cloud);



        //当前城市
        text_city_title = findViewById(R.id.tv_city);
        //天气信息
        tv_text_info = findViewById(R.id.tv_text_info);
        //显示天气图标
        iv_image_pic = findViewById(R.id.iv_image_pic);
        //当前温度
        tv_tmp = findViewById(R.id.tv_tmp);


        ll_weather = findViewById(R.id.ll_weather);

        //背景图
        iv_bing_pic = findViewById(R.id.iv_bing_pic);

        //更新
        srl_refresh = findViewById(R.id.srl_refresh);
        srl_refresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.resume(weatherId);
            }
        });


        tv_update_time = findViewById(R.id.tv_update_time);

    }

    @Override
    protected WeatherInfoPresenter initPresenter() {
        return new WeatherInfoPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        weatherId = data.getString("weatherId");
        presenter.resume(weatherId);
    }



    /**
     * 加载天气详细信息
     * @param weather
     */
    @Override
    public void showInfo(Weather weather) {


        //如果显示status为ok则有数据
        if(weather.status.equals("ok")){

            ll_weather.setVisibility(View.VISIBLE);



            String body_tmp = weather.now.body_tmp;//体感温度
            String wind_deg = weather.now.wind_deg;//风向角度
            String wind_dir = weather.now.wind_dir;//风向
            String wind_sc = weather.now.wind_sc;//风力
            String wind_spd = weather.now.wind_spd;//风速
            String hum = weather.now.hum;//相对湿度
            String water = weather.now.water;//降水量
            String vis = weather.now.vis;//能见度
            String pres = weather.now.pres;//大气压强
            String cloud = weather.now.cloud;//云量


            String cityName = weather.basic.cityName;//城市名称
            String weather_info = weather.now.weather_info;//天气状态
            String imageCode = weather.now.imageCode;//天气状态图标
            String tmp = weather.now.tmp;//温度



            /****设置天气基本信息****/
            text_city_title.setText(cityName);
            tv_text_info.setText(weather_info);
            AssetManager assets = getAssets();
            try {
                InputStream stream = assets.open("weatherimage/" + imageCode + ".png");
                Bitmap bitmap=BitmapFactory.decodeStream(stream);
                iv_image_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tv_tmp.setText(tmp+"°");

            tv_update_time.setText(weather.update.locTime);


            /********设置详细信息****************************/
            text_fl.setWeatherInfoContent(body_tmp+"°");
            text_wind_deg.setWeatherInfoContent(wind_deg+"°");
            text_wind_dir.setWeatherInfoContent(wind_dir);
            text_wind_sc.setWeatherInfoContent(wind_sc+"级");
            text_wind_spd.setWeatherInfoContent(wind_spd);
            text_hum.setWeatherInfoContent(hum+"%");
            text_pcpn.setWeatherInfoContent(water);
            text_vis.setWeatherInfoContent(vis+"公里");
            text_pres.setWeatherInfoContent(pres+"帕");
            text_cloud.setWeatherInfoContent(cloud);
        }else{
            ll_weather.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(WeatherInfoActivity.this, msg, Toast.LENGTH_SHORT).show();

    }


    /**
     * 加载背景图
     * @param url
     */
    @Override
    public void loadBingPic(String url) {
        Glide.with(this).load(url).into(iv_bing_pic);
    }

    @Override
    public void downRefresh() {
        srl_refresh.setRefreshing(false);
    }


    @Override
    public void onBackPressed() {

    }
}
