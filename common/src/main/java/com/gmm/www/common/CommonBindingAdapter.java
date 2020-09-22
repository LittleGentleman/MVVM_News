package com.gmm.www.common;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * @author:gmm
 * @date:2020/4/29
 * @类说明:
 */
public class CommonBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view,String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(url)
                    .transition(withCrossFade())
                    .into(view);
        }
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view,Boolean value) {
        view.setVisibility(value? View.VISIBLE : View.GONE);
    }
}
