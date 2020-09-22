package com.gmm.www.base.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public abstract class BaseCustomView<V extends ViewDataBinding,M extends BaseCustomViewModel> extends LinearLayout implements ICustomView<M> , View.OnClickListener {
    private V dataBinding;
    private M model;
    private ICustomViewActionListener listener;

    public BaseCustomView(Context context) {
        super(context);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public View getRootView() {
        return dataBinding.getRoot();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataBinding = DataBindingUtil.inflate(inflater,setViewLayoutId(),this,false);
        dataBinding.getRoot().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAction(ICustomViewActionListener.ACTION_ROOT_VIEW_CLICKED,v,model);
                }
                onRootClick(v);
            }
        });
        this.addView(dataBinding.getRoot());
    }

    @Override
    public void setData(M data) {
        model = data;
        setDataToView(model);
        if (dataBinding != null) {
            dataBinding.executePendingBindings();
        }
        onDataUpdated();
    }

    protected abstract @LayoutRes int setViewLayoutId();

    protected abstract void setDataToView(M data);

    protected abstract void onRootClick(View view);

    protected void onDataUpdated() {

    }

    @Override
    public void setStyle(int resId) {

    }

    @Override
    public void setActionListener(ICustomViewActionListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {

    }

    protected V getDataBinding() {
        return dataBinding;
    }

    protected M getModel() {
        return model;
    }
}
