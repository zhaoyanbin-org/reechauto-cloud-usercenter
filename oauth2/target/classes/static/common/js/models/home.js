/**
 * Created by zhutw on 2018/1/26.
 */
layui.define(["layer", "element"], function (exports) {
    "use strict";
    var element = layui.element;
    var home = {
        init: function () {
            this.loadUserInfo();
            this.render();
        },
        userData: null,
        render: function () {
            var that = this;
            $(".content-body").width($(window).width() - 260);
            $(".content-body").height($(window).height() - 150);
            //初始化一级菜单
            var navmenu = $("#navmenu");
            if (NAV_MENUS && NAV_MENUS.length) {
            	
                $(NAV_MENUS).each(function () {
                	
                    var hasPerm = false;
                    
                    
                    if (this.role) {
                    	
                    	hasPerm = true;
                    	
                       /* if (that.userData.roles && that.userData.roles.length > 0) {
                        	
                            if ($.inArray(this.role, that.userData.roles) >= 0 || $.inArray("admin", that.userData.roles) >= 0) {
                                hasPerm = true;
                            }
                        }*/
                    } else {
                    	
                        hasPerm = true;
                    }
                   
             //       alert("hasPerm="+hasPerm);
                    if (hasPerm) {
                        navmenu.append('<li class="layui-nav-item" id="' + this.id + '"><a href="javascript:;">' + this.text + '</a></li>');
                    }
                });
            }
            
            element.render('nav(navmenu)');
            element.on('nav(logout)', function (elem) {
                elem.removeClass("layui-this");
            });
            //初始化二级菜单
            element.on('nav(navmenu)', function (elem) {
                var items = SUB_MENUS[elem.attr("id")];
                layer.closeAll();
                var leftmenu = $("#submenu");
                leftmenu.empty();
                if (items) {
                    if (items instanceof Array) {
                        $(items).each(function (idx, item) {
                            var html = [];
                            var hasPerm = false;
                            if (item.role) {
                                if (that.userData.roles && that.userData.roles.length > 0) {
                                    if ($.inArray(item.role, that.userData.roles) >= 0) {
                                        hasPerm = true;
                                    }
                                }
                            } else {
                                hasPerm = true;
                            }
                            if (hasPerm) {
                                html.push('<li class="layui-nav-item" url="' + (item.url || "") + '" model="' + item.model + '"><a href="javascript:;" class="toggle">' + item.text + '</a>');
                                if (item.items && item.items.length) { //存在子元素
                                    html.push('<dl class="layui-nav-child">');
                                    $(item.items).each(function () {
                                        hasPerm = false;
                                        if (this.role) {
                                            if (that.userData.roles && that.userData.roles.length > 0) {
                                                if ($.inArray(this.role, that.userData.roles) >= 0 || $.inArray("admin", that.userData.roles) >= 0) {
                                                    hasPerm = true;
                                                }
                                            }
                                        } else {
                                            hasPerm = true;
                                        }
                                        if (hasPerm) {
                                            html.push('<dd url="' + (this.url || "") + '" model="' + this.model + '"><a href="javascript:;">' + this.text + '</a></dd>');
                                        }
                                    });
                                    html.push('</dl>');
                                }
                                html.push('</li>');
                            }
                            leftmenu.append(html.join(''));
                        });
                        var toggles = leftmenu.find(".toggle");
                        toggles.unbind("click");
                        toggles.click(function () {
                            $(this).parent().find("dl").toggle("slow");
                        });
                    } else {
                        var hasPerm = false;
                        if (items.role) {
                            if (that.userData.roles && that.userData.roles.length > 0) {
                                if ($.inArray(items.role, that.userData.roles) >= 0 || $.inArray("admin", that.userData.roles) >= 0) {
                                    hasPerm = true;
                                }
                            }
                        } else {
                            hasPerm = true;
                        }
                        if (hasPerm) {
                            leftmenu.append('<li class="layui-nav-item" url="' + (items.url || "") + '" model="' + items.model + '"><a href="javascript:;" id="tab">items.text</a></li>');
                        }
                    }
                } else {
                    layui.layer.msg('敬请期待...', {icon: 4});
                }
                element.render('nav(submenu)');
                element.on('nav(submenu)', function () {
                    var url = $(this).attr("url");
                    var model = $(this).attr("model");
                    setCookie('model', model, 1);
                    if (url) {
                        //加载页面信息
                        $.get(url, function (data) {
                            $(".content-body").html(data);
                            layer.closeAll();
                            if (model) {
                                layui.use(model, function () {
                                    layui[model]();
                                });
                            }
                        });
                    } else {
                        layui.layer.msg('敬请期待...', {icon: 4});
                    }
                });
                //初始化打开第一个左侧菜单，第一个子菜单
                var firstmenu = $(leftmenu.find("a")[0]);
                firstmenu.trigger("click");
                var pathModel = getCookie('model');
                if (pathModel) {
                    $(firstmenu.parent().find("dd[model=" + pathModel + "]")).trigger("click");
                } else {
                    $(firstmenu.parent().find("dd")[0]).trigger("click");
                }
            });
            //自动触发top菜单
            $($(".layui-header .layui-nav-item")[0]).trigger("click");

            //初始化退出事件
            $("#logout").on("click", function () {
                layui.layer.msg('您确定要退出吗？', {
                    time: 0 //不自动关闭
                    , icon: 6
                    , btn: ['确定', '取消']
                    , yes: function (index) {
                        delCookie('model');
                        $.get("/api/logout", function (data) {
                            layui.layer.close(index);
                            window.location.href = "index.html";
                        });
                    }
                });
            });
        },
        loadUserInfo: function () {
            var that = this;
            $.ajax({
                type: "GET",
                url: "/api/userInfo",
                cache: false,
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data && data.code == 1000) {
                        that.userData = data.data;
                        $("#user-photo").attr("src", data.data.context.imgUrl || "http://t.cn/RCzsdCq");
                        $("#user-photo").parent().append(data.data.context.nickName || data.data.context.realName);
                    } else {
                        window.location.href = "index.html";
                    }
                }
            });
        }
    };
    exports('home', function () {
        home.init();
    });
});