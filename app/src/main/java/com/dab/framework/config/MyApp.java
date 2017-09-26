package com.dab.framework.config;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.dab.framework.utils.PathManager;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by 八神火焰 on 2016/12/10.
 */

public class MyApp extends Application
{
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        PathManager.init(context);
        FileDownloader.init(context);
    }
}
