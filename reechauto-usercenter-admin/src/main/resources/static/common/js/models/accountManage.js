/**
 * 用户账号管理模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;
    var accountManage = {
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
                    field: "userId",
                    title: "账号ID",
                    templet:function (row) {
                        if(row.globalId && row.userId != row.globalId){
                            return row.userId +"("+ (row.globalId || "")+")";
                        }else{
                            return row.userId;
                        }
                    }
                },{
                    field: "userName",
                    title: "用户名"
                },{
                    field: "email",
                    title: "电子邮箱"
                },{
                    field: "mobile",
                    title: "手机号"
                },{
                    field: "passwordMobile",
                    title: "密码手机号"
                },{
                    field: "fullName",
                    title: "昵称"
                },{
                    field: "userType",
                    title: "用户类型",
                    templet:function (row) {
                        return row.userType ? "企业":"个人";
                    }
                },{
                    field: "verifyStatus",
                    title: "激活状态",
                    templet: function (row) {
                        if(row.verifyStatus){
                            var str =[];
                            if((row.verifyStatus & 4) == 4){
                                str.push("手机号");
                            }
                            if((row.verifyStatus & 2) == 2){
                                str.push("邮箱");
                            }
                            if((row.verifyStatus & 1) == 1){
                                str.push("用户名");
                            }
                            return str.join(",");
                        }
                        return "未激活";
                    }
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
                    toolbar:"#user-toolbar",
                    width: 200
                    //templet
                }]]
                ,page: false
            });

            table.on('tool(accountTable)', function(obj){
                var data = obj.data;
                if(obj.event === 'reset'){
                    var account = data.accountName || data.email || data.mobile;
                    if(!account || !account.length){
                        layui.layer.msg('该账号信息不全，无法重置密码', {icon: 2});
                        return;
                    }
                    layer.prompt({title: '请输入待重置密码', formType: 1}, function(pass, index){
                            if(!(pass=pass.trim()) || pass.length < 6 || pass.length > 16 ){
                                layui.layer.msg('密码不能为空，不少于6大于16位', {icon: 2});
                                return;
                            }
                            $.post("/api/account/resetPassword", { identity: account, password: pass }, function(data) {
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
                }else if(obj.event == 'deleteUser'){
                    layui.layer.msg('您确定要删除该账号['+(data.fullName || data.accountName || data.mobile || data.email)+']吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/api/account/deleteUser?userId="+data.userId,function(data){
                                if(!data || data.code != 0 ){
                                    layui.layer.msg('删除失败', {icon: 2});
                                }else{
                                    layui.layer.msg('删除成功', {icon: 1});
                                    $("#search-btn").trigger("click");
                                }
                            });
                        }
                    });
                }
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("account-table",{
                    url:"/api/account/search",
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

            //注册按钮
            $("#batch-account-new-btn").on("click",function () {
                layui.layer.open({
                    type : 1
                    ,title: "批量注册账号" //不显示标题栏
                    ,area: '380px;'
                    ,id: 'batch-account-layer' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'r'
                    ,shade:0
                    ,moveType: 1 //拖拽模式，0或者1
                    ,zIndex:999
                    ,content: $('#batch-account-new').html()
                    ,success: function(layero, index){
                        form.render("radio");
                        form.on('radio(radioType)', function(data){
                           $('.layui-layer input[name="type"]').val(data.value);
                        });
                    }
                    ,yes: function(index, layero){
                        var accountStrList = $("#batch-account-layer textarea[name='accountStrList']").val();
                        accountStrList= $.trim(accountStrList);
                        var type = $('.layui-layer input[name="type"]').val();
                        if(!accountStrList){
                            layui.layer.msg('请输入注册账号信息', {icon: 2,time:3000});
                            return false;
                        }
                        var accounts = accountStrList.split("\n");
                        var accountList = [];
                        for(var i=0;i<accounts.length;i++){
                            var str = $.trim(accounts[i]);
                            if(!str){
                                continue;
                            }
                            accountList.push(str);
                            var accs = str.split(":");
                            if(accs.length != 2 || accs[0].length<3 || accs[0].length>64 || accs[1].length <6 || accs[1].length > 16){
                                layui.layer.msg(str+',输入错误', {icon: 2,time:3000});
                                return false;
                            }
                        }
                        accountStrList = accountList.join(",");
                        $.post('/api/account/batSignup',{
                                type : type,
                               accountStrList:accountStrList
                            }, function(data) {
                                if(data.code == 0){
                                    layui.layer.msg('注册成功', {icon: 1,time:3000});
                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                }else{
                                    layui.layer.msg(data.data.join(",")+' # 注册失败', {icon: 2,time:6000});
                                }
                        });
                        return false;
                    }
                    ,cancel: function(index, layero){
                        layer.close(index);
                    }
                });
            });
        }
    };

    exports('accountManage', function () {
        accountManage.init();
    });
});