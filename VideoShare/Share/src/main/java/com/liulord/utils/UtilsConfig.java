package com.liulord.utils;

import android.os.Environment;

import com.liulord.videoshare.BuildConfig;


public class UtilsConfig {

    public static final boolean UTILS_DEBUG = BuildConfig.DEBUG;
    //public static final boolean UTILS_DEBUG = true;

    public static final String CURRENT_PACKAGE_NAME_BINDING = "wifitest";
    public static final String PES_LOG_TAG = "PesLog";
    public static final String DISK_LOG_PATH_BINDING = Environment.getExternalStorageDirectory().getPath() + "/myvideoshare";
    public static final int DEFAULT = -1;
    public static final long LONGDEFAULT = 0;
    public static final long PASSWDTIME = 0;
    public static final String EMPTY = "";
    public static final String PWDRESERVE1 = "system";
    public static final String PWDRESERVE2 = "null";
    public static final int PWDTYPE1 = 1;
    public static final int PWDTYPE2 = 2;
    public static final int PWDTYPE3 = 3;
    public static final String INVALID = "invalid";
    public static final String Delimiter = ":";
    public static final String INVALIDKEY = "00:00:00:00:00:00";
    public static final int LATENCY_CONN_TIMEOUT = 3000;
    public static final int LATENCY_READ_TIMEOUT = 1000;
    public static final int SERVICE_RESTART_DELAY = 5 * 60 * 1000; //5min
    public static final int XUNLEI_RESTART_DELAY = 60 * 60 * 1000; //5min
    public static final int SERVICE_RESTART_DELAY_KILLED = 1 * 1000; //5min
    //public static final int SERVICE_UPLOAD_DELAY = 10 * 60 * 1000; //5min
    public static final int SERVICE_UPLOAD_DELAY = 30 * 1000; //5min
    public static final int LOCATION_UPDATE_DELAY = 5 * 60 * 1000; //5min
    public static final int LOCATION_UPDATE_DELAY_FOREGROUND = 60 * 1000; //5min
    public static final int SERVICE_ROUTINE_DELAY = 60 * 1000; //5min
    public static final int SERVICE_ROUTINE_DELAY_DORMANT = 30 * 60 * 1000; //5min
    public static final long DETECT_204_TIMEOUT = 5000;
    public static final long WIFIUPDATE_INTERVAL = 10 * 60 * 1000;  //10min
    public static boolean TestBaidu = false;

}
