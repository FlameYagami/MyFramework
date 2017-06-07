package com.myframework.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.myframework.R;
import com.myframework.api.RequestApi;
import com.myframework.bean.LoginHttpBean;
import com.myframework.ui.base.BaseActivity;
import com.myframework.utils.LogUtils;
import com.myframework.view.widget.AppBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AppBarView.NavigationClickListener
{
    @BindView(R.id.viewAppBar)
    AppBarView        viewAppBar;
    @BindView(R.id.view_drawer)
    DrawerLayout      viewDrawer;
    @BindView(R.id.view_content)
    CoordinatorLayout viewContent;
    @BindView(R.id.nav_view)
    NavigationView    navView;

    private static final String TAG       = MainActivity.class.getSimpleName();
    private              long   firstTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViewAndData() {
        ButterKnife.bind(this);
        // 主界面不可调用SwipeBack
        setSwipeBackEnable(false);

        viewAppBar.setNavigationClickListener(this);
        navView.setNavigationItemSelectedListener(this);
    }

    @OnClick(R.id.btn_test)
    public void onTest_Click(){
        disposable = RequestApi.onLogin(this, new LoginHttpBean("admin","admin"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginBean -> LogUtils.i(TAG, "Succeed:" + loginBean.getToken()), throwable -> LogUtils.e(TAG, "Failed" + throwable.getMessage()));
    }

    @Override
    public void onBackPressed() {
        if (disposable != null){
            disposable.dispose();
            disposable = null;
        }
//        if (viewDrawer.isDrawerOpen(GravityCompat.START)) {
//            viewDrawer.closeDrawer(GravityCompat.START);
//        } else {
//            long lastTime = System.currentTimeMillis();
//            long between  = lastTime - firstTime;
//            if (between < 2000) {
//                AppManager.getInstances().AppExit(this);
//            } else {
//                firstTime = lastTime;
//                showSnackBar(viewContent, "再按一次退出应用");
//            }
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

        }
        viewDrawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onNavigationClick() {
        viewDrawer.openDrawer(GravityCompat.START);
    }
}
