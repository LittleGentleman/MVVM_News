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
public class CustomCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        Toast.makeText(BaseApplication.sApplication, "Hello buddy, how r u! :p", Toast.LENGTH_SHORT).show();
        view.findViewById(R.id.iv_gift).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseApplication.sApplication, "It's your gift! :p", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
}


