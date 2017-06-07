package com.myframework.api;

import android.content.Context;
import android.content.DialogInterface;

import com.myframework.view.dialog.DialogLoadingUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 八神火焰 on 2017/6/6.
 * DialogLoading封装类
 * 网络请求时自动显示及隐藏等待提示框,同时支持返回键取消网络请求
 */

public class ProgressSubscriber<T>
{
    private Context       mContext;
    private Observable<T> mObservable;
    private Disposable    mDisposable;

    ProgressSubscriber(Context mContext, Observable<T> mObservable) {
        this.mContext = mContext;
        this.mObservable = mObservable;
    }

    Observable<T> transform() {
        return Observable.create(subscriber -> mObservable.subscribe(new Observer<T>()
        {
            @Override
            public void onSubscribe(Disposable disposable) {
                mDisposable = disposable;
                DialogLoadingUtils.show(mContext, CancelListener);
            }

            @Override
            public void onNext(T t) {
                subscriber.onNext(t);
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
                onComplete();
            }

            @Override
            public void onComplete() {
                DialogLoadingUtils.hide();
            }
        }));
    }

    /**
     * 返回键的监听
     */
    private DialogInterface.OnCancelListener CancelListener = new DialogInterface.OnCancelListener()
    {
        @Override
        public void onCancel(DialogInterface dialog) {
            mDisposable.dispose();
        }
    };
}