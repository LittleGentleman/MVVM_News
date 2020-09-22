package com.gmm.www.news.newslist;

import com.gmm.www.base.activity.IBaseView;
import com.gmm.www.base.customview.BaseCustomViewModel;
import com.gmm.www.base.fragment.IBasePageView;
import com.gmm.www.base.model.BasePagingModel;
import com.gmm.www.base.model.SuperBaseModel;
import com.gmm.www.base.viewmodel.BaseViewModel;

import java.util.ArrayList;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public class NewsListViewModel extends BaseViewModel<NewsListViewModel.INewsView,NewsListModel> implements BasePagingModel.IModelListener<ArrayList<BaseCustomViewModel>> {
    public ArrayList<BaseCustomViewModel> mNewsList = new ArrayList<>();

    public NewsListViewModel(String channelId,String channelName) {
        model = new NewsListModel(channelId,channelName);
        model.register(this);
        model.getCachedDataAndLoad();
    }

    @Override
    public void onLoadFinish(BasePagingModel model, ArrayList<BaseCustomViewModel> data, boolean isEmpty, boolean isFirstPage) {
        if (getPageView() != null) {
            if (model instanceof NewsListModel) {
                if (isFirstPage)
                    mNewsList.clear();

                if (isEmpty) {
                    if (isFirstPage) {
                        getPageView().onRefreshEmpty();
                    } else {
                        getPageView().onLoadMoreEmpty();
                    }
                } else {
                    mNewsList.addAll(data);
                    getPageView().onNewsLoaded(mNewsList);
                }
            }
        }
    }

    @Override
    public void onLoadFail(BasePagingModel model, String prompt, boolean isFirstPage) {
        if (getPageView() != null) {
            if (isFirstPage) {
                getPageView().onRefreshFailure(prompt);
            } else {
                getPageView().onLoadMoreFailure(prompt);
            }
        }
    }

    public void tryToRefresh() {
        model.refresh();
    }

    public void tryToLoadNextPage() {
        model.loadNextPage();
    }

    public interface INewsView extends IBasePageView {
        void onNewsLoaded(ArrayList<BaseCustomViewModel> newsList);
    }
}
