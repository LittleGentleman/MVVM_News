package com.gmm.www.common.views.titleview;

import android.content.Context;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.View;

import com.gmm.www.base.customview.BaseCustomView;
import com.gmm.www.common.R;
import com.gmm.www.common.databinding.TitleViewBinding;
import com.gmm.www.common.webview.WebActivity;

import androidx.annotation.Nullable;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public class TitleView extends BaseCustomView<TitleViewBinding,TitleViewModel> {
    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int setViewLayoutId() {
        return R.layout.title_view;
    }

    @Override
    protected void setDataToView(TitleViewModel data) {
        getDataBinding().setViewModel(data);
    }

    @Override
    protected void onRootClick(View view) {
        WebActivity.startCommonWeb(view.getContext(),"",getModel().link);
    }
}
