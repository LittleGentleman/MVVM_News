package com.gmm.www.network.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author:gmm
 * @date:2020/4/27
 * @类说明:
 */
public class BaseResponse {
    @SerializedName("showapi_res_code")
    @Expose
    public Integer code;

    @SerializedName("showapi_res_error")
    @Expose
    public String errorMsg;
}
