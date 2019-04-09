/**
 * 审计日志查询模块
 */
layui.define(["table","form","laydate"],function(exports){
    "use strict";
    var table = layui.table;
    var laydate = layui.laydate;
    var form = layui.form;
    var auditLog = {
        init : function () {
            this.render();
        },
        render : function () {
            var that = this;
            //日期范围
            laydate.render({
                elem: '#time-range'
                ,range: true
            });
            table.render({
                elem: '#auditLog-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                }
                ,cols :[[{
                    field: "createTime",
                    title: "操作时间",
                    templet:function (row) {
                        return row.createTime ? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },{
                    field: "globalId",
                    title: "GlobalId"
                },{
                    field: "accountName",
                    title: "登录账号"
                },{
                    field: "remoteIp",
                    title: "登录IP"
                },{
                    field: "moduleName",
                    title: "模块名称"
                },{
                    field: "objectId",
                    title: "对象Id"
                },{
                    field: "objectIdentify",
                    title: "对象标识"
                },{
                    field: "content",
                    title: "操作内容",
                    templet:function (row) {
                        return '<span title="'+row.content+'">'+row.content+"</span>";
                    }
                }]]
                ,page:true
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("auditLog-table",{
                    url:"/api/log/auditLog",
                    method:"post",
                    where:{
                        moduleName : data.field.moduleName,
                        globalId : data.field.globalId,
                        objectIdentify : data.field.objectIdentify,
                        queryTime : data.field.queryTime
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

    exports('auditLog', function () {
        auditLog.init();
    });
});