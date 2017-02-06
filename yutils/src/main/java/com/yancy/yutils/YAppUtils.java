package com.yancy.yutils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * App相关辅助类
 * Created by Yancy on 2015/9/24.
 */
public class YAppUtils {

    private YAppUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取当前应用的版本号 (内部识别)
     */
    public static Integer getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取 android API 版本号  : API 21
     */
    public static int getAPIVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取 android 版本号    : Android 5.0
     */
    public static String getAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }


    /**
     * 判断 是否在后台运行
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
//                Logger.i(context.getPackageName(), "此appimportace =" + appProcess.importance + ",context.getClass().getName()=" + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//                    Logger.i(context.getPackageName(), "处于后台" + appProcess.processName);
                    return true;
                } else {
//                    Logger.i(context.getPackageName(), "处于前台" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }


}