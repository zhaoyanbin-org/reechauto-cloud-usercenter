/**
 * 用户量统计查询模块
 */
layui.define(["form","laydate"],function(exports){
    "use strict";
    var laydate = layui.laydate;
    var form = layui.form;
    var accountStatAccountCount = {
        init : function () {
            this.render();
            this.loadData();
        },
        render : function () {
            var that = this;
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
            that.myChart =myChart;
            // 指定图表的配置项和数据
            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                animation:true,
                animationEasing:"cubicOut",
                legend: {
                    left: 'center',
                    data: ['用户总量', '用户增量']
                },
                xAxis: {
                    type: 'category',
                    name: '日期',
                    splitLine: {
                        show:false
                    },
                    axisTick:{
                        show : false
                    },
                },
                grid: {
                    left: '4%',
                    right: '4%',
                    bottom: '4%',
                    show:false
                },
                yAxis: [{
                    type: 'value',
                    name: '总量',
                    splitLine: {
                        lineStyle: {
                            color: ["#eee"],
                            type: "dashed"
                        }
                    }
                },{
                    type: 'value',
                    name: '增量',
                    splitLine: {
                    lineStyle: {
                        color: ["#eee"],
                            type: "dashed"
                    }
                    }
                }],
                series: [
                    {
                        name: '用户总量',
                        type: 'line'
                    },
                    {
                        name: '用户增量',
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
        loadData : function () {
            var that  = this;
            var date = $("#time-range").val();
            var startDate = date.split(" - ")[0];
            var endDate = date.split(" - ")[1];
            startDate = startDate.replace(/-/g,"");
            endDate = endDate.replace(/-/g,"");
            $.get('/api/stat/getAccountStat?startDate='+startDate+'&endDate='+endDate, function(data) {
                if(data.code == 0){
                    var totalData = data.data.total;
                    var countData = data.data.count;
                    var categories = [];
                    var totalValues = [];
                    var countValues = [];
                    $(totalData).each(function () {
                        for( var k in this){
                            categories.push(k.substr(0,4)+"-"+k.substr(4,2)+"-"+k.substr(6,2));
                            totalValues.push(this[k]);
                        }
                    });

                    $(countData).each(function () {
                        for( var k in this){
                            countValues.push(this[k]);
                        }
                    });
                    that.myChart.setOption({
                        xAxis: {
                            data: categories
                        },
                        series: [{
                            name: '用户总量',
                            data: totalValues
                        },{
                            name: '用户增量',
                            data: countValues
                        }]
                    });
                }
            });
        }
    };

    exports('accountStatAccountCount', function () {
        accountStatAccountCount.init();
    });
});