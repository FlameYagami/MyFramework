package com.myframework.utils;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

import static com.myframework.config.MyApp.context;


/**
 * Created by 八神火焰 on 2016/12/29.
 */
public class SystemUtils
{
    private static final String TAG = SystemUtils.class.getSimpleName();

    /**
     * 获取当前系统版本号
     */
    public static int getSystemVersionCode() {
        int versionCode = 100;
        // 获取PackageManager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return versionCode;
        }
        return versionCode;
    }

    /**
     * 获取当前系统版本名称
     */
    public static String getVersionName() {
        String versionName;
        // 获取PackageManager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            versionName = "1.0.0";
        }
        return versionName;
    }

    /**
     * 判断版本号
     *
     * @param versionCode 服务器文件的版本号
     * @return 比对结果
     */
    private static boolean checkVersionCode(int versionCode) {
        int systemVersionCode = getSystemVersionCode();
        return versionCode > systemVersionCode;
    }

    /**
     * 安装apk
     *
     * @param path apk的文件地址
     */
    public static void installApk(String path) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
