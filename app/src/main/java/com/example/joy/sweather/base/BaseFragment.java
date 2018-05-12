package com.example.joy.sweather.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by joy on 2018/5/12.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.fragments
 * 简介
 */


/**
 * 初始化presenter 并绑定view
 * @param <V>
 * @param <T>
 */
public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment  {

    protected T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=initPresenter();
    }

    protected abstract T initPresenter();

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dettach();
    }
}
