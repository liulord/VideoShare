package com.liulord.videoshare.api;

import com.plugin.internet.core.RequestBase;

/**
 * Created by aaronliu on 14-12-1.
 */
public class VideoRequestBase<T> extends RequestBase<T> {
    private static String BASE_API_URL = null;

    // 测试
    private static final String API_TEST_URL = "http://twin13a087vm5.sandai.net/api/";
    // 定版
    private static final String API_PRE_URL = "http://sslquery-wifibee.xunlei.com/test/api/";
    // 线上
    private static final String API_RELEASE_URL = "http://sslquery-wifibee.xunlei.com/api/";

    public static final String APPID = "227695";
    private static final String KEY_METHOD = "method";
    private static final String KEY_HTTP_METHOD = "httpMethod";



}
