package com.gmm.www.base.model;

import java.io.Serializable;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public class BaseCachedData<T> implements Serializable {
    public long updateTimeInMills;
    public T data;
}
