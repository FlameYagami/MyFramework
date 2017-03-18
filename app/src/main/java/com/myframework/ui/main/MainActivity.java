package com.myframework.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ImageView;

import com.myframework.R;
import com.myframework.ui.base.BaseActivity;
import com.myframework.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @BindView(R.id.img_icon)
    ImageView         imgIcon;
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

        imgIcon.setOnClickListener(view -> viewDrawer.openDrawer(GravityCompat.START));
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (viewDrawer.isDrawerOpen(GravityCompat.START)) {
            viewDrawer.closeDrawer(GravityCompat.START);
        } else {
            long lastTime = System.currentTimeMillis();
            long between  = lastTime - firstTime;
            if (between < 2000) {
                AppManager.getInstances().AppExit(this);
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
}
