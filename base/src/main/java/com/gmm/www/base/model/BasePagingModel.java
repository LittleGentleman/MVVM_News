package com.gmm.www.base.model;

import java.lang.ref.WeakReference;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明: 处理分页的网络请求
 */
public abstract class BasePagingModel<T> extends SuperBaseModel<T> {
   protected boolean isRefresh = true;
   protected int pageNumber = 0;

    @Override
    protected void notifyCachedData(T data) {
        loadSuccess(data,false,true,true);
    }

    protected void loadSuccess(T data,boolean isEmpty,boolean isFirstPage,boolean hasNextPage) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                    if (weakListener.get() instanceof IModelListener){
                        IModelListener listener = (IModelListener) weakListener.get();
                        if (null != listener)
                            listener.onLoadFinish(BasePagingModel.this,data,isEmpty,isFirstPage);

                        if (isNeedToUpdate())
                            saveDataToPreference(data);
                    }
                }
            },0);

        }
    }

    protected void loadFail(String prompt,boolean isFirstPage) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakListenerArrayList) {
                    if (weakListener.get() instanceof IModelListener) {
                        IModelListener listener = (IModelListener) weakListener.get();
                        if (null != listener)
                            listener.onLoadFail(BasePagingModel.this,prompt,isFirstPage);
                    }
                }
            },0);

        }
    }

    public interface IModelListener<T> extends IBaseModelListener {
        void onLoadFinish(BasePagingModel model,T data,boolean isEmpty,boolean isFirstPage);
        void onLoadFail(BasePagingModel model,String prompt,boolean isFirstPage);
   }
}
