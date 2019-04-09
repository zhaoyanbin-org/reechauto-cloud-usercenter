/**
 * 邀约账号查询模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;
    var accountInvite = {
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
                    field: "email",
                    title: "电子邮箱"
                },{
                    field: "mobile",
                    title: "手机号"
                },{
                    field: "appKey",
                    title: "appKey"
                },{
                    field: "initPassword",
                    title: "初始化密码"
                },{
                    field: "nickname",
                    title: "昵称"
                },{
                    field: "activated",
                    title: "激活状态",
                    templet:function (row) {
                        return row.activated ? "已激活":"未激活"
                    }
                },{
                    field: "createTime",
                    title: "注册时间",
                    templet:function (row) {
                        return row.createTime ? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                }]]
                ,page:false
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("account-table",{
                    url:"/api/account/searchInvite",
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
        }
    };

    exports('accountInvite', function () {
        accountInvite.init();
    });
});