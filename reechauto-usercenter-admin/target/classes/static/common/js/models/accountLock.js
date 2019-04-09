/**
 * 账号锁定查询模块
 */
layui.define(["form"],function(exports){
    "use strict";
    var form = layui.form;
    var accountLock = {
        status: false,
        init : function () {
            this.render();
        },
        render : function () {
            this.status = false;
            var that = this;
            form.render("checkbox");
            form.on('submit(query)', function(data){
                var identity = data.field.identity || "";
                identity = identity.trim();
                if(!identity){
                    layui.layer.msg('请输入锁定标识', {icon: 2,time:3000});
                    return;
                }
                $.get('/api/account/queryLockedAccount?identity='+identity, function(data) {
                     if(data && data.code == 0){
                         data = data.data;
                         var minute = data.minute;
                         var longtime = data.longtime;
                         var identity = data.identity;
                         var status = data.status;
                         $("input[name='minute']").val(minute || 0);
                         $("input[name='longtime']").val(longtime || 0);
                         if(identity && identity.length > 0){
                             var ids = identity.split(":");
                             $("input[name='appkey']").val(ids[1] || "");
                             $("input[name='ip']").val(ids[2] || "");
                             if(ids[3] == 2){
                                 $("input[name='source']").val("CAS登录");
                             }else if(ids[3] == 1) {
                                 $("input[name='source']").val("OpenId[CAS]登录");
                             }else if(ids[3] == 3) {
                                 $("input[name='source']").val("OAUTH2登录");
                             }else{
                                 $("input[name='source']").val("未知");
                             }
                         }else{
                             $("input[name='appkey']").val("");
                             $("input[name='ip']").val("");
                             $("input[name='source']").val("");
                         }
                         if(status == 5 ){
                             if(!that.status){
                                 $("#account-detail .layui-form-switch").trigger('click');
                             }
                             that.status= true;
                             $("input[name='status']").attr("checked","checked");
                             $("#unlock-btn").removeClass("layui-btn-disabled");
                         }else{
                             if(that.status){
                                 $("#account-detail .layui-form-switch").trigger('click');
                             }
                             $("#unlock-btn").addClass("layui-btn-disabled");
                             that.status = false;
                             $("input[name='status']").removeAttr("checked");
                         }
                        // form.render("checkbox");
                     }else{
                         layui.layer.msg('查询失败', {icon: 2,time:3000});
                     }
                });
            });
            $("#unlock-btn").on("click",function(){
                    if(that.status){
                        var identity = $("input[name='identity']").val();
                        if(identity){
                            $.get('/api/account/unLockedAccount?identity='+identity, function(data) {
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

    exports('accountLock', function () {
        accountLock.init();
    });
});