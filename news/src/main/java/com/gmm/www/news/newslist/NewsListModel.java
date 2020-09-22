package com.gmm.www.news.newslist;

import com.gmm.www.base.customview.BaseCustomViewModel;
import com.gmm.www.base.model.BasePagingModel;
import com.gmm.www.common.views.picturetitleview.PictureTitleViewModel;
import com.gmm.www.common.views.titleview.TitleViewModel;
import com.gmm.www.network.bean.NewsListBean;
import com.gmm.www.network.errorhandler.ExceptionHandle;
import com.gmm.www.network.observer.BaseObserver;
import com.gmm.www.news.api.NewsApi;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:
 */
public class NewsListModel<T> extends BasePagingModel<T> {
    private String channelId;
    private String channelName;
    private static final String PREF_KEY_NEWS_CHANNEL = "pref_key_news_";

    public NewsListModel(String channelId,String channelName) {
        this.channelId = channelId;
        this.channelName = channelName;
    }

    @Override
    protected String getCachedPreferenceKey() {
        return PREF_KEY_NEWS_CHANNEL;
    }

    @Override
    protected Type getTClass() {
        return new TypeToken<ArrayList<PictureTitleViewModel>>(){}.getType();
    }

    @Override
    public void refresh() {
        isRefresh = true;
        load();
    }

    public void loadNextPage() {
        isRefresh = false;
        load();
    }

    @Override
    protected void load() {
        NewsApi.getInstance().getNewsList(new BaseObserver<NewsListBean>(this) {
            @Override
            public void onError(ExceptionHandle.ResponseThrowable e) {
                e.printStackTrace();
                loadFail(e.message,isRefresh);//isRefresh true 就是下拉刷新，false就是上拉加载
            }

            @Override
            public void onNext(NewsListBean newsListBean) {
                pageNumber = isRefresh ? 2 : pageNumber+1;
                ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();

                for (NewsListBean.Contentlist source : newsListBean.showapiResBody.pagebean.contentlist) {
                    if (source.imageurls != null && source.imageurls.size() > 1) {
                        PictureTitleViewModel model = new PictureTitleViewModel();
                        model.avatarUrl = source.imageurls.get(0).url;
                        model.link = source.link;
                        model.title = source.title;
                        baseViewModels.add(model);
                    } else {
                        TitleViewModel model = new TitleViewModel();
                        model.link = source.link;
                        model.title = source.title;
                        baseViewModels.add(model);
                    }
                }

                loadSuccess((T) baseViewModels,baseViewModels.isEmpty(),isRefresh,baseViewModels.isEmpty());
            }
        },channelId,channelName,String.valueOf(isRefresh? 1 : pageNumber));
    }
}
