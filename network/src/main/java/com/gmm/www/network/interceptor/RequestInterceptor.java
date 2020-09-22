package com.gmm.www.network.interceptor;

import android.text.TextUtils;

import com.gmm.www.network.INetworkRequestInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public class RequestInterceptor implements Interceptor {
    private INetworkRequestInfo mNetworkRequestInfo;
    public RequestInterceptor(INetworkRequestInfo networkRequestInfo) {
        this.mNetworkRequestInfo = networkRequestInfo;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        if (null != mNetworkRequestInfo) {
            for (String key : mNetworkRequestInfo.getRequestHeaderMap().keySet()) {
                if (!TextUtils.isEmpty(mNetworkRequestInfo.getRequestHeaderMap().get(key)))
                    builder.addHeader(key,mNetworkRequestInfo.getRequestHeaderMap().get(key));
            }
        }
        //接入
        return chain.proceed(builder.build());
    }
}
