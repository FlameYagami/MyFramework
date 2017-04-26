package com.myframework.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.myframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 八神火焰 on 2016/12/22.
 */

public class DialogEditText extends AlertDialog
{
    @BindView(R.id.editText)
    EditText txtContent;

    private OnButtonClick onButtonClick;

    public interface OnButtonClick
    {
        void getText(DialogEditText dialog, String content);
    }

    public DialogEditText(@NonNull Context context, String title, String content, String hint, OnButtonClick mOnButtonClick) {
        super(context);
        View view = View.inflate(context, R.layout.dialog_edittext, null);
        ButterKnife.bind(this, view);

        setView(view);
        setTitle(title);
        txtContent.setHint(hint);
        txtContent.setText(content);
        txtContent.setSelection(txtContent.getText().length());
        this.onButtonClick = mOnButtonClick;
    }

    @OnClick(R.id.tv_cancel)
    public void onCancel_Click() {
        dismiss();
    }

    @OnClick(R.id.tv_ok)
    public void onOk_Click() {
        String text = txtContent.getText().toString().trim();
        if (!TextUtils.isEmpty(text)) {
            this.onButtonClick.getText(this, text);
        }
    }
}
