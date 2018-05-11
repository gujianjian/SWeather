package com.example.joy.sweather.Constract.presenter;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.joy.sweather.Constract.presenter.base.BasePresenter;
import com.example.joy.sweather.Constract.view.AeraView;
import com.example.joy.sweather.entity.City;
import com.example.joy.sweather.entity.Province;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.Common;
import com.example.joy.sweather.utils.NetUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.Util;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.Constract.presenter
 * 简介
 */

public class AeraPresenter extends BasePresenter<AeraView> {

    private Handler mHandler;
    private List<String> dataList=new ArrayList<>();

    private int currentLevel=0;

    private final int PROVINCE_LEVEL=0;
    private final int CITY_LEVEL=1;
    private final int COUNTY_LEVEL=2;
    private List<Province> all_province;
    private Province selectedProvince;
    private List<City> all_cities;


    public AeraPresenter() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void onResume(String address) {
        mView.backButtonHide();
        mView.setTitle("中国");
        NetUtil.sendOkhttpClient(address, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mView.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s = response.body().string();
                if (!TextUtils.isEmpty(s)) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            queryProvinces();
                        }
                    });
                }
            }
        });
    }


    /**
     * view点击时切换数据
     * @param position
     */
    public void onItemClicked(int position){
        if(currentLevel==PROVINCE_LEVEL){
            selectedProvince = all_province.get(position);
            queryCity();
            currentLevel=CITY_LEVEL;
        }else if (currentLevel==CITY_LEVEL){

        }
    }


    private void queryProvinces() {
        mView.backButtonHide();
        mView.setTitle("中国");
        all_province = DataSupport.findAll(Province.class);
        if (all_province.size() > 0) {
            dataList.clear();
            for (Province p : all_province) {
                dataList.add(p.getProvinceName());
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mView.setListItem(dataList);
                }
            });

        }else{
            String address=Canstants.provinceAddress;
            queryFormServer(address,"province");
        }
    }




    private void queryCity() {
        mView.backButtonShow();
        mView.setTitle(selectedProvince.getProvinceName());
        all_cities = DataSupport.where("provinceCode=?", String.valueOf(selectedProvince.getProvinceCode())).find(City.class);
        if(all_cities.size()>0){
            dataList.clear();
            for (City city : all_cities) {
                dataList.add(city.getCityName());
            }

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mView.setListItem(dataList);
                }
            });


        }else{
            String address = String.format(Locale.CHINA,Canstants.cityAddress, selectedProvince.getProvinceCode());
            queryFormServer(address,"city");
        }

    }


    private void queryFormServer(String address, final String type) {
        NetUtil.sendOkhttpClient(address, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mView.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String s=response.body().string();
                boolean result=false;
                if (!TextUtils.isEmpty(s)) {
                    if (type.equals("province")) {
                        result=Common.handleProvinceJson(s);
                    } else if (type.equals("city")) {
                        result=Common.handleCityJson(s,selectedProvince.getProvinceCode());
                    }
                }

                if (result) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (type.equals("province")) {
                                queryProvinces();
                            } else if (type.equals("city")) {
                                queryCity();
                            }

                        }
                    });
                }

            }
        });
    }


    public void onBackPress(){
        if (currentLevel == PROVINCE_LEVEL) {
            queryProvinces();
        } else if (currentLevel == CITY_LEVEL) {
            queryCity();
            currentLevel=PROVINCE_LEVEL;
        }
    }
}
