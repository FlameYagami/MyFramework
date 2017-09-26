package com.dab.framework.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.dab.framework.R;
import com.dab.framework.ui.base.BaseActivity;
import com.dab.framework.ui.base.ScrimInsetsFrameLayout;
import com.dab.framework.utils.AppManager;
import com.dab.framework.view.widget.AppBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AppBarView.NavigationClickListener
{
    @BindView(R.id.viewAppBar)
    AppBarView             viewAppBar;
    @BindView(R.id.viewDrawer)
    DrawerLayout           viewDrawer;
    @BindView(R.id.viewContent)
    CoordinatorLayout      viewContent;
    @BindView(R.id.viewNav)
    NavigationView         navView;
    @BindView(R.id.scrimInsetsFrameLayout)
    ScrimInsetsFrameLayout mScrimInsetsFrameLayout;

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
        mScrimInsetsFrameLayout.setOnInsetsCallback(insets -> mScrimInsetsFrameLayout.setPadding(0, insets.top, 0, 0));
    }

    @Override
    public void onBackPressed() {
        if (viewDrawer.isDrawerOpen(GravityCompat.START)) {
            viewDrawer.closeDrawer(GravityCompat.START);
        } else {
            long lastTime = System.currentTimeMillis();
            long between  = lastTime - firstTime;
            if (between < 2000) {
                AppManager.AppExit(this);
            } else {
                firstTime = lastTime;
                showSnackBar(viewContent, "再按一次退出应用");
            }
        }
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
