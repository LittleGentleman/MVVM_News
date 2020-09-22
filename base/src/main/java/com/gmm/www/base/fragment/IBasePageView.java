package com.gmm.www.base.fragment;

import com.gmm.www.base.activity.IBaseView;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public interface IBasePageView extends IBaseView {

    void onLoadMoreFailure(String message);

    void onLoadMoreEmpty();
}
