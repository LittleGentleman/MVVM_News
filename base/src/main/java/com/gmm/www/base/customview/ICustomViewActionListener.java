package com.gmm.www.base.customview;

import android.view.View;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public interface ICustomViewActionListener {
    String ACTION_ROOT_VIEW_CLICKED = "action_root_view_clicked";

    /**
     *
     * @param action 行为
     * @param view  目标控件
     * @param model
     */
    void onAction(String action, View view,BaseCustomViewModel model);
}
