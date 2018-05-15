package com.example.joy.sweather.Constract.presenter;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.joy.sweather.Constract.view.ISettingView;
import com.example.joy.sweather.application.BaseApplication;
import com.example.joy.sweather.base.BasePresenter;
import com.example.joy.sweather.utils.Canstants;
import com.example.joy.sweather.utils.SpUtils;

/**
 * Created by joy on 2018/5/15.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.Constract.presenter
 * 简介
 */

public class SettingPresenter extends BasePresenter<ISettingView> {


    public void resume() {
        //判断是否开启自动更新

        boolean is_update = SpUtils.getInstance().getBoolean(Canstants.IS_AUTO_UPDATE, false);
        mView.checkUpdateSetting(is_update);


        PackageManager packageManager = BaseApplication.getInstance().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            String versionName = packageInfo.versionName;
            mView.checkVersion(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
