package com.terwergreen.bugucms.utils;

public class Constants {
    public static final String SITE_CONFIG_KEY = "siteConfig";
    /**
     * 验证码Session Key
     */
    public static final String SESSION_VERIFY_CODE_KEY = "sessionVerifyCode";
    /**
     * 标记为Y的常量
     */
    public static final String YES_FLAG = "Y";
    /**
     * 标记为N的常量
     */
    public static final String NO_FLAG = "N";
    /**
     * 标记为1的常量
     */
    public static final String Y_FLAG = "1";
    /**
     * 标记为1的常量
     */
    public static final String N_FLAG = "0";
    /**
     * 标记为0的常量
     */
    public static final String BG_ZERO = "0";
    /**
     * 标记为1的常量
     */
    public static final String BG_ONE = "1";
    /**
     * 标记为2的常量
     */
    public static final String BGTWO = "2";
    /**
     * Cookie的过期时间为12个小时
     */
    public static final int COOKIE_MAX_AGE = 43200;

    /**
     * 默认页面
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 默认每页展示的数目
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 后台路径错误信息
     */
    public static final String ADMIN_PATH_ERROR_MESSAGE = "站点后台路径配置错误，不允许访问";

    /**
     * 登录失效提示
     */
    public static final String ADMIN_USER_NOT_LOGIN = "尚未登录或者登录失败，请重新登录";

    /**
     * 登录地址
     */
    public static final String AUTH_LOGIN_PAGE = "/auth/login";
    /**
     * 登录出错地址
     */
    public static final String AUTH_ERROR_URL = "/auth/login";
    /**
     * 自定义Servlet根路径
     */
    public static final String SERVLET_BASE_PATH = "/servlet";
    /**
     * 自定义Api文档根路径
     */
    public static final String API_DOC_BASE_PATH = "/doc";
    /**
     * xmlrpc地址
     */
    public static final String XMLRPC_URL = "/xmlrpc";
}
