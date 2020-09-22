package com.gmm.www.common.views.picturetitleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.gmm.www.base.customview.BaseCustomView;
import com.gmm.www.common.R;
import com.gmm.www.common.databinding.PictureTitleViewBinding;
import com.gmm.www.common.webview.WebActivity;

import androidx.annotation.Nullable;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public class PictureTitleView extends BaseCustomView<PictureTitleViewBinding,PictureTitleViewModel> {
    public PictureTitleView(Context context) {
        super(context);
    }

    public PictureTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PictureTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PictureTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int setViewLayoutId() {
        return R.layout.picture_title_view;
    }

    @Override
    protected void setDataToView(PictureTitleViewModel data) {
        getDataBinding().setViewModel(data);
    }


    @Override
    protected void onRootClick(View view) {
        WebActivity.startCommonWeb(view.getContext(),"",getModel().link);
    }
}
