package com.dab.framework.utils.statusbar;

import android.app.Activity;
import android.os.Build;

public class StatusBarHelper
{

    private final StatusBarHelperApi mImpl;

    protected boolean mActivityRootLayoutFitSystemWindows = true;

    public StatusBarHelper(Activity activity) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            mImpl = new StatusBarHelperImpl19(activity);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImpl = new StatusBarHelperImpl21(activity);
        } else {
            mImpl = new StatusBarHelperImpl(activity);
        }
    }

    public void setColor(int ResId) {
        mImpl.setColor(ResId);
    }

    public void setActivityRootLayoutFitSystemWindows(boolean fitSystemWindows) {
        mImpl.setActivityRootLayoutFitSystemWindows(fitSystemWindows);
    }
}
