package com.gmm.www.network.errorhandler;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */

import com.gmm.www.network.bean.BaseResponse;

import io.reactivex.functions.Function;

/**
 * HandlerFuc处理以下网络错误:
 * 1.应用数据的错误会抛RuntimeException
 */
public class AppDataErrorHandler implements Function<BaseResponse,BaseResponse> {
    @Override
    public BaseResponse apply(BaseResponse response) throws Exception {
        //response中code不是0 出现错误
        if (response instanceof BaseResponse && response.code != 0)
            throw new RuntimeException(response.code + "" + (null != response.errorMsg?response.errorMsg:""));
        return response;
    }
}
