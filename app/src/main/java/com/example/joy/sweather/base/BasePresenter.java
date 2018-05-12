package com.example.joy.sweather.base;

/**
 * Created by joy on 2018/5/12.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.Constract.presenter.base
 * 简介
 */

/**
 * 绑定和解绑view
 * @param <T>
 */
public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }


    public void dettach() {
        this.mView = null;
    }
}
