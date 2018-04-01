(function(e) {
    "use strict";
    e.QUCi360 = {
        ui: {},
        init: function() {}
    };
    var t = {},
        n = {};
    QUCi360.ui.$modal = function(e) {
        function f() {
            n.remove(), i.remove()
        }
        e = e || {};
        var n = $('<div class="modal-mask">').css(t),
            r = $('<div class="modal-dialog"></div>'),
            i = $('<div class="modal-wrapper"></div>');
        i.append(r);
        if (e.content) {
            var s = $('<div class="modal-body"></div>');
            s.html(e.content), r.append(s)
        }
        var o = $('<div class="modal-footer"></div>'),
            u = $("<button>").addClass("modal-btn modal-btn-primary");
        u.text(e.confirmButtonText);
        var a = $("<button>").addClass("modal-btn modal-btn-secondary");
        a.text(e.cancelButtonText), o.append(a).append(u), r.append(o), u.on("click", function() {
            e.onconfirm && e.onconfirm(), f()
        }), a.on("click", function() {
            e.oncancel && e.oncancel(), f()
        }), $(document.body).append(n).append(i)
    }
})(window), function(e, t) {
    "use strict";

    function s(e) {
        var t = _.get(e, "data") || {};
        return t.o + "." + t.m
    }
    function u(e) {
        return i.each(e, function(t, n) {
            i.type(n) == "boolean" && (e[t] = n ? 1 : 0)
        }), e
    }
    function a(e, t) {
        t.url && (t.action = t.url, delete t.url), t.method = "post";
        var n = i("<form>").attr(t).hide();
        return i.each(e, function(t, r) {
            if (t == "usedPass") for (var s = 0, o = e.usedPass.length; s < o; s++) i("<input>").attr({
                type: "hidden",
                name: "usedPass[" + s + "]",
                value: e.usedPass[s]
            }).appendTo(n);
            else if (t == "imageUrls") for (var s = 0, o = e.imageUrls.length; s < o; s++) i("<input>").attr({
                type: "hidden",
                name: "imageUrls[" + s + "]",
                value: e.imageUrls[s]
            }).appendTo(n);
            else if (t == "recLoginPro") for (var s = 0, o = e.recLoginPro.length; s < o; s++) i("<input>").attr({
                type: "hidden",
                name: "recLoginPro[" + s + "]",
                value: e.recLoginPro[s]
            }).appendTo(n);
            else if (t == "recLoginCity") for (var s = 0, o = e.recLoginCity.length; s < o; s++) i("<input>").attr({
                type: "hidden",
                name: "recLoginCity[" + s + "]",
                value: e.recLoginCity[s]
            }).appendTo(n);
            else if (t == "isNeedId") for (var s = 0, o = e.isNeedId.length; s < o; s++) i("<input>").attr({
                type: "hidden",
                name: "isNeedId[" + s + "]",
                value: e.isNeedId[s]
            }).appendTo(n);
            else i("<input>").attr({
                    type: "hidden",
                    name: t,
                    value: r
                }).appendTo(n)
        }), n[0]
    }
    function f(e) {
        return !e || e.length == 32 ? e : t.utils.md5(e)
    }
    function l(e) {
        return function(n) {
            var r = i.Deferred();
            return n.errno && n.errno.toString().indexOf("login_captcha") == -1 ? n.errno = parseInt(n.errno, 10) : n.errno = t.ERROR.CAPTCHA_INVALID.errno, n.errno === e ? r.resolve(n) : (n.errmsg = t.utils.getErrorMsg(n.errno, n.errmsg || n.msg || ""), r.reject(n)), r.promise()
        }
    }
    function c(e, t) {
        i.each(t, function(t, n) {
            e[t] !== undefined && (e[n] = e[t], delete e[t])
        })
    }
    var n = window.logger || require("min-log"),
        r = n.getLogger("sync"),
        i = t.$,
        o = function(e, n, r) {
            r = r || t.getConfig("protocol").toLowerCase() == "https";
            var s = this.protocol = r ? "https" : "http",
                o = {
                    src: t.getConfig("src"),
                    from: t.getConfig("src"),
                    charset: t.getConfig("charset"),
                    requestScema: s
                },
                u = {
                    url: s + "://" + QHDomain.login_http.split("//")[1],
                    type: "GET",
                    dataType: "jsonp",
                    timeout: 2e4
                };
            e = e || {}, n = n || {}, this.protocol = s, this.param = i.extend(o, e), this.ajaxOpt = i.extend({
                data: this.param
            }, u, n), this.I360 = s + "://" + location.host
        };
    i.extend(o.prototype, {
        get: function(e) {
            var n = this;
            t.events.trigger("get.sync", {
                opt: this.ajaxOpt,
                url: e
            });
            var o = i.ajax(e, this.ajaxOpt);
            return o.done(function(e) {
                r.debug("get", s(n.ajaxOpt), n.ajaxOpt, e)
            }), o.then(this.done, function(t) {
                return n.fail({
                    method: "get",
                    url: e,
                    status: t
                })
            })
        },
        post: function(e) {
            t.events.trigger("post.sync", {
                opt: this.ajaxOpt,
                url: e
            });
            var n = i.Deferred(),
                o = null,
                u = t.utils.getGuid(),
                f = "QiUserJsonp" + u,
                l = "QucI360Form" + u,
                c = "QucI360Iframe" + u,
                h = this,
                p = i('<iframe name="' + c + '">').hide(),
                d = i.extend({}, this.param, {
                    proxy: t.getConfig("proxy"),
                    callback: f,
                    func: f
                }),
                v = a(d, {
                    name: l,
                    target: c,
                    url: e || this.ajaxOpt.url
                });
            return window[f] = function(e) {
                clearTimeout(o);
                var t;
                for (var u in e) e.hasOwnProperty(u) && (t = decodeURIComponent(e[u]), t.match(/^(\{.*\})|(\[.*\])$/) && (t = i.parseJSON(t)), e[u] = t);
                n.resolve(e), r.debug("post", s(h.ajaxOpt), h.ajaxOpt, e)
            }, o = setTimeout(function() {
                n.reject({
                    method: "post",
                    url: e,
                    status: {
                        status: 0,
                        statusText: "post \u8bf7\u6c42\u8d85\u65f6"
                    }
                })
            }, this.ajaxOpt.timeout), n.always(function() {
                i(p).remove(), i(v).remove();
                try {} catch (e) {}
            }), i(document.body).append(p).append(v), i(v).submit(), n.then(function(e) {
                return h.done(e)
            }, function(e) {
                return h.fail(e)
            })
        },
        done: l(0),
        fail: function(e) {
            if (this.protocol == "https" && location.protocol == "http:") return this.retryHttp(e);
            var n = i.Deferred();
            return n.reject({
                errno: 999999,
                errmsg: i.type(e) == "string" ? e : "\u7f51\u7edc\u9519\u8bef"
            }), t.events.trigger("error.sync", e.url || this.ajaxOpt.url), n.promise()
        },
        getDomainApi: function(e) {
            return e = e || location.hostname.replace(/^(?:.+\.)?(\w+\.\w+)$/, "$1"), this.protocol + "://login." + e
        },
        retryHttp: function(e) {
            this.protocol = "http", this.ajaxOpt.url = this.ajaxOpt.url.replace(/^https/, "http"), this.I360 = this.I360.replace(/^https/, "http");
            var n = e.url && e.url.replace(/^https/, "http");
            return t.events.trigger("retryHttp.sync", n || this.ajaxOpt.url), this[e.method](n)
        }
    }), e.sync = {
        getNewEmailStatus: function(e) {
            var t = new o(e);
            return t.post(t.I360 + "/complaint/checkLoginEmail")
        },
        submitComplaintInfo: function(e) {
            var t = new o(e, {}, !0);
            return t.post(t.I360 + "/complaint/secondStep")
        },
        submitAllData: function(e) {
            var t = new o(e, {}, !0);
            return t.post(t.I360 + "/complaint/doadd")
        },
        sendVerifyActivationEmail: function(e) {
            var t = new o({
                crumb: e
            });
            return t.post(t.I360 + "/active/doSetLoginEmail")
        },
        checkSecEmailStatus: function(e) {
            var t = new o({
                crumb: e
            });
            return t.get(t.I360 + "/security/checkSecEmailStatus")
        },
        checkMobileNumberExist: function(e) {
            var t = new o({
                o: "User",
                m: "checkmobile",
                mobile: e
            });
            return t.post()
        },
        modifyMobile: function(e, t, n) {
            var t = t.areaCode + t.mobileNumber,
                r = new o({
                    o: "user",
                    m: "modifyMobile",
                    crumb: e,
                    newmobile: t,
                    smscode: n
                }, {}, !0);
            return r.post()
        },
        delBindMobile: function(e) {
            var t = new o({
                o: "user",
                m: "delMobile",
                crumb: e
            }, {}, !0);
            return t.post()
        },
        modifyEmail: function(e, t) {
            var n = new o({
                crumb: e,
                loginEmail: t
            }, {}, !0);
            return n.post(n.I360 + "/active/doModifyLoginEmail")
        },
        modifySecEmail: function(e, t) {
            var n = new o({
                crumb: e,
                newsecEmail: t
            }, {}, !0);
            return n.post(n.I360 + "/profile/domodifysecemail")
        },
        setCommonArea: function(e, t, n) {
            var r = new o({
                o: "user",
                m: "setComArea",
                crumb: e,
                pro: t,
                city: n
            }, {}, !0);
            return r.get()
        },
        delBindThirdAccount: function(e, t) {
            var n = new o({
                client_id: e,
                bind_uid: t
            }, {}, !0);
            return n.post(n.I360 + "/profile/dodelbind")
        },
        delBindDev: function(e, t) {
            var n = new o({
                o: "user",
                m: "unbindDevice",
                crumb: e,
                devauthid: t
            }, {}, !0);
            return n.post()
        },
        modifyDevName: function(e, t, n) {
            var r = new o({
                o: "user",
                m: "modifyDevName",
                crumb: e,
                devauthid: t,
                devname: n
            }, {}, !0);
            return r.post()
        },
        getFindPwdToken: function(e) {
            var t = new o({
                account: e
            });
            return t.post(t.I360 + "/findpwd/getIndexVC")
        },
        getFindPwdWays: function(e, t, n) {
            var r = new o({
                account: e,
                captcha: t,
                src: n,
                from: n
            });
            return r.post(r.I360 + "/findpwdwap/getUserSecInfo")
        },
        getFindPwdWaysPc: function(e, t, n, r) {
            var i = new o({
                account: e,
                captcha: t,
                vc: r,
                src: n,
                from: n
            });
            return i.post(i.I360 + "/findpwd/getUserSecInfo")
        },
        checkWeakPwd: function(e) {
            var t = new o({
                password: f(e)
            });
            return t.get(t.I360 + "/reg/checkWeakPwd")
        },
        sendToEmail: function(e, t, n) {
            var r = new o({
                vc: e,
                skin: t,
                client: n
            });
            return r.post(r.I360 + "/findpwdwap/doSendLoginEmail")
        },
        sendToEmailPc: function(e) {
            var t = new o({
                vc: e
            });
            return t.post(t.I360 + "/findpwd/doSendLoginEmail")
        },
        sendToSecEmail: function(e, t, n) {
            var r = new o({
                vc: e,
                skin: t,
                client: n
            });
            return r.post(r.I360 + "/findpwdwap/doSendSecEmail")
        },
        sendToSecEmailPc: function(e) {
            var t = new o({
                vc: e
            });
            return t.post(t.I360 + "/findpwd/doSendSecEmail")
        },
        sendSmsTokenNeedPhrase: function(e, t, n, r, i) {
            typeof e == "boolean" && (n = t, t = e, r = n, i = r, e = null);
            var s = t ? 1 : 2,
                u = {
                    o: "User",
                    m: "sendSmsCodeNew",
                    condition: s,
                    account: n,
                    crumb: e,
                    captcha: r,
                    vt: i
                },
                a = new o(u);
            return a.post()
        },
        validSmsToken: function(e, t) {
            var n = new o({
                smscode: e,
                vc: t
            }, {}, !0);
            return n.post(n.I360 + "/findpwdwap/doCheckSmsCode")
        },
        validSmsTokenPc: function(e, t) {
            var n = new o({
                smscode: e,
                vc: t
            }, {}, !0);
            return n.post(n.I360 + "/findpwd/doCheckSmsCode")
        },
        findPwdBySecQuestion: function(e, t) {
            var n = new o({
                secAnswer: e,
                vc: t
            }, {}, !0);
            return n.post(n.I360 + "/findpwdwap/doAnswerSecQuestion")
        },
        modifySecWarn: function(e, t) {
            var n = new o({
                o: "user",
                m: "modifySecWarn",
                sensop: e,
                crumb: t
            }, {}, !0);
            return n.post()
        },
        checkabnormality: function(e, t) {
            var n = new o({
                account: e,
                captcha: t
            }, {}, !0);
            return n.post(n.I360 + "/findpwd/checkAccount")
        },
        getSecAnswers: function(e, t) {
            var n = new o({
                account: e,
                vc: t
            }, {}, !0);
            return n.post(n.I360 + "/findpwd/getSecAnswers")
        },
        checkSecAnswer: function(e) {
            var t = new o({
                vc: e.vc,
                account: e.account,
                answer_apps: e.answer_apps,
                answer_login: e.answer_login
            }, {}, !0);
            return t.post(t.I360 + "/findpwd/checkSecAnswer")
        },
        findPwdBySecQuestionPc: function(e, t) {
            var n = new o({
                secAnswer: e,
                vc: t
            }, {}, !0);
            return n.post(n.I360 + "/findpwd/doAnswerSecQuestion")
        },
        setPassword: function(e, t) {
            var n = new o({
                newPassword: t,
                vc: e
            }, {}, !0);
            return n.post(n.I360 + "/findpwdwap/doResetPwd")
        },
        setPasswordPc: function(e, t) {
            var n = new o({
                newPassword: t,
                vc: e
            }, {}, !0);
            return n.post(n.I360 + "/findpwd/doResetPwd")
        },
        modifyPassword: function(e, t) {
            var n = new o({
                o: "user",
                m: "modifyPwd",
                newPwd: f(t),
                rePwd: f(t),
                crumb: e
            }, {}, !0);
            return n.post()
        },
        modifyUserInfo: function(e) {
            var t = new o(e, {}, !0);
            return t.post(t.I360 + "/profile/doprofile")
        },
        modifyLoginMethod: function(e, t) {
            var n = t == "open" ? 1 : 0,
                r = new o({
                    o: "user",
                    m: "modifyLoginMethod",
                    toValue: n,
                    loginMethod: 1,
                    crumb: e
                }, {}, !0);
            return r.post()
        },
        validAccount: function(e) {
            var t = new o(e, {}, !0);
            return t.post(t.I360 + "/complaint/firstStep")
        },
        validEmail: function(e) {
            var t = new o(e, {}, !0);
            return t.post(t.I360 + "/complaint/secondStep")
        },
        _key0330: "...",
        perfectMobile: function(e, t, n, r) {
            var i = new o({
                o: "user",
                m: "perfectMobile",
                crumb: e,
                mobile: t,
                password: n,
                rePassword: n,
                smscode: r
            }, {}, !0);
            return i.post()
        },
        submitComplaint: function(e) {
            var t = new o(e, {}, !0);
            return t.post(t.I360 + "/complaint/doadd")
        },
        sendEmailToken: function(e, t, n, r) {
            var i = {
                    condition: 3,
                    account: e,
                    acctype: t,
                    email: n,
                    vc: r
                },
                s = new o(i);
            return s.post(s.I360 + "/complaint/sendEmsCodeForComplaint")
        },
        sendEmsCode: function(e, t) {
            var n = {
                    o: "User",
                    m: "sendEmsCode",
                    charset: "UTF-8",
                    requestScema: "http",
                    condition: 3,
                    crumb: e,
                    email: t,
                    vtype: ""
                },
                r = new o(n);
            return r.post()
        },
        cancelAccount: function(e) {
            var t = {
                    reason: e.reason,
                    email: e.email,
                    vc: e.emscode,
                    opinion: e.opinion
                },
                n = new o(t);
            return n.post(n.I360 + "/cancel/cancelAccount")
        },
        getHistoryInfo: function() {
            var e = new o;
            return e.post(e.I360 + "/cancel/getHistoryInfo")
        },
        checkBindStatus: function() {
            var e = new o;
            return e.post(e.I360 + "/cancel/checkBindStatus")
        },
        validSecWays: function(e, t, n) {
            var r = {
                    vtype: t,
                    vc: n,
                    crumb: e
                },
                i = new o(r, {}, !0);
            return i.post(i.I360 + "/task/checkSecWay")
        },
        idCertificate: function(e, t, n, r) {
            var i = new o({
                crumb: e,
                cardType: n,
                name: t,
                cardNo: r
            }, {}, !0);
            return i.post(i.I360 + "/identity/verify")
        }
    };
    var h = {};
    i.each(t.sync, function(e, n) {
        var r = function() {
            var r = arguments[0],
                s = e + (i.isPlainObject(r) ? t.utils.JSON.stringify(r) : [].join.apply(arguments)),
                o = h[s];
            return o ? h[s] : (o = h[s] = n.apply(t.sync, arguments), o.always(function() {
                delete h[s]
            }), o)
        };
        r.funcName = n.funcName = "sync." + e, t.sync[e] = r
    })
}(QUCi360, QHPass), function(e, t) {
    "use strict";
    var n = t.$,
        r = null,
        i = function(e) {
            this.name = "func_" + t.utils.getGuid(), this.extend(e), this._initFlag = !1, this._data = {}
        };
    n.extend(i.prototype, {
        init: function() {
            var t = this;
            return t._initFlag ? t.reset() : (t._initFlag = !0, t.setUI(e.ui[t.name]), t.setDeferred(), t.trigger("init"), t.on("show", function() {
                t._isShown = !0
            }), t.on("hide", function() {
                t._isShown = !1
            })), t._passThrough = r, r = null, t
        },
        reset: function() {
            return this._isShown && this.trigger("hide"), this.setDeferred(), this
        },
        isInit: function() {
            return this._initFlag
        },
        get: function(e, t) {
            var n = this._data[e];
            return n !== undefined ? n : t
        },
        set: function(e, t) {
            return n.isPlainObject(e) ? n.extend(this._data, e) : this._data[e] = t, this
        },
        setDeferred: function(e) {
            var r = this;
            return r._deferred = e || n.Deferred(), r._deferred.done(function(e) {
                r._callback && t.utils.parseCallback(r._callback)(e)
            }), r
        },
        resolve: function(e) {
            return this._deferred && this._deferred.resolve(e), this
        },
        getCallback: function() {
            return this._callback
        },
        setCallback: function(e) {
            return this._callback = e, this
        },
        clear: function() {
            return this._data = {}, this
        },
        getUI: function() {
            return this.ui
        },
        setUI: function(e) {
            return this.ui = e, e.init(this), this
        },
        getPassThrough: function() {
            return this._passThrough
        },
        setPassThrough: function(e) {
            r = e
        },
        reportError: function(e, n, r) {
            n = n ? "Msg:" + n + " " : "", e.errno ? n = n + "Error:(" + e.errno + ")" + e.errmsg : n += e.toString(), t.events.trigger((r ? "warn." : "error.") + this.name, n)
        },
        reportWarn: function(e, t) {
            this.reportError(e, t, !0)
        },
        extend: function() {
            var e = [].slice.apply(arguments);
            e.unshift(this), n.extend.apply(null, e)
        },
        setCaptchaUrl: function(e) {
            this._captchaUrl = e
        },
        getCaptchaUrl: function(e) {
            var r = this,
                i = r._captchaUrl,
                s = n.Deferred();
            return i ? (i += "&_=" + (new Date).getTime(), s.resolve(i)) : t.sync.getCaptchaUrl(e).then(function(e) {
                r._captchaUrl = e.captchaUrl, i += "&_=" + (new Date).getTime(), s.resolve(i)
            }), s.promise()
        }
    }), n.each(["on", "one", "off", "trigger"], function(e, n) {
        i.prototype[n] = function() {
            return arguments[0] = arguments[0].replace(/( |$)/g, "." + this.name + "$1"), t.events[n].apply(null, arguments), this
        }
    }), e.getLogic = function(e) {
        return new i(e)
    }
}(QUCi360, QHPass), function(e, t) {
    "use strict";
    var n = {},
        r, i = e.getLogic({
            name: "identify",
            validate: function(e, n) {
                var r = t.validate,
                    i;
                n = n || this.get(e);
                switch (e.toLowerCase()) {
                    case "token":
                        i = r.checkSmsToken(n);
                        break;
                    case "password":
                        i = r.checkPassword(n), i && (i = r.checkPassword(n).errno == 211 ? r.checkPassword(n) : t.ERROR.PASSWORD_WRONG);
                        break;
                    case "captcha":
                        i = r.checkCaptcha(n)
                }
                return i
            },
            getMethods: function() {
                return n[r]
            },
            prepareMethods: function(r) {
                var i = this;
                return n[r] ? $.Deferred().resolve().promise() : t.getUserInfo().then(function(e) {
                    return t.sync.getIdentifyMethod(e.crumb, r)
                }).done(function(e) {
                    n[r] = e.ways
                }).fail(function(t) {
                    e.utils.panel({
                        type: "fail",
                        message: t.errmsg,
                        supplement: ""
                    })
                })
            },
            sendToken: function(e) {
                var n = this;
                return e = e || this.get("method"), t.getUserInfo().then(function(n) {
                    return e == "secMobile" ? t.sync.sendSmsToken(n.crumb, !0) : t.sync.sendEmailToken(n.crumb, e)
                }).fail(function(e) {
                    n.trigger("invalid", e)
                })
            },
            run: function(e, t) {
                var n = this;
                n.init(), r = e, n.trigger("show", t)
            },
            submit: function() {
                var e = this,
                    n = e.get("token"),
                    i = e.get("method"),
                    s = e.get("password"),
                    o = e.get("captcha"),
                    u = $.Deferred();
                this.trigger("beforeSubmit");
                var a = i == "pwd" ? e.validate("password") || e.validate("captcha") : e.validate("token");
                return a ? u.reject(a) : u.resolve(), u.then(function(e) {
                    return t.getUserInfo()
                }).then(function(e) {
                    var u = i == "pwd" ? s : n;
                    return t.sync.identify(e.crumb, r, i, u, o)
                }).done(function(t) {
                    e.trigger("hide").trigger("success")
                }).fail(function(t) {
                    var n = t.errdetail && t.errdetail.captchaUrl;
                    n && e.setCaptchaUrl(n), t.fromServer = !0, e.trigger("invalid", t)
                })
            }
        }),
        s = {
            bindMobile: "bindMobile",
            modifyMobile: "modifyMobile",
            delBindMobile: "delMobile",
            setEmail: "setLoginEmail",
            modifyEmail: "modifyLoginEmail",
            identifyEmail: "verifyLoginEmail",
            setSecEmail: "setSecEmail",
            modifySecEmail: "modifySecEmail",
            modifyPassword: "modifyPwd",
            setCommonArea: "setComArea",
            closeTextLogin: "closeCodeLogin",
            secwarnopen: "secwarnopen",
            secwarnclose: "secwarnclose",
            cancelAccount: "cancelAccount"
        };
    e.identify = function(e) {
        var n = s[e.name],
            r;
        return e.one("beforeShow", function(e, t) {
            r = t, i.run(n, t)
        }), e.on("invalid", function(e, s) {
            t.utils.isSameError(s, t.ERROR.IDENTIFY_EXPIRE) && i.run(n, r)
        }), e.on("reset", function() {
            i.run(n, r)
        }), i.prepareMethods(n)
    }
}(QUCi360, QHPass), function(e, t) {
    "use strict";
    var n = t.$,
        r = '<div class="quc-mod-identify"><form class="quc-form"><div class="quc-tip-wrapper"><p class="quc-tip">\u4e3a\u4e86\u4fdd\u62a4\u60a8\u7684\u5e10\u53f7\u5b89\u5168\uff0c\u64cd\u4f5c\u524d\u8bf7\u60a8\u8fdb\u884c\u5b89\u5168\u9a8c\u8bc1</p></div><div class="quc-other-wrapper"><p class="quc-field quc-field-method quc-clearfix"><label class="quc-label">\u9a8c\u8bc1\u65b9\u5f0f</label><select class="quc-select quc-select-method" name="secMethod"></select><span class="quc-input quc-input-method"></span><a class="quc-method-tip quc-link" href="' + QHDomain.i360 + '/complaint" target="_blank" tabindex="99">\u9a8c\u8bc1\u65b9\u5f0f\u90fd\u4e0d\u80fd\u7528\u4e86\uff1f</a>' + "</p>" + '<p class="quc-field quc-field-token quc-input-middle quc-clearfix">' + '<label class="quc-label">\u6821\u9a8c\u7801</label>' + '<span class="quc-input-bg">' + '<input class="quc-input quc-input-token" type="tel" name="token" maxlength="6" placeholder="\u8bf7\u8f93\u51656\u4f4d\u6821\u9a8c\u7801" />' + "</span>" + '<a href="#" class="quc-button quc-button-blue quc-get-token">\u514d\u8d39\u83b7\u53d6\u6821\u9a8c\u7801</a>' + '<span class="quc-tip"></span>' + '<a class="quc-secMobileOnly-tip" href="' + QHDomain.i360 + '/complaint" target="_blank" tabindex="99">\u624b\u673a\u53f7\u4e0d\u7528\u4e86\uff1f</a>' + '<a class="quc-token-tip quc-link" href="#" target="_blank" tabindex="99"></a>' + "</p>" + "</div>" + '<div class="quc-password-wrapper">' + '<p class="quc-field quc-field-password quc-input-long quc-clearfix">' + '<label class="quc-label">\u767b\u5f55\u5bc6\u7801</label>' + '<span class="quc-input-bg">' + '<input class="quc-input quc-input-password" type="password" name="password" maxlength="20" placeholder="\u8bf7\u8f93\u5165\u60a8\u7684\u767b\u5f55\u5bc6\u7801" />' + "</span>" + '<i class="quc-tip-icon"></i>' + '<a class="quc-token-tip quc-link" href="' + QHDomain.i360 + '/findpwd/" target="_blank" tabindex="99">\u5fd8\u8bb0\u5bc6\u7801\uff1f</a>' + "</p>" + '<p class="quc-field quc-field-captcha quc-input-short">' + '<label class="quc-label">\u9a8c\u8bc1\u7801</label>' + '<span class="quc-input-bg">' + '<input class="quc-input quc-input-captcha" type="text" name="phrase" maxlength="7" autocomplete="off" placeholder="\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801" />' + "</span>" + '<img class="quc-captcha-img quc-captcha-change" alt="\u9a8c\u8bc1\u7801" title="\u70b9\u51fb\u66f4\u6362" tabindex="99" /> ' + '<a class="quc-link quc-captcha-change-link quc-captcha-change" href="#">\u6362\u4e00\u5f20</a>' + "</p>" + "</div>" + '<p class="quc-field quc-field-submit">' + '<input class="quc-button quc-button-primary quc-button-submit" type="submit" value="\u63d0\u4ea4" />' + "</p>" + "</form>" + "</div>",
        i = {
            init: function(e) {
                this.model = e, this.initModelEvent(), this.initMethods(), this.initToken(), this.initErrorTip()
            },
            selElement: function() {
                this.$el && this.$el.remove(), this.$el = n(r), this.initElementEvent()
            },
            initToken: function() {
                var e = this,
                    n = t.utils.getTimer("identify"),
                    r, i = "quc-button-disabled";
                n.on("timer_start", function(e, t) {
                    r.addClass(i), r.html(t + "\u79d2\u540e\u53ef\u91cd\u65b0\u83b7\u53d6")
                }), n.on("timer_tick", function(e, t) {
                    r.html(t + "\u79d2\u540e\u53ef\u91cd\u65b0\u83b7\u53d6")
                }), n.on("timer_stop", function() {
                    r.html("\u91cd\u65b0\u83b7\u53d6\u6821\u9a8c\u7801"), r.removeClass(i)
                });
                var s = function(t) {
                    t.preventDefault();
                    if (r.hasClass(i)) return;
                    r.html("\u53d1\u9001\u4e2d..."), r.addClass(i), e.model.sendToken().done(function(t) {
                        n.start(), e.$el.find(".quc-tip-wrapper .quc-tip").removeClass("quc-tip-error").addClass("quc-tip-success").html("\u53d1\u9001\u6210\u529f\uff0c\u8bf7\u67e5\u6536\uff01")
                    }).fail(function(t) {
                        r.html("\u514d\u8d39\u83b7\u53d6\u6821\u9a8c\u7801"), r.removeClass(i), e.model.trigger("invalid", t)
                    })
                };
                e.model.on("show", function() {
                    r = e.$el.find(".quc-get-token"), r.on("click", s), n.isRunning() && n.resume()
                })
            },
            initElementEvent: function() {
                var e = this;
                this.$el.find(".quc-input").focus(function() {
                    n(this).parent().addClass("quc-input-bg-focus")
                }).blur(function() {
                    n(this).parent().removeClass("quc-input-bg-focus")
                }), this.$el.find(".quc-form").submit(function(t) {
                    t.preventDefault(), e.submit()
                }), this.$el.find(".quc-select-method").on("change", function() {
                    var t = n(this).val();
                    t == "secMobile" ? e.$el.find(".quc-sec-help").children("a").attr("href", QHDomain.i360 + "/findpwd/customerhelper#mobiledisabled") : e.$el.find(".quc-sec-help").children("a").attr("href", QHDomain.i360 + "/findpwd/customerhelper#recieveemailcode")
                })
            },
            initMethods: function() {
                var e = this;
                this.model.on("show", function() {
                    var t = e.$el.find(".quc-select-method"),
                        r = !0,
                        i = e.model.getMethods(),
                        s = [];
                    n.each(i, function(i, o) {
                        r && (e.changeMethod(i, o.captchaUrl), r = !1), n("<option>").val(i).html(o.name + "(" + o.value + ")").appendTo(t), s.push(i)
                    });
                    if (s.length == 1) {
                        var o = i[s[0]];
                        s == "secMobile" ? (e.$el.find(".quc-secMobileOnly-tip").show(), e.$el.find(".quc-input-method").html(o.name + "(" + o.value + ")")) : e.$el.find(".quc-input-method").html(o.name + "(" + o.value + ")"), t.parent().addClass("quc-method-single")
                    }
                    t.change(function() {
                        e.changeMethod(t.val())
                    }), r && e.changeMethod("pwd")
                })
            },
            changeMethod: function(e, t) {
                var n = this.$el.find(".quc-token-tip"),
                    r = this.$el.find(".quc-field-captcha"),
                    i = this,
                    s = function(e) {
                        i.model.getCaptchaUrl("login").then(function(t) {
                            r.find(".quc-captcha-img").attr("src", t);
                            var n = r.find(".quc-input-captcha").val("");
                            e && n.focus()
                        })
                    };
                e == "pwd" ? (this.$el.find(".quc-other-wrapper").hide(), this.$el.find(".quc-password-wrapper").children().show(), t && (this.model.setCaptchaUrl(t), s()), this.$el.find(".quc-captcha-change").click(function(e) {
                    e.preventDefault(), s()
                })) : e == "secMobile" ? n.attr("href", QHDomain.i360 + "/findpwd/customerhelper#mobiledisabled").html("\u6536\u4e0d\u5230\u77ed\u4fe1\u6821\u9a8c\u7801\uff1f") : n.attr("href", QHDomain.i360 + "/findpwd/customerhelper#recieveemailcode").html("\u6536\u4e0d\u5230\u90ae\u4ef6\u6821\u9a8c\u7801\u600e\u4e48\u529e\uff1f"), this.model.set("method", e), this.model.on("invalid", function(e, t) {
                    t.fromServer && s()
                })
            },
            initModelEvent: function() {
                var e = this;
                e.model.on("show", function(t, n) {
                    e.show(n)
                }).on("hide", function() {
                    e.hide()
                })
            },
            submit: function() {
                var e = this,
                    t = e.$el.find(".quc-button-submit"),
                    n = e.$el.find(".quc-input-token").val(),
                    r = e.$el.find(".quc-input-password").val(),
                    i = e.$el.find(".quc-input-captcha").val();
                t.prop("disabled", !0).val("\u63d0\u4ea4\u4e2d..."), e.model.one("invalid", function() {
                    t.prop("disabled", !1).val("\u63d0\u4ea4")
                }), e.model.set("token", n), e.model.set("password", r), e.model.set("captcha", i), e.model.submit()
            },
            initErrorTip: function() {
                var e = this;
                e.model.on("invalid", function(t, n) {
                    var r = e.$el.find(".quc-tip-wrapper .quc-tip");
                    r.removeClass("quc-tip-success").addClass("quc-tip-error").html(n.errmsg)
                })
            },
            show: function(e) {
                this.$orgForm = n(e), this.selElement(), this.model.trigger("beforeShow", this.$el[0]);
                if (this.$orgForm.parents(".quc-panel").length > 0) this.$orgForm.parents(".quc-panel").find(".quc-mod-active-email").remove(), this.$orgForm.hide().before(this.$el);
                else {
                    var r = this.panel = new t.utils.Panel;
                    r.setTitle(t.getConfig("identify.panelTitle", "\u9700\u8981\u6821\u9a8c\u60a8\u7684\u8eab\u4efd")).setContent(this.$el).show()
                }
                this.$orgForm.triggerHandler("QucDOMUpdated"), this.model.trigger("afterShow", this.$el[0])
            },
            hide: function() {
                this.model.trigger("beforeHide", this.$el[0]), this.panel ? this.panel.hide() : (this.$el.remove(), this.$orgForm.show()), this.$orgForm.triggerHandler("QucDOMUpdated"), this.model.trigger("afterHide", this.$el[0])
            }
        };
    e.ui.identify = {
        init: function() {
            i.init.apply(i, arguments)
        }
    }
}(QUCi360, QHPass), function(e, t) {
    "use strict";
    var n = t.$,
        r = '<div class="quc-mod-active-email"><p class="quc-send-result">\u9a8c\u8bc1\u90ae\u4ef6\u5df2\u7ecf\u53d1\u9001\u5230\u60a8\u7684\u90ae\u7bb1:</p><p class="quc-email-address"><a href="#" class="quc-link quc-link-jump" target="_blank">\u53bb\u6fc0\u6d3b</a></p><p class="quc-tip">\u8bf7\u60a8\u572848\u5c0f\u65f6\u5185\u767b\u5f55\u90ae\u7bb1\u5b8c\u6210\u9a8c\u8bc1</p><p class="quc-button-wrapper"><a href="#" class="quc-button quc-button-blue quc-button-activated" target="_blank">\u5df2\u5b8c\u6210\u6fc0\u6d3b</a></p><div class="quc-resend"><p>\u6ca1\u6536\u5230\u90ae\u4ef6\u600e\u4e48\u529e\uff1f</p><p class="quc-modify-wrapper">&middot; \u90ae\u7bb1\u586b\u9519\u4e86\uff0c<a href="#" class="quc-link quc-link-modify">\u91cd\u65b0\u4fee\u6539\u90ae\u7bb1</a></p><p>&middot; <a href="#" class="quc-link quc-link-resend">\u91cd\u65b0\u53d1\u9001</a>\u9a8c\u8bc1\u90ae\u4ef6&nbsp;<span class="quc-resend-result"></span></p><p>&middot; \u4f9d\u7136\u6536\u4e0d\u5230\u90ae\u4ef6\uff0c<a class="quc-link" href="' + QHDomain.i360 + '/findpwd/customerhelper#recieveemailcode" target="_blank">\u67e5\u770b\u5e2e\u52a9</a></p>' + "</div>" + "</div>",
        i = {
            init: function(e) {
                this.model = e, this.initModelEvent()
            },
            initModelEvent: function() {
                var e = this;
                e.model.on("show", function(t, n) {
                    e.show(n)
                }).on("hide", function() {
                    e.hide()
                }).on("resendSuccess", function() {
                    e.$el.find(".quc-resend-result").removeClass("quc-resending quc-tip-error").html("\u53d1\u9001\u6210\u529f\uff01").show()
                }).on("resendFail", function(t, n) {
                    e.$el.find(".quc-resend-result").removeClass("quc-resending").addClass("quc-tip-error").css("color", "red").html(n.errmsg).show()
                })
            },
            setElement: function() {
                var e = ["signUp", "identifyEmail", "identifySecEmail"],
                    t = this;
                t.$el && t.$el.remove(), t.$el = n(r);
                var i = this.model.getEmailInfo();
                i.site && t.$el.find(".quc-link-jump").attr("href", i.site).show(), n.inArray(i.type, e) > -1 && t.$el.find(".quc-modify-wrapper").hide(), t.$el.find(".quc-email-address").prepend(i.email), t.$el.find(".quc-link-resend").click(function(e) {
                    e.preventDefault(), t.model.resend(), t.$el.find(".quc-resend-result").addClass("quc-resending").html("\u53d1\u9001\u4e2d...").show()
                }), t.$el.find(".quc-link-modify").click(function(e) {
                    e.preventDefault(), t.hide(), t.model.modify(i.type)
                }), t.$el.find(".quc-button-activated").click(function(e) {
                    e.preventDefault();
                    var r = n(this);
                    r.html("\u68c0\u67e5\u6fc0\u6d3b\u72b6\u6001...").addClass("quc-button-disabled"), t.model.checkActive().fail(function() {
                        var e = i.site ? '<a class="quc-link" href="' + i.site + '" target="_blank">\u767b\u5f55\u90ae\u7bb1</a>' : "\u767b\u5f55\u90ae\u7bb1";
                        t.$el.find(".quc-tip").addClass("quc-tip-error").html("\u60a8\u4f3c\u4e4e\u8fd8\u6ca1\u6709\u6fc0\u6d3b\uff0c\u8bf7" + e + "\uff0c\u5e76\u6309\u90ae\u4ef6\u5185\u7684\u63d0\u793a\u64cd\u4f5c\u3002"), r.html("\u5df2\u5b8c\u6210\u6fc0\u6d3b").removeClass("quc-button-disabled")
                    })
                })
            },
            show: function() {
                this.$orgForm = n(this.model.getEmailInfo().el), this.setElement(), this.model.trigger("beforeShow", this.$el[0]);
                if (this.$orgForm.parents(".quc-panel").length > 0) this.$orgForm.hide().before(this.$el);
                else {
                    var e = this.panel = new t.utils.Panel;
                    e.setTitle("\u8bf7\u6fc0\u6d3b\u60a8\u7684\u90ae\u7bb1").setContent(this.$el).show()
                }
                this.$orgForm.triggerHandler("QucDOMUpdated"), this.model.trigger("afterShow", this.$el[0])
            },
            hide: function() {
                this.model.trigger("beforeHide", this.$el[0]), this.panel ? this.panel.hide() : (this.$el.remove(), this.$orgForm.show()), this.$orgForm.triggerHandler("QucDOMUpdated"), this.model.trigger("afterHide", this.$el[0])
            }
        };
    e.ui.activeEmail = {
        init: function() {
            i.init.apply(i, arguments)
        }
    }
}(QUCi360, QHPass), function(e, t) {
    "use strict";
    var n = {},
        r = ["setEmail", "modifyEmail", "identifyEmail", "resendEmail"],
        i = ["setSecEmail", "modifySecEmail", "identifySecEmail", "resendSecEmail"],
        s = e.getLogic({
            name: "activeEmail",
            run: function(e) {
                var s = this;
                s.init();
                if (n.type == "signUp") {
                    n.activeUrl = e.activeurl, n.email = e.email;
                    if (n.wrapper) {
                        location.href = e.activeurl;
                        return
                    }
                    t.sync.sendSignUpActivationEmail(e.activeurl).then(function(e) {
                        n.site = e.goToMail, s.trigger("show")
                    })
                } else {
                    if ($.inArray(n.type, r) > -1) n.type == "identifyEmail" ? n.email = e.safeLoginEmailUnactivated : n.email = e.loginEmailUnactivated;
                    else {
                        if (!($.inArray(n.type, i) > -1)) {
                            this.trigger("error", "");
                            return
                        }
                        n.email = e.safeSecEmail
                    }
                    n.site = e.mailHostUrl, s.trigger("show")
                }
            },
            getEmailInfo: function() {
                return n
            },
            resend: function() {
                var e = this,
                    s = $.when();
                return n.type == "signUp" ? s = t.sync.sendSignUpActivationEmail(n.activeUrl) : $.inArray(n.type, r) > -1 ? s = t.getUserInfo().then(function(e) {
                    return t.sync.sendActivationEmail(e.crumb)
                }) : $.inArray(n.type, i) > -1 && (s = t.getUserInfo().then(function(e) {
                    return t.sync.sendSecActivationEmail(e.crumb)
                })), s.done(function(t) {
                    e.trigger("resendSuccess", t)
                }).fail(function(t) {
                    e.trigger("resendFail", t)
                })
            },
            modify: function(r) {
                var i = null;
                switch (r) {
                    case "resendSecEmail":
                        i = QHPass.getConfig("secEmail.successCB"), t.getUserSecInfo(function(n) {
                            n.safeSecEmail ? e.modifySecEmail(i) : t.setSecEmail(i)
                        });
                        break;
                    case "resendEmail":
                        i = QHPass.getConfig("email.successCB"), t.getEmailStatus(function(n) {
                            n.loginEmail ? e.modifyEmail(i) : t.setEmail(i)
                        });
                        break;
                    default:
                        n.model.trigger("reset")
                }
            },
            checkActive: function() {
                var i = this,
                    s = $.Deferred();
                return n.type == "signUp" ? t.sync.checkEmailExist(n.email).always(function(e) {
                    t.utils.isSameError(e, t.ERROR.EMAIL_NOT_ACTIVATED) ? s.reject() : s.resolve()
                }) : $.inArray(n.type, r) > -1 ? t.getEmailStatus(function(e) {
                    n.type == "setEmail" ? e.loginEmail ? s.resolve() : s.reject() : e.needActive ? s.reject() : s.resolve()
                }) : n.type == "setSecEmail" ? t.getUserSecInfo(function(e) {
                    e.safeSecEmail ? s.resolve() : s.reject()
                }) : t.getUserInfo().then(function(t) {
                    return e.sync.checkSecEmailStatus(t.crumb)
                }).done(function(e) {
                    e.seUatd ? s.reject() : s.resolve()
                }), s.then(function() {
                    i.trigger("hide").trigger("success").resolve()
                }), s.promise()
            }
        });
    e.activeEmail = function(e, t) {
        s.setCallback(t).run(e)
    }, e.activeEmail.prepare = function(e, t) {
        n.model = e, n.wrapper = t, n.type = e.name, e.on("beforeShow", function(e, t) {
            n.el = t
        })
    }
}(QUCi360, QHPass), function(e) {
    e.utils = {
        getParams: function(e) {
            var t = {},
                n = e ? e.split("?")[1].split("&") : location.search.substr(1).split("&");
            return $.each(n, function(e, n) {
                var r = n.split("=");
                t[r[0]] = decodeURIComponent(r[1])
            }), t
        },
        addRandomParam: function() {
            var e = QHPass.utils.md5,
                t = $("a"),
                n = /\//,
                r = /\?/,
                i = /#/,
                s = "";
            $.each(t, function() {
                var t = $(this).attr("href"),
                    o = n.test(t),
                    u = r.test(t),
                    a = i.test(t),
                    f = Math.random() + "";
                o && !a && (u ? s = "&sb_param=" + e(f) : s = "?sb_param=" + e(f), $(this).attr("href", t + s))
            })
        }
    }
}(QUCi360), function(e, t) {
    var n = '<div class="uc-panel panel-t1"><div class="hd"><span class="close uc-panel-close" title="\u5173\u95ed">\u00d7</span></div><div class="bd"><div class="pannel-feedback"><i class="ico ico-succ"></i><div class="cont"><h2 class="uc-panel-title"></h2><p class="uc-panel-content"></p></div></div></div><span class="sd"></span></div>',
        r = '<div class="uc-panel panel-t1"><div class="hd"><span class="close uc-panel-close" title="\u5173\u95ed">\u00d7</span></div><div class="bd"><div class="pannel-feedback"><i class="ico ico-info"></i><div class="cont"><h2 class="uc-panel-title"></h2><p class="uc-panel-content"></p></div></div></div><span class="sd"></span></div>',
        i = '<div class="uc-panel panel-t1" style="width: 518px;"><div class="hd"><h2 class="uc-panel-title"></h2><span class="close uc-panel-close" title="\u5173\u95ed">\u00d7</span></div><div class="bd"><div class="pannel-feedback"><i class="ico ico-err-m"></i><div class="cont"><div class="uc-panel-content"></div></div><div class="btns text-right"><span class="btn btn-3 uc-panel-close uc-panel-sure">\u786e\u5b9a</span> <span class="btn btn-4 uc-panel-close uc-panel-cancel">\u53d6\u6d88</span></div></div></div><span class="sd"></span></div>',
        s = '<div class="uc-panel panel-t1 timeConfirm" style="width: 370px;"><div class="hd"><h2 class="uc-panel-title"></h2><span class="close uc-panel-close" title="\u5173\u95ed">\u00d7</span></div><div class="bd"><div class="pannel-feedback"><div class="cont"><div class="uc-panel-content"></div></div><div class="btns"><span class="btn btn-3 btn3-disabled uc-panel-sure" data-text="\u7ee7\u7eed\u6ce8\u9500"></span></div></div></div><span class="sd"></span></div>',
        o = '<div class="uc-panel panel-t1" style="width:372px;height:246px;"><div class="hd"><h2 class="uc-panel-title"></h2><span class="close uc-panel-close" title="\u5173\u95ed">\u00d7</span></div><div class="bd"><div class="pannel-feedback"><div class=""><span class="findpwd-fail-icon"></span></div><div class="cont"><div class="uc-panel-content"></div></div></div></div><span class="sd"></span></div>',
        u = {
            type: "normal",
            message: "\u7f51\u7edc\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5",
            supplement: "",
            time: 3,
            reload: !0,
            jumpUrl: "",
            countDown: 20,
            isAutoHide: !0,
            callback: null
        },
        a = {
            closeSelector: ".uc-panel-close",
            titleSelector: ".uc-panel-title",
            contentSelector: ".uc-panel-content",
            confirmSelector: ".uc-panel-sure",
            tpl: i
        };
    e.utils.panel = function(e) {
        var i = null,
            f = $.extend({}, u, e);
        switch (f.type) {
            case "success":
                a.tpl = n;
                break;
            case "fail":
                a.tpl = r;
                break;
            case "findpwd":
                a.tpl = o;
                break;
            case "timeConfirmTpl":
                a.tpl = s;
                break;
            case "normal":
        }
        i = new t.utils.Panel(a), i.setTitle(f.message).setContent("<span>" + f.supplement + "</span>").show();
        if (f.type == "timeConfirmTpl") {
            var l = $(a.confirmSelector),
                c = l.data("text"),
                h = f.countDown;
            l.html(h + "s");
            var p = setInterval(function() {
                h > 1 ? (h--, l.html(h + "s")) : (l.html(c).removeClass("btn3-disabled"), clearInterval(p))
            }, 1e3)
        }
        $(".uc-panel-sure").on("click", function(e) {
            e.preventDefault();
            var t = $(a.confirmSelector);
            f.type == "timeConfirmTpl" && !t.hasClass("btn3-disabled") ? f.confirmHandler && f.confirmHandler() : f.type != "timeConfirmTpl" && (f.confirmHandler && f.confirmHandler(), $(".uc-panel-close").trigger("click"))
        }), $(".uc-panel-cancel").on("click", function(e) {
            e.preventDefault(), f.cancelHandler && f.cancelHandler()
        }), $(".uc-panel-close").on("click", function(e) {
            e.preventDefault(), f.cancelHandler && f.cancelHandler()
        }), f.isAutoHide && setTimeout(function() {
            f.reload = f.reload ? !0 : !1, i.hide(), f.reload && (f.jumpUrl ? location.href = f.jumpUrl : location.reload(!0)), f.callback && f.callback()
        }, f.time * 1e3)
    }
}(QUCi360, QHPass), function(e) {
    "use strict";
    var t = '<a class="quc-link" href="http://' + location.host + '/login">\u7acb\u5373\u767b\u5f55</a>',
        n = e.ERROR = {
            REALNAME_EMPTY: {
                errno: 204,
                errmsg: "\u8bf7\u8f93\u5165\u60a8\u7684\u771f\u5b9e\u59d3\u540d"
            },
            REALNAME_INVALID: {
                errno: 227,
                errmsg: "\u8bf7\u786e\u8ba4\u60a8\u8f93\u5165\u7684\u771f\u5b9e\u59d3\u540d\u662f\u5426\u6709\u8bef"
            },
            ACCOUNT_EMPTY: {
                errno: 1030,
                errmsg: "\u8bf7\u8f93\u5165360\u5e10\u53f7"
            },
            ACCOUNT_INVALID: {
                errno: 1035,
                errmsg: "\u8bf7\u786e\u8ba4\u60a8\u7684\u5e10\u53f7\u8f93\u5165\u662f\u5426\u6709\u8bef"
            },
            ACCOUNT_DUPLICATE: {
                errno: 1037,
                errmsg: "\u8be5\u5e10\u53f7\u5df2\u7ecf\u6ce8\u518c\uff0c" + t
            },
            USERNAME_DUPLICATE: {
                errno: 213,
                errmsg: "\u7528\u6237\u540d\u5df2\u7ecf\u88ab\u4f7f\u7528\uff0c" + t
            },
            USERNAME_EMPTY: {
                errno: 215,
                errmsg: "\u8bf7\u8f93\u5165\u7528\u6237\u540d"
            },
            USERNAME_INAPPROPRIATE: {
                errno: 225,
                ermsg: "\u7528\u6237\u540d\u5305\u542b\u4e0d\u9002\u5f53\u5185\u5bb9"
            },
            USERNAME_INVALID: {
                errno: 199,
                errmsg: '\u7528\u6237\u540d\u5e94\u4e3a2-14\u4e2a\u5b57\u7b26,\u652f\u6301\u4e2d\u82f1\u6587\u3001\u6570\u5b57\u6216"_"'
            },
            USERNAME_NUMBER: {
                errno: 200,
                errmsg: "\u7528\u6237\u540d\u4e0d\u80fd\u5168\u4e3a\u6570\u5b57"
            },
            NICKNAME_EMPTY: {
                errno: 205,
                errmsg: "\u8bf7\u8f93\u5165\u6635\u79f0"
            },
            NICKNAME_DUPLICATE: {
                errno: 260,
                errmsg: "\u6635\u79f0\u5df2\u7ecf\u88ab\u4f7f\u7528"
            },
            NICKNAME_INAPPROPRIATE: {
                errno: 226,
                errmsg: "\u6635\u79f0\u5305\u542b\u4e0d\u9002\u5f53\u5185\u5bb9"
            },
            NICKNAME_NUMBER: {
                errno: 262,
                errmsg: "\u6635\u79f0\u4e0d\u80fd\u5168\u90e8\u662f\u6570\u5b57"
            },
            NICKNAME_INVALID: {
                errno: 15e3,
                errmsg: '\u6635\u79f0\u5e94\u4e3a2-14\u4e2a\u5b57\u7b26,\u652f\u6301\u4e2d\u82f1\u6587\u3001\u6570\u5b57\u3001"_"\u6216"."'
            },
            EMAIL_EMPTY: {
                errno: 203,
                errmsg: "\u8bf7\u8f93\u5165\u90ae\u7bb1"
            },
            EMAIL_INVALID: {
                errno: 1532,
                errmsg: "\u90ae\u7bb1\u683c\u5f0f\u6709\u8bef"
            },
            EMAIL_NOT_ACTIVATED: {
                errno: 2e4
            },
            MOBILE_EMPTY: {
                errno: 1107,
                errmsg: "\u8bf7\u8f93\u5165\u624b\u673a\u53f7"
            },
            MOBILE_INVALID: {
                errno: 1100,
                errmsg: "\u624b\u673a\u53f7\u683c\u5f0f\u6709\u8bef"
            },
            CAPTCHA_INVALID: {
                errno: 78e3,
                errmsg: "\u9a8c\u8bc1\u7801\u8f93\u5165\u6709\u8bef"
            },
            CAPTCHA_INVALID_OLD: {
                errno: 1670,
                errmsg: "\u9a8c\u8bc1\u7801\u8f93\u5165\u6709\u8bef"
            },
            CAPTCHA_EMPTY: {
                errno: 78002,
                errmsg: "\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801"
            },
            CAPTCHA_APPID_INVALID: {
                errno: 1300,
                errmsg: "\u9a8c\u8bc1\u7801\u683c\u5f0f\u6709\u8bef"
            },
            SMS_TOKEN_EMPTY: {
                errno: 1350,
                errmsg: "\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801"
            },
            SMS_TOKEN_INCORRECT: {
                errno: 1351,
                errmsg: "\u9a8c\u8bc1\u7801\u8f93\u5165\u6709\u8bef"
            },
            PASSWORD_EMPTY: {
                errno: 211,
                errmsg: "\u8bf7\u8f93\u5165\u5bc6\u7801"
            },
            PASSWORD_INVALID: {
                errno: 1065,
                errmsg: "\u5bc6\u7801\u957f\u5ea6\u5e94\u4e3a8-20\u4e2a\u5b57\u7b26"
            },
            PASSWORD_LEVEL_LOW: {
                errno: 1070,
                errmsg: "\u5bc6\u7801\u8fc7\u4e8e\u7b80\u5355"
            },
            PASSWORD_WEAK: {
                errno: 1070,
                errmsg: "\u5bc6\u7801\u5f31\uff0c\u6709\u98ce\u9669\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165"
            },
            PASSWORD_ORDERED: {
                errno: 1070,
                errmsg: "\u5bc6\u7801\u4e0d\u80fd\u4e3a\u8fde\u7eed\u5b57\u7b26"
            },
            PASSWORD_CHAR_REPEAT: {
                errno: 1070,
                errmsg: "\u5bc6\u7801\u4e0d\u80fd\u5168\u4e3a\u76f8\u540c\u5b57\u7b26"
            },
            PASSWORD_WRONG: {
                errno: 220,
                errmsg: "\u767b\u5f55\u5bc6\u7801\u9519\u8bef\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165"
            },
            PASSWORD_NOT_MATCH: {
                errno: 1091,
                errmsg: "\u4e24\u6b21\u5bc6\u7801\u8f93\u5165\u4e0d\u4e00\u81f4"
            },
            PASSWORD_FULL_SHARP: {
                errno: 54e3,
                errmsg: "\u5bc6\u7801\u4e0d\u80fd\u5305\u542b\u4e2d\u6587\u5b57\u7b26\uff0c\u8bf7\u91cd\u65b0\u8bbe\u7f6e"
            },
            PASSWORD_TOO_SHORT: {
                errno: 211,
                errmsg: "\u5bc6\u7801\u957f\u5ea6\u4e0d\u80fd\u5c11\u4e8e8\u4e2a\u5b57\u7b26\uff0c\u8bf7\u91cd\u65b0\u8bbe\u7f6e"
            },
            PASSWORD_TOO_LONG: {
                errno: 1065,
                errmsg: "\u5bc6\u7801\u957f\u5ea6\u4e0d\u80fd\u5927\u4e8e20\u4e2a\u5b57\u7b26\uff0c\u8bf7\u91cd\u65b0\u8bbe\u7f6e"
            },
            PASSWORD_TYPY_SINGLE: {
                errno: 1070,
                errmsg: "\u5bc6\u7801\u4e0d\u80fd\u7531\u76f8\u540c\u7c7b\u578b\u7684\u5b57\u7b26\u7ec4\u6210\uff0c\u8bf7\u91cd\u65b0\u8bbe\u7f6e"
            },
            PASSWORD_WORD_SINGLE: {
                errno: 1070,
                errmsg: "\u7ec4\u6210\u5bc6\u7801\u7684\u6bcf\u79cd\u7c7b\u578b\u5b57\u7b26\u9700\u8981\u81f3\u5c112\u4f4d\uff0c\u8bf7\u91cd\u65b0\u8bbe\u7f6e"
            },
            PASSWORD_INCLUDE_USERNAME: {
                errno: 1070,
                errmsg: "\u5bc6\u7801\u4e2d\u4e0d\u80fd\u5305\u542b\u7528\u6237\u540d\uff0c\u8bf7\u91cd\u65b0\u8bbe\u7f6e"
            },
            PASSWORD_PARTOF_USERNAME: {
                errno: 1070,
                errmsg: "\u7528\u6237\u540d\u4e2d\u4e0d\u80fd\u5305\u542b\u5bc6\u7801\uff0c\u8bf7\u91cd\u65b0\u8bbe\u7f6e"
            },
            IDENTIFY_EXPIRE: {
                errno: 153e3
            },
            NOT_SIGNED_IN: {
                errno: 1501,
                errmsg: "\u7528\u6237\u672a\u767b\u9646"
            },
            UNKNOWN_ERROR: {
                errno: 999999,
                errmsg: "\u672a\u77e5\u9519\u8bef"
            },
            SUCCESS: {
                errno: 0,
                errmsg: "\u64cd\u4f5c\u6210\u529f"
            },
            TIME_OUT: {
                errno: 1,
                errmsg: "\u7f51\u7edc\u8d85\u65f6"
            },
            IDNUMBER_EMPTY: {
                errno: "ms100",
                errmsg: "\u8bf7\u8f93\u5165\u8bc1\u4ef6\u53f7\u7801"
            },
            IDNUMBER_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u6b63\u786e\u586b\u5199\u8bc1\u4ef6\u53f7\u7801"
            },
            AREAR_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u9009\u62e9\u6b63\u786e\u7684\u5e38\u9a7b\u7701\u5e02"
            },
            ADDRESS_INVALID: {
                errono: "79000",
                errmsg: "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u901a\u4fe1\u5730\u5740\u4fe1\u606f"
            },
            SCHOOL_INVALID: {
                errono: "79005",
                errmsg: "\u8bf7\u586b\u5199\u6b63\u786e\u7684\u9662\u6821\u4fe1\u606f"
            },
            ZIPCODE_EMPTY: {
                errno: "ms100",
                errmsg: "\u8bf7\u8f93\u5165\u90ae\u653f\u7f16\u7801"
            },
            ZIPCODE_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u90ae\u653f\u7f16\u7801"
            },
            DATE_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u9009\u62e9\u6b63\u786e\u7684\u65e5\u671f"
            },
            HEIGHT_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u8eab\u9ad8"
            },
            URL_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u94fe\u63a5\u5730\u5740"
            },
            QQ_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u8f93\u5165\u6b63\u786e\u7684QQ\u53f7\u7801"
            },
            IDCARD_EMPTY: {
                errno: "ms100",
                ermsg: "\u8bf7\u8f93\u5165\u8bc1\u4ef6\u53f7\u7801"
            },
            IDCARD_INVALID: {
                errno: "ms100",
                errmsg: "\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u8bc1\u4ef6\u53f7"
            }
        },
        r = e.utils = e.utils || {},
        i = {
            1105: "\u8be5\u624b\u673a\u53f7\u672a\u6ce8\u518c360\u5e10\u53f7",
            1402: "\u624b\u673a\u53f7\u5f53\u5929\u53d1\u9001\u77ed\u4fe1\u6b21\u6570\u8d85\u9650",
            201: "\u8be5\u90ae\u7bb1\u5df2\u7ecf\u6ce8\u518c\uff0c" + t,
            1106: "\u8be5\u624b\u673a\u53f7\u5df2\u7ecf\u6ce8\u518c\uff0c" + t,
            3e4: "\u8be5\u624b\u673a\u53f7\u5df2\u7ecf\u6ce8\u518c\uff0c" + t,
            65002: '\u624b\u673a\u53f7\u672a\u5f00\u542f\u77ed\u4fe1\u767b\u5f55\u529f\u80fd\uff0c<a class="quc-link" href="http://i.360.cn/security/setloginmethod" target="_blank">\u7acb\u5373\u5f00\u542f</a>',
            65001: '\u624b\u673a\u53f7\u5df2\u5f00\u542f\u77ed\u4fe1\u767b\u5f55\u529f\u80fd\uff08\u5f00\u542f\u540e\u9ed8\u8ba4\u5fc5\u987b\u4f7f\u7528\u624b\u673a\u53f7\u767b\u5f55\uff09\uff0c<a class="quc-link quc-link-login" href="http://i.360.cn/login" target="_blank">\u5173\u95ed\u6b64\u529f\u80fd</a>',
            221: '\u5e10\u53f7\u88ab\u5c01\u7981\uff0c<a class="quc-link quc-link-login" href="http://i.360.cn/complaint" target="_blank">\u70b9\u6b64\u8054\u7cfb\u5ba2\u670d</a>',
            78001: "\u63d0\u4ea4\u8fc7\u4e8e\u9891\u7e41\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5"
        };
    $(n).each(function(e, t) {
        t.errmsg && t.errmsg.length > 0 && (i[t.errno] = t.errmsg)
    }), r.isSameError = function(e, t) {
        return e.errno === undefined || t.errno === undefined ? !1 : e.errno === t.errno
    }, r.defineError = function(e, t) {
        var r;
        for (var s in n) n.hasOwnProperty(s) && n[s].errno == e && (r = n[s], r.errmsg = t);
        i[e] = t
    }, r.getErrorMsg = function(e, t) {
        return $.isPlainObject(e) && (t = e.errmsg, e = e.errno), i[e] || t.replace(/\+/g, " ").replace(/class=(['"]).+?\1/, 'class="quc-link"')
    }, r.getErrorType = function(e) {
        e = e.errno || e;
        switch (e) {
            case n.MOBILE_EMPTY.errno:
            case n.MOBILE_INVALID.errno:
                return "mobile";
            case n.EMAIL_EMPTY.errno:
            case n.EMAIL_INVALID.errno:
                return "email";
            case n.USERNAME_EMPTY.errno:
            case n.USERNAME_INVALID.errno:
            case n.USERNAME_DUPLICATE.errno:
            case n.USERNAME_NUMBER.errno:
            case n.USERNAME_INAPPROPRIATE.errno:
                return "username";
            case n.NICKNAME_EMPTY.errno:
            case n.NICKNAME_INVALID.errno:
            case n.NICKNAME_DUPLICATE.errno:
            case n.NICKNAME_INAPPROPRIATE.errno:
            case n.NICKNAME_NUMBER.errno:
                return "nickname";
            case n.ACCOUNT_EMPTY.errno:
            case n.ACCOUNT_INVALID.errno:
            case n.ACCOUNT_DUPLICATE.errno:
                return "account";
            case n.PASSWORD_INVALID.errno:
            case n.PASSWORD_EMPTY.errno:
            case n.PASSWORD_CHAR_REPEAT.errno:
            case n.PASSWORD_ORDERED.errno:
            case n.PASSWORD_WEAK.errno:
            case n.PASSWORD_WRONG.errno:
            case n.PASSWORD_LEVEL_LOW.errno:
                return "password";
            case n.PASSWORD_NOT_MATCH.errno:
                return "password-again";
            case n.CAPTCHA_INVALID.errno:
            case n.CAPTCHA_EMPTY.errno:
            case n.CAPTCHA_APPID_INVALID.errno:
            case n.CAPTCHA_INVALID_OLD.errno:
                return "captcha";
            case n.SMS_TOKEN_EMPTY.errno:
            case n.SMS_TOKEN_INCORRECT.errno:
                return "sms-token"
        }
        return e -= e > 9e5 ? 9e5 : 0, e >= 1e4 && e < 15e3 ? "username" : e >= 15e3 && e < 2e4 ? "nickname" : e >= 2e4 && e < 3e4 ? "email" : e >= 3e4 && e < 45e3 ? "mobile" : e >= 5e4 && e < 55e3 ? "password" : e >= 55e3 && e < 6e4 ? "sec-email" : e >= 65e3 && e < 75e3 ? "account" : e >= 78e3 && e < 79e3 ? "captcha" : "other"
    }
}(QHPass), function(e, t, n) {
    "use strict";

    function i(e) {
        return String(e).replace(/[^\x00-\xff]/g, "--").length
    }
    function s(e, t, n) {
        var r = i(e);
        return t <= r && r <= n
    }
    function o(e) {
        return e = e === undefined ? "" : e, e.length == 0
    }
    function u(e) {
        e = String(e);
        var t = e.length,
            n = null,
            r;
        for (var i = 1; i < t; i++) {
            r = e.charCodeAt(i) - e.charCodeAt(i - 1);
            if (n !== null && n !== r || Math.abs(r) > 1) return !1;
            n = r
        }
        return !0
    }
    function a(e) {
        var n = [],
            r;
        if (!t.isArray(e)) throw new Error("TypeError: not Array");
        e = e.sort();
        if (e.length == 1) return e;
        for (var i = 1, s = 0; r = e[i]; i++) r === e[i - 1] && (s = n.push(i));
        while (s--) e.splice(n[s], 1);
        return e
    }
    function l(e, n, r) {
        if (r) {
            var i = e;
            i = String(e);
            var s = i.length,
                o = n && n.length || 0,
                a = Math.abs(s - o),
                l = c(i, s),
                h = l.num.d + l.word.d + l.pun.d;
            return s <= 7 ? -5 : s >= 21 ? -6 : h < 2 ? -7 : h != 2 || l.num.count != 1 && l.word.count != 1 && l.pun.count != 1 ? a <= 2 && i.indexOf(n) > -1 ? -9 : a <= 2 && n.indexOf(i) > -1 ? -10 : 1 : -8
        }
        e = String(e);
        var p = e.length,
            d = e.split(""),
            v = t.unique(d),
            m;
        if (p >= 21 || p <= 5) return -1;
        if (v.length == 1) return -2;
        if (u(e)) return -3;
        if (f.join("#").indexOf("#" + e + "#") > -1) return -4;
        var g = {
            d: 0,
            c: 0,
            o: 0
        };
        return t.each(v, function(e, t) {
            /\d/.test(t) ? g.d = 1 : /[a-zA-Z]/.test(t) ? g.c = 1 : g.o = 1
        }), m = g.d + g.c + g.o + (p > 9 ? 2 : 1), m = Math.max(3, m), m
    }
    function c(e, t) {
        var n = {
                num: {
                    count: 0,
                    d: 0
                },
                word: {
                    count: 0,
                    d: 0
                },
                pun: {
                    count: 0,
                    d: 0
                }
            },
            r = e,
            i = r.replace(/\d+/g, ""),
            s = r.replace(/([A-Za-z])+/g, ""),
            o = r.replace(/(\W|_)+/g, "");
        return n.num.count = t - i.length, n.num.d = n.num.count > 0 ? 1 : 0, n.word.count = t - s.length, n.word.d = n.word.count > 0 ? 1 : 0, n.pun.count = t - o.length, n.pun.d = n.pun.count > 0 ? 1 : 0, n
    }
    var r = e.ERROR;
    t.unique = t.unique || a;
    var f = ["", "abcabc", "abc123", "a1b2c3", "aaa111", "123abc", "123456abc", "abc123456", "qwerty", "qwertyuiop", "qweasd", "123qwe", "1qaz2wsx", "1q2w3e4r", "1q2w3e4r5t", "asdasd", "asdfgh", "asdfghjkl", "zxcvbn", "qazwsxedc", "qq123456", "admin", "password", "p@ssword", "passwd", "Password", "Passwd", "Iloveyou", "Woaini", "iloveyou", "Wodemima", "Woaiwojia", "tamade", "nimade", "123789", "1234560", "123465", "123321", "102030", "100200", "4655321", "987654", "123123", "123123123", "121212", "111222", "12301230", "168168", "456456", "321321", "521521", "5201314", "520520", "201314", "211314", "7758258", "7758521", "1314520", "1314521", "147258369", "147852369", "159357", "741852", "741852963", "654321", "852963", "963852741", "115415", "123000", ""];
    t.extend(QHPass.validate, {
        checkRealName: function(e) {
            e = t.trim(e);
            var n = /^[\u4E00-\u9FA5]{2,14}$/;
            return e ? n.test(e) ? !1 : r.REALNAME_INVALID : r.REALNAME_EMPTY
        },
        checkMobile: function(e, n) {
            var i, s, u;
            return typeof e == "object" ? (i = t.trim(e.mobileNumber), s = e.regExp || "^1\\d{10}$", u = new RegExp(s)) : (i = t.trim(e), u = /^0?1[3456789]\d{9}$/), n = n ? !0 : !1, !n && o(i) ? r.MOBILE_EMPTY : i && !u.test(i) ? r.MOBILE_INVALID : !1
        },
        checkPassword: function(e, t, n) {
            var i = String(e);
            if (o(i)) return r.PASSWORD_EMPTY;
            if (i.match(/[^\x00-\xff]/)) return r.PASSWORD_FULL_SHARP;
            if (!t && !n) return !1;
            switch (l(i, t, n)) {
                case -1:
                    return r.PASSWORD_INVALID;
                case -2:
                    return r.PASSWORD_CHAR_REPEAT;
                case -3:
                    return r.PASSWORD_ORDERED;
                case -4:
                    return r.PASSWORD_WEAK;
                case -5:
                    return r.PASSWORD_TOO_SHORT;
                case -6:
                    return r.PASSWORD_TOO_LONG;
                case -7:
                    return r.PASSWORD_TYPY_SINGLE;
                case -8:
                    return r.PASSWORD_WORD_SINGLE;
                case -9:
                    return r.PASSWORD_INCLUDE_USERNAME;
                case -10:
                    return r.PASSWORD_PARTOF_USERNAME;
                default:
                    return !1
            }
        },
        checkEmail: function(e) {
            var n = /^[\w.\-+a-z0-9]*@[a-z0-9][\w.-]*\.[a-z]{2,8}$/i;
            return e = t.trim(e), o(e) ? r.EMAIL_EMPTY : n.test(e) ? !1 : r.EMAIL_INVALID
        },
        checkIdNumber: function(n, r) {
            n = t.trim(n);
            if (o(n)) return e.ERROR.IDNUMBER_EMPTY;
            switch (r) {
                case "idCard":
                    var i = isCnNewID(n);
                    if (n.length == 15) {
                        var s = idCardOldTowNew(n);
                        i = isCnNewID(s)
                    }
                    if (!i) return e.ERROR.IDNUMBER_INVALID
            }
            return !1
        },
        checkDate: function(t, n, r) {
            return t != -1 && n != -1 && r != -1 || t == -1 && n == -1 && r == -1 ? !1 : e.ERROR.DATE_INVALID
        },
        checkProvinceCity: function(n, r) {
            return n = t.trim(n), r = t.trim(r), n != -2 && r != -2 ? !1 : e.ERROR.AREAR_INVALID
        },
        checkAddress: function(n) {
            n = t.trim(n);
            var r = /^[0-9a-zA-Z\-\.\(\)\,\\uff08\\uff09\\uff0c\u4e00-\u9fa5]+$/;
            return n && (i(n) > 200 || i(n) < 4) ? e.ERROR.ADDRESS_INVALID : n && !r.test(n) ? e.ERROR.ADDRESS_INVALID : !1
        },
        checkSchool: function(n) {
            n = t.trim(n);
            var r = /^[\w#&\-\.\u4e00-\u9fa5]+$/;
            return n && (i(n) > 200 || i(n) < 4) ? e.ERROR.SCHOOL_INVALID : n && !r.test(n) ? e.ERROR.SCHOOL_INVALID : !1
        },
        checkZipcode: function(n) {
            n = t.trim(n);
            if (o(n)) return e.ERROR.ZIPCODE_EMPTY;
            var r = /\d{6}/;
            return r.test(n) ? !1 : e.ERROR.ZIPCODE_INVALIDE
        },
        checkBodyHeight: function(n) {
            n = t.trim(n);
            var r = /\d{2,3}/;
            return n && (!r.test(n) || n > 300 || n < 100) ? e.ERROR.HEIGHT_INVALID : !1
        },
        checkUrl: function(n) {
            var r = /^(https?|ftp):\/\/([\w-]+\.)+[\w-]+(\/[\w.\/?%&=-]*)?$/i;
            return n = t.trim(n), n && !r.test(n) ? e.ERROR.URL_INVALID : !1
        },
        checkQQ: function(n) {
            n = t.trim(n);
            var r = /\d{5,20}/;
            return n && !r.test(n) ? e.ERROR.QQ_INVALID : !1
        },
        checkIdCard: function(r) {
            return r = t.trim(r), r ? n.utils.checkIdCard(r) ? !1 : e.ERROR.IDCARD_INVALID : e.ERROR.IDCARD_EMPTY
        }
    })
}(QHPass, window.jQuery, QUCi360), $(function() {
    "use strict";

    function t() {
        var e = $("#doc").height(),
            t = $(window).height(),
            n = t > e ? t : e;
        $("body").height(n + 10)
    }
    var e = $("body");
    $("#currentYear").html((new Date).getFullYear()), t(), $(document).on("resize", t)
})