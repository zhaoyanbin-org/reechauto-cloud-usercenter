/**
 * 用户身份认证模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;
    var accountIdentityAudit = {
        init : function () {
            this.render();
        },
        render : function () {
            form.render("select");
            table.render({
                elem: '#account-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                }
                ,cols :[[{
                    field: "userIdStr",
                    title: "用户ID"
                },{
                    field: "status",
                    title: "审核状态",
                    templet:function (row) {
                        if(row.status == 1 ){
                            return "审核通过";
                        }
                        if(row.status == 2 ){
                            return "审核不通过";
                        }
                        return "未审核";
                    }
                },{
                    field: "realName",
                    title: "真实姓名"
                },{
                    field: "idCardNo",
                    title: "身份证号码"
                },{
                    field: "auditRemark",
                    title: "结果备注"
                },{
                    field: "updateTime",
                    title: "更新时间",
                    templet:function (row) {
                        return row.updateTime ? new Date(row.updateTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },{
                    field: "createTime",
                    title: "申请时间",
                    templet:function (row) {
                        return row.createTime ? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss"):"";
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
                ,page:true
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("account-table",{
                    url:"/api/account/authAccounts",
                    method:"post",
                    where:{
                        identity:data.field.identity,
                        realName:data.field.realName,
                        status:data.field.status
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
                if(obj.event === 'audit') {
                    layui.layer.open({
                        type: 1
                        , title: "信息审核" //不显示标题栏
                        , area: ['600px', '500px']
                        , offset: 'auto'
                        , id: 'account-audit-layer' //设定一个id，防止重复弹出
                        , btn: data.status==0 ? ['审核通过', '审核不通过'] : []
                        , btnAlign: 'r'
                        , shade: 0
                        , moveType: 1 //拖拽模式，0或者1
                        , zIndex: 999
                        , content: $('#detail-info').html()
                        , success: function (layero, index) {
                            $("#account-audit-layer #auth-images").empty();
                            $.get('/api/account/authAttachments?userId='+data.userIdStr,null, function(data) {
                                if(data.code == 0){
                                    data =data.data;
                                    var html = [];
                                    for(var i = 0; i< data.length ;i+=2){
                                        html.push('<div class="layui-row">');
                                        html.push('<div class="layui-col-xs6"><img src="/api/account/file/'+data[i].id+'" onclick="openImgesDiv(this)" style="width: 280px;height: 260px;margin-left: 10px;" /></div>');
                                        if(i+1 < data.length){
                                            html.push('<div class="layui-col-xs6"><img src="/api/account/file/'+data[i+1].id+'" onclick="openImgesDiv(this)" style="width: 280px;height: 260px;margin-left: 10px;" /></div>');
                                        }
                                        html.push('</div>');
                                    }
                                    $("#account-audit-layer #auth-images").html(html.join(""));
                                }
                            },"json");
                            $('#account-audit-layer input[name="realName"]').val(data.realName);
                            $('#account-audit-layer input[name="idCardNo"]').val(data.idCardNo);
                            form.render();
                        }
                        , yes: function (index, layero) {
                            var _index = index;
                            layui.layer.msg('您确定该用户信息正确，可以审核通过吗？', {
                                time: 0 //不自动关闭
                                ,icon: 6
                                ,btn: ['确定', '取消']
                                ,yes: function(index){
                                    $.post("/api/account/doAuth", { userId: data.userIdStr, reason: "审核通过",status : 1 }, function(data) {
                                        if(!data){
                                            layui.layer.msg('审核失败', {icon: 2});
                                        }else if(data.code == 0 ){
                                            layui.layer.close(_index);
                                            layui.layer.msg('审核通过成功', {icon: 1});
                                            $("#search-btn").trigger("click");
                                        }else{
                                            layui.layer.msg(data.cause || '审核失败', {icon: 2});
                                        }
                                    });
                                }
                            });
                            return false;
                        }
                        , btn2: function (index, layero) {
                            var _index = index;
                            layui.layer.prompt({title: '请输入不通过原因', formType: 2}, function(reason, index){
                                if(!(reason=reason.trim()) || reason.length < 3 || reason.length > 32 ){
                                            layui.layer.msg('不通过原因不能为空，不少于3大于32个字符', {icon: 2});
                                    return;
                                }
                                $.post("/api/account/doAuth", { userId: data.userIdStr, reason: reason,status : 2 }, function(data) {
                                    if(!data){
                                        layui.layer.msg('审核失败', {icon: 2});
                                    }else if(data.code == 0 ){
                                        layui.layer.close(index);
                                        layui.layer.close(_index);
                                        layui.layer.msg('审核不通过成功', {icon: 1});
                                        $("#search-btn").trigger("click");
                                    }else{
                                        layui.layer.msg(data.cause || '审核失败', {icon: 2});
                                    }
                                });
                            });
                            return false;
                        }
                        ,cancel: function(index, layero){
                            layui.layer.close(index);
                        }
                    });
                }
            });
        }
    };

    exports('accountIdentityAudit', function () {
        accountIdentityAudit.init();
    });
});