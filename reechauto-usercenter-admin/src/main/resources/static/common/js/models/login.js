/**
 * Created by zhutw on 2018/1/25.
 */
;!function(){
    "use strict";
    layui.define(["layer","element"],function(exports) {
        var element = layui.element;
        var login = {
          init : function () {
             this.render();
          } ,
          render : function () {
              var that = this;
        	 $("#captcha-img").on("click",function(){
                 $("#captcha-img").attr("src","/captcha?t="+new Date().getTime());
        	 });
              $('input[type="button"]').on("click",function () {
                  that.login();
              });
              $(document).keydown(function(event){
                  if(event.keyCode == 13){
                      that.login();
                  }
              });
            $("#captcha-img").attr("src","/captcha?t="+new Date().getTime());
          },
          login : function(){
        	  var username = $("input[name='username']").val();
        	  if(!username || !(username = username.trim())){
        		  layui.layer.tips('请输入登录用户名', 'input[name="username"]',{icon:2,time:3000,tips:[2,"#B03A5B"]});
        		  $("input[name='username']").focus();
        		  return;
        	  }
        	  var password = $("input[name='password']").val();
        	  if(!password || !(password = password.trim())){
        		  layui.layer.tips('请输入密码', 'input[name="password"]',{icon:2,time:3000,tips:[2,"#B03A5B"]});
        		  $("input[name='password']").focus();
        		  return;
        	  }
        	  var captcha = $("input[name='captcha']").val();
        	  if(!captcha || !(captcha = captcha.trim())){
        		  layui.layer.tips('请输入验证码', 'input[name="captcha"]',{icon:2,time:3000,tips:[2,"#B03A5B"]});
        		  $("input[name='captcha']").focus();
        		  return;
        	  }
        	  //请求后后端
        	  $.post("/login",{
        		  username : username,
        		  password : password,
        		  captcha : captcha
        	  },function(data){
        		  if(data){
        			  if(data.code == 1000 ){
        				  window.location.href = "home.html";
        			  }else if(data.code == 1007 ){
        				  layui.layer.msg('验证码错误', {icon: 2,time:3000});
        			  }else if(data.code == 1002 ){
        				  layui.layer.msg('用户名或密码错误', {icon: 2,time:3000});
        			  }else if(data.code == 1008){
        				  layui.layer.msg('您没有当前用户权限', {icon: 2,time:3000});
                      }else if(data.code == 11100){
                          layui.layer.msg('账号被锁', {icon: 2,time:3000});
                      }else if(data.code == 11103){
                          layui.layer.msg('账号未激活', {icon: 2,time:3000});
        			  }else{
        				  layui.layer.msg('登录失败', {icon: 2,time:3000});
        			  }
        		  }else{
        			  layui.layer.msg('登录失败', {icon: 2,time:3000});
        		  }
        		  $("#captcha-img").attr("src","/captcha?t="+new Date().getTime());
        	  });
          }
        };
        exports('login', function () {
            login.init();
        });
    });
}();