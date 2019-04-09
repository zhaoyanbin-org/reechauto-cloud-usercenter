/**
 * 后台管理角色模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;
    var adminRole = {
        init : function () {
            this.render();
        },
        render : function () {
            table.render({
                elem: '#role-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                }
                ,cols :[[{
                    field: "rolename",
                    title: "名称"
                },{
                    field: "code",
                    title: "编码"
                },{
                    field: "createTime",
                    title: "创建时间",
                    templet:function (row) {
                        return row.createTime ? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss") : "";
                    }
                },{
                    field: "op",
                    title: "操作",
                    fixed:"right",
                    align:"center",
                    toolbar:"#role-toolbar",
                    width: 200
                }]]
            });

            $("#admin-role-btn").on("click",function () {
                layui.layer.open({
                    type : 1
                    ,title: "新增角色" //不显示标题栏
                    ,area:  ['400px', '240px']
                    ,id: 'admin-role-layer' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'r'
                    ,shade:0
                    ,moveType: 1 //拖拽模式，0或者1
                    ,zIndex:999
                    ,content: $('#admin-role-new').html()
                    ,success: function(layero, index){
                        form.render();
                    }
                    ,yes: function(index, layero){
                        var code = $('#admin-role-layer input[name="code"]').val().trim();
                        var rolename = $('#admin-role-layer input[name="rolename"]').val().trim();
                        if(!code){
                            layui.layer.msg("请输入角色编码", {icon: 2,time:3000});
                            return false;
                        }
                        if(!/^[a-zA-Z][0-9a-zA-Z\-\_]{3,31}$/.test(code)){
                            layui.layer.msg("角色编码需字母开头，有字母数字下划线、横线组成不能超过32个字符，大于3个字符", {icon: 2,time:3000});
                            return false;
                        }
                        if(!rolename){
                            layui.layer.msg("请输入角色名称", {icon: 2,time:3000});
                            return false;
                        }
                        if(rolename.length < 3 || rolename .length > 32){
                            layui.layer.msg("角色名称需大于2，不超过32个字符", {icon: 2,time:3000});
                            return false;
                        }
                        $.post('/api/adminRole/add',{
                            rolename : rolename,
                            code:code
                        }, function(data) {
                            if(data.code == 0){
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
                table.reload("role-table",{
                    url:"/api/adminRole/selectList",
                    method:"post",
                    where:{
                        name:data.field.name
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
            table.on('tool(roleTable)', function(obj){
                var data = obj.data;
                if(obj.event == 'deleteRole'){
                    layui.layer.msg('您确定要删除该角色['+data.name+']吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/api/adminRole/delete?id="+data.id,function(data){
                                if(!data || data.code != 0 ){
                                    layui.layer.msg(data.cause ? data.cause : "删除失败", {icon: 2});
                                }else{
                                    layui.layer.msg('删除成功', {icon: 1});
                                    $("#query-btn").trigger("click");
                                }
                            });
                        }
                    });
                }else if(obj.event == 'updateRole'){
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
                        ,content: $('#admin-role-new').html()
                        ,success: function(layero, index){
                            $('#admin-role-layer input[name="code"]').val(data.code);
                            $('#admin-role-layer input[name="name"]').val(data.name);
                            $('#admin-role-layer input[name="code"]').attr("disabled","disabled");
                            form.render();
                        }
                        ,yes: function(index, layero){
                            var rolename = $('#admin-role-layer input[name="rolename"]').val().trim();
                            if(!rolename){
                                layui.layer.msg("请输入角色名称", {icon: 2,time:3000});
                                return false;
                            }
                            if(rolename.length < 3 || rolename.length > 32){
                                layui.layer.msg("角色名称需大于2，不超过32个字符", {icon: 2,time:3000});
                                return false;
                            }
                            $.post('/api/adminRole/update',{
                                id : data.id,
                                rolename:rolename
                            }, function(data) {
                                if(data.code == 0){
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

    exports('adminRole', function () {
        adminRole.init();
    });
});