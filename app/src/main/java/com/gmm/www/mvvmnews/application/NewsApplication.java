package com.gmm.www.mvvmnews.application;

import com.billy.cc.core.component.CC;
import com.gmm.www.base.BaseApplication;
import com.gmm.www.base.loadsir.CustomCallback;
import com.gmm.www.base.loadsir.EmptyCallback;
import com.gmm.www.base.loadsir.ErrorCallback;
import com.gmm.www.base.loadsir.LoadingCallback;
import com.gmm.www.base.loadsir.TimeOutCallback;
import com.gmm.www.base.preference.PreferencesUtil;
import com.gmm.www.base.utils.GsonUtils;
import com.gmm.www.base.utils.Logger;
import com.gmm.www.mvvmnews.BuildConfig;
import com.gmm.www.network.ApiBase;
import com.kingja.loadsir.core.LoadSir;

import java.util.concurrent.Executors;

/**
 * @author:gmm
 * @date:2020/4/25
 * @类说明:
 */
public class NewsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        setDebug(BuildConfig.DEBUG);

        PreferencesUtil.init(this);

        ApiBase.setNetworkRequestInfo(new NetworkRequestInfo());

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeOutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();

        if (isMainProcess()) {
            CC.enableDebug(BuildConfig.DEBUG); //开启debug模式 默认是false: 关闭状态
            CC.enableVerboseLog(BuildConfig.DEBUG);	//开启CC调用日志跟踪 默认是false: 关闭状态
            CC.enableRemoteCC(BuildConfig.DEBUG); //开启跨app组件调用 默认是false: 关闭状态
        }

        //开启打印日志
        Logger.init(sApplication,true);
//        Logger.e("TAG","NewsApplication");
    }
}
