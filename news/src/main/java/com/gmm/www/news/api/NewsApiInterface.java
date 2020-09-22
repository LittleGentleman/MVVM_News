package com.gmm.www.news.api;

import com.gmm.www.network.bean.NewsChannelsBean;
import com.gmm.www.network.bean.NewsListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public interface NewsApiInterface {


    @GET("release/channel")
    Observable<NewsChannelsBean> getNewsChannels(
            @Header("Source") String source,
            @Header("Authorization") String authorization,
            @Header("Date") String date,
            @QueryMap Map<String,String> options);

    @GET("release/news")
    Observable<NewsListBean> getNewsList(
            @Header("Source") String source,
            @Header("Authorization") String authorization,
            @Header("Date") String date,
            @QueryMap Map<String,String> options
    );
}
