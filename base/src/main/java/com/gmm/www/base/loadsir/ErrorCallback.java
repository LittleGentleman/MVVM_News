package com.gmm.www.base.loadsir;

import com.gmm.www.base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_error;
    }
}
