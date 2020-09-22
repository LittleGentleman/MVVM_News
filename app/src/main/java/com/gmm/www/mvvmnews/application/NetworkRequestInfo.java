package com.gmm.www.mvvmnews.application;

import com.gmm.www.mvvmnews.BuildConfig;
import com.gmm.www.network.INetworkRequestInfo;

import java.util.HashMap;

/**
 * @author:gmm
 * @date:2020/4/28
 * @类说明:统一添加请求头信息
 */
public class NetworkRequestInfo implements INetworkRequestInfo {
    HashMap<String,String> headerMap = new HashMap<>();

    public NetworkRequestInfo() {
        headerMap.put("os","android");
        headerMap.put("versionName",BuildConfig.VERSION_NAME);
        headerMap.put("versionCode",String.valueOf(BuildConfig.VERSION_CODE));
        headerMap.put("applicationId",BuildConfig.APPLICATION_ID);
    }

    @Override
    public HashMap<String, String> getRequestHeaderMap() {
        return headerMap;
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
