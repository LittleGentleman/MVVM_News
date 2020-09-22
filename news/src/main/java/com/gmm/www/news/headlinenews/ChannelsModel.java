package com.gmm.www.news.headlinenews;

import com.gmm.www.base.model.BaseModel;
import com.gmm.www.base.model.SuperBaseModel;
import com.gmm.www.network.bean.NewsChannelsBean;
import com.gmm.www.network.errorhandler.ExceptionHandle;
import com.gmm.www.network.observer.BaseObserver;
import com.gmm.www.news.api.NewsApi;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public class ChannelsModel extends BaseModel<ArrayList<ChannelsModel.Channel>> {
    private static final String PREF_KEY_HOME_CHANNEL = "pref_key_home_channel";
    //apk级别缓存
    public static final String PREDEFINED_CHANNELS = "[\n" +
            "    {\n" +
            "        \"channelId\": \"5572a108b3cdc86cf39001cd\",\n" +
            "        \"channelName\": \"国内焦点\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"channelId\": \"5572a108b3cdc86cf39001ce\",\n" +
            "        \"channelName\": \"国际焦点\"\n" +
            "    }\n" +
            "]";

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        NewsApi.getInstance().getNewsChannel(new BaseObserver<NewsChannelsBean>(this) {
            @Override
            public void onNext(NewsChannelsBean newsChannelsBean) {
                ArrayList<Channel> channels = new ArrayList<>();
                for (NewsChannelsBean.ChannelBean source : newsChannelsBean.showapiResBody.channelList) {
                    Channel channel = new Channel();
                    channel.channelId = source.channelId;
                    channel.channelName = source.name;
                    channels.add(channel);
                }
                loadSuccess(channels);
            }

            @Override
            public void onError(ExceptionHandle.ResponseThrowable e) {
                e.printStackTrace();
                loadFail(e.message);
            }
        });
    }

    @Override
    protected String getCachedPreferenceKey() {
        return PREF_KEY_HOME_CHANNEL;
    }

    @Override
    protected String getApkString() {
        return PREDEFINED_CHANNELS;
    }

    @Override
    protected Type getTClass() {
        return new TypeToken<ArrayList<Channel>>() {}.getType();
    }

    public class Channel {
        public String channelId;
        public String channelName;
    }
}
