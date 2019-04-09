/**
 * 用户登录量统计查询模块
 */
layui.define(["form","laydate"],function(exports){
    "use strict";
    var laydate = layui.laydate;
    var form = layui.form;
    var accountStatLoginCount = {
        init : function () {
            this.render();
            this.loadData();
        },
        render : function () {
            var that  = this;
            //日期范围
            laydate.render({
                elem: '#time-range'
                ,range: true
            });

            //初始化日期为最近一周
            var date = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
            var end = date.format("yyyy-MM-dd");
            var start = new Date(date.getTime() - 7 * 24 * 60 * 60 * 1000).format("yyyy-MM-dd");
            $("#time-range").val(start+" - "+end);
            //初始化报表
            var report_dom = $("#report");
            var _width = report_dom.parent().width();
            var _height = report_dom.parent().parent().height() - 243;
            report_dom.width(_width);
            report_dom.height(_height);
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('report'));
            that.myChart = myChart;
            // 指定图表的配置项和数据
            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                animation:true,
                animationEasing:"cubicOut",
                legend: {
                    left: 'center',
                    data:['登录用户量', '用户登录次数']
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
                    name: '登录用户量',
                    splitLine: {
                        lineStyle: {
                            color: ["#eee"],
                            type: "dashed"
                        }
                    }
                },{
                    type: 'value',
                    name: '用户登录次数',
                    splitLine: {
                    lineStyle: {
                        color: ["#eee"],
                            type: "dashed"
                    }
                    }
                }],
                series: [
                         {
                             name: '登录用户量',
                             type: 'line'
                         },
                         {
                             name: '用户登录次数',
                             type: 'line',
                             yAxisIndex: 1
                         }
                     ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            form.on('submit(query)', function(data){
                that.loadData();
            });
        },
        loadData : function(){
            var that  = this;
            var date = $("#time-range").val();
            var startDate = date.split(" - ")[0];
            var endDate = date.split(" - ")[1];
            startDate = startDate.replace(/-/g,"");
            endDate = endDate.replace(/-/g,"");
            $.get('/api/stat/getLoginStat?startDate='+startDate+'&endDate='+endDate, function(data) {
                if(data.code == 0){
                	var accountList = data.data.accountList;
                    var loginCountList = data.data.loginCountList;
                    var categories = [];
                    var accountValues = [];
                    var loginCountValues = [];
                    $(accountList).each(function () {
                        for( var k in this){
                            categories.push(k.substr(0,4)+"-"+k.substr(4,2)+"-"+k.substr(6,2));
                            accountValues.push(this[k]);
                        }
                    });
                    $(loginCountList).each(function () {
                        for( var k in this){
                        	loginCountValues.push(this[k]);
                        }
                    });
                    that.myChart.setOption({
                        xAxis: {
                            data: categories
                        },
                        series: [{
                            name: '登录用户量',
                            data: accountValues
                        },{
                            name: '用户登录次数',
                            data: loginCountValues
                        }]
                    });
                    
                	
//                    data = data.data;
//                    var categories = [];
//                    var values = [];
//                    $(data).each(function () {
//                        for( var k in this){
//                            categories.push(k.substr(0,4)+"-"+k.substr(4,2)+"-"+k.substr(6,2));
//                            values.push(this[k]);
//                        }
//                    });
//                    that.myChart.setOption({
//                        xAxis: {
//                            data: categories
//                        },
//                        series: [{
//                            name: '活跃用户量',
//                            data:values
//                        }]
//                    });
                }
            });
        }
    };

    exports('accountStatLoginCount', function () {
        accountStatLoginCount.init();
    });
});