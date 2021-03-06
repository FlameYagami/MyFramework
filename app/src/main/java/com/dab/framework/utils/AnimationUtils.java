package com.dab.framework.utils;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by 八神火焰 on 2017/5/26.
 */

public class AnimationUtils
{
    // 默认动画时间:250毫秒
    private static int DEFAULT_DURATION = 250;

    /**
     * 设置动画时间
     *
     * @param duration 动画时间(毫秒)
     */
    public static void setDuration(int duration) {
        DEFAULT_DURATION = duration;
    }

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return TranslateAnimation
     */
    public static TranslateAnimation moveOutBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(DEFAULT_DURATION);
        return mHiddenAction;
    }

    /**
     * 从控件所在位置移动到控件的顶部
     *
     * @return TranslateAnimation
     */
    public static TranslateAnimation moveOutTop() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(DEFAULT_DURATION);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return TranslateAnimation
     */
    public static TranslateAnimation moveInBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(DEFAULT_DURATION);
        return mHiddenAction;
    }


    /**
     * 从控件的顶部移动到控件所在位置
     *
     * @return TranslateAnimation
     */
    public static TranslateAnimation moveInTop() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(DEFAULT_DURATION);
        return mHiddenAction;
    }
}