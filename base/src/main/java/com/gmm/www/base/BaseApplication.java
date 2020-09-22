package com.gmm.www.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

/**
 * @author:gmm
 * @date:2020/4/25
 * @类说明:
 */
public class BaseApplication extends Application {
    //OOM won't happen!
    public static Application sApplication;
    public static boolean sDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public void setDebug(boolean isDebug) {
        sDebug = isDebug;
    }

    /**
     * 包名判断是否为主进程
     */
    public boolean isMainProcess() {
        return getApplicationContext().getPackageName().equals(getCurrentProcessName());
    }

    /**
     * 获取当前进程名
     * @return
     */
    private String getCurrentProcessName() {
        int pid = Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                processName = processInfo.processName;
            }
        }
        return processName;
    }
}
