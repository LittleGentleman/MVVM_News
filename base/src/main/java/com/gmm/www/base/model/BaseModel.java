package com.gmm.www.base.model;

import com.gmm.www.base.utils.Logger;

import java.lang.ref.WeakReference;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:  不涉及分页的网络请求
 */
public abstract class BaseModel<T> extends SuperBaseModel<T> {

    @Override
    protected void notifyCachedData(T data) {
        loadSuccess(data);
    }

    /**
     * 加载网络数据成功
     * 通知所有的注册者加载结果
     * @param data
     */
    protected void loadSuccess(T data) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                    if (weakListener.get() instanceof IModelListener) {
                        IModelListener listener = (IModelListener) weakListener.get();
                        if (null != listener) {
                            listener.onLoadFinish(BaseModel.this,data);
                        }
                    }
                }
                //如果我们需要缓存数据，加载成功，让我们保存它到preference
                if (null != getCachedPreferenceKey())
                    saveDataToPreference(data);
            },0);
        }
    }

    /**
     * 加载网络失败
     * 通知所有的注册者加载结果
     * @param prompt
     */
    protected void loadFail(String prompt) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                    if (weakListener.get() instanceof IModelListener) {
                        IModelListener listener = (IModelListener) weakListener.get();
                        if (null != listener)
                            listener.onLoadFail(BaseModel.this,prompt);
                    }
                }
            },0);
        }
    }

    public interface IModelListener<T> extends IBaseModelListener {
        void onLoadFinish(BaseModel model,T data);
        void onLoadFail(BaseModel model,String prompt);
    }
}
