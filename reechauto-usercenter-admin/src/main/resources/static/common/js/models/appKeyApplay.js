/**
 * appkey申请模块
 */
layui.define(["form"],function(exports){
    "use strict";
    var form = layui.form;
    var appKeyApplay = {
        pages:["page-0","page-1","page-2"],
        currentIdx : 0, //当前页索引
        verifyData:false, //标识每一页数据是否校验通过
        data:{ //需要提交的数据
            approved:true,
            appType :1,
            authorities:"ROLE_CLIENT,ROLE_RESOURCE",
            authorizedGrantTypes:"implicit,authorization_code,password,client_credentials,refresh_token",
            loginType:2,
            ignoreAttributes:true,
            autoRegister:false
        },
        init : function () {
            this.render();
        },
        render : function () {
            this.currentIdx = 0;
            this.verifyData = false;
            var that = this;
            form.render("checkbox");
            form.render("select");
            $("#page-0").show();
            $("#next-btn").show();
            $("#next-btn").on("click",function () {
                that.page(that.currentIdx);
                if(!that.verifyData){
                    return;
                }
                $("#page-"+(that.currentIdx)).hide();
                $("#page-"+(that.currentIdx+1)).show();
                $("#pre-btn").show();
                that.currentIdx++;
                if(that.currentIdx == that.pages.length-1){
                    $("#next-btn").hide();
                    $("#submit-btn").show();
                }
                that.verifyData = false;
            });
            $("#pre-btn").on("click",function () {
                $("#page-"+(that.currentIdx)).hide();
                $("#page-"+(that.currentIdx-1)).show();
                $("#submit-btn").hide();
                $("#next-btn").show();
                that.currentIdx--;
                if(that.currentIdx == 0){
                    $("#pre-btn").hide();
                }
            });

            form.on('switch(approved)', function(data){
                that.data.approved = data.elem.checked;
            });
            form.on('select(appType)', function(data){
                that.data.appType = data.value;
            });
            form.on('checkbox(authorities)', function(data){
                var authorities = that.data.authorities.split(",");
                if(data.elem.checked){
                    authorities.push(data.value);
                }else{
                    authorities.splice($.inArray(data.value,authorities),1);
                }
                that.data.authorities = authorities.join(",");
            });
            form.on('checkbox(authorized_grant_types)', function(data){
                var authorizedGrantTypes = that.data.authorizedGrantTypes.split(",");
                if(data.elem.checked){
                    authorizedGrantTypes.push(data.value);
                }else{
                    authorizedGrantTypes.splice($.inArray(data.value,authorizedGrantTypes),1);
                }
                that.data.authorizedGrantTypes = authorizedGrantTypes.join(",");
            });
            form.on('select(loginType)', function(data){
                that.data.loginType = data.value;
            });
            form.on('switch(ignoreAttributes)', function(data){
                that.data.ignoreAttributes = data.elem.checked;
            });
            form.on('switch(autoRegister)', function(data){
                that.data.autoRegister = data.elem.checked;
            });
            //表单校验
            form.verify({
                appId: function (value, item) {
                    if(!new RegExp("^[a-zA-Z0-9_-]{4,32}$").test(value)){
                        return '4-32个字母、数字、横线、下划线';
                    }
                    //检查是否存在
                    var data =$.ajax({url:"/api/appkey/checkAppId?appId="+value,async:false});
                    if(data && data.responseText){
                        data = $.parseJSON(data.responseText);
                        if(data.code ==0 && data.data){
                                return false;
                        }
                    }
                    return "appId已存在";
                },
                appName : [/^[\u4e00-\u9fa5_a-zA-Z0-9\-]{3,32}$/,'3到32个数字、字母、汉字、横线、下划线'],
                appKey : function(value, item){
                    if(!new RegExp("^[a-zA-Z0-9]{24,64}$").test(value)){
                        return '24-64个数字、字母';
                    }
                    //检查是否存在
                    var data =$.ajax({url:"/api/appkey/checkAppKey?appKey="+value,async:false});
                    if(data && data.responseText){
                        data = $.parseJSON(data.responseText);
                        if(data.code ==0 && data.data){
                            return false;
                        }
                    }
                    return "appKey已存在";
                },
                appSecret:[ /^[a-zA-Z0-9]{24,64}$/,'24到64个数字、字母']
            });


            
            $("#get-appkey-btn").on("click",function () {
                $.get('/api/appkey/getRand?n='+32, function(data) {
                    if(data.code == 0){
                        $('input[name="appKey"]').val(data.data);
                    }else{
                        layui.layer.msg('获取失败', {icon: 2});
                    }
                });
            });
            $("#get-secret-btn").on("click",function () {
                $.get('/api/appkey/getRand?n='+32, function(data) {
                    if(data.code == 0){
                        $('input[name="appSecret"]').val(data.data);
                    }else{
                        layui.layer.msg('获取失败', {icon: 2});
                    }
                });
            });

            form.on('submit(form0)', function(data){
                that.data.appId = data.field.appId;
                that.data.appKey = data.field.appKey;
                that.data.appName = data.field.appName;
                that.data.appSecret = data.field.appSecret;
                that.data.description = data.field.description || null;
                that.verifyData = true;
            });
            form.on('submit(form1)', function(data){
                //赋值校验数据
                that.data.accessTokenValidity = data.field.accessTokenValidity;
                that.data.refreshTokenValidity = data.field.refreshTokenValidity;
                that.data.tokenCallback = data.field.tokenCallback || null;
                that.data.resourceIds = data.field.resourceIds || null;
                that.data.scopes = data.field.scopes || null;
                that.data.additionalInformation = data.field.additionalInformation || null;
                that.verifyData = true;
            });
            form.on('submit(form2)', function(data){
                //赋值校验数据
                that.data.evaluationOrder = data.field.evaluationOrder;
                that.data.loginCallbackUrl = data.field.loginCallbackUrl || null;
                that.data.logoutCallbackUrl = data.field.logoutCallbackUrl || null;
                that.verifyData = true;
            });
            $("#submit-btn").on("click",function () {
                that.page2();
                if(that.verifyData){
                    $.post("/api/appkey/add",that.data,function(data){
                        if(data){
                            if(data.code == 0 ){
                                layui.layer.msg('创建成功', {icon: 1,time:3000},function () {
                                    $("#submenu .layui-nav-child>dd.layui-this").trigger("click");
                                });
                            }else if(data.code == 1001 ){
                                layui.layer.msg('参数错误', {icon: 2,time:3000});
                            }else if(data.code == 1010 ){
                                layui.layer.msg('APP名称和服务注册优先级已存在', {icon: 2,time:3000});
                            }else{
                                layui.layer.msg('创建失败', {icon: 2,time:3000});
                            }
                        }else{
                            layui.layer.msg('创建失败', {icon: 2,time:3000});
                        }
                    });
                }
            });
        },
        page : function (idx) {
            switch(idx){
                case 0 :{
                    this.page0();
                    break;
                }
                case 1 : {
                    this.page1();
                    break;
                }
                case 2 : {
                    this.page2();
                    break;
                }
            }
        },
        page0:function () {
            $('button[lay-filter="form0"]').trigger("click");
        },
        page1:function () {
            $('button[lay-filter="form1"]').trigger("click");
        },
        page2:function () {
            $('button[lay-filter="form2"]').trigger("click");
        }
    };

    exports('appKeyApplay', function () {
        appKeyApplay.init();
    });
});