package com.plugin.internet.core;

import com.plugin.common.utils.CommonUtilsConfig;

public class InternetConfig {

	public static final boolean DEBUG = true & CommonUtilsConfig.UTILS_DEBUG;

	public static final int SIG_PARAM_MAX_LENGTH = 5000;

    // 系统错误返回值的最小值
    public static final int SYSTEM_ERROR_CODE_START =  1;
    // 系统错误返回值的最大值
    public static final int SYSTEM_ERROR_CODE_END =  999;

}
