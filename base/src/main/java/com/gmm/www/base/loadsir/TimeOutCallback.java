package com.gmm.www.base.loadsir;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.gmm.www.base.BaseApplication;
import com.gmm.www.base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public class TimeOutCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_timeout;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        Toast.makeText(BaseApplication.sApplication, "Connecting to the network again!", Toast.LENGTH_SHORT).show();
        return false;
    }
}
