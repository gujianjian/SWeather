package com.example.joy.sweather.Constract.view;

import android.content.Context;

import java.util.List;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.Constract
 * 简介
 */

public interface IAeraView {

    //填充listview数据
    void setListItem(List<String> mList);
    void onFailure(String message);
    //设置标题
    void setTitle(String text);
    //显示加载对话框
    void showDialog();
    //隐藏加载对话框
    void hideDialog();

    void backButtonShow();
    void backButtonHide();

}
