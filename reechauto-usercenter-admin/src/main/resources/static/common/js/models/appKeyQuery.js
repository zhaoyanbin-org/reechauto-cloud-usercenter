/**
 * appkey申请模块
 */
layui.define(["form"],function(exports){
    "use strict";
    var form = layui.form;
    var appKeyQuery = {
        pages:["page-0","page-1","page-2"],
        currentIdx : 0,
        init : function () {
            this.render();
        },
        data: { //需要提交的数据
        },
        render : function () {
            this.currentIdx = 0;
            var that = this;
            form.render("checkbox");
            form.render("select");
            $("#page-0").show();
            $("#next-btn").show();
            $("#next-btn").on("click",function () {
                $("#page-"+(that.currentIdx)).hide();
                $("#page-"+(that.currentIdx+1)).show();
                that.renderPage(that.currentIdx+1);
                $("#pre-btn").show();
                that.currentIdx++;
                if(that.currentIdx == that.pages.length-1){
                    $("#next-btn").hide();
                }
                that.verifyData = false;
            });
            $("#pre-btn").on("click",function () {
                $("#page-"+(that.currentIdx)).hide();
                $("#page-"+(that.currentIdx-1)).show();
                $("#next-btn").show();
                that.renderPage(that.currentIdx-1);
                that.currentIdx--;
                if(that.currentIdx == 0){
                    $("#pre-btn").hide();
                }
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
                appName : [/^[\u4e00-\u9fa5_a-zA-Z0-9\-]{3,32}$/,'3到32个数字、字母、汉字、横线、下划线'],
                appSecret:[ /^[a-zA-Z0-9]{24,64}$/,'24到64个数字、字母']
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
            $("#save-btn").on("click",function () {
                that.page(that.currentIdx);
                if(that.verifyData){
                    that.data["pageIndex"] = that.currentIdx+1;
                    $.post("/api/appkey/update",that.data,function(data){
                        if(data){
                            if(data.code == 0 && data.data == true){
                                layui.layer.msg('保存成功', {icon: 1,time:3000});
                            }else if(data.code == 1001 ){
                                layui.layer.msg('参数错误', {icon: 2,time:3000});
                            }else if(data.code == 1010 ){
                                layui.layer.msg('APP名称和服务注册优先级已存在', {icon: 2,time:3000});
                            }else{
                                layui.layer.msg('保存失败', {icon: 2,time:3000});
                            }
                        }else{
                            layui.layer.msg('保存失败', {icon: 2,time:3000});
                        }
                    });
                }
            });

            form.on('submit(query)', function(data){
                $.get('/api/appkey/query?appKey='+data.field.appKey, function(data) {
                    if(data.code == 0 && data.data){
                        that.data = data.data;
                        that.renderPage(that.currentIdx);
                        $("#save-btn").show();
                    }else{
                        that.data = {};
                        that.renderPage(that.currentIdx);
                        $("#save-btn").hide();
                        layui.layer.msg('未查询到appKey', {icon: 2});
                    }
                });
            });
        },
        renderPage : function (idx) {
            var that = this;
            switch(idx){
                case 0 :{
                    $("input[name=appId]").val(that.data.appId || "");
                    $("input[name=appName]").val(that.data.appName || "");
                    $("input[name=appKey]").val(that.data.appKey || "");
                    $("input[name=appSecret]").val(that.data.appSecret || "");
                    $("textarea[name=description]").val(that.data.description || "");
                    if(that.data.approved){
                        $("input[name=approved]").attr("checked","true");
                    }else{
                        $("input[name=approved]").removeAttr("checked");
                    }
                    $("select[name=appType] option").each(function () {
                        if($(this).attr("value") == that.data.appType){
                            $(this).attr("selected","selected");
                        }else{
                            $(this).removeAttr("selected");
                        }
                    });
                    $("input[name=authorities]").each(function () {
                        if($(this).attr("value") && that.data.authorities &&that.data.authorities.indexOf($(this).attr("value")) > -1){
                            $(this).attr("checked","true");
                        }else{
                            $(this).removeAttr("checked");
                        }
                    });
                    form.render("checkbox");
                    form.render("select");
                    break;
                }
                case 1 : {
                    $("input[name=authorized_grant_types]").each(function () {
                        if($(this).attr("value") && that.data.authorizedGrantTypes &&that.data.authorizedGrantTypes.indexOf($(this).attr("value")) > -1){
                            $(this).attr("checked","true");
                        }else{
                            $(this).removeAttr("checked");
                        }
                    });
                    $("input[name=accessTokenValidity]").val(that.data.accessTokenValidity || "");
                    $("input[name=refreshTokenValidity]").val(that.data.refreshTokenValidity || "");
                    $("input[name=tokenCallback]").val(that.data.tokenCallback || "");
                    $("textarea[name=resourceIds]").val(that.data.resourceIds || "");
                    $("textarea[name=scopes]").val(that.data.scopes || "");
                    $("textarea[name=additionalInformation]").val(that.data.additionalInformation || "");
                    form.render("checkbox");
                    break;
                }
                case 2 : {
                    $("select[name=loginType] option").each(function () {
                        if($(this).attr("value") == that.data.loginType){
                            $(this).attr("selected","selected");
                        }else{
                            $(this).removeAttr("selected");
                        }
                    });
                    $("input[name=evaluationOrder]").val(that.data.evaluationOrder || "");
                    $("input[name=loginCallbackUrl]").val(that.data.loginCallbackUrl || "");
                    $("input[name=logoutCallbackUrl]").val(that.data.logoutCallbackUrl || "");
                    if(that.data.ignoreAttributes){
                        $("input[name=ignoreAttributes]").attr("checked","true");
                    }else{
                        $("input[name=ignoreAttributes]").removeAttr("checked");
                    }
                    if(that.data.autoRegister){
                        $("input[name=autoRegister]").attr("checked","true");
                    }else{
                        $("input[name=autoRegister]").removeAttr("checked");
                    }
                    form.render("checkbox");
                    form.render("select");
                    break;
                }
            }
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
    exports('appKeyQuery', function () {
        appKeyQuery.init();
    });
});