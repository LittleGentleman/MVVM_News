package com.gmm.www.network.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:    @Expose 是Gson提供的，作用是当只部分属性参与序列化，只需要在需要序列化的
 * 属性上加入@Expose注解，@Expose 默认有两个属性：serialize 和 deserialize，默认值都为 true
 * 意思就是支持 序列化和反序列化
 */
public class NewsChannelsBean extends BaseResponse {
    @SerializedName("showapi_res_body")
    @Expose
    public ShowapiResBody showapiResBody;

    public class ShowapiResBody {
        @SerializedName("totalNum")
        @Expose
        public Integer totalNum;
        @SerializedName("ret_code")
        @Expose
        public Integer retCode;
        @SerializedName("channelList")
        @Expose
        public List<ChannelBean> channelList = null;
    }

    public class ChannelBean {
        @SerializedName("channelId")
        @Expose
        public String channelId;
        @SerializedName("name")
        @Expose
        public String name;
    }
}
