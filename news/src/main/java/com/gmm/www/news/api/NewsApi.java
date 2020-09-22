package com.gmm.www.news.api;

import com.gmm.www.network.ApiBase;
import com.gmm.www.network.bean.NewsChannelsBean;
import com.gmm.www.network.bean.NewsListBean;
import com.gmm.www.network.utils.TecentUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public class NewsApi extends ApiBase {
    //volatile 易变的
    private static volatile NewsApi instance = null;
    private NewsApiInterface newsApiInterface;
    public static final String PAGE = "page";

    private NewsApi() {
        super("https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/");
        newsApiInterface = retrofit.create(NewsApiInterface.class);//内部是通过动态代理，生成 实现了NewsApiInterface的对象
    }

    public static NewsApi getInstance() {
        if (null == instance) {
            synchronized (NewsApi.class) {
                if (null == instance) {
                    instance = new NewsApi();
                }
            }
        }
        return instance;
    }

    /**
     * 获取新闻栏目
     * @param observer 由调用者传过来的观察者对象
     */
    public void getNewsChannel(Observer<NewsChannelsBean> observer) {
        Map<String,String> requestMap = new HashMap<>();
        String timeStr = TecentUtil.getTimeStr();
        ApiSubscribe(newsApiInterface.getNewsChannels("source",TecentUtil.getAuthorization(timeStr),timeStr,requestMap),observer);
    }

    public void getNewsList(Observer<NewsListBean> observer,String channelId,String channelName,String page) {
        Map<String,String> requestMap = new HashMap<>();
        String timeStr = TecentUtil.getTimeStr();
        requestMap.put("channelId",channelId);
        requestMap.put("channelName",channelName);
        requestMap.put(PAGE,page);
        ApiSubscribe(newsApiInterface.getNewsList("source",TecentUtil.getAuthorization(timeStr),timeStr,requestMap),observer);
    }
}
