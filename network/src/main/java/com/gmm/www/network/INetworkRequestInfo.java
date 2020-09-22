package com.gmm.www.network;

import java.util.HashMap;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明: 请求头信息
 */
public interface INetworkRequestInfo {
    HashMap<String,String> getRequestHeaderMap();

    boolean isDebug();
}
