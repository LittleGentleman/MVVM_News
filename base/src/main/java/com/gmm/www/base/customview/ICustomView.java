package com.gmm.www.base.customview;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public interface ICustomView<M extends BaseCustomViewModel> {
    void setData(M data);

    void setStyle(int resId);

    void setActionListener(ICustomViewActionListener listener);
}
