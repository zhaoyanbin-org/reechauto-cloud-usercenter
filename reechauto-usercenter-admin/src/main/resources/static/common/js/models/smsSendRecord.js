/**
 * Email模板
 */
layui.define(["table","form","laydate"],function(exports){
    "use strict";
    var laydate = layui.laydate;
    var table = layui.table;
    var form = layui.form;
    var smsSendRecord = {
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
                elem: '#smsSendRecord-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                },cols :[[{
                    field: "mobile",
                    title: "手机号",
                    width: 160
                },{
                    field: "content",
                    title: "发送内容"
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
                	field: "templateId",
                    title: "模板ID"
                },{
                	field: "appKey",
                    title: "应用Key"
                },
                {
                	field: "channel",
                    title: "发送渠道"
                },
                {
                	field: "respStatus",
                    title: "渠道响应状态",
                    width: 120
                },
                {
                	field: "voiceMsg",
                    title: "语音短信",
                    width: 80,
                    templet:function (row) {
                    	if(row.voiceMsg == true ){
                            return "是";
                        }
                    	if(row.voiceMsg == false){
                            return "否";
                        }
                    }
                }]]
                ,page:true
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("smsSendRecord-table",{
                    url:"/api/colibri/smsSendRecord/list",
                    method:"post",
                    where:{
                    	mobile:data.field.mobile,
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
   
 
        }
    };

    exports('smsSendRecord', function () {
    	smsSendRecord.init();
    	//$("#search-btn").trigger("click");
    });
});