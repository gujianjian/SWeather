package com.example.joy.sweather.utils;

import android.nfc.Tag;
import android.os.Debug;
import android.util.Log;

/**
 * Created by joy on 2018/5/11.
 * 项目名   SWeather
 * 类名   com.example.joy.sweather.utils
 * 简介   Log日志工具类
 */

public class L {

    private final static boolean DEBUG=true;

    private final static String TAG="SWeather";

    public static void d(String string){
       if(DEBUG){
           Log.d(TAG,string);
       }
    }

    public static void i(String string){
        if(DEBUG){
            Log.i(TAG,string);
        }
    }

    public static void e(String string){
        if(DEBUG){
            Log.e(TAG,string);
        }
    }
}
