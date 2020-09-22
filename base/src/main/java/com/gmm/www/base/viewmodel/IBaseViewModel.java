package com.gmm.www.base.viewmodel;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public interface IBaseViewModel<V> {

    void attachUI(V ui);

    V getPageView();

    boolean isUIAttached();

    void detachUI();
}
