package com.gmm.www.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmm.www.base.BaseApplication;
import com.gmm.www.base.R;
import com.gmm.www.base.loadsir.EmptyCallback;
import com.gmm.www.base.loadsir.ErrorCallback;
import com.gmm.www.base.loadsir.LoadingCallback;
import com.gmm.www.base.utils.Logger;
import com.gmm.www.base.utils.ToastUtil;
import com.gmm.www.base.viewmodel.BaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements IBasePageView {

    protected V viewDataBinding;
    protected VM viewModel;
    private LoadService mLoadService;
    private boolean isShowContent = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParameters();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        if (null != viewModel) {
            viewModel.attachUI(this);
        }
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModel);
            viewDataBinding.executePendingBindings();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d(getFragmentTag(), this + ": " + "onActivityCreated");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Logger.d(getFragmentTag(), this + ": " + "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(getFragmentTag(), this + ": " + "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(getFragmentTag(), this + ": " + "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(getFragmentTag(), this + ": " + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(getFragmentTag(), this + ": " + "onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.d(getFragmentTag(), this + ": " + "onDetach");
        if (null != viewModel && viewModel.isUIAttached())
            viewModel.detachUI();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(getFragmentTag(), this + ": " + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(getFragmentTag(), this + ": " + "onDestroy");
    }

    @Override
    public void showContent() {
        if (null != mLoadService) {
            isShowContent = true;
            mLoadService.showSuccess();
        }
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
        if (null != mLoadService) {
            if (!isShowContent) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                ToastUtil.show(BaseApplication.sApplication,message);
            }
        }
    }

    @Override
    public void onLoadMoreEmpty() {
        ToastUtil.show(BaseApplication.sApplication,getString(R.string.no_more_data));
    }

    @Override
    public void onLoadMoreFailure(String message) {
        ToastUtil.show(BaseApplication.sApplication,message);
    }

    public void setLoadSir(View view) {
        // You can change the callback on sub thread directly.
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract VM getViewModel();

    protected abstract int getBindingVariable();

    protected abstract void onRetryBtnClick();

    protected abstract String getFragmentTag();

    /**
     * 初始化参数
     */
    protected void initParameters(){}

}
