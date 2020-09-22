package com.gmm.www.news.newslist;

import android.view.ViewGroup;

import com.gmm.www.base.customview.BaseCustomViewModel;
import com.gmm.www.base.recyclerview.BaseViewHolder;
import com.gmm.www.common.views.picturetitleview.PictureTitleView;
import com.gmm.www.common.views.picturetitleview.PictureTitleViewModel;
import com.gmm.www.common.views.titleview.TitleView;
import com.gmm.www.common.views.titleview.TitleViewModel;
import com.gmm.www.news.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public class NewsListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private ArrayList<BaseCustomViewModel> items;

    private final int VIEW_TYPE_PICTURE_TITLE = 1;
    private final int VIEW_TYPE_TITLE = 2;

    public void setData(ArrayList<BaseCustomViewModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PICTURE_TITLE) {
            PictureTitleView pictureTitleView = new PictureTitleView(parent.getContext());
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            pictureTitleView.setLayoutParams(params);
            return new BaseViewHolder(pictureTitleView);
        } else if (viewType == VIEW_TYPE_TITLE) {
            TitleView titleView = new TitleView(parent.getContext());
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            titleView.setLayoutParams(params);
            return new BaseViewHolder(titleView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items != null && !items.isEmpty()) {
            return items.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof PictureTitleViewModel) {
            return VIEW_TYPE_PICTURE_TITLE;
        } else if (items.get(position) instanceof TitleViewModel){
            return VIEW_TYPE_TITLE;
        }
        return -1;
    }
}
