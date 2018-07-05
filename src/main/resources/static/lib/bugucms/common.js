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
layui.define(['jquery'], function(exports) {
    var $ = layui.$,
        bugucms = top.layui.bugucms;

    //监听锚点打开选项卡
    $(document).on('click', '*[lau-href]', function () {
        var _this = $(this);
        bugucms.go(_this.attr('lau-href'), _this.attr('lau-title'), _this.attr('lau-icon'));
    });

    exports('common', bugucms);
});
