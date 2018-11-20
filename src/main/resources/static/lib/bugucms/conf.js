/***
 *      _______
 *     |__   __|
 *        | |  ___  _ __ __      __ ___  _ __
 *        | | / _ \| '__|\ \ /\ / // _ \| '__|
 *        | ||  __/| |    \ V  V /|  __/| |
 *        |_| \___||_|     \_/\_/  \___||_|
 *
 *
 */
/**
 @Name：Bugucms Admin iframe版全局配置
 @Author：Terwer
 @Site：http://www.terwergreen.com/
 @License：Apache V2
 */
layui.define(['layer', 'element', 'util'], function (exports) {
    exports('conf', {
        pageTabs: true, //是否开启页面选项卡功能。iframe版推荐开启
        debug: true, //是否开启调试模式。如开启，接口异常时会抛出异常 URL 等信息
        //自定义请求字段
        request: {
            tokenName: true, //自动携带 token 的字段名（如：access_token）。可设置 false 不携带。
            tokenString: 'r6o-glNUVnFxMyEUBHGK9i4vriKCfpvnHXv0Rsc6zx2ZWtLArXsAF6mE36ZrLqCbrT95cnrOD_TM0-qOIkcLBQ'
        }
    });
});