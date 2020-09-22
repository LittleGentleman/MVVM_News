package com.gmm.www.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author:gmm
 * @date:2020/4/26
 * @类说明:
 */
public class ToastUtil {
    private static Toast mToast;

    public static void show(Context context,String msg) {
        if (null != context && !TextUtils.isEmpty(msg)) {
            if (null != mToast) {
                mToast.cancel();
            }
            mToast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
            mToast.setText(msg);
            mToast.show();
        }

    }
}
