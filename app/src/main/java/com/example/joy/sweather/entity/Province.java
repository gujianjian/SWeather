package com.example.joy.sweather.entity;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 简介
 */

//  链接http://guolin.tech/api/china
//        {
//        "id": 1,
//        "name": "北京"
//        }

import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.L;
import com.example.joy.sweather.utils.NetUtil;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;

/**
 * 省
 */
public class Province extends DataSupport {

    private int provinceCode;//这个对应json的id
    private String provinceName;//对应name


    public int getProvinceCode() {
        return provinceCode;
    }


    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }



}
