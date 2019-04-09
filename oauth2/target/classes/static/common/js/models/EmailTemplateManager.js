/**
 * 后台管理用户模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;

    var EmailTemplateManager = {
        init : function () {
            this.render();
        },
        render : function () {
            table.render({
                elem: '#template-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                }
                ,cols :[[{
                    field: "id",
                    title: "模板ID"
                },{
                    field: "name",
                    title: "名称"
                },{
                    field: "appKey",
                    title: "appKey"
                },{
                    field: "status",
                    title: "状态"
                },{
                    field: "createTime",
                    title: "注册时间",
                    templet:function (row) {
                        return row.createTime ? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },{
                    field: "updateTime",
                    title: "更新时间",
                    templet:function (row) {
                        return row.updateTime ? new Date(row.updateTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },{
                    field: "op",
                    title: "操作",
                    fixed:"right",
                    align:"center",
                    toolbar:"#template-toolbar",
                    width: 200
                }]]
                ,page:true
            });

            $("#email-template-new-btn").on("click",function () {
                layui.layer.open({
                    type : 1
                    ,title: "新增模板" //不显示标题栏
                    ,area:  ['800px', '600px']
                    ,id: 'template-layer' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'r'
                    ,shade:0
                    ,moveType: 1 //拖拽模式，0或者1
                    ,zIndex:999
                    ,content: $('#email-template-new').html()
                    ,yes: function(index, layero){
                        var id = $('#template-layer input[name="id"]').val().trim();
                        var name = $('#template-layer input[name="name"]').val().trim();
                        var appKey = $('#template-layer input[name="appKey"]').val().trim();
                        var subject = $('#template-layer input[name="subject"]').val().trim();
                        var content = $('#template-layer textarea[name="content"]').val().trim();
                        if(!id){
                            layui.layer.msg("请输入模板ID，由字母、数字、下划线组成，如account_email_invite_zh_CN", {icon: 2,time:3000});
                            return false;
                        }
                        if(!name){
                            layui.layer.msg("请输入模板名称，如用户中心-邀请用户注册-简体", {icon: 2,time:3000});
                            return false;
                        }
                        if(!appKey){
                            layui.layer.msg("请输入该模板所属应用的appKey", {icon: 2,time:3000});
                            return false;
                        }
                        if(!subject){
                            layui.layer.msg("请输入邮件标题", {icon: 2,time:3000});
                            return false;
                        }
                        if(!content){
                            layui.layer.msg("请输入模板内容", {icon: 2,time:3000});
                            return false;
                        }

                        $.post('/api/colibri/emailTemplate/add',{
                            id : id,
                            name : name,
                            appKey : appKey,
                            subject : subject,
                            content : content
                        }, function(data) {
                            if(data.code == 0){
                                layui.layer.msg('新增成功', {icon: 1,time:3000});
                                layer.close(index); //如果设定了yes回调，需进行手工关闭
                                $("#query-btn").trigger("click");
                            }else if(data.code == 1001){
                                layui.layer.msg('参数错误', {icon: 2,time:3000});
                            }else if(data.code == 1011){
                                layui.layer.msg('模板ID已存在', {icon: 2,time:3000});
                            }else if(data.code == 1013){
                                layui.layer.msg('appKey不存在', {icon: 2,time:3000});
                            }else{
                                layui.layer.msg('添加失败' + (data.cause ? "，"+data.cause : ""), {icon: 2,time:3000});
                            }
                        });
                        return false;
                    }
                    ,cancel: function(index, layero){
                        layer.close(index);
                    }
                });
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("template-table",{
                    url:"/api/colibri/emailTemplate/queryList",
                    method:"get",
                    where:{
                        id:data.field.query,
                        appKey:data.field.appKey
                    },
                    response:{
                        statusName: 'code',
                        statusCode: 0,
                        msgName:"message",
                        countName:"count",
                        dataName:"data"
                    }
                });
            });
            table.on('tool(templateTable)', function(obj){
                var data = obj.data;
                if(obj.event == 'disableTemplate'){
                    layui.layer.msg('您确定要停用该模板吗['+data.id+']吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/api/colibri/emailTemplate/modify",{
                                id : data.id,
                                status : "disable"
                            },function(data){
                                if(!data || data.code != 0 ){
                                    layui.layer.msg('停用失败' + (data.cause ? "，"+data.cause : ""), {icon: 2});
                                }else{
                                    layui.layer.msg('停用成功', {icon: 1});
                                    $("#query-btn").trigger("click");
                                }
                            });
                        }
                    });
                }else if(obj.event == 'enableTemplate'){
                    layui.layer.msg('您确定要启用该模板吗['+data.id+']吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/api/colibri/emailTemplate/modify",{
                                id : data.id,
                                status : "enable"
                            },function(data){
                                if(!data || data.code != 0 ){
                                    layui.layer.msg('启用失败' + (data.cause ? "，"+data.cause : ""), {icon: 2});
                                }else{
                                    layui.layer.msg('启用成功', {icon: 1});
                                    $("#query-btn").trigger("click");
                                }
                            });
                        }
                    });
                }else if(obj.event == 'modifyTemplate'){
                    layui.layer.open({
                        type : 1
                        ,title: "修改模板" //不显示标题栏
                        ,area:  ['800px', '600px']
                        ,offset: 'auto'
                        ,id: 'template-layer' //设定一个id，防止重复弹出
                        ,btn: ['提交', '取消']
                        ,btnAlign: 'r'
                        ,shade:0
                        ,moveType: 1 //拖拽模式，0或者1
                        ,zIndex:999
                        ,content: $('#email-template-new').html()
                        ,success: function (layero, index) {
                            $.ajax({
                                type: "GET",
                                url:"/api/colibri/emailTemplate/query?id="+data.id,
                                cache: false,
                                dataType: "json",
                                async: false,
                                success: function(response){
                                    $('#template-layer input[name="id"]').val(data.id).attr('disabled',true);
                                    $('#template-layer input[name="name"]').val(data.name);
                                    $('#template-layer input[name="appKey"]').val(data.appKey);
                                    $('#template-layer input[name="subject"]').val(response.data.subject);
                                    $('#template-layer textarea[name="content"]').val(response.data.content);
                                }
                            });
                            form.render();
                        }
                        ,yes: function(index, layero){
                            var id = $('#template-layer input[name="id"]').val().trim();
                            var name = $('#template-layer input[name="name"]').val().trim();
                            var appKey = $('#template-layer input[name="appKey"]').val().trim();
                            var subject = $('#template-layer input[name="subject"]').val().trim();
                            var content = $('#template-layer textarea[name="content"]').val().trim();
                            if(!id){
                                layui.layer.msg("请输入模板ID，由字母、数字、下划线组成，如account_email_invite_zh_CN", {icon: 2,time:3000});
                                return false;
                            }
                            if(!name){
                                layui.layer.msg("请输入模板名称，如用户中心-邀请用户注册-简体", {icon: 2,time:3000});
                                return false;
                            }
                            if(!appKey){
                                layui.layer.msg("请输入该模板所属应用的appKey", {icon: 2,time:3000});
                                return false;
                            }
                            if(!subject){
                                layui.layer.msg("请输入邮件标题", {icon: 2,time:3000});
                                return false;
                            }
                            if(!content){
                                layui.layer.msg("请输入模板内容", {icon: 2,time:3000});
                                return false;
                            }

                            $.post('/api/colibri/emailTemplate/modify',{
                                id : id,
                                name : name,
                                appKey : appKey,
                                subject : subject,
                                content : content
                            }, function(data) {
                                if(data.code == 0){
                                    layui.layer.msg('修改成功', {icon: 1,time:3000});
                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                    $("#query-btn").trigger("click");
                                }else if(data.code == 1001){
                                    layui.layer.msg('参数错误', {icon: 2,time:3000});
                                }else if(data.code == 1012){
                                    layui.layer.msg('模板不存在', {icon: 2,time:3000});
                                }else{
                                    layui.layer.msg('修改失败' + (data.cause ? "，"+data.cause : ""), {icon: 2,time:3000});
                                }
                            });
                            return false;
                        }
                        ,cancel: function(index, layero){
                            layer.close(index);
                        }
                    });
                }
            });
            $("#query-btn").trigger("click");


        }
    };

    exports('EmailTemplateManager', function () {
        EmailTemplateManager.init();
    });
});