package com.dab.framework.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dab.framework.config.MyApp;
import com.dab.framework.view.dialog.DialogLoadingUtils;
import com.dab.framework.view.widget.ToastView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 八神火焰 on 2017/5/3.
 */

public abstract class BaseFragment extends Fragment
{
    // ButterKnife解绑
    protected Unbinder unbinder;

    // 是否初始化控件
    protected boolean isInitView;

    // 是否初始化数据
    protected boolean isInitData;

    //当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
    private boolean isViewVisible;

    protected void showToast(String message) {
        getActivity().runOnUiThread(() -> ToastView.make(MyApp.context, message, Toast.LENGTH_SHORT).show());
    }

    protected void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    protected void showDialog() {
        DialogLoadingUtils.show(getActivity());
    }

    protected void hideDialog() {
        DialogLoadingUtils.hide();
    }

    public abstract int getLayoutId();

    public abstract void initView(View view);

    public abstract void initData(boolean isViewVisible);

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        isInitView = true;
        // 视图：可见 没有加载过数据
        if (isViewVisible) {
            initData(true);
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isViewVisible = true;
        }
        if (!isInitView) {
            return;
        }
        // 视图：不可见->可见 没有加载过数据
        if (isViewVisible) {
            initData(true);
            return;
        }
        // 视图：可见—>不可见 已经加载过数据
        initData(false);
        isViewVisible = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isInitView = false;
    }
}
