package com.gmm.www.base.activity;

import android.os.Bundle;
import android.view.View;

import com.gmm.www.base.loadsir.EmptyCallback;
import com.gmm.www.base.loadsir.ErrorCallback;
import com.gmm.www.base.loadsir.LoadingCallback;
import com.gmm.www.base.viewmodel.BaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明: Activity基类
 */
public abstract class BaseActivity<V extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity implements IBaseView {

    protected VM viewModel;
    protected V viewDataBinding;
    private LoadService mLoadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    /**
     * 数据绑定
     */
    private void performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());

        if (null == viewModel) {
            viewModel = getViewModel();
        }

        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(),viewModel);
        }
        viewDataBinding.executePendingBindings();
    }

    public void setLoadSir(View view) {
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    @Override
    public void showContent() {
        if (null != mLoadService)
            mLoadService.showSuccess();
    }

    @Override
    public void showLoading() {
        if (null != mLoadService)
            mLoadService.showCallback(LoadingCallback.class);
    }

    @Override
    public void onRefreshEmpty() {
        if (null != mLoadService)
            mLoadService.showCallback(EmptyCallback.class);
    }

    @Override
    public void onRefreshFailure(String message) {
        if (null != mLoadService)
            mLoadService.showCallback(ErrorCallback.class);
    }

    public abstract @LayoutRes int getLayoutId();

    protected abstract VM getViewModel();

    public abstract int getBindingVariable();

    protected abstract void onRetryBtnClick();
}
