/**
 * 短信锁定查询模块
 */
layui.define(["form"],function(exports){
    "use strict";
    var form = layui.form;
    var smsLock = {
        status: false,
        init : function () {
            this.render();
        },
        render : function () {
            this.status = false;
            var that = this;
            form.render("select");
            form.on('submit(query)', function(data){
                var mobile = data.field.mobile || "";
                var code = data.field.sendScene;
                mobile = mobile.trim();
                if(!mobile){
                    layui.layer.msg('请输入手机号', {icon: 2,time:3000});
                    return;
                }
                $("#unlock-btn").addClass("layui-btn-disabled");
                $.get('/api/sendInfor/queryLockedInfor?mobile='+mobile+'&code='+code, function(data) {
                     if(data && data.code == 0){
                         data = data.data;
                         var minute = data.minute;
                         var hour = data.hour;
                         var day = data.day;
                         var validateTimes = data.validateMobileTimes;
                         $("input[name='minuteTimes']").val(minute || 0);
                         $("input[name='hourTimes']").val(hour || 0);
                         $("input[name='dayTimes']").val(day || 0);
                         $("input[name='validateTimes']").val(validateTimes || 0);
                         
                         that.status= true;
                         if(!minute || !hour || !day || !validateTimes){
                        	$("#unlock-btn").removeClass("layui-btn-disabled");
                         }
                     }else{
                    	 $("#unlock-btn").addClass("layui-btn-disabled");
                         layui.layer.msg('查询失败', {icon: 2,time:3000});
                     }
                });
            });
            $("#unlock-btn").on("click",function(){
                    if(that.status){
                        var mobile = $("input[name='mobile']").val();
                        var code = $("select[name='sendScene']").val();
                        if(mobile){
                            $.get('/api/sendInfor/unLockedInfor?mobile='+mobile+'&code='+code, function(data) {
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

    exports('smsLock', function () {
        smsLock.init();
    });
});