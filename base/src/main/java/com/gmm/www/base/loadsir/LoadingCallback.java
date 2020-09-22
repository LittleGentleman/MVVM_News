package com.gmm.www.base.loadsir;

import android.content.Context;
import android.view.View;

import com.gmm.www.base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
