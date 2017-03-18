package com.myframework.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.myframework.R;

/**
 * Created by 八神火焰 on 2016/12/22.
 */

public class DialogEditText extends AlertDialog
{
    private EditText txtContent;

    private OnButtonClick onButtonClick;

    public interface OnButtonClick
    {
        void getText(DialogEditText dialog, String content);
    }

    public DialogEditText(@NonNull Context context, String title, String content, String hint, OnButtonClick mOnButtonClick) {
        super(context);
        View view = View.inflate(context, R.layout.dialog_edittext, null);
        txtContent = (EditText)view.findViewById(R.id.editText);
        TextView tvCancel = (TextView)view.findViewById(R.id.tv_cancel);
        TextView tvOk     = (TextView)view.findViewById(R.id.tv_ok);

        setView(view);
        setTitle(title);
        txtContent.setHint(hint);
        txtContent.setText(content);
        txtContent.setSelection(txtContent.getText().length());
        this.onButtonClick = mOnButtonClick;
        tvCancel.setOnClickListener(v -> dismiss());
        tvOk.setOnClickListener(v -> {
            String text = txtContent.getText().toString().trim();
            if (!TextUtils.isEmpty(text)) {
                this.onButtonClick.getText(this, text);
            }
        });
    }
}
