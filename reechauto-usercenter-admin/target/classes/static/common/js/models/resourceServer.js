/**
 * 后台管理用户模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;
    var adminAccount = {
        init : function () {
            this.render();
        },
        render : function () {
            table.render({
                elem: '#account-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                },
                cols :[[{
                    field: "resourceId",
                    title: "资源服务器ID"
                },{
                    field: "resourceName",
                    title: "资源服务器名字"
                },{
                    field: "op",
                    title: "操作",
                    fixed:"right",
                    align:"center",
                    toolbar:"#account-toolbar",
                    width: 200
                }]]
                ,page:true
            });

            $("#admin-account-btn").on("click",function () {
                layui.layer.open({
                    type : 1
                    ,title: "新增资源服务器" //不显示标题栏
                    ,area:  ['400px', '240px']
                    ,id: 'admin-role-layer' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'r'
                    ,shade:0
                    ,moveType: 1 //拖拽模式，0或者1
                    ,zIndex:999
                    ,content: $('#admin-account-new').html()
                    ,success: function(layero, index){
                        form.render();
                    }
                    ,yes: function(index, layero){
                        var resourceId = $('#admin-role-layer input[name="resourceId"]').val().trim();
                        var resourceName = $('#admin-role-layer input[name="resourceName"]').val().trim();
                        if(!resourceId){
                            layui.layer.msg("请输入资源服务器ID", {icon: 2,time:3000});
                            return false;
                        }
                        
                        if(!resourceName){
                            layui.layer.msg("请输入资源服务器名称", {icon: 2,time:3000});
                            return false;
                        }
                        
                        $.post('/resourceServer/add',{
                        	resourceId : resourceId,
                        	resourceName:resourceName
                        }, function(data) {
                            if(data.code == 1000){
                                layui.layer.msg('新增成功', {icon: 1,time:3000});
                                layer.close(index); //如果设定了yes回调，需进行手工关闭
                                $("#query-btn").trigger("click");
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
                table.reload("account-table",{
                    url:"/resourceServer/query",
                    method:"get",
                    where:{
                        query:data.field.query
                    },
                    response:{
                        statusName: 'code',
                        statusCode: 1000,
                        msgName:"message",
                        countName:"count",
                        dataName:"data"
                    }
                });
            });
            table.on('tool(accountTable)', function(obj){
                var data = obj.data;
                if(obj.event == 'deleteUser'){
                    layui.layer.msg('您确定要删除该资源服务器['+data.resourceId+']吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/resourceServer/delete?resourceId="+data.resourceId,function(data){
                                if(!data || data.code != 1000 ){
                                    layui.layer.msg(data.cause ? data.cause : "删除失败", {icon: 2});
                                }else{
                                    layui.layer.msg('删除成功', {icon: 1});
                                    $("#query-btn").trigger("click");
                                }
                            });
                        }
                    });
                }else if(obj.event == 'updateUser'){
                    layui.layer.open({
                        type : 1
                        ,title: "修改角色" //不显示标题栏
                        ,area:   ['400px', '240px']
                        ,offset: 'auto'
                        ,id: 'admin-role-layer' //设定一个id，防止重复弹出
                        ,btn: ['提交', '取消']
                        ,btnAlign: 'r'
                        ,shade:0
                        ,moveType: 1 //拖拽模式，0或者1
                        ,zIndex:999
                        ,content: $('#admin-account-new').html()
                        ,success: function(layero, index){
                            $('#admin-role-layer input[name="resourceId"]').val(data.resourceId);
                            $('#admin-role-layer input[name="resourceName"]').val(data.resourceName);
                            form.render();
                        }
                        ,yes: function(index, layero){
                            var resourceId = $('#admin-role-layer input[name="resourceId"]').val().trim();
                            var resourceName = $('#admin-role-layer input[name="resourceName"]').val().trim();
                            if(!resourceId){
                                layui.layer.msg("请输入资源服务器ID", {icon: 2,time:3000});
                                return false;
                            }
                            if(!resourceName){
                                layui.layer.msg("请输入资源服务器名称", {icon: 2,time:3000});
                                return false;
                            }
                            $.post('/resourceServer/update',{
                            	oldResourceId : data.resourceId,
                            	newResourceId:resourceId,
                            	newResourceName:resourceName
                            }, function(data) {
                                if(data.code == 1000){
                                    layui.layer.msg('修改成功', {icon: 1,time:3000});
                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                    $("#query-btn").trigger("click");
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


    exports('resourceServer', function () {
        adminAccount.init();
    });
});