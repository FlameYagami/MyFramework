package com.dab.framework.ui.base;

import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by 八神火焰 on 2016/12/22.
 */

public interface BaseRecyclerViewListener
{
    interface ItemClickListener
    {
        void onClick(View view, List<?> data, int position);
    }

    interface ItemLongClickListener
    {
        void onClick(View view, List<?> data, int position);
    }

    interface ItemTouchListener
    {
        void OnTouch(View view, MotionEvent motionEvent, List<?> data, int position);
    }
}
