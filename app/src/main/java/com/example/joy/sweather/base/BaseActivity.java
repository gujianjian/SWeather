package com.example.joy.sweather.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by joy on 2018/5/12.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.base
 * 简介
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity  {

    public T presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter=initPresenter();
    }

    protected abstract void initView();

    protected abstract T initPresenter();


    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
    }
}
