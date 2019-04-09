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
                    field: "userId",
                    title: "用户ID"
                },{
                    field: "realName",
                    title: "真实姓名"
                },{
                    field: "nickName",
                    title: "昵称"
                },{
                    field: "sex",
                    title: "性别"
                },{
                    field: "birthday",
                    title: "生日"
                },{
                    field: "imgUrl",
                    title: "头像",
                    templet:function (row) {
                        return '<div  onclick="show_img(this)" ><img src="'+row.imgUrl+'" alt="图片名"  width="50px" height="50px"></div>';
                    }
                },{
                    field: "userStatus",
                    title: "用户状态"
                },{
                    field: "isDel",
                    title: "是否删除"
                },{
                    field: "createTime",
                    title: "注册时间",
                    templet:function (row) {
                        return row.createTime ? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },{
                    field: "modifyTime",
                    title: "更新时间",
                    templet:function (row) {
                        return row.modifyTime ? new Date(row.modifyTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
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

            function show_img(t) {
            	alert("222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
            	var t = $(t).find("img");
            	//页面层
            	layer.open({
            		type: 1, 
            		skin: 'layui-layer-rim', //加上边框
            		area: ['80%', '80%'], //宽高
            		shadeClose: true, //开启遮罩关闭
            		end: function (index, layero) {
            			return false;
            			},
            			content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
            			});
            	}
            $("#admin-account-btn").on("click",function () {
                layui.layer.open({
                    type : 1
                    ,title: "新增用户" //不显示标题栏
                    ,area:  ['600px', '400px']
                    ,id: 'admin-account-layer' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'r'
                    ,shade:0
                    ,moveType: 1 //拖拽模式，0或者1
                    ,zIndex:999
                    ,content: $('#admin-account-new').html()
                    ,success: function(layero, index){
                        $("#admin-account-layer select[name='role']").empty();
                        $("#admin-account-layer select[name='role']").append('<option value="">请选择</option>');
                        $.ajax({
                            type: "POST",
                            url:"/api/adminRole/selectList",
                            cache: false,
                            dataType: "json",
                            async: false,
                            success: function(data){
                                if(data.data && data.data.length > 0){
                                    $(data.data).each(function(){
                                        $("#admin-account-layer select[name='role']").append('<option value="'+this.rolename+'">'+this.rolename+'</option>');
                                    });
                                }
                            }
                        });
                        form.render();
                    }
                    ,yes: function(index, layero){
                        var mobile = $('#admin-account-layer input[name="mobile"]').val().trim();
                        var roles = $('#admin-account-layer select[name="role"]').val();
                        var username = $('#admin-account-layer input[name="username"]').val().trim();
                        if(!mobile){
                            layui.layer.msg("请输入手机号", {icon: 2,time:3000});
                            return false;
                        }
                        if(!username || username.length == 0){
                            layui.layer.msg("请输入用户姓名", {icon: 2,time:3000});
                            return false;
                        }
                        if(username.length > 20){
                            layui.layer.msg("用户姓名不能超过20字符", {icon: 2,time:3000});
                            return false;
                        }
                        if(!roles ){
                            layui.layer.msg("请选择用户角色", {icon: 2,time:3000});
                            return false;
                        }

                        $.post('/api/adminUser/add',{
                            mobile : mobile,
                            username : username,
                            role:roles.join(",")
                        }, function(data) {
                            if(data.code == 0){
                                layui.layer.msg('新增用户成功', {icon: 1,time:3000});
                                layer.close(index); //如果设定了yes回调，需进行手工关闭
                                $("#query-btn").trigger("click");
                            }else if(data.code == 1006){
                                layui.layer.msg('用户中心不存在', {icon: 2,time:3000});
                            }else if(data.code == 1009){
                                layui.layer.msg('该用户已存在', {icon: 2,time:3000});
                            }else{
                                layui.layer.msg('添加失败', {icon: 2,time:3000});
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
                    url:"/api/search",
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
                    layui.layer.msg('您确定要删除该用户['+data.name+']吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/api/delete?userId="+data.id,function(data){
                                if(!data || data.code != 0 ){
                                    layui.layer.msg('删除失败', {icon: 2});
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
                        ,title: "修改用户" //不显示标题栏
                        ,area:  ['600px', '400px']
                        ,offset: 'auto'
                        ,id: 'admin-account-layer' //设定一个id，防止重复弹出
                        ,btn: ['提交', '取消']
                        ,btnAlign: 'r'
                        ,shade:0
                        ,moveType: 1 //拖拽模式，0或者1
                        ,zIndex:999
                        ,content: $('#admin-account-new').html()
                        ,success: function(layero, index){
                            $($('#admin-account-layer .layui-form-item')[0]).remove();
                            $("#admin-account-layer select[name='role']").empty();
                            $("#admin-account-layer select[name='role']").append('<option value="">请选择</option>');
                            $.ajax({
                                type: "POST",
                                url:"/api/adminRole/selectList",
                                cache: false,
                                dataType: "json",
                                async: false,
                                success: function(response){
                                    if(response.data && response.data.length > 0){
                                        $(response.data).each(function(){
                                            $("#admin-account-layer select[name='role']").append('<option value="'+this.rolename+'">'+this.rolename+'</option>');
                                        });
                                        var roles = (data.role || '' ).split(",");
                                        var temps = [];
                                        $(roles).each(function () {
                                            if(this && this.length > 0){
                                                var role = this.replace(/,/g,'').trim();
                                                if(role){
                                                    temps.push(role);
                                                }
                                            }
                                        });
                                        $('#admin-account-layer select[name="role"]').val(temps);
                                    }
                                }
                            });
                            $('#admin-account-layer input[name="username"]').val(data.username);
                            form.render();
                        }
                        ,yes: function(index, layero){
                            var roles = $('#admin-account-layer select[name="role"]').val();
                            var name = $('#admin-account-layer input[name="username"]').val().trim();
                            if(!name){
                                layui.layer.msg("请输入用户标识", {icon: 2,time:3000});
                                return false;
                            }
                            if(!roles ){
                                layui.layer.msg("请选择用户角色", {icon: 2,time:3000});
                                return false;
                            }
                            $.post('/api/adminUser/update',{
                                username : name,
                                id : data.id,
                                role : roles.join(",")
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

    exports('adminAccount', function () {
        adminAccount.init();
    });
});