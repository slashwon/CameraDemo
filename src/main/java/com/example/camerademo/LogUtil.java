package com.example.camerademo;

import android.util.Log;

/**
 * Created by Administrator on 2016/8/16.
 */
public class LogUtil {
    private static final boolean LOG_ON = true ;

    public static void e(String msg,Class t){
        if(LOG_ON){
            Log.e(t.getSimpleName(),msg);
        }
    }
}
