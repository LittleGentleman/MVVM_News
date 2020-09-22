package com.gmm.www.base.activity;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public interface IBaseView {
    void showContent();

    void showLoading();

    void onRefreshEmpty();

    void onRefreshFailure(String message);
}
