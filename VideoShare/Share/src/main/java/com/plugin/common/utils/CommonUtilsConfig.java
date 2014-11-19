/**
 * Config.java
 */
package com.plugin.common.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.liulord.videoshare.BuildConfig;
import com.plugin.common.utils.SingleInstanceBase.SingleInstanceManager;
import com.plugin.common.utils.files.DiskManager;

/**
 * @author Guoqing Sun Mar 11, 20133:08:25 PM
 */
public class CommonUtilsConfig {

    public static final boolean UTILS_DEBUG = BuildConfig.DEBUG;

    public static final boolean DEBUG_NETWORK_ST = false & UTILS_DEBUG;
    public static final boolean RELEASE_UPLOAD_CRASH_LOG = true;

    // public static final String CURRENT_PACKAGE_NAME =
    // "com.photo_message.mobile.android";
    /**
     * 褰�������binding���搴���ㄧ��搴�������锛���ㄥ��濮���������跺��缁�瀹�
     */
    public static String CURRENT_PACKAGE_NAME_BINDING = null;

    public static String DISK_DIR_PATH_BINDING = null;
    public static String DISK_TMP_DIR_PATH_BINDING = null;

    public static String DISK_LOG_PATH_BINDING = null;

    public static final int BITMAP_COMPRESS_LOW = 80;
    public static final int BITMAP_COMPRESS_MEDIUM = 90;
    public static final int BITMAP_COMPRESS_HIGH = 100;

    /*
     * ���绉���剧��CacheManager���Category
     */
    public static final String IMAGE_CACHE_CATEGORY_USER_HEAD_ROUNDED = "user_head_rounded";
    public static final String IMAGE_CACHE_CATEGORY_RAW = "image_cache_category_source";
    public static final String IMAGE_CACHE_CATEGORY_THUMB = "image_cache_category_thumb";
    public static final String IMAGE_CACHE_CATEGORY_SMALL = "image_cache_category_small";

    public static DeviceInfo DEVICE_INFO;

    /**
     * init the utils lib
     *
     * @param context
     */
    public static void init(Context context) {
        if (context != null) {
            // 璁剧疆褰����������浠ュ����稿�宠矾寰�
            String diskDir;
            if (CommonUtilsRuntime.isExternalStorageAvailable()) {
                diskDir = Environment.getExternalStorageDirectory()
                        .getAbsolutePath();
                CURRENT_PACKAGE_NAME_BINDING = CommonUtilsRuntime
                        .getPackageName(context);
                DISK_DIR_PATH_BINDING = diskDir + "/."
                        + CURRENT_PACKAGE_NAME_BINDING + "/";
                DISK_TMP_DIR_PATH_BINDING = diskDir + "/."
                        + CURRENT_PACKAGE_NAME_BINDING + ".tmp/";
                DISK_LOG_PATH_BINDING = diskDir + "/."
                        + CURRENT_PACKAGE_NAME_BINDING + "/";
            } else {
                diskDir = context.getFilesDir().getAbsolutePath();

                CURRENT_PACKAGE_NAME_BINDING = CommonUtilsRuntime
                        .getPackageName(context);
                DISK_DIR_PATH_BINDING = diskDir + "/";
                DISK_TMP_DIR_PATH_BINDING = context.getCacheDir().getAbsolutePath() + "/";
                DISK_LOG_PATH_BINDING = diskDir + "/";
            }
            DiskManager.init();

            SingleInstanceManager.getInstance().init(context.getApplicationContext());
            CommonUtilsConfig.DEVICE_INFO = new DeviceInfo(context.getApplicationContext());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String jsonFormatter(String uglyJSONString) {
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		JsonParser jp = new JsonParser();
//		JsonElement je = jp.parse(uglyJSONString);
//		String prettyJsonString = gson.toJson(je);
//		return prettyJsonString;
        return uglyJSONString;
    }

    public static void LOGD(String msg, boolean withExtraInfo) {
        if (CommonUtilsConfig.UTILS_DEBUG) {
            String method = "";
            if (withExtraInfo) {
                method = CommonUtilsRuntime.getCurrentStackMethodName();
            }
            CommonDebugLog.d(method, msg);
        }
    }

    public static void LOGD(String msg, Throwable t) {
        if (CommonUtilsConfig.UTILS_DEBUG) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
            String invokeMethodName = ste.getMethodName();
            String fileName = ste.getFileName();
            long line = ste.getLineNumber();
            String method = "com.plugin.common.utils.Config";
            if (!TextUtils.isEmpty(invokeMethodName)) {
                method = fileName + "::" + invokeMethodName + "::" + line;
            }
            CommonDebugLog.d(method, msg, t);
        }
    }

    public static void LOGD(String msg) {
        LOGD(msg, true);
    }

    public static void LOGD(String tag, String msg) {
        String method = CommonUtilsRuntime.getCurrentStackMethodName();
        CommonDebugLog.d(tag, "[[" + method + "]]" + msg);
    }

    public static void LOGD_WITH_TIME(String msg) {
        LOGD(msg + " >>>>>> TIME : "
                + CommonUtilsRuntime.debugFormatTime(System.currentTimeMillis()));
    }

    /**
     * 娴����缁�璁″�抽��瀛�
     */
    public static final String NETWORK_STATISTICS_TYPE = "newtwork_st";

    public static final String NETWORK_STATISTICS_CATEGORY_IMAGE = "newtwork_image";

    public static final String NETWORK_STATISTICS_UP = "up";

    public static final String NETWORK_STATISTICS_DOWN = "down";

}
