/**
 * Created by zhutw on 2018/1/26.
 */

"use strict";
//初始化顶部导航菜单
var NAV_MENUS = [];
NAV_MENUS.push({
    text:"资源服务器（认证服务器）"
    ,id:"userCenter"
    ,role : "user-center"
});
NAV_MENUS.push({
    text:"资源范围"
    ,id:"entAccount"
    ,role : "ent-account"
});
NAV_MENUS.push({
    text:"客户端"
    ,id:"message"
    ,role :"message"
});
NAV_MENUS.push({
    text:"用户"
    ,id:"sysManager"
    ,role :"system"
});
//初始化左边子菜单
var SUB_MENUS = {
    "userCenter" : [{
    	text: "资源服务器管理",
        items: [{
            text: "资源服务器管理",
            url: "pages/resourceServer.html",
            model: "resourceServer"
        }]
    }],
    "entAccount":[{
    	text: "授权服务器管理",
        items: [{
            text: "授权服务器管理",
            url: "pages/resourceScope.html",
            model: "resourceScope"
        }]
    }],
    "message" :[{
    	text: "客户端管理",
        items: [{
            text: "客户端管理",
            url: "pages/clientDetails.html",
            model: "clientDetails"
        }]
    }],
    "sysManager" :[{
        text: "用户管理",
        items: [{
            text: "用户管理",
            url: "pages/system-admin-account.html",
            model: "adminAccount"
        }]
    }]
};