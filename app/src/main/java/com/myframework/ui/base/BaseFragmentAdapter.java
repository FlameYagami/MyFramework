package com.myframework.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 八神火焰 on 2017/5/3.
 */

public class BaseFragmentAdapter extends FragmentStatePagerAdapter
{
    private BaseFragment  mBaseFragment[];
    private List<String>  mTitles;
    private List<Class>   mClass;

    public BaseFragmentAdapter(FragmentManager manager, List<String> mTitles, List<Class> mClass) {
        super(manager);
        this.mTitles = mTitles;
        this.mClass = mClass;
        mBaseFragment = new BaseFragment[3];
    }

    @Override
    public Fragment getItem(int position) {
        if (null == mBaseFragment[position]) {
            try {
                mBaseFragment[position] = (BaseFragment)Class.forName(mClass.get(position).getName()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mBaseFragment[position];
    }

    @Override
    public int getCount() {
        return mBaseFragment == null ? 0 : mBaseFragment.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
