package com.myframework.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.myframework.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 八神火焰 on 2017/1/12.
 */

public class MessageView extends LinearLayout
{
    @BindView(R.id.tv_key)
    AppCompatTextView tvKey;
    @BindView(R.id.tv_value)
    AppCompatTextView tvValue;

    public MessageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.widget_message, null);
        addView(view);
        ButterKnife.bind(this, view);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageView);
        String     keyText    = typedArray.getString(R.styleable.MessageView_title_text);
        String     valueText  = typedArray.getString(R.styleable.MessageView_message_text);
        typedArray.recycle();
        tvKey.setText(keyText);
        tvValue.setText(valueText);
        view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setValue(String message) {
        tvValue.setText(message);
    }
}
