/**
 * 用户域账号解锁模块
 */
layui.define(["form"],function(exports){
    "use strict";
    var form = layui.form;
    var domainAccountLock = {
        init : function () {
            this.render();
        },
        status : false,
        render : function () {
            var that = this;
            form.render("checkbox");
            form.on('submit(query)', function(data){
                var identity = data.field.identity || "";
                identity = identity.trim().toLowerCase();
                if(!identity || !(/.+\@glodon.com$/.test(identity) || /.+\@grandsoft.com.cn$/.test(identity))){
                    layui.layer.msg('请输入公司邮箱错误', {icon: 2,time:3000});
                    return;
                }
                $.get('/api/account/queryLockedDomainAccount?identity='+identity, function(data) {
                    if(data && data.code == 0){
                        var time = data.data;
                        $("input[name='minute']").val(parseInt(time/60) || 0);
                        if(time > 0){
                            that.status = true;
                            $("#unlock-btn").removeClass("layui-btn-disabled");
                        }else{
                            $("#unlock-btn").addClass("layui-btn-disabled");
                            that.status = false;
                        }
                    }else{
                        layui.layer.msg('查询失败', {icon: 2,time:3000});
                    }
                });
            });
            $("#unlock-btn").on("click",function(){
                if(that.status){
                    var identity = $("input[name='identity']").val() || "";
                    identity = identity.trim().toLowerCase();
                    if(identity){
                        $.get('/api/account/unLockedDomainAccount?identity='+identity, function(data) {
                            if(data.code == 0){
                                layui.layer.msg('解锁成功', {icon: 1});
                            }else{
                                layui.layer.msg('解锁失败', {icon: 2});
                            }
                            $("#query-btn").trigger('click');
                        });
                    }
                }
            });
        }
    };

    exports('domainAccountLock', function () {
        domainAccountLock.init();
    });
});