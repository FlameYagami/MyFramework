package com.dab.framework.utils.statusbar;

import android.app.Activity;

class StatusBarHelperImpl extends StatusBarHelperApi
{
    public StatusBarHelperImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    protected void setColor(int color) {

    }

    @Override
    protected void setStatusBarTranslucent() {

    }

    @Override
    protected void setActivityRootLayoutFitSystemWindows(boolean fitSystemWindows) {
        mActivityRootLayoutFitSystemWindows = fitSystemWindows;
    }
}
