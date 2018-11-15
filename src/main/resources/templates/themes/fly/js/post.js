/**
 @Name: fly - 飞翔
 @Author: terwer
 @Copyright: terwergreen.com
 */
layui.define(['layer', 'laytpl', 'form', 'element', 'upload', 'util'], function (exports) {

    var $ = layui.jquery
        , layer = layui.layer
        , laytpl = layui.laytpl
        , form = layui.form
        , element = layui.element
        , upload = layui.upload
        , util = layui.util
        , device = layui.device()

        , DISABLED = 'layui-btn-disabled';

    //阻止IE7以下访问
    if (device.ie && device.ie < 8) {
        layer.alert('如果您非得使用 IE 浏览器访问Fly社区，那么请使用 IE8+');
    }

    //搜索
    $('.fly-search').on('click', function () {
        layer.open({
            type: 1
            , title: false
            , closeBtn: false
            //,shade: [0.1, '#fff']
            , shadeClose: true
            , maxWidth: 10000
            , skin: 'fly-layer-search'
            , content: ['<form action="../">'
                , '<input autocomplete="off" placeholder="输入内容，回车搜索" type="text" name="q">'
                , '</form>'].join('')
            , success: function (layero) {
                var input = layero.find('input');
                input.focus();

                layero.find('form').submit(function () {
                    var val = input.val();
                    if (val.replace(/\s/g, '') === '') {
                        return false;
                    }
                    input.val(input.val());
                });
            }
        })
    });
});