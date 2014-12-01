package com.liulord.videoshare.api.request;

import android.os.Bundle;
import android.text.TextUtils;

import com.liulord.videoshare.api.UseHttps;
import com.plugin.internet.core.NetWorkException;
import com.plugin.internet.core.RequestBase;
import com.plugin.internet.core.annotations.NeedTicket;
import com.plugin.internet.core.annotations.OptionalTicket;

/**
 * Created by aaronliu on 14-12-1.
 */
public class VideoRequestBase<T> extends RequestBase<T> {
    private static String BASE_API_URL = null;

    // 测试
    private static final String API_TEST_URL = "http://10.3.5.42:8383/";
    // 定版
    private static final String API_PRE_URL = "http://sslquery-wifibee.xunlei.com/test/api/";
    // 线上
    private static final String API_RELEASE_URL = "http://sslquery-wifibee.xunlei.com/api/";

    public static final String APPID = "227695";
    private static final String KEY_METHOD = "method";
    private static final String KEY_HTTP_METHOD = "httpMethod";

    @Override
    public Bundle getParams() throws NetWorkException {
        Bundle params = super.getParams();

        Class<?> c = this.getClass();

        boolean needTicket = false;
        String ticket = null;
        String userSecret = null;
//
//        if (c.isAnnotationPresent(NeedTicket.class)) {
//            needTicket = true;
//            ticket = UserInfoKeeper.getTicket(WifiApplication.getInstance());
//            userSecret = UserInfoKeeper.getUserSecret(WifiApplication.getInstance());
//        } else if (c.isAnnotationPresent(OptionalTicket.class)) {
//            ticket = UserInfoKeeper.getTicket(WifiApplication.getInstance());
//            userSecret = UserInfoKeeper.getUserSecret(WifiApplication.getInstance());
//            if (!TextUtils.isEmpty(ticket) && !TextUtils.isEmpty(userSecret)) {
//                needTicket = true;
//            }
//        }

        String method = params.getString(KEY_METHOD);
        if (TextUtils.isEmpty(method)) {
            throw new RuntimeException("Method Name MUST NOT be NULL");
        }

        String methodName = method;

        if (!method.startsWith("http://") && !method.startsWith("https://")) {
            method = getBaseApiUrl() + method.replace('.', '/');
        }

        if (c.isAnnotationPresent(UseHttps.class)) {
            method = method.replace("http", "https");
            method = method.replaceAll(":(\\d+)/", "/");
        }

        String httpMethod = params.getString(KEY_HTTP_METHOD);
        params.remove(KEY_HTTP_METHOD);
        params.remove(KEY_METHOD);



        params.putString(KEY_METHOD, method);
        params.putString(KEY_HTTP_METHOD, httpMethod);

        return params;
    }

    public static String getBaseApiUrl() {
        if (BASE_API_URL == null) {
//            String serverType = UtilsRuntime.getMetadata(
//                    WifiApplication.getInstance(), "SERVER_TYPE");
//            if ("pre".equals(serverType)) {
//                BASE_API_URL = API_PRE_URL;
//            } else if ("release".equals(serverType)) {
//                BASE_API_URL = API_RELEASE_URL;
//            } else {
//                BASE_API_URL = API_TEST_URL;
//            }
            BASE_API_URL = API_TEST_URL;
        }
        return BASE_API_URL;
    }
}
