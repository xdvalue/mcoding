!
function() {
    function e() {
        clearTimeout(o),
        n.addClass("done")
    }
    var n = $(".js-tpl-weixin-list-item"),
    a = $(".js-tpl-weixin-bg");
    if (! (a.length <= 0)) {
        var t = a[0],
        o = setTimeout(function() {
            e()
        },
        2e3);
        t.onload = t.onerror = t.onabort = e,
        t.complete && e()
    }
} (),
function() {
    var e = $(".js-tpl-shop"),
    n = "/v2/showcase/homepage/goodscount.json";
    e.length && $.ajax({
        url: n,
        type: "GET",
        dataType: "json",
        data: {
            kdt_id: window._global.kdt_id
        }
    }).done(function(n) {
        if (0 === +n.code) {
            var a = e.find(".js-all-goods"),
            t = e.find(".js-new-goods"),
            o = e.find(".js-order"),
            i = n.data,
            s = "";
            s = (i.all_goods.count + "").length,
            a.find("a").attr("href", i.all_goods.url),
            a.find(".count").html(i.all_goods.count).addClass("l-" + s),
            s = (i.new_goods.count + "").length,
            t.find("a").attr("href", i.new_goods.url),
            t.find(".count").html(i.new_goods.count).addClass("l-" + s),
            o.find("a").attr("href", i.order.url)
        }
    })
} (),
function() {
    $(".js-select-coupon").on("click",
    function() {
        var e = $(this),
        n = e.data("id"),
        a = e.data("kdt-id"),
        t = window.motify;
        $.ajax({
            url: "/v2/guang/promocard/take.json",
            type: "POST",
            data: {
                groupId: n,
                kdtId: a
            }
        }).done(function(e) {
            0 === +e.code ? t.log(e.msg || "成功领取优惠券") : 1 === +e.code ? window.location.href = e.data.redirect: t.log(e.msg || "网络错误")
        }).fail(function() {
            t.log("网络错误")
        })
    })
} (),
function() {
    var e = $(".js-scroll-notice");
    e.length && e.each(function() {
        function e() {
            i--,
            0 > i + t && (i = o),
            n.css({
                left: i
            })
        }
        var n = $(this),
        a = n.parents(".custom-notice-inner"),
        t = n.width(),
        o = a.width(),
        i = 0;
        o >= t || (n.css({
            position: "relative"
        }), setInterval(e, 25))
    })
} (),
define("wap/showcase/homepage/homepage",
function() {}),
function() {
    var e = $(".js-custom-level"),
    n = $(".js-custom-point"),
    a = $(".js-custom-level-title-section");
    if (! ( + _global.fans_id <= 0 && +_global.buyer_id <= 0)) {
        var t = window._global.url.wap + "/showcase/component/point.jsonp?" + $.param({
            kdt_id: window._global.kdt_id
        }); (e.length > 0 || n.length > 0) && $.ajax({
            dataType: "jsonp",
            type: "GET",
            url: t,
            success: function(t) {
                0 === +t.code && (e.html(t.data.level || "会员"), n.html(t.data.point || "暂无数据"), a.removeClass("hide"))
            }
        })
    }
} (),
define("wap/uc/title",
function() {}),
define("wap/showcase/shop_nav/main", ["vendor/zepto/outer"],
function() {
    var e = $(".js-navmenu"),
    n = e.find(".js-nav-pop");
    if (e.length) {
        var a = {
            showSubmenu: function(e) {
                var n = $(e.target),
                a = n.parents(".nav-item"),
                t = n.hasClass(".js-mainmenu") ? n: a.find(".js-mainmenu"),
                o = a.find(".js-submenu"),
                i = o.find(".arrow"),
                s = n.parents(".js-navmenu"),
                c = s.find(".nav-item");
                o.css("opacity", "0").toggle();
                var r = c.length,
                d = c.index(a),
                l = t.outerWidth(),
                u = (o.outerWidth() - t.outerWidth()) / 2,
                h = o.outerWidth() / 2;
                if (0 === o.size()) $(".js-submenu:visible").hide();
                else {
                    var f = o.outerWidth(),
                    p = a.outerWidth(),
                    m = "auto",
                    v = "auto",
                    g = "auto",
                    w = "auto";
                    0 === d ? (m = t.position().left - u, v = h - i.outerWidth() / 2) : d === r - 1 && f > p ? (g = 8, w = l / 2 - g) : (m = t.position().left - u, v = h - i.outerWidth() / 2);
                    var b = 5;
                    0 > m && (v = v + m + b, m = b),
                    0 > g && (w = w + g + b, g = b),
                    o.css({
                        left: m,
                        right: g
                    }),
                    i.css({
                        left: v,
                        right: w
                    }),
                    $(".js-submenu:visible").not(o).hide(),
                    o.css("opacity", "1")
                }
            }
        };
        $(document).on("click",
        function(a) {
            e[0] && (a.target == e[0] || $.contains(e[0], a.target) || ($(".js-submenu:visible").hide(0), e.hasClass("nav-show") && (e.removeClass("nav-show").addClass("nav-hide"), setTimeout(function() {
                n.hide(0)
            },
            500))))
        }),
        $("body").on("click", ".js-navmenu",
        function(e) {
            var n = $(e.target);
            e.fromMenu = !0,
            window.Logger.log({
                fm: "click",
                title: n.prop("title") || n.text()
            })
        }),
        $("body").on("click", ".js-submenu",
        function(e) {
            var n = $(e.target);
            e.fromMenu = !0,
            window.Logger.log({
                fm: "click",
                title: n.prop("title") || n.text()
            }),
            e.stopPropagation()
        }),
        $("body").on("click", ".js-mainmenu",
        function(e) {
            a.showSubmenu(e)
        });
        var t = ".js-navmenu",
        o = $(".js-custom-paginations"),
        i = $(t),
        s = i.data("type");
        4 != s && i.size() + o.size() > 0 && $("body").css("padding-bottom", $(".js-navmenu").height() || o.height());
        var c;
        $(window).on("scroll",
        function(e) {
            e.preventDefault();
            var n = $(".js-navmenu"),
            a = n.find(".js-nav-pop");
            n.hasClass("nav-show") && (n.removeClass("nav-show").addClass("nav-hide"), setTimeout(function() {
                a.hide(0)
            },
            500))
        }),
        $("body").on("click", ".js-nav-special",
        function(e) {
            var n = $(e.target),
            a = n.parents(".js-navmenu"),
            t = a.find(".js-nav-pop");
            "animation" != a.data("animation") && (a.data("animation", "animation"), a.hasClass("nav-show") ? (a.removeClass("nav-show").addClass("nav-hide"), c = setTimeout(function() {
                t.css("display", "none"),
                a.data("animation", "")
            },
            600)) : (t.css("display", "block"), a.addClass("nav-show").removeClass("nav-hide"), setTimeout(function() {
                a.data("animation", "")
            },
            600)))
        })
    }
}),
define("wap/showcase/base/batch", [],
function() {
    function e(e) {
        e = e || {};
        var n = e.key,
        c = e.url,
        r = e.type || "GET",
        d = e.para || {},
        l = e.handler || s;
        n && c && (o[n] || (o[n] = {
            url: c,
            param: $.extend({},
            d),
            type: r
        },
        t[n] = []), i && (i = !1, a()), t[n].push(l))
    }
    function n() {
        if ($.isEmptyObject(o)) return void(i = !0);
        var e = $.extend({},
        o);
        o = {},
        $.ajax({
            url: "/v2/batch",
            type: "post",
            dataType: "json",
            data: {
                query: e
            },
            success: function(e) {
                var n = e.code,
                a = e.data || {};
                0 == n && $.each(a,
                function(e, n) {
                    var o = t[e] || [];
                    $.each(o,
                    function(n, t) {
                        try {
                            t(JSON.parse(a[e]))
                        } catch(o) {}
                    })
                })
            },
            complete: function() {
                a()
            }
        })
    }
    function a() {
        setTimeout(n, 1e3)
    }
    var t = {},
    o = {},
    i = !1,
    s = function() {};
    setTimeout(n, 500),
    window.queryBatch = e
}),
window.Zepto &&
function(e) {
    e.fn.serializeArray = function() {
        var n, a, t = [],
        o = function(e) {
            return e.forEach ? e.forEach(o) : void t.push({
                name: n,
                value: e
            })
        };
        return this[0] && e.each(this[0].elements,
        function(t, i) {
            a = i.type,
            n = i.name,
            n && "fieldset" != i.nodeName.toLowerCase() && !i.disabled && "submit" != a && "reset" != a && "button" != a && "file" != a && ("radio" != a && "checkbox" != a || i.checked) && o(e(i).val())
        }),
        t
    },
    e.fn.serialize = function() {
        var e = [];
        return this.serializeArray().forEach(function(n) {
            e.push(encodeURIComponent(n.name) + "=" + encodeURIComponent(n.value))
        }),
        e.join("&")
    },
    e.fn.submit = function(n) {
        if (0 in arguments) this.bind("submit", n);
        else if (this.length) {
            var a = e.Event("submit");
            this.eq(0).trigger(a),
            a.isDefaultPrevented() || this.get(0).submit()
        }
        return this
    }
} (Zepto),
define("vendor/zepto/form",
function() {}),
define("wap/showcase/search_bar/main", ["vendor/zepto/form"],
function() {
    $.fn.searchBar = function() {
        var e = $.fn.searchBar.container;
        return e || (e = $.fn.searchBar.init()),
        this.each(function() {
            $(this).on("click",
            function() {
                e.removeClass("hide"),
                e.find(".search-input").focus()
            })
        })
    },
    $.fn.searchBar.init = function() {
        var e, n = window.localStorage,
        a = $('<div class="search-container hide"></div>'),
        t = $(['<form class="search-form" action="/v2/search" method="GET">', '<input type="search" class="search-input" placeholder="搜索本店所有商品" name="q" value="">', '<input type="hidden" name="kdt_id" value="' + window._global.kdt_id + '">', '<a class="js-search-cancel search-cancel" href="javascript:;">取消</a>', '<span class="search-icon"></span>', '<span class="close-icon hide"></span>', "</form>"].join("")),
        o = $('<div class="history-wrap center"></div>'),
        i = t.find(".js-search-cancel"),
        s = $('<ul class="history-list"></ul>'),
        c = $('<a class="tag tag-clear c-gray-darker hide" href="javascript:;">清除历史搜索</a>'),
        r = t.find(".search-input"),
        d = t.find(".close-icon"),
        l = "";
        return n && (e = (JSON.parse(n.getItem("searchhistory")) || {}).result, e && ($.each(e,
        function(e, n) {
            l += "<li>" + n + "</li>"
        }), s.append(l), c.removeClass("hide"))),
        o.append(s).append(c),
        a.append(t).append(o),
        $("body").append(a),
        $.fn.searchBar.container = a,
        t.on("submit",
        function() {
            var a = $.trim(r.val());
            n && a && (e = e || [], e = $.grep(e,
            function(e) {
                return e != a
            }), e.unshift(a), n.setItem("searchhistory", JSON.stringify({
                result: e
            })))
        }).on("input",
        function() {
            "" !== $.trim(r.val()) ? (o.addClass("hide"), d.removeClass("hide")) : (o.removeClass("hide"), d.addClass("hide"))
        }),
        d.on("click",
        function() {
            r.val(""),
            d.addClass("hide")
        }),
        i.on("click",
        function() {
            a.addClass("hide")
        }),
        s.on("click", "li",
        function(e) {
            r.val($(e.currentTarget).text()),
            t.submit()
        }),
        c.on("click",
        function() {
            n && (n.removeItem("searchhistory"), e = null),
            o.html("")
        }),
        a
    }
}),
require(["vendor/zepto/outer", "wap/showcase/homepage/homepage", "wap/uc/title", "wap/showcase/shop_nav/main", "wap/showcase/base/batch", "wap/showcase/search_bar/main"],
function() {
    _global.spm && "h" === _global.spm.logType && _global.spm.logType2 && onReady && onReady("Logger",
    function() {
        Logger && Logger.log({
            spm: _global.spm.logType2 + _global.spm.logId2,
            fm: "display"
        })
    }),
    $(".js-search").searchBar()
}),
define("main",
function() {});