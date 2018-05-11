package com.example.joy.sweather.Constract.view;

import java.util.List;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.Constract
 * 简介
 */

public interface AeraView {

    //填充listview数据
    void setListItem(List<String> mList);
    void onFailure(String message);
    void setTitle(String text);
    void backButtonShow();
    void backButtonHide();

}
