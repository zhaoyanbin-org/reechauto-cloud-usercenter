/**
 * 遗留账号查询模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;
    var accountLagecy = {
        init : function () {
            this.render();
        },
        render : function () {
            table.render({
                elem: '#account-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                }
                ,cols :[[{
                    field: "id",
                    title: "账号ID"
                },{
                    field: "username",
                    title: "用户名"
                },{
                    field: "email",
                    title: "电子邮箱"
                },{
                    field: "mobile",
                    title: "手机号"
                },{
                    field: "globalId",
                    title: "GlobalId",
                    templet : function (row) {
                        if(!row.description){
                            return "";
                        }
                        try {
                            var obj = eval("("+row.description+")");
                            if(obj){
                                return obj.globalId || "";
                            }
                        }catch (e){
                        }
                        return "";
                    }
                },{
                    field: "fullname",
                    title: "昵称"
                },{
                    field: "statusBit",
                    title: "激活状态",
                    templet: function (row) {
                        if(row.statusBit){
                            var str =[];
                            if((row.statusBit & 4) == 4){
                                str.push("手机号");
                            }
                            if((row.statusBit & 2) == 2){
                                str.push("邮箱");
                            }
                            if((row.statusBit & 1) == 1){
                                str.push("用户名");
                            }
                            return str.join(",");
                        }
                        return "未激活";
                    }
                },{
                    field: "migrated",
                    title: "迁移状态",
                    templet:function (row) {
                        return row.migrated ? "已迁移":"未迁移"
                    }
                },{
                    field: "createTime",
                    title: "注册时间",
                    templet:function (row) {
                        return row.createTime ? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },{
                    field: "lastUpdateTime",
                    title: "更新时间",
                    templet:function (row) {
                        return row.lastUpdateTime ? new Date(row.lastUpdateTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },{
                    field: "op",
                    title: "操作",
                    fixed:"right",
                    align:"center",
                    toolbar:"#user-toolbar",
                    width: 200
                    //templet
                }]]
                ,page:false
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("account-table",{
                    url:"/api/account/searchLegacy",
                    method:"get",
                    where:{
                        query:encodeURIComponent(data.field.query)
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
            table.on('tool(accountTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'reset'){
                    if(data.migrated){
                        layui.layer.msg('该账号已迁移，不能重置密码', {icon: 2});
                        return ;
                    }
                    var account = data.email || data.mobile;
                    if(!account || !account.length){
                        layui.layer.msg('该账号信息不全，不存在邮箱或手机号，无法重置密码', {icon: 2});
                        return;
                    }
                    layer.prompt({title: '请输入待重置密码', formType: 1}, function(pass, index){
                        if(!(pass=pass.trim()) || pass.length < 6 || pass.length > 16 ){
                            layui.layer.msg('密码不能为空，不少于6大于16位', {icon: 2});
                            return;
                        }
                        $.post("/api/account/resetPasswordLegacy", { identity: account, password: pass }, function(data) {
                            if(!data || !data.code){
                                layui.layer.msg('重置密码失败', {icon: 2});
                            }else if(data.code == 200 ){
                                layui.layer.close(index);
                                layui.layer.msg('重置密码成功', {icon: 1});
                            }else{
                                layui.layer.msg('重置密码失败', {icon: 2});
                            }
                        });
                    });
                }else if(obj.event == 'migrated'){
                    if(data.migrated){
                        layui.layer.msg('该账号已迁移', {icon: 2});
                        return ;
                    }
                    layui.layer.msg('您确定要设置该账号['+(data.fullName || data.userName || data.mobile || data.email)+']已迁移吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/api/account/setMigrated?userId="+data.id,function(data){
                                if(!data || data.code != 0 ){
                                    layui.layer.msg('操作失败', {icon: 2});
                                }else{
                                    layui.layer.msg('操作成功', {icon: 1});
                                    $("#search-btn").trigger("click");
                                }
                            });
                        }
                    });
                }
            });
        }
    };

    exports('accountLagecy', function () {
        accountLagecy.init();
    });
});