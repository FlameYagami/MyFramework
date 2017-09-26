package com.dab.framework.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;

public class DialogConfirm
{
    private static Handler handler;
    public         Context context;
    private        int     dialogResult;

    private DialogConfirm(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setPositiveButton("确定", new DialogButtonOnClick(1))
                .setNegativeButton("取消", new DialogButtonOnClick(0))
                .setOnCancelListener(new DialogCancelOnClick())
                .setTitle(title)
                .setMessage(message)
                .create().show();
        try {
            Looper.loop();
        } catch (Exception ignored) {
        }
    }

    public static boolean show(Context context, String message) {
        return show(context, "提示", message);
    }

    public static boolean show(Context context, String title, String message) {
        handler = new MyHandler();
        return new DialogConfirm(context, title, message).getResult() == 1;
    }

    private int getResult() {
        return dialogResult;
    }

    private static class MyHandler extends Handler
    {
        public void handleMessage(Message message) {
            throw new RuntimeException();
        }
    }

    /**
     * 确定、取消按钮的监听
     */
    private final class DialogButtonOnClick implements OnClickListener
    {
        int type;

        DialogButtonOnClick(int type) {
            this.type = type;
        }

        public void onClick(DialogInterface dialog, int which) {
            DialogConfirm.this.dialogResult = type;
            Message m = handler.obtainMessage();
            handler.sendMessage(m);
        }
    }

    /**
     * 返回键的监听
     */
    private final class DialogCancelOnClick implements DialogInterface.OnCancelListener
    {
        @Override
        public void onCancel(DialogInterface dialog) {
            DialogConfirm.this.dialogResult = 0;
            Message m = handler.obtainMessage();
            handler.sendMessage(m);
        }
    }
}
