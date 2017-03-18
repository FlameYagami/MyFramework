package com.myframework.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myframework.R;


/**
 * Created by 八神火焰 on 2017/3/15.
 */

public class SwitchView extends LinearLayout
{
    SwitchCompat switchCompat;
    TextView     textView;

    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.widget_switch, null);
        addView(view);
        switchCompat = (SwitchCompat)view.findViewById(R.id.switch_compat);
        textView = (TextView)view.findViewById(R.id.title);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchView);
        String     titleText  = typedArray.getString(R.styleable.SwitchView_title_text);
        boolean    switchOpen = typedArray.getBoolean(R.styleable.SwitchView_switch_open, true);
        textView.setText(titleText);
        typedArray.recycle();
    }
}
