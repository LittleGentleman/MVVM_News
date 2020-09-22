package com.gmm.www.base.viewmodel;


import com.gmm.www.base.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public class BaseViewModel<V,M extends SuperBaseModel> extends ViewModel implements IBaseViewModel<V> {

    private Reference<V> mUIRef;
    protected M model;

    public void attachUI(V ui) {
        mUIRef = new WeakReference<>(ui);
    }

    @Override
    public V getPageView() {
        if (null != mUIRef) {
            return mUIRef.get();
        }
        return null;
    }

    @Override
    public boolean isUIAttached() {
        return mUIRef != null && mUIRef.get() != null;
    }

    @Override
    public void detachUI() {
        if (null != mUIRef) {
            mUIRef.clear();
            mUIRef = null;
        }
        if (null != model) {
            model.cancel();
        }
    }
}
