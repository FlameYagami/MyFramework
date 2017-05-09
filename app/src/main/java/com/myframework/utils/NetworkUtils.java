package com.myframework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;

import static com.myframework.config.MyApp.context;

public class NetworkUtils
{
    private static final String TAG = NetworkUtils.class.getSimpleName();

    /**
     * 检查网络状态
     *
     * @return true|false
     */
    public static boolean checkNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Network[] networkArray = connectivityManager.getAllNetworks();
            if (networkArray != null && networkArray.length > 0) {
                for (Network network : networkArray) {
                    Log.d(TAG, connectivityManager.getNetworkInfo(network).getTypeName() + "->" + connectivityManager.getNetworkInfo(network).getState());
                    if (connectivityManager.getNetworkInfo(network).getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            NetworkInfo[] networkInfoArray = connectivityManager.getAllNetworkInfo();
            if (networkInfoArray != null && networkInfoArray.length > 0) {
                for (NetworkInfo networkInfo : networkInfoArray) {
                    Log.d(TAG, networkInfo.getTypeName() + "->" + networkInfo.getState());
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
