package com.gmm.www.network.errorhandler;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */

/**
 * HttpResponseFunc处理以下两类网络错误：
 * 1、http请求相关的错误，例如：404，403，socket timeout等等；
 * 2、应用数据的错误会抛RuntimeException，最后也会走到这个函数来统一处理；
 */
public class HttpErrorHandler<T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}
