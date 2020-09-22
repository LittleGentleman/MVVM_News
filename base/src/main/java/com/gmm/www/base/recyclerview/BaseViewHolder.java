package com.gmm.www.base.recyclerview;

import android.view.View;

import com.gmm.www.base.customview.BaseCustomViewModel;
import com.gmm.www.base.customview.ICustomView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    ICustomView view;
    public BaseViewHolder(ICustomView view) {
        super((View) view);
        this.view = view;
    }

    public void bind(BaseCustomViewModel model) {
        view.setData(model);
    }
}
