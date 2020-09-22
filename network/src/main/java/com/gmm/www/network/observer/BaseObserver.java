package com.gmm.www.network.observer;

import com.gmm.www.base.model.SuperBaseModel;
import com.gmm.www.network.errorhandler.ExceptionHandle;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public abstract class BaseObserver<T> implements Observer<T> {
    SuperBaseModel baseModel;

    public BaseObserver(SuperBaseModel baseModel) {
        this.baseModel = baseModel;
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ExceptionHandle.ResponseThrowable) {
            onError((ExceptionHandle.ResponseThrowable)e);
        } else {
            onError(new ExceptionHandle.ResponseThrowable(e,ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (null != baseModel) {
            baseModel.addDisposable(d);
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onError(ExceptionHandle.ResponseThrowable e);
}
