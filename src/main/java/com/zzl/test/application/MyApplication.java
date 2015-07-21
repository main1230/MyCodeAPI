package com.zzl.test.application;

import android.app.Application;

/**
 * Description:
 * Created by zhangzl
 * Date: 15/7/21
 */
public class MyApplication extends Application {
    /**应用程序instance*/
    private static MyApplication mInstance = null;
    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }
}
