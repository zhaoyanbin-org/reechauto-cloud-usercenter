/**
 * 用户登录量统计查询模块
 */
layui.define(["table","form","laydate"],function(exports){
    "use strict";
    var laydate = layui.laydate;
    var form = layui.form;
    var table = layui.table;
    var smsStatCount = {
        init : function () {
            this.render();
            this.dateInit();
           
        },
        dateInit:function(){
        	 var end = new Date(new Date().getTime()- 1 * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
             var start = new Date(new Date().getTime()- 7 * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
            
        	 laydate.render({
                 elem: '#daterange'
                 ,range: true
                 ,type: 'date'
                 ,max:end
                 ,value:start+" - "+end
             });
        	 
        },
        render : function () {
        	 table.render({
	                elem: '#smsStatCount-table'
	                ,width:750
	                ,loading:true
	                ,text:{
	                    none: '暂无相关数据'
	                }
			        ,cols :[[
	                {
	                	field: "appName",
	                    title: "应用名称"
	                },
	                {
	                	field: "num",
	                    title: "短信数量",
	                    width:100
	                },
	                {
	                	field: "",
	                    title: "详情",
	                    toolbar:"#smsStat-toolbar",
	                    width:150
	                }]]
	                ,page:false
	            });
        	
            $.post("/api/colibri/appKey/colibrisms",{},function(data){
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
            
 
        //初始化报表
        var report_dom = $("#report");
        var _width = report_dom.parent().width();
        var _height = report_dom.parent().parent().height() - 380;
        report_dom.width(_width);
        report_dom.height(_height);
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('report'));
        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            animation:true,
            animationEasing:"cubicOut",
            legend: {
                left: 'center'
            },
            xAxis: {
                type: 'category',
                name: '日期',
                splitLine: {
                    show:false
                },
                axisTick:{
                    show : false
                }
            },
            grid: {
                left: '4%',
                right: '4%',
                bottom: '4%',
                show:false
            },
            yAxis: [{
                type: 'value',
                name: '短信发送次数',
                splitLine: {
                    lineStyle: {
                        color: ["#eee"],
                        type: "dashed"
                    }
                }
            }],
            series: []
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        
        form.on('submit(query)', function(data){
        	var statDate=data.field.daterange.split(" - ")[0];
        	var endDate=data.field.daterange.split(" - ")[1];
        	var appKey=data.field.appKey;
        	
        	if((new Date(statDate).getTime() + 30 * 24 * 60 * 60*1000)<new Date(endDate).getTime()){
        		 layui.layer.msg("最大统计周期不能超过30天", {icon: 2,time:3000});
                 return false;
        	}
        	
        	$.post("/api/colibri/stat/smscount",{"statDate":statDate,"endDate":endDate,"appKey":appKey},function(mydata){
        		 if(mydata.code == 0){
        			 $("#smsStatCount-detail").data("smsDetailAll",mydata.detailAll);
        			 myChart.clear();
        			 myChart.setOption(option,true) 
        			 var chartData = mydata.echartData;
        			 
        			 myChart.setOption({
                         xAxis: {
                             data: chartData.option_xAxis_data
                         },
                         legend:{
                        	 data:chartData.option_legend_data
                         },
                         series:chartData.option_series
                     });
        			
        			 
        			 table.reload("smsStatCount-table",{
                             data:mydata.detail
                     });
        			 

        		 }
        		 else{
        			 layui.layer.msg(mydata.message, {icon: 2,time:3000});
                     return false;
        		 }
        	},"json");
        });
            
        table.on('tool(smsStatCounttable)', function(obj){
       	   var data = obj.data;
           	 if(obj.event == 'smsStatDetail'){
           		 
           		 var detailData =  $("#smsStatCount-detail").data("smsDetailAll");
           		 var htmlStr="<table style=\"padding:20px;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"layui-table\" ><tr><th>日期</th><th>数量</th></tr>";
           		 $.each(detailData,function(i,item){
           			 if(item.appKey==data.appKey){
           				htmlStr+="<tr><td>"+item.dateStr+"</td><td>"+item.num+"</td></tr>";
           			 }
           		 });
           		htmlStr+="</table>"
           			
           		layui.layer.open({
                        type : 1
                        ,title: "明细" //不显示标题栏
                        ,area:  ['750px', '500px']
                        ,id: 'smsStatCount-detail-layer' //设定一个id，防止重复弹出
                        ,shade:0
                        ,moveType: 1 //拖拽模式，0或者1
                        ,zIndex:999
                        ,content: htmlStr
          			});
           
           	 }
       	})
           
        }
    };

    exports('smsStatCount', function () {
    	smsStatCount.init();
    	//$("#search-btn").trigger("click");
    });
});