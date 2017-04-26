package com.myframework.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.myframework.config.MyApp;
import com.myframework.utils.AppManager;
import com.myframework.utils.DisplayUtils;
import com.myframework.utils.RxBus;
import com.myframework.utils.StatusBarUtils;
import com.myframework.view.dialog.DialogLoading;
import com.myframework.view.widget.ToastView;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

public abstract class BaseActivity extends SwipeBackActivity
{
    protected SwipeBackLayout mSwipeBackLayout;

    protected void showToast(String message) {
        runOnUiThread(() -> ToastView.make(MyApp.context, message, Toast.LENGTH_SHORT).show());
    }

    protected void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void showDialog(String message) {
        DialogLoading.showDialog(this, message, false);
    }

    protected void hideDialog() {
        DialogLoading.hideDialog();
    }

    public abstract int getLayoutId();

    /**
     * 初始化控件以及装填数据
     */
    public abstract void initViewAndData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        StatusBarUtils.enableTranslucentStatusBar(this);
        setContentView(getLayoutId());
        initViewAndData();
        AppManager.getInstances().addActivity(this);
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            DisplayUtils.hideKeyboard(this);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        DialogLoading.hideDialog();
        mSwipeBackLayout.scrollToFinishActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe(this);
        AppManager.getInstances().finishActivity(this);
    }
}
