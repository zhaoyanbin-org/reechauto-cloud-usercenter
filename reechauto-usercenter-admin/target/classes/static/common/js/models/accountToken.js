/**
 * 用户锁定查询模块
 */
layui.define(["form"],function(exports){
    "use strict";
    var form = layui.form;
    var token;
    var $returnTokenTextarea = $("textarea[name='returnToken']");
    var $clearTokenBtn = $("#clearToken-btn");
    var accountToken = {
        status: false,
        init : function () {
            this.render();
        },
        render : function () {
            this.status = false;
            var that = this;
            form.render("checkbox");
            form.on('submit(query)', function(data){
                token = data.field.token || "";
                token = token.trim();
                if(!token){
                    layui.layer.msg('请输入access_token', {icon: 2,time:3000});
                    return;
                }
                $returnTokenTextarea.text('');//清空文本框
                $.get('/api/token/check?token='+token, function(data) {
                     if(data && data.code == 0){
                         that.status = true;
                         $returnTokenTextarea.text(formatJson(data.data));
                         $clearTokenBtn.removeClass("layui-btn-disabled");
                         
                     }else{
                         layui.layer.msg('accessToken无效', {icon: 2,time:3000});
                         $clearTokenBtn.addClass("layui-btn-disabled");
                     }
                });
            });
            $clearTokenBtn.on("click",function(){
                    if(that.status){
                        if(token){
                            $.get('/api/token/clean?token='+token, function(data) {
                                if(data && data.code == 0){
                                    layui.layer.msg('清除成功', {icon: 1});
                                    $returnTokenTextarea.text('');//清空文本框
                                    $clearTokenBtn.addClass("layui-btn-disabled");
                                    that.status = false;
                                }else{
                                    layui.layer.msg('清除失败', {icon: 2});
                                }
                            });
                        }
                    }
            });
        }
    };

    exports('accountToken', function () {
        accountToken.init();
    });
});