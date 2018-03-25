<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <title>注册 - ${webname}</title>

    <meta name="keywords" content="${keywords}">
    <meta name="description" content="${description}">

    <!--公共的样式-->
    <link rel="stylesheet" href="./static/css/base.css">
    <!--页面自定义样式-->
    <link rel="stylesheet" href="./static/css/uc-reg.css">
    <style>
        .quc-wrapper a:active, .quc-wrapper a:hover {
            outline-width: 0
        }

        .quc-wrapper input, .quc-wrapper
        select, .quc-wrapper textarea {
            outline: 0
        }

        .quc-icon-sad, .quc-mod-bind-mobile .quc-field-mobile
        .quc-input-bg, .quc-mod-bind-mobile .quc-field-mobile .quc-input-bg-focus, .quc-mod-bind-mobile .quc-field-mobile
        .quc-input-bg-incorrect, .quc-mod-bind-mobile .quc-state-wrapper, .quc-mod-bind-mobile .quc-state-wrapper
        .quc-area-arrow, .quc-mod-fill-profile .quc-field-mobile .quc-input-bg, .quc-mod-fill-profile .quc-field-mobile
        .quc-input-bg-focus, .quc-mod-fill-profile .quc-field-mobile .quc-input-bg-incorrect, .quc-mod-fill-profile
        .quc-state-wrapper, .quc-mod-fill-profile .quc-state-wrapper .quc-area-arrow, .quc-mod-sign-in
        .quc-third-part-icon, .quc-mod-sign-up .quc-left-bar .quc-icon, .quc-mod-sign-up .quc-left-bar .quc-sign-up-type
        li.quc-current, .quc-panel-sad .quc-panel-hd .quc-panel-title, .quc-panel .quc-panel-hd, .quc-panel .quc-panel-hd
        .quc-panel-close, .quc-panel .quc-panel-hd .quc-panel-title, .quc-wrapper .quc-input-bg, .quc-wrapper
        .quc-tip-icon {
            background-image: url(//p.ssl.qhimg.com/t01bc75514bf55a2f53.png);
            background-repeat: no-repeat
        }

        .quc-wrapper {
        + line-height: 14 px;
            font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB,
            Microsoft YaHei, 微软雅黑, Arial, SimSun, sans-serif;
            font-size: 12px;
            line-height: 1em
        }

        .quc-icon {
            vertical-align: middle
        }

        .quc-icon-sad {
            background-position: -403px -607px
        }

        .quc-wrapper dd, .quc-wrapper div, .quc-wrapper dl, .quc-wrapper dt, .quc-wrapper form, .quc-wrapper
        i, .quc-wrapper label, .quc-wrapper li, .quc-wrapper ol, .quc-wrapper p, .quc-wrapper
        ul {
            -moz-box-sizing: content-box;
            -webkit-box-sizing: content-box;
            box-sizing: content-box;
            height: auto;
            list-style: none;
            margin: 0;
            padding: 0;
            width: auto
        }

        .quc-wrapper
        a {
            text-decoration: none
        }

        .quc-clearfix {
            *zoom: 1
        }

        .quc-clearfix:after {
            clear: both;
            content: ".";
            display: block;
            height: 0;
            visibility: hidden
        }

        .quc-wrapper
        .quc-button-save {
            background-position: -270px -240px;
            width: 75px
        }

        .quc-wrapper .quc-button-save:focus, .quc-wrapper
        .quc-button-save:hover {
            background-position: -270px -280px
        }

        .quc-wrapper
        .quc-button-save:active {
            background-position: -270px -240px
        }

        .quc-wrapper .quc-button-saving, .quc-wrapper
        .quc-button-saving:hover {
            background-position: -270px -320px
        }

        .quc-wrapper
        .quc-button-sign-up {
            background-position: 0 -360px;
            width: 263px
        }

        .quc-wrapper .quc-button-sign-up:focus, .quc-wrapper
        .quc-button-sign-up:hover {
            background-position: 0 -400px
        }

        .quc-wrapper
        .quc-button-sign-up:active {
            background-position: 0 -360px
        }

        .quc-wrapper .quc-button-submit {
            background-position: 0 -240px;
            width: 263px
        }

        .quc-wrapper .quc-button-submit:focus, .quc-wrapper
        .quc-button-submit:hover {
            background-position: 0 -280px
        }

        .quc-wrapper .quc-button-submitting, .quc-wrapper
        .quc-button-submitting:hover {
            background-position: 0 -320px;
            cursor: default;
            width: 263px
        }

        .quc-wrapper
        .quc-button-sign-in {
            background-position: 0 -120px;
            width: 263px
        }

        .quc-wrapper .quc-button-sign-in:focus, .quc-wrapper
        .quc-button-sign-in:hover {
            background-position: 0 -160px
        }

        .quc-wrapper .quc-button-signing-in, .quc-wrapper
        .quc-button-signing-in:hover {
            background-position: 0 -200px;
            cursor: default;
            width: 263px
        }

        .quc-wrapper
        .quc-button-blue:focus, .quc-wrapper .quc-button-blue:hover {
            background-position: -270px -400px;
            text-decoration: none
        }

        .quc-wrapper .quc-button-disabled, .quc-wrapper
        .quc-button-disabled:focus, .quc-wrapper .quc-button-disabled:hover, .quc-wrapper
        .quc-button-disabled:visited {
            background-position: -350px -240px;
            color: #bababa;
            cursor: default;
            text-decoration: none
        }

        .quc-wrapper
        .quc-input {
            -moz-box-sizing: content-box;
            -webkit-box-sizing: content-box;
            background-color: #fff;
            border: none;
            box-sizing: content-box;
            color: grey;
            font-size: 14px;
            height: 21px;
            *height: 20px;
            line-height: 20px;
            margin: 5px 4px 3px;
            *margin-top: 4px;
            outline: none;
            padding: 6px 7px;
            width: 247px
        }

        .quc-wrapper
        .quc-input-bg {
            display: inline-block;
            *display: inline;
            height: 42px;
            vertical-align: middle;
            width: 270px;
            *zoom: 1
        }

        .quc-wrapper
        .quc-input-bg-incorrect {
            background-position: 0 -485px
        }

        .quc-wrapper .quc-input-bg-focus {
            background-position: 0 -530px
        }

        .quc-input-long .quc-input-bg {
            background-position: 0 -440px;
            width: 270px
        }

        .quc-input-long
        .quc-input-bg-incorrect {
            background-position: 0 -485px
        }

        .quc-input-long .quc-input-bg-focus {
            background-position: 0 -530px
        }

        .quc-input-middle .quc-input-bg {
            background-position: -270px -439px;
            width: 145px
        }

        .quc-input-middle
        .quc-input-bg-incorrect {
            background-position: -270px -484px
        }

        .quc-input-middle
        .quc-input-bg-focus {
            background-position: -270px -529px
        }

        .quc-input-middle .quc-input {
            width: 123px
        }

        .quc-input-short
        .quc-input-bg {
            background-position: 0 -575px;
            width: 120px
        }

        .quc-input-short
        .quc-input-bg-incorrect {
            background-position: -120px -575px
        }

        .quc-input-short
        .quc-input-bg-focus {
            background-position: -240px -573px
        }

        .quc-input-short .quc-input {
            width: 97px
        }

        .quc-wrapper
        .quc-select {
            -moz-border-radius: 2px;
            -moz-box-shadow: inset 0 -2px 1px hsla(0, 0%, 72%, .2);
            -webkit-border-radius: 2px;
            -webkit-box-shadow: inset 0 -2px 1px hsla(0, 0%, 72%, .2);
            border: 1px solid #b7b7b7;
            border-radius: 2px;
            box-shadow: inset 0 -2px 1px hsla(0, 0%, 72%, .2);
            color: grey;
            font-size: 14px;
            height: 33px;
            line-height: 33px;
            margin: 4px 3px;
            outline: none;
            padding: 6px;
            vertical-align: middle;
            width: 266px
        }

        .quc-wrapper .quc-select:focus {
            -moz-box-shadow: 0 0 3px 1px rgba(110, 212, 42, .4);
            -webkit-box-shadow: 0 0 3px 1px rgba(110, 212, 42, .4);
            border-color: #6ed42a;
            box-shadow: 0 0 3px 1px rgba(110, 212, 42, .4)
        }

        .quc-wrapper
        .quc-checkbox, .quc-wrapper .quc-radio {
            margin-right: 5px;
            vertical-align: middle
        }

        .quc-wrapper
        .quc-tip {
            color: #959393
        }

        .quc-wrapper .quc-tip-error {
            color: red
        }

        .quc-wrapper
        .quc-tip-success {
            color: #64c82c
        }

        .quc-wrapper a.quc-link, .quc-wrapper a.quc-link:active {
            color: #0082cb
        }

        .quc-wrapper
        a.quc-link:hover {
            text-decoration: underline
        }

        .quc-wrapper a.quc-link-grey {
            color: #999
        }

        .quc-wrapper
        .quc-tip-icon {
            background-position: 999px;
            display: inline-block;
            height: 15px;
            margin: 0 0 2px 4px;
            vertical-align: middle;
            width: 17px
        }

        .quc-wrapper .quc-tip-icon-correct {
            background-position: -350px -330px
        }

        .quc-wrapper .quc-tip-icon-incorrect {
            background-position: -350px -345px
        }

        .quc-mask {
            background: #000;
            filter: alpha(opacity=40);
            height: 100%;
            left: 0;
            opacity: .4;
            overflow: hidden;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 110000;
            *zoom: 1
        }

        .quc-ie6-iframe, .quc-mask-inner {
            background: #000;
            height: 100%;
            position: absolute;
            width: 100%;
            z-index: 100000
        }

        .quc-panel {
            -moz-box-shadow: 2px 2px 10px rgba(0, 0, 0, .3);
            -webkit-box-shadow: 2px 2px 10px rgba(0, 0, 0, .3);
            background-color: #fff;
            box-shadow: 2px 2px 10px rgba(0, 0, 0, .3);
            left: 410px;
            margin: 0;
            min-width: 200px;
            padding: 0;
            position: absolute;
            text-align: left;
            z-index: 130000
        }

        .quc-panel
        .idt-bar {
            padding: 0 .5em
        }

        .quc-confirm {
            width: 460px
        }

        .quc-confirm .quc-panel-hd {
            width: 100%
        }

        .quc-panel
        .quc-panel-hd {
            background-repeat: repeat-x;
            height: 57px;
            overflow: hidden
        }

        .quc-panel .quc-panel-hd
        .quc-panel-title {
            background-position: 0 -60px;
            color: #666;
            font-size: 16px;
            height: 38px;
            margin-left: 17px;
            min-width: 300px;
            padding: 24px 40px 0 83px
        }

        .quc-panel
        .quc-panel-hd .quc-panel-close {
            background-position: -350px -280px;
            cursor: pointer;
            font-size: 0;
            height: 0;
            overflow: hidden;
            padding-top: 24px;
            position: absolute;
            right: 10px;
            top: 16px;
            width: 29px
        }

        .quc-panel
        .quc-panel-hd .quc-panel-close:hover {
            background-position: -350px -305px
        }

        .quc-panel-sad .quc-panel-hd
        .quc-panel-title {
            background-position: 0 -754px
        }

        .quc-confirm .quc-panel-main, .quc-message
        .quc-panel-main {
            color: #666;
            font-size: 14px;
            line-height: 24px;
            padding: 40px;
            text-align: center
        }

        .quc-confirm
        .quc-panel-main .quc-icon, .quc-message .quc-panel-main
        .quc-icon {
            display: inline-block;
            height: 24px;
            padding-left: 5px;
            width: 24px
        }

        .quc-confirm .quc-panel-btns, .quc-message
        .quc-panel-btns {
            margin-top: 26px;
            text-align: center
        }

        .quc-confirm .quc-panel-btns .quc-button-confirm, .quc-message
        .quc-panel-btns .quc-button-confirm {
            margin-left: 10px
        }

        .quc-wrapper
        .quc-email-hint-wrapper {
            height: 0;
            position: relative;
            width: 0;
            z-index: 130500
        }

        .quc-wrapper
        .quc-email-hint {
            background: #fff;
            border: 1px solid #d3d3d3;
            margin: -4px 0 0 3px;
            position: absolute;
            z-index: 130550
        }

        .quc-email-hint
        a {
            -moz-user-select: none;
            -webkit-user-select: none;
            color: grey;
            display: block;
            font-size: 14px;
            line-height: 1.8em;
            outline: none;
            padding: 0 5px
        }

        .quc-email-hint a:hover {
            text-decoration: none
        }

        .quc-email-hint
        a.quc-on {
            background: #e8f8da;
            color: #000
        }

        .quc-wrapper .quc-ie-placeholder {
            color: #b4bccc !important
        }

        .quc-wrapper
        ::-webkit-input-placeholder {
            color: #b4bccc
        }

        .quc-wrapper :-ms-input-placeholder {
            color: #b4bccc
        }

        .quc-wrapper
        :-moz-placeholder, .quc-wrapper ::-moz-placeholder {
            color: #b4bccc
        }

        .quc-wrapper
        ::placeholder {
            color: #b4bccc
        }

        .quc-wrapper .quc-mod-set-nickname {
            min-height: 60px;
            padding: 30px 0 20px 40px
        }

        .quc-mod-set-nickname .quc-button-save {
            margin-left: 20px
        }

        .quc-mod-set-nickname
        .quc-tip {
            height: 20px;
            margin: 5px 0 10px 3px
        }

        .quc-mod-set-nickname
        .quc-alternative-wrapper {
            display: none;
            margin: 5px 0 15px 3px
        }

        .quc-mod-set-nickname
        .quc-alternatives {
            margin-top: 10px
        }

        .quc-mod-set-nickname .quc-alternatives a {
            margin-right: 10px
        }

        .quc-wrapper
        .quc-mod-set-username {
            min-height: 60px;
            padding: 30px 0 20px 40px
        }

        .quc-mod-set-username
        .quc-button-save {
            margin-left: 20px
        }

        .quc-mod-set-username .quc-tip {
            height: 20px;
            margin: 5px 0 10px 3px
        }

        .quc-mod-set-username .quc-alternative-wrapper {
            display: none;
            margin: 5px 0 15px 3px
        }

        .quc-mod-set-username
        .quc-alternatives {
            margin-top: 10px
        }

        .quc-mod-set-username .quc-alternatives a {
            margin-right: 10px
        }

        .quc-wrapper
        .quc-mod-authentication {
            padding: 2px 20px 20px 30px;
            width: 380px
        }

        .quc-mod-authentication
        .quc-tip-wrapper {
            height: 21px;
            line-height: 21px;
            padding: 3px 0 0 75px;
            *padding-top: 5px
        }

        .quc-mod-authentication
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            line-height: 31px;
            margin-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-authentication
        .quc-field .quc-button-blue {
            margin: 0 4px 1px 1px
        }

        .quc-mod-authentication .quc-field-mobile
        .quc-link-mobile-tip {
            float: right;
            margin-right: 50px
        }

        .quc-mod-authentication .quc-field .quc-input-bg {
            margin: 3px 0 4px
        }

        .quc-mod-authentication .quc-field .quc-tip-icon {
            visibility: hidden
        }

        .quc-mod-authentication .quc-field
        .quc-tip-icon-correct, .quc-mod-authentication .quc-field
        .quc-tip-icon-incorrect {
            visibility: visible
        }

        .quc-mod-authentication .quc-field
        .quc-tip {
            line-height: 1;
            margin-left: 75px
        }

        .quc-mod-authentication .quc-button-submit {
            margin: 10px 0 0 75px
        }

        .quc-mod-authentication
        .quc-captcha-img {
            cursor: pointer;
            height: 35px;
            outline: none;
            vertical-align: middle
        }

        .quc-mod-authentication
        .quc-captcha-change-link {
            margin-left: 11px
        }

        .quc-mod-authentication-token
        .quc-input-method {
        + diaplay: inline;
        + zoom: 1;
            display: inline-block;
            vertical-align: middle
        }

        .quc-mod-authentication-token
        .quc-field-send-smstoken .quc-input {
            width: 194px
        }

        .quc-mod-authentication-token
        .quc-field-send-smstoken {
            margin: 3px 0
        }

        .quc-mod-authentication-token
        .quc-link-mobile-tip {
            margin-left: 250px
        }

        .quc-mod-authentication-token
        .quc-link-go-back {
            display: inline-block;
            margin-left: 10px;
            margin-top: 10px;
            vertical-align: middle
        }

        .quc-mod-authentication
        .quc-mod-authentication-success, .quc-mod-authentication
        .quc-mod-authentication-token {
            display: none
        }

        .quc-mod-authentication-success
        .quc-field {
            font-size: 14px;
            padding: 10px 0;
            text-align: center
        }

        .quc-wrapper .quc-mod-set-email {
            padding: 0 40px 10px;
            width: 350px
        }

        .quc-mod-set-email .quc-tip {
            height: 14px;
            line-height: 14px;
            padding: 15px 0 2px 75px
        }

        .quc-mod-set-email .quc-form p {
            padding: 5px 0
        }

        .quc-mod-set-email
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            height: 41px;
            line-height: 41px;
            padding-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-set-email
        .quc-button-submit {
            color: #fff;
            font-size: 14px;
            margin-left: 73px
        }

        .quc-mod-set-email .quc-help {
            border-top: 1px solid #a2a2a2;
            color: #666;
            line-height: 1.5em;
            margin-top: 10px;
            padding-top: 10px
        }

        .quc-wrapper .quc-sign-in-nav {
            margin: 0 35px 0 -30px;
            text-align: center
        }

        .quc-wrapper .quc-sign-in-nav
        a {
            color: #666;
            display: inline-block;
            height: 36px;
            margin: 0 2px;
            vertical-align: bottom
        }

        .quc-wrapper
        .quc-sign-in-nav-c2 a {
            margin-right: 20px;
            padding: 0 15px
        }

        .quc-wrapper .quc-sign-in-nav-c3 a {
            padding: 0 10px
        }

        .quc-wrapper .quc-sign-in-nav-c4 a {
            font-size: 14px;
            padding: 0 5px
        }

        .quc-wrapper .quc-sign-in-nav
        .quc-current {
            border-bottom: 2px solid #41c42e;
            margin-bottom: -2px
        }

        .quc-mod-sign-in {
            overflow: hidden
        }

        .quc-mod-sign-in
        .quc-main {
            min-height: 190px;
            padding: 0 0 10px 30px;
            width: 380px
        }

        .quc-mod-sign-in
        .quc-tip-wrapper {
            height: 21px;
            line-height: 21px;
            padding: 13px 0 0 105px
        }

        .quc-mod-sign-in
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            line-height: 31px;
            margin-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-sign-in
        .quc-field {
            margin-bottom: 10px
        }

        .quc-mod-sign-in
        .quc-field-third-part {
            color: #888;
            margin-bottom: 0;
            padding-top: 3px
        }

        .quc-mod-sign-in
        .quc-third-part {
            margin-left: 5px
        }

        .quc-mod-sign-in
        .quc-third-part-icon {
            display: inline-block;
            height: 26px;
            margin: -3px 0 0 -1px;
            vertical-align: middle;
            width: 31px
        }

        .quc-mod-sign-in .quc-third-part-icon:focus, .quc-mod-sign-in
        .quc-third-part-icon:hover {
            position: relative
        }

        .quc-mod-sign-in
        .quc-third-part-icon-sina {
            background-position: -400px -280px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-sina:hover {
            background-position: -430px -280px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-renren {
            background-position: -400px -305px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-renren:hover {
            background-position: -430px -305px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-fetion {
            background-position: -400px -330px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-fetion:hover {
            background-position: -430px -330px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-msn {
            background-position: -400px -355px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-msn:hover {
            background-position: -430px -355px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-telecom {
            background-position: -400px -380px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-telecom:hover {
            background-position: -430px -380px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-tencent {
            background-position: -400px -355px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-tencent:hover {
            background-position: -430px -355px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-weixin {
            background-position: -400px -576px
        }

        .quc-mod-sign-in
        .quc-third-part-icon-weixin:hover {
            background-position: -430px -576px
        }

        .quc-mod-mobile-sign-in
        .quc-field-send-sms, .quc-mod-normal-sign-in .quc-field-third-part, .quc-mod-sign-in
        .quc-field-keep-alive, .quc-mod-sign-in .quc-field-submit {
            padding-left: 75px
        }

        .quc-mod-mobile-sign-in
        .quc-link-mobile-tip {
            margin-left: 53px
        }

        .quc-mod-sign-in
        .quc-captcha-img {
            cursor: pointer;
            height: 35px;
            outline: none;
            vertical-align: middle
        }

        .quc-mod-sign-in
        .quc-field-captcha {
            display: none
        }

        .quc-mod-qrcode-sign-in .quc-title {
            font-size: 14px;
            margin: 20px 0;
            text-align: center
        }

        .quc-mod-qrcode-sign-in .quc-title .quc-link {
            color: #41c42e
        }

        .quc-mod-qrcode-sign-in
        .quc-qrcode-wrapper {
            height: 180px;
            margin: 0 auto;
            position: relative;
            width: 250px
        }

        .quc-mod-qrcode-sign-in
        .quc-qrcode {
            display: block;
            margin: 0 auto;
            position: relative;
            width: 100%
        }

        .quc-mod-qrcode-sign-in
        .quc-qrcode-box {
            -moz-background-size: cover;
            background: url(//p.ssl.qhimg.com/d/quc/uc_qrcode_background.png);
            background-size: cover;
            color: #fff;
            font-weight: bolder;
            height: 180px;
            margin: 0 auto;
            position: relative;
            width: 180px
        }

        .quc-mod-qrcode-sign-in .quc-qrcode-box
        .quc-qrcode-mask {
            display: none
        }

        .quc-mod-qrcode-sign-in .quc-qrcode-box
        .quc-qrcode-mask-bg {
            background: #000;
            filter: alpha(opacity=70);
            height: 100%;
            left: 0;
            opacity: .7;
            position: absolute;
            top: 0;
            width: 100%
        }

        .quc-mod-qrcode-sign-in
        .quc-qrcode-box
        .quc-qrcode-refresh {
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            background: #4ab91d;
            border-radius: 4px;
            color: #fff;
            height: 20px;
            line-height: 20px;
            margin: 18px auto;
            position: relative;
            text-align: center;
            width: 75px
        }

        .quc-mod-qrcode-sign-in .quc-qrcode-box
        .quc-qrcode-icon {
            -webkit-filter: glow(color=#ffffff, strength=1);
            filter: glow(color=#ffffff, strength=1);
            line-height: 28px;
            margin-top: -42px;
            position: absolute;
            text-align: center;
            top: 50%;
            width: 100%
        }

        .quc-mod-quick-sign-in
        .quc-loading, .quc-mod-quick-sign-in .quc-tip-wrapper {
            display: none
        }

        .quc-mod-quick-sign-in
        .quc-main {
            height: 262px;
            padding: 0;
            width: 460px
        }

        .quc-mod-quick-sign-in
        .quc-sign-in-iframe {
            height: 100%;
            width: 100%
        }

        .quc-mod-sign-in .quc-footer {
            border-top: 1px solid #f7f7f7;
            padding: 10px 0 17px;
            text-align: center
        }

        .quc-mod-sign-in
        .quc-footer:before {
            background: -webkit-gradient(linear, left top, right top, from(#f7f7f7),
            color-stop(#d5d5d5), to(#f7f7f7));
            background: -webkit-linear-gradient(left, #f7f7f7, #d5d5d5, #f7f7f7);
            background: -moz-linear-gradient(left, #f7f7f7, #d5d5d5, #f7f7f7);
            background: -o-linear-gradient(left, #f7f7f7, #d5d5d5, #f7f7f7);
            background: linear-gradient(90deg, #f7f7f7, #d5d5d5, #f7f7f7);
            content: "";
            display: block;
            height: 1px;
            margin-top: -11px;
            position: absolute;
            width: 100%
        }

        .quc-mod-sign-in
        .quc-footer .quc-link {
            border-right: 1px solid #999;
            display: inline-block;
            padding: 0 12px
        }

        .quc-mod-qrcode-sign-in
        .quc-footer .quc-link-sign-up {
            border-right: 0
        }

        .quc-mod-sign-in .quc-footer
        .quc-link-about {
            margin-right: -24px
        }

        .quc-mod-normal-sign-in .quc-footer
        .quc-link-about-normal {
            margin-right: 0
        }

        .quc-mod-quick-sign-in .quc-footer .quc-link, .quc-mod-sign-in .quc-footer
        .quc-link-about, .quc-mod-sign-in .quc-footer
        .quc-link-normal-sign-in {
            border: none;
            display: none
        }

        .quc-mod-mobile-sign-in .quc-footer
        .quc-link-about-mobile, .quc-mod-normal-sign-in .quc-footer .quc-link-about-normal, .quc-mod-onekey-sign-in
        .quc-footer .quc-link-about-onekey, .quc-mod-quick-sign-in .quc-footer
        .quc-link-normal-sign-in {
            display: inline-block
        }

        .quc-wrapper .quc-mod-set-sec-email {
            padding: 0 40px 30px
        }

        .quc-mod-set-sec-email .quc-tip {
            height: 14px;
            line-height: 14px;
            padding: 15px 0 2px 75px
        }

        .quc-mod-set-sec-email
        .quc-form p {
            padding: 5px 0
        }

        .quc-mod-set-sec-email
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            height: 41px;
            line-height: 41px;
            padding-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-set-sec-email
        .quc-button-submit {
            color: #fff;
            font-size: 14px;
            margin-left: 73px
        }

        .quc-mod-sign-up
        .quc-left-bar {
            background-color: #fbfefe;
            float: left;
            width: 143px
        }

        .quc-mod-sign-up .quc-left-bar
        .quc-sign-up-type {
            border-top: 1px solid #e4ebec;
            margin-top: 20px
        }

        .quc-mod-sign-up .quc-left-bar .quc-sign-up-type
        li {
            border-bottom: 1px solid #e4ebec;
            font-size: 16px;
            line-height: 50px;
            *zoom: 1
        }

        .quc-mod-sign-up .quc-left-bar
        .quc-sign-up-type li
        a {
            color: #666;
            display: block;
            height: 50px;
            _height: 39px;
            padding-left: 17px;
            _padding-top: 11px;
            text-decoration: none;
            width: 126px
        }

        .quc-mod-sign-up
        .quc-left-bar .quc-sign-up-type li.quc-current {
            background-position: -240px -280px;
            border: none;
            height: 52px;
            _margin-right: -13px;
            position: relative;
            top: -1px;
            width: 156px
        }

        .quc-mod-sign-up
        .quc-left-bar .quc-sign-up-type .quc-current a {
            -moz-box-shadow: inset 0 1px hsla(0, 0%, 100%, .35);
            -webkit-box-shadow: inset 0 1px hsla(0, 0%, 100%, .35);
            background: #a7deef;
            border: 1px solid #88cde2;
            border-right: none;
            box-shadow: inset 0 1px hsla(0, 0%, 100%, .35);
            color: #fff;
            cursor: default;
            padding-left: 16px
        }

        .quc-mod-sign-up .quc-left-bar
        .quc-icon {
            display: inline-block;
            height: 26px;
            margin: 0 10px 4px 0;
            vertical-align: middle;
            width: 20px
        }

        .quc-mod-sign-up
        .quc-left-bar .quc-icon-email {
            background-position: -440px -490px
        }

        .quc-mod-sign-up .quc-left-bar
        .quc-icon-mobile {
            background-position: -440px -516px
        }

        .quc-mod-sign-up .quc-left-bar
        .quc-icon-username {
            background-position: -440px -542px
        }

        .quc-mod-sign-up .quc-left-bar .quc-current
        .quc-icon-email {
            background-position: -440px -410px
        }

        .quc-mod-sign-up .quc-left-bar .quc-current
        .quc-icon-mobile {
            background-position: -440px -436px
        }

        .quc-mod-sign-up .quc-left-bar .quc-current
        .quc-icon-username {
            background-position: -440px -462px
        }

        .quc-mod-sign-up .quc-main {
            -moz-box-shadow: -7px 0 5px -3px #f8f9f9;
            -webkit-box-shadow: -7px 0 5px -3px #f8f9f9;
            background: #fff;
            border-left: 1px solid #e4ebec;
            box-shadow: -7px 0 5px -3px #f8f9f9;
            float: left;
            padding: 0 0 20px 20px;
            width: 380px
        }

        .quc-mod-sign-up .quc-main
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            line-height: 31px;
            margin-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-sign-up
        .quc-tip-wrapper {
            height: 21px;
            line-height: 21px;
            padding: 3px 0 0 75px
        }

        .quc-mod-sign-up .quc-tip-wrapper
        .quc-tip {
            display: none
        }

        .quc-mod-sign-up .quc-field {
            color: #959393
        }

        .quc-mod-sign-up .quc-field
        .quc-input-bg {
            margin: 3px 0 4px
        }

        .quc-mod-sign-up .quc-field .quc-tip-icon {
            visibility: hidden
        }

        .quc-mod-sign-up
        .quc-field .quc-tip-icon-correct, .quc-mod-sign-up .quc-field
        .quc-tip-icon-incorrect {
            visibility: visible
        }

        .quc-mod-sign-up .quc-field
        .quc-tip {
            line-height: 1;
            *line-height: 14px;
            margin-left: 75px
        }

        .quc-mod-sign-up .quc-field-sms-token
        .quc-mobile-tip {
            float: right;
            margin-right: 50px
        }

        .quc-mod-sign-up .quc-field-sms-token
        .quc-tip {
            *float: left
        }

        .quc-mod-sign-up .quc-field-mobile .quc-button {
            margin: 0 4px 1px 1px
        }

        .quc-mod-sign-up
        .quc-field-captcha {
            display: none
        }

        .quc-mod-sign-up .quc-captcha-img {
            height: 36px;
            margin: 3px 0 4px 2px;
            vertical-align: middle;
            width: 99px
        }

        .quc-mod-sign-up .quc-link-captcha-change {
            margin-left: 10px
        }

        .quc-mod-sign-up
        .quc-captcha-change {
            cursor: pointer
        }

        .quc-mod-sign-up .quc-button-sign-up {
            margin: 12px 0 0 74px
        }

        .quc-mod-sign-up
        .quc-field-licence {
            line-height: 18px;
            margin: 9px 0 0 75px
        }

        .quc-mod-sign-up .quc-field-licence label
        span {
            vertical-align: middle
        }

        .quc-mod-sign-up .quc-login {
            margin: 0;
            padding-top: 15px;
            text-align: center;
            position: relative;
        }

        .quc-wrapper .quc-mod-bind-mobile {
            padding: 2px 20px 20px 30px;
            width: 380px
        }

        .quc-mod-bind-mobile .quc-tip-wrapper {
            height: 21px;
            line-height: 21px;
            padding: 3px 0 0 75px
        }

        .quc-mod-bind-mobile .quc-field-mobile .quc-input-bg {
            background-position: -4px -622px;
            height: 41px;
            width: 267px
        }

        .quc-mod-bind-mobile .quc-field-mobile
        .quc-input-bg-focus {
            background-position: 77px -666px
        }

        .quc-mod-bind-mobile .quc-field-mobile
        .quc-input-bg-incorrect {
            background-position: 77px -709px
        }

        .quc-mod-bind-mobile .quc-field-mobile .quc-input-bg
        .quc-input {
            height: 20px;
            margin-left: 85px;
            margin-top: 3px;
            width: 160px
        }

        .quc-mod-bind-mobile
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            line-height: 31px;
            margin-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-bind-mobile
        .quc-field .quc-button-blue {
            margin: 0 4px 1px 1px
        }

        .quc-mod-bind-mobile
        .quc-field-mobile {
            position: relative;
            z-index: 130300
        }

        .quc-mod-bind-mobile .quc-field-sms-token
        .quc-link-mobile-tip {
            float: right;
            margin-right: 50px
        }

        .quc-mod-bind-mobile .quc-field .quc-input-bg {
            margin: 3px 0 4px 72px;
            *margin-top: 4px
        }

        .quc-mod-bind-mobile .quc-field-sms-token .quc-input-bg {
            margin: 3px 0 4px 71px
        }

        .quc-mod-bind-mobile .quc-field .quc-tip-icon {
            visibility: hidden
        }

        .quc-mod-bind-mobile .quc-field
        .quc-tip-icon-correct, .quc-mod-bind-mobile .quc-field
        .quc-tip-icon-incorrect {
            visibility: visible
        }

        .quc-mod-bind-mobile .quc-field
        .quc-tip {
            display: inline-block;
            *display: inline;
            height: 16px;
            line-height: 16px;
            margin-left: 75px;
            overflow: hidden;
            width: 250px;
            *zoom: 1
        }

        .quc-mod-bind-mobile
        .quc-button-submit {
            margin: 10px 0 0 74px
        }

        .quc-mod-bind-mobile .quc-state-wrapper {
            background-position: 0 -622px;
            height: 40px;
            left: 72px;
            position: absolute;
            top: 3px;
            *top: 4px;
            width: 77px
        }

        .quc-mod-bind-mobile
        .quc-state-wrapper .quc-area-arrow {
            background-position: -273px -622px;
            cursor: pointer;
            height: 6px;
            position: absolute;
            right: 8px;
            top: 16px;
            width: 7px
        }

        .quc-mod-bind-mobile
        .quc-state-wrapper
        .quc-activeState {
            color: grey;
            cursor: pointer;
            display: inline-block;
            *display: inline;
            font-size: 16px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            width: 77px;
            *zoom: 1
        }

        .quc-mod-bind-mobile
        .quc-mobile-Statelist {
            background: #fff;
            border: 1px solid #d8d8d8;
            display: none;
            height: 150px;
            left: 75px;
            overflow-x: hidden;
            overflow-y: auto;
            position: absolute;
            top: 41px;
            width: 259px
        }

        .quc-mod-bind-mobile
        .quc-mobile-Statelist .quc-list-item {
            color: grey;
            font-size: 14px;
            height: 30px;
            line-height: 30px
        }

        .quc-wrapper
        .quc-mod-fill-profile {
            background: #fff;
            padding: 0 0 20px 30px;
            width: 380px
        }

        .quc-mod-fill-profile
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            line-height: 31px;
            margin-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-fill-profile
        .quc-tip-wrapper {
            height: 21px;
            line-height: 21px;
            padding: 3px 0 0 75px
        }

        .quc-mod-fill-profile .quc-tip-wrapper
        .quc-tip {
            display: none
        }

        .quc-mod-fill-profile .quc-field {
            color: #959393
        }

        .quc-mod-fill-profile .quc-field
        .quc-input-bg {
            margin: 3px 0 4px;
            *margin-top: 4px
        }

        .quc-mod-fill-profile .quc-field
        .quc-tip-icon {
            visibility: hidden
        }

        .quc-mod-fill-profile .quc-field .quc-tip-icon-correct, .quc-mod-fill-profile
        .quc-field .quc-tip-icon-incorrect {
            visibility: visible
        }

        .quc-mod-fill-profile
        .quc-captcha-img {
            height: 36px;
            margin: 3px 0 4px 2px;
            vertical-align: middle;
            width: 99px
        }

        .quc-mod-fill-profile
        .quc-captcha-change {
            cursor: pointer
        }

        .quc-mod-fill-profile
        .quc-link-captcha-change {
            margin-left: 6px
        }

        .quc-mod-fill-profile .quc-field
        .quc-tip {
            line-height: 1;
            margin-left: 75px
        }

        .quc-mod-fill-profile .quc-button-submit {
            margin: 12px 0 0 74px
        }

        .quc-mod-fill-profile .quc-go-signIn {
            cursor: pointer;
            display: block;
            margin: 10px 0 0;
            text-align: right
        }

        .quc-mod-fill-profile .quc-state-wrapper {
            background-position: 0 -622px;
            height: 40px;
            left: 70px;
            position: absolute;
            top: 3px;
            *top: 4px;
            width: 77px
        }

        .quc-mod-fill-profile
        .quc-state-wrapper .quc-area-arrow {
            background-position: -273px -622px;
            cursor: pointer;
            height: 6px;
            position: absolute;
            right: 8px;
            top: 16px;
            width: 7px
        }

        .quc-mod-fill-profile
        .quc-state-wrapper
        .quc-activeState {
            color: grey;
            cursor: pointer;
            display: inline-block;
            *display: inline;
            font-size: 16px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            width: 77px;
            *zoom: 1
        }

        .quc-mod-fill-profile
        .quc-mobile-Statelist {
            background: #fff;
            border: 1px solid #d8d8d8;
            display: none;
            height: 150px;
            left: 73px;
            overflow: scroll;
            overflow-x: hidden;
            position: absolute;
            top: 41px;
            width: 259px
        }

        .quc-mod-fill-profile
        .quc-mobile-Statelist
        .quc-list-item {
            color: grey;
            font-size: 14px;
            height: 30px;
            line-height: 30px
        }

        .quc-mobile-Statelist .quc-list-item
        .quc-left-tip, .quc-mobile-Statelist .quc-list-item
        .quc-right-tip {
            display: inline-block;
            *display: inline;
            *zoom: 1
        }

        .quc-mobile-Statelist .quc-list-item
        .quc-left-tip {
            margin-left: 20px;
            width: 40px
        }

        .quc-mobile-Statelist .quc-list-item
        .quc-right-tip {
            margin-left: 60px
        }

        .quc-mod-fill-profile
        .quc-field-mobile {
            position: relative;
            z-index: 130300
        }

        .quc-mod-fill-profile .quc-field-mobile
        .quc-input-bg {
            background-position: -4px -622px;
            height: 41px;
            width: 267px
        }

        .quc-mod-fill-profile .quc-field-mobile
        .quc-input-bg-focus {
            background-position: 77px -666px
        }

        .quc-mod-fill-profile .quc-field-mobile
        .quc-input-bg-incorrect {
            background-position: 77px -709px
        }

        .quc-mod-fill-profile .quc-field-mobile .quc-input-bg
        .quc-input {
            height: 20px;
            margin-left: 85px;
            margin-top: 3px;
            width: 160px
        }

        .quc-wrapper
        .quc-mod-identify {
            color: #959393;
            padding: 0 0 10px 30px;
            width: 380px
        }

        .quc-mod-identify
        .quc-tip-wrapper {
            height: 21px;
            line-height: 21px;
            margin-top: 10px;
            padding-left: 74px
        }

        .quc-mod-identify
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            line-height: 31px;
            margin-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-identify
        .quc-field {
            margin-bottom: 5px
        }

        .quc-mod-identify .quc-input-method, .quc-mod-identify .quc-method-single
        .quc-method-tip, .quc-mod-identify .quc-method-single .quc-select-method {
            display: none
        }

        .quc-mod-identify
        .quc-method-single
        .quc-input-method {
        + diaplay: inline;
        + zoom: 1;
            display: inline-block;
            vertical-align: middle
        }

        .quc-mod-identify
        .quc-field .quc-link {
            float: right;
            margin: 4px 43px 0
        }

        .quc-mod-identify
        .quc-captcha-img {
            cursor: pointer;
            height: 35px;
            outline: none;
            vertical-align: middle
        }

        .quc-mod-identify
        .quc-field-captcha a.quc-link {
            float: none;
            margin: 0
        }

        .quc-mod-identify .quc-field-captcha, .quc-mod-identify
        .quc-field-password {
            display: none
        }

        .quc-mod-identify .quc-field-submit {
            margin: 10px 0;
            padding-left: 75px
        }

        .quc-wrapper .quc-mod-active-email {
            color: #a2a2a2;
            line-height: 22px;
            padding: 20px 40px
        }

        .quc-mod-active-email .quc-send-result {
            color: #000;
            font-size: 14px
        }

        .quc-mod-active-email
        .quc-email-address {
            color: #25b311;
            font-size: 16px;
            line-height: 40px
        }

        .quc-mod-active-email
        .quc-button-blue {
            margin: 10px 10px 10px 0
        }

        .quc-mod-active-email
        .quc-link-jump {
            display: none;
            font-size: 12px;
            margin-left: 20px
        }

        .quc-mod-active-email .quc-resend {
            border-top: 1px solid #e9e9e9;
            line-height: 20px;
            margin-top: 5px;
            padding-top: 5px
        }

        .quc-mod-active-email
        .quc-resend-result {
            color: #25b311;
            display: none;
            margin-left: 20px
        }

        .quc-mod-active-email
        .quc-resending {
            color: #0001b3
        }

        .quc-loading {
            background: url(//p.ssl.qhimg.com/d/quc/loading.gif) no-repeat 50%;
            height: 100%;
            left: 0;
            overflow: hidden;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 120000;
            zoom: 1
        }

        .quc-wrapper
        .quc-mod-modify-password {
            padding: 0 40px 30px
        }

        .quc-mod-modify-password
        .quc-tip {
            height: 14px;
            line-height: 14px;
            padding: 15px 0 2px 75px
        }

        .quc-mod-modify-password .quc-form p {
            padding: 5px 0
        }

        .quc-mod-modify-password
        .quc-label {
            color: #333;
            display: inline-block;
            font-size: 14px;
            height: 41px;
            line-height: 41px;
            padding-right: 10px;
            text-align: right;
            width: 60px
        }

        .quc-mod-modify-password
        .quc-button-submit {
            color: #fff;
            font-size: 14px;
            margin-left: 73px
        }

        .quc-new-button {
            -moz-user-select: none;
            -ms-user-select: none;
            -webkit-appearance: none;
            -webkit-user-select: none;
            outline: none;
            text-align: center;
            user-select: none;
            white-space: nowrap
        }

        .quc-button, .quc-new-button {
            -moz-border-radius: 3px;
            -moz-transition: .1s;
            -o-transition: .1s;
            -webkit-border-radius: 3px;
            -webkit-transition: .1s;
            background-position: 0 0 !important;
            border-radius: 3px;
            cursor: pointer;
            display: inline-block;
            font-size: 16px;
            line-height: 34px;
            outline: none;
            text-align: center;
            transition: .1s
        }

        .quc-button-default {
            background: #fff;
            border: 1px solid #6fbc44;
            color: #6fbc44;
            width: 136px
        }

        .quc-button-primary {
            *-webkit-filter: chroma(color=#000000);
            background-color: #81cd55 !important;
            *background-image: none !important;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#81cd55),
            to(#66b33b)) !important;
            background-image: -webkit-linear-gradient(top, #81cd55, #66b33b) !important;
            background-image: -moz-linear-gradient(top, #81cd55 0, #66b33b 100%) !important;
            background-image: -o-linear-gradient(top, #81cd55 0, #66b33b 100%) !important;
            background-image: linear-gradient(180deg, #81cd55 0, #66b33b) !important;
            border: 1px solid #6fbc44;
            color: #fff;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ff81cd55,
            endColorstr=#ff66b33b, GradientType=0) !important;
            *filter: chroma(color=#000000);
            width: 194px
        }

        .quc-button-primary:hover {
            *-webkit-filter: chroma(color=#000000);
            background-color: #86de4d !important;
            *background-image: none !important;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#86de4d),
            to(#6bcf2b)) !important;
            background-image: -webkit-linear-gradient(top, #86de4d, #6bcf2b) !important;
            background-image: -moz-linear-gradient(top, #86de4d 0, #6bcf2b 100%) !important;
            background-image: -o-linear-gradient(top, #86de4d 0, #6bcf2b 100%) !important;
            background-image: linear-gradient(180deg, #86de4d 0, #6bcf2b) !important;
            border: 1px solid #6bcf2b;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ff86de4d,
            endColorstr=#ff6bcf2b, GradientType=0) !important;
            *filter: chroma(color=#000000)
        }

        .quc-button-blue {
            background-color: #def4fc;
            border: 1px solid #a9d5ea;
            color: #2c7aa6;
            font-size: 12px;
            position: relative;
            top: 1px;
            width: 120px
        }

        .quc-button-blue:hover {
            background-color: #ecfafd;
            border-color: #bae0ef;
            color: #4c8eb2
        }

        .quc-button-disabled {
            background-image: none;
            cursor: not-allowed !important
        }

        .quc-button-disabled, .quc-button-disabled:hover {
            background-color: #fff;
            border-color: #e6ebf5;
            color: #b4bccc
        }

        .login-page
        .quc-button-primary {
            background-color: #22ac69 !important;
            background-image: none !important;
            border-color: #22ac69;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ff22ac69,
            endColorstr=#ff22ac69, GradientType=0) !important
        }

        .login-page
        .quc-button-default {
            border-color: #22ac69;
            color: #22ac69
        }
    </style>
</head>

<body class="login-page" style="height: 965px;">

<div id="doc" style="background: rgb(245, 245, 245);">

    <div class="reg-page">
        <div id="regHeader">
            <div class="header-content">
					<span class="switch-login"> <a
                            href="${weburl}/login" title="登录" target="_blank">登录</a>
					</span> <a href="${weburl}/reg" title=""> <span
                    class="logo"></span>
            </a> <span class="page-title"> <a
                    href="${weburl}/reg" title="注册帐号">注册帐号</a>
					</span>
            </div>

        </div>
        <div class="reg-content">
            <div id="regWrap" style="display: block;">
                <div class="reg-title">注册特维博客帐号</div>
                <div class="quc-sign-up-wrapper quc-wrapper quc-page">
                    <div class="quc-mod-sign-up quc-clearfix">
                        <div class="quc-main">
                            <div class="quc-tip-wrapper quc-global-error">
                                <p class="quc-tip quc-tip-error"></p>
                            </div>
                            <form class="quc-form" method="post"
                                  action="https://i.360.cn/signUp/dosignUpAccount">
                                <div>
                                    <p class="quc-field
        quc-field-mobile quc-input-long">
											<span class="quc-fixIe6margin"><label
                                                    class="quc-label"></label></span><span class="quc-input-bg"><input
                                            class="quc-input quc-input-mobile" type="tel" name="account"
                                            data-name="mobile" maxlength="11" placeholder="请输入要注册的手机号"></span>
                                    </p>
                                    <span class="quc-tip"></span>
                                    <p
                                            class="quc-field
        quc-field-password quc-input-long">
											<span class="quc-fixIe6margin"><label
                                                    class="quc-label"></label></span><span class="quc-input-bg"><input
                                            class="quc-input quc-input-password" type="password"
                                            name="password" placeholder="密码请设置8-20个字符" maxlength="20"></span>
                                    </p>
                                    <span class="quc-tip"></span>
                                    <p
                                            class="quc-field
        quc-field-captcha quc-input-short"
                                            style="display: block;">
                                        <img class="quc-captcha-img quc-captcha-change" alt="验证码"
                                             title="点击更换" tabindex="99" src="./reg_files/captcha.php"><span
                                            class="quc-input-bg"><input
                                            class="quc-input quc-input-captcha" type="text"
                                            name="captcha" maxlength="7" placeholder="请输入验证码"
                                            autocomplete="off"></span>
                                    </p>
                                    <span class="quc-tip"></span>
                                    <p class="quc-field quc-next-step quc-clearfix">
                                        <a href="${weburl}/user/register"
                                           class="quc-nextAndGet-sms-token">下一步</a>
                                    </p>
                                    <p
                                            class="quc-field quc-field-sms-token quc-input-middle
        quc-clearfix">
											<span class="quc-sms-tips">短信验证码已发送至<label
                                                    class="quc-sms-tips-mobile"></label></span><a
                                            href="${weburl}/reg"
                                            class="quc-get-sms-token">免费获取校验码</a><span
                                            class="quc-input-bg"><input
                                            class="quc-input quc-input-sms-token" type="text"
                                            name="smscode" data-name="smsToken" placeholder="请输入短信验证码"
                                            maxlength="6"></span>
                                    </p>
                                </div>
                                <p class="quc-field
        quc-field-submit">
                                    <input class="quc-button quc-button-sign-up" type="submit"
                                           value="立即注册">
                                </p>
                                <p class="quc-field quc-field-smscodetip">
										<span><a class="quc-link"
                                                 href="http://i.360.cn/findpwd/customerhelper#recievemobilecode"
                                                 target="_blank">短信验证码没收到？</a></span>
                                </p>
                                <p class="quc-field quc-field-licence" style="display: none;">
                                    <label><span>点击“下一步”，即表示您已同意并愿意遵守<a
                                            class="quc-link" href="${weburl}/"
                                            target="_blank">《${webname}服务条款》</a></span></label>
                                </p>
                                <p class="quc-login">
                                    <label><span>已有帐号，<a
                                            href="${weburl}/login"
                                            class="quc-link quc-link-login">立即登录</a></span></label>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="reg-succeed">
            <div id="regWrap" style="display: block;">
                <div class="reg-succeed-title">注册成功</div>
                <div class="quc-sign-up-succeed">
                    <p>
                        <span class="reg-succeed-icon"></span>
                    </p>
                    <p>恭喜！您已完成360帐号注册</p>
                    <p>
                        您可以用该帐号登录<a href="${weburl}/" class="link"
                                    target="_blank">${webname}</a>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <div style="padding: 10px;"></div>

    <div id="ft">
        Copyright©2011-<span id="currentYear">2014</span> terwergreen.com All
        Rights Reserved ${webname}
        <p>
            <a target="_blank" href="http://www.miibeian.gov.cn/">${beianinfo}</a>
        </p>

    </div>

</div>
<!--公共需要的全局变量-->

<script type="text/javascript">
    window.QHDomain = {
        'i360': 'http://i.360.cn',
        'login_http': 'http://login.360.cn',
        'login_https': 'https://login.360.cn',
        'js_login': 'http://js.login.360.cn',
        '1360': 'http://www.1360.com',
        'qihoo': 'http://www.qihoo.com',
        'so': 'http://www.so.com',
        'woxihuan': 'http://www.woxihuan.com',
        'yunpan': 'http://yunpan.360.cn',
        'help': 'http://help.360.cn',
        'baike': 'http://baike.360.cn',
        'rdLoginUrl': {
            "qihoo": "http:\/\/login.qihoo.com",
            "woxihuan": "http:\/\/login.woxihuan.com",
            "1360": "http:\/\/login.1360.com",
            "so": "http:\/\/login.so.com",
            "360pay": "http:\/\/login.360pay.cn",
            "leidian": "http:\/\/login.leidian.com",
            "qikoo": "http:\/\/login.qikoo.com"
        },
        'captcha': 'http://i.360.cn/reg/dogetcap?i360',
        'jifen': 'http://jifen.360.cn',
        'src': 'pcw_i360',
        'srcRoot': 'i360'
    };
    window.QHUserInfo = {
        'loginStatus': '',
        /*****1为登录，0为未登录*******/
        'figure': '',
        'qid': '',
        'userName': '',
        'nickName': '',
        'loginEmail': ''
    };
    window.quc_lang = '';
    window.qCrumb = "";
</script>
<!--公共 js-->
<script type="text/javascript" src="./static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="./static/js/i360utils.js"></script>
<script>
    window.QUC = window.QUC || {};
    QUC.t3 = +new Date();
</script>

<script type="text/javascript" src="./static/js/5.0.3.js"></script>
<script>
    window.QUC = window.QUC || {};
    QUC.t5 = +new Date();
</script>
<script type="text/javascript" src="./static/js/base.js"></script>
<script type="text/javascript">
    $(function () {
        var protocol = location.protocol.slice(0, -1);
        QHPass.init('pcw_i360');
        QHPass.DEBUG = false;

        QHPass.setConfig('domainList', ["360pay.cn", "so.com", "360.cn",
            "360.com"]);

        QUCi360.utils.addRandomParam();
        $('.sign-out').on('click', function () {
            QHPass.signOut(protocol + "://i.360.cn");
        });
    });
</script>

<script type="text/javascript" src="./static/js/uc_reg.js"></script>
<script type="text/javascript">
    $(function () {
        var src = 'pcw_i360';
        var destUrl = "${weburl}";
        $("#doc").css('background', '#f5f5f5');
        var $wrapper = $('.quc-sign-up-wrapper');
        QHPass.setConfig('src', src);
        QHPass.setConfig('signUp.hideNickname', true);
        QHPass.setConfig('signUp.hideUsername', true);
        QHPass.events.on('afterShow.*', function (e, el) {
            $(el).find(".quc-input-mobile").focus();
            $(el).find('.quc-left-bar').remove();
            $(el).find('.quc-login').hide();
        });

        var regType = QUCi360.utils.getParams().regtype;
        if (regType == "email") {
            $('.mobile-sign-up').removeClass('cur');
            $('.email-sign-up').addClass('cur');
            QHPass.setConfig('signUp.types', ['email', 'mobile']);
        }
        QHPass.signUp($wrapper, function () {
            $(".reg-content").hide();
            $(".reg-succeed").show();
            setTimeout(function () {
                location.href = destUrl;
            }, 5000);

        });
        /*QHPass.events.on('changeType.signUp', function(e, type) {
        switch(type) {
        case 'email':
        $('#quc-bd').removeClass('reg-wrapper2').addClass('reg-wrapper1');
        break;
        case 'mobile':
        $('#quc-bd').removeClass('reg-wrapper1').addClass('reg-wrapper2');
        break;
        }
        });

        $('.email-sign-up').on('click', function(e) {
        e.preventDefault();
        $('.mobile-sign-up').removeClass('cur');
        $(this).addClass('cur');
        QHPass.ui.signUp.changeType('email');
        });

        $('.mobile-sign-up').on('click', function(e) {
        e.preventDefault();
        $('.email-sign-up').removeClass('cur');
        $(this).addClass('cur');
        QHPass.ui.signUp.changeType('mobile');
        });*/

        $('.page-title').find('a').on('click', function (e) {
            e.preventDefault();
            location.reload();
        });
        /*
        $('.switch-login').find('a').attr(
                "href",
                QHDomain.i360 + "/login?src=" + src + "&destUrl="
                        + encodeURIComponent(destUrl));
        $(".logo").parent().attr(
                "href",
                QHDomain.i360 + "/reg?src=" + src + "&destUrl="
                        + encodeURIComponent(destUrl));
        */
    });
</script>
</body>