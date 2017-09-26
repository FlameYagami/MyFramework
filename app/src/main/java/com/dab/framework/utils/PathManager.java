package com.dab.framework.utils;

import android.content.Context;
import android.os.Environment;

import com.dab.framework.R;

import java.io.File;

import static com.dab.framework.config.MyApp.context;

/**
 * Created by 八神火焰 on 2017/3/6.
 */

public class PathManager
{
    public static String appDir;
    public static String downloadDir;

    public static void init(Context context) {
        appDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + context.getString(R.string.app_name) + File.separator;
        downloadDir = appDir + "download/";
    }

    private static String getDatabasePath() {
        if (SystemUtils.isInstallOnSDCard()) {
            return context.getFilesDir().getParent() + "/databases/data.db";
        } else {
            return "data/data/" + context.getPackageName() + "/databases/data.db";
        }
    }
}
