package com.myframework.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.myframework.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 八神火焰 on 2016/12/1.
 */

public class DialogLoading extends AlertDialog
{
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindString(R.string.please_waiting)
    String   pleaseWaiting;

    protected DialogLoading(Context context) {
        super(context);
        initView(context, pleaseWaiting, null);
    }

    protected DialogLoading(Context context, DialogInterface.OnCancelListener onCancelListener) {
        super(context);
        initView(context, pleaseWaiting, onCancelListener);
    }

    protected DialogLoading(Context context, CharSequence message) {
        super(context);
        initView(context, message, null);
    }

    protected DialogLoading(Context context, CharSequence message, DialogInterface.OnCancelListener onCancelListener) {
        super(context);
        initView(context, message, onCancelListener);
    }

    private void initView(Context context, CharSequence message, DialogInterface.OnCancelListener onCancelListener) {
        View view = View.inflate(context, R.layout.dialog_loading, null);
        ButterKnife.bind(this, view);
        setView(view);
        tvMessage.setText(message);
        if (onCancelListener != null) {
            setCancelable(true);
            setOnCancelListener(onCancelListener);
        } else {
            setCancelable(false);
            setOnCancelListener(null);
        }
    }
}
