/**
 * Email模板
 */
layui.define(["table","form","laydate"],function(exports){
    "use strict";
    var laydate = layui.laydate;
    var table = layui.table;
    var form = layui.form;
    var emailSendRecord = {
        init : function () {
            this.render();
            this.dateInit();
        },
        dateInit:function(){
            var end = new Date(new Date().getTime()).format("yyyy-MM-dd")+" 23:59:59";
            var start = new Date(new Date().getTime()).format("yyyy-MM-dd")+" 00:00:00";
            
        	 laydate.render({
                 elem: '#daterange'
                 ,range: true
                 ,type: 'datetime'
                 ,value:start+" - "+end
             });
        	 
        },
        render : function () {
            table.render({
                elem: '#emailSendRecord-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                },cols :[[
                	{
                    	field: "templateId",
                        title: "模板ID"
                    },{
                    	field: "appKey",
                        title: "应用Key"
                    },{
                	field: "sendTime",
                    title: "发送时间",
                    width: 160,
                    templet:function (row) {
                        return row.sendTime ? new Date(row.sendTime).format("yyyy-MM-dd hh:mm:ss"):"";
                    }
                },
                {
                    field: "result",
                    title: "发送结果",
                    width: 100,
                    templet:function (row) {
                    	if(row.result == 0 ){
                            return "成功";
                        }
                    	if(row.result == -1 ){
                            return "失败";
                        }
                        return "失败";
                    }
                },{
                	field: "mailTo",
                    title: "接收邮箱"
                },
                {
                	field: "channel",
                    title: "发送渠道",
                    width:120
                },
                {
                	field: "subject",
                    title: "邮件标题"
                }]]
                ,page:true
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("emailSendRecord-table",{
                    url:"/api/colibri/emailSendRecord/list",
                    method:"post",
                    where:{
                    	appKey:data.field.appKey,
                    	startTime:data.field.daterange.split(" - ")[0],
                    	endTime:data.field.daterange.split(" - ")[1]
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
           //
            $.post("/api/colibri/appKey/colibriemail",{},function(data){
            	if(data.code==0){
            		var list = data.data;
            		if(list.length>0){
            			var html="<option value=\"\">请选择</option>";
            			$.each(list,function(index,item){
            				html+="<option value=\""+item.appKey+"\">"+item.appName+"</option>";
            			});
            			$("#appKey").html(html);
            			form.render("select");
            		}
            	}
            },"json");
 
        }
    };

    exports('emailSendRecord', function () {
    	emailSendRecord.init();
    });
});