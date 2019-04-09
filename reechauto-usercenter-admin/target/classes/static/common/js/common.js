/**
 * Created by zhutw on 2018/1/25.
 */
;!function(){
    "use strict";
    //禁用鼠标右键
    document.oncontextmenu = function () {
        return false;
    };
    layui.config({
        base:"common/js/models/"
        ,version:"20180401"
    });
    layui.use(["jquery","layer"],function() {
        window.jQuery = window.jquery = window.$ = layui.$; //获取jquery对象
        var layer = layui.layer;
        $.ajaxSetup({
            error: function (jqXHR, textStatus, errorThrown) {
                if(jqXHR.status >= 500 && jqXHR.status< 600){
                    layui.layer.msg('服务器出现异常', {icon: 2});
                }else if(jqXHR.status >= 400 && jqXHR.status< 500){
                    layui.layer.msg('请求出现异常', {icon: 2});
                }else{
                    layui.layer.msg('网络出现异常，请联系管理员', {icon: 2});
                }
            },
            beforeSend:function (jqXHR) {
                $.layerLoadIdx = layer.load();
                return true;
            },
            complete:function (jqXHR,textStatus) {
                layer.close($.layerLoadIdx);
                if(jqXHR.status == 401){
                    layer.alert('登录信息已失效，请重新登录!', {icon: 4,closeBtn: 0},function () {
                            window.location.href="index.html";
                    });
                }
            }
        });
        Date.prototype.format = function(fmt) {
            var o = {
                "M+" : this.getMonth()+1,                 //月份
                "d+" : this.getDate(),                    //日
                "h+" : this.getHours(),                   //小时
                "m+" : this.getMinutes(),                 //分
                "s+" : this.getSeconds(),                 //秒
                "q+" : Math.floor((this.getMonth()+3)/3), //季度
                "S"  : this.getMilliseconds()             //毫秒
            };
            if(/(y+)/.test(fmt)) {
                fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            }
            for(var k in o) {
                if(new RegExp("("+ k +")").test(fmt)){
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                }
            }
            return fmt;
        }
    });
}();
