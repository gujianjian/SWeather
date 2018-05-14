package com.example.joy.sweather.Constract.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.joy.sweather.base.BasePresenter;
import com.example.joy.sweather.Constract.view.IAeraView;
import com.example.joy.sweather.entity.City;
import com.example.joy.sweather.entity.County;
import com.example.joy.sweather.entity.Province;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.Common;
import com.example.joy.sweather.utils.NetUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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

public class AeraPresenter extends BasePresenter<IAeraView> {

    private Handler mHandler;

    //listview中的list数据
    private List<String> dataList=new ArrayList<>();

    //当前选择的级别
    private int currentLevel=0;
    //当前为省
    private final int PROVINCE_LEVEL=0;
    //当前为市
    private final int CITY_LEVEL=1;
    //当前为县
    private final int COUNTY_LEVEL=2;

    //省的数据
    private List<Province> all_province;
    //市的数据
    private List<City> all_cities;

    private Province selectedProvince;

    private City selectedCity;
    private ProgressDialog progressDialog;
    private List<County> all_counties;


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

        }else if (currentLevel==CITY_LEVEL){
            selectedCity = all_cities.get(position);
            queryCounty();
            currentLevel=COUNTY_LEVEL;
        } else if (currentLevel == COUNTY_LEVEL) {
            County county = all_counties.get(position);
            String weatherId = county.getWeatherId();
            mView.invokeWeatherInfo(weatherId);
        }
    }


    /**
     * 查询数据，先从数据库获取，没有则从网络获取然后存取到数据库再从数据库获取
     */
    private void queryProvinces() {
        mView.backButtonHide();
        mView.setTitle("中国");
        all_province = DataSupport.findAll(Province.class);
        if (all_province.size() > 0) {
            dataList.clear();
            for (Province p : all_province) {
                dataList.add(p.getProvinceName());
            }
            currentLevel=PROVINCE_LEVEL;

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mView.setListItem(dataList);
                }
            });

        }else{
            String address=Canstants.PROVINCE_ADDRESS;
            queryFormServer(address,"province");
        }
    }




    /**
     * 查询数据，先从数据库获取，没有则从网络获取然后存取到数据库再从数据库获取
     */

    private void queryCity() {
        mView.backButtonShow();
        mView.setTitle(selectedProvince.getProvinceName());
        all_cities = DataSupport.where("provinceCode=?", String.valueOf(selectedProvince.getProvinceCode())).find(City.class);
        if(all_cities.size()>0){
            dataList.clear();
            for (City city : all_cities) {
                dataList.add(city.getCityName());
            }
            currentLevel=CITY_LEVEL;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mView.setListItem(dataList);
                }
            });


        }else{
            String address = String.format(Locale.CHINA,Canstants.CITY_ADDRESS, selectedProvince.getProvinceCode());
            queryFormServer(address,"city");
        }

    }

    private void queryCounty() {
        mView.setTitle(selectedCity.getCityName());
        mView.backButtonShow();
        all_counties = DataSupport.where("cityCode = ?", String.valueOf(selectedCity.getCityCode())).find(County.class);
        if (all_counties.size() > 0) {
            dataList.clear();
            for (County county : all_counties) {
                dataList.add(county.getCityName());
            }
            currentLevel=COUNTY_LEVEL;
            mView.setListItem(dataList);

        }else{
            String address = String.format(Locale.CHINA, Canstants.COUNTY_ADDRESS, selectedProvince.getProvinceCode(), selectedCity.getCityCode());
            queryFormServer(address,"county");
        }
    }



    /**
     * 从网络获取然后存取到数据库再从数据库获取
     */

    private void queryFormServer(String address, final String type) {
        mView.showDialog();
        NetUtil.sendOkhttpClient(address, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                mView.onFailure(e.getMessage());
                hideProgressDialog();
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
                    } else if (type.equals("county")) {
                        result = Common.handleCountyJson(s, selectedCity.getCityCode());
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
                            } else if (type.equals("county")) {
                                queryCounty();
                            }
                            mView.hideDialog();

                        }
                    });
                }

            }
        });

    }


    //点击返回箭头时的逻辑判断
    public void onBackPress(){
      if (currentLevel == CITY_LEVEL) {
            queryProvinces();
            currentLevel=PROVINCE_LEVEL;
        } else if (currentLevel == COUNTY_LEVEL) {
            queryCity();
            currentLevel=CITY_LEVEL;
        }
    }

    /**
     * 显示进度框
     */
    public void showProgressDialog(Context mContext) {
        if(progressDialog==null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("正在加载中..");

        }
        progressDialog.show();
    }

    /**
     * 隐藏进度框
     */
    public void hideProgressDialog(){
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
