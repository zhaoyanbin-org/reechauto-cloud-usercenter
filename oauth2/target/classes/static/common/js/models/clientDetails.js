/**
 * 后台管理用户模块
 */
layui.define(["table","form"],function(exports){
    "use strict";
    var table = layui.table;
    var form = layui.form;
    var adminAccount = {
        init : function () {
            this.render();
        },
        render : function () {
            table.render({
                elem: '#account-table'
                ,loading:true
                ,text:{
                    none: '暂无相关数据'
                },
                cols :[[{
                    field: "clientId",
                    title: "客户端ID"
                },{
                    field: "resourceIds",
                    title: "resourceIds"
                },{
                    field: "clientSecret",
                    title: "clientSecret"
                },{
                    field: "scope",
                    title: "scope"
                },{
                    field: "authorizedGrantTypes",
                    title: "authorizedGrantTypes"
                },{
                    field: "webServerRedirectUri",
                    title: "webServerRedirectUri"
                },{
                    field: "authorities",
                    title: "authorities"
                },{
                    field: "accessTokenValidity",
                    title: "accessTokenValidity"
                    
                },{
                    field: "refreshTokenValidity",
                    title: "refreshTokenValidity"
                    
                },{
                    field: "op",
                    title: "操作",
                    fixed:"right",
                    align:"center",
                    toolbar:"#account-toolbar",
                    width: 200
                }]]
                ,page:true
            });

            $("#admin-account-btn").on("click",function () {
                layui.layer.open({
                    type : 1
                    ,title: "新增客户端" //不显示标题栏
                    ,area:  ['600px', '800px']
                    ,id: 'admin-account-layer' //设定一个id，防止重复弹出
                    ,btn: ['提交', '取消']
                    ,btnAlign: 'r'
                    ,shade:0
                    ,moveType: 1 //拖拽模式，0或者1
                    ,zIndex:999
                    ,content: $('#admin-account-new').html()
                    ,success: function(layero, index){
                        $("#admin-account-layer select[name='resourceIds']").empty();
                        $("#admin-account-layer select[name='resourceIds']").append('<option value="">请选择</option>');
                        $.ajax({
                            type: "POST",
                            url:"/resourceServers/query",
                            cache: false,
                            dataType: "json",
                            async: false,
                            success: function(data){
                                if(data.data && data.data.length > 0){
                                    $(data.data).each(function(){
                                        $("#admin-account-layer select[name='resourceIds']").append('<option value="'+this.resourceId+'">'+this.resourceId+'</option>');
                                    });
                                }
                            }
                        });
                        $("#admin-account-layer select[name='scope']").empty();
                        $("#admin-account-layer select[name='scope']").append('<option value="">请选择</option>');
                        $.ajax({
                            type: "POST",
                            url:"/resourceScopes/query",
                            cache: false,
                            dataType: "json",
                            async: false,
                            success: function(data){
                                if(data.data && data.data.length > 0){
                                    $(data.data).each(function(){
                                        $("#admin-account-layer select[name='scope']").append('<option value="'+this.scope+'">'+this.scope+'</option>');
                                    });
                                }
                            }
                        });
                        $("#admin-account-layer select[name='authorizedGrantTypes']").empty();
                        $("#admin-account-layer select[name='authorizedGrantTypes']").append('<option value="">请选择</option>');
                        $.ajax({
                            type: "POST",
                            url:"/grantTypes/query",
                            cache: false,
                            dataType: "json",
                            async: false,
                            success: function(data){
                                if(data.data && data.data.length > 0){
                                    $(data.data).each(function(){
                                        $("#admin-account-layer select[name='authorizedGrantTypes']").append('<option value="'+this.grantTypes+'">'+this.meaning+'</option>');
                                    });
                                }
                            }
                        });
                        form.render();
                    }
                    ,yes: function(index, layero){
                        var clientId = $('#admin-account-layer input[name="clientId"]').val().trim();
                        var resourceIds = $('#admin-account-layer select[name="resourceIds"]').val();
                        var clientSecret = $('#admin-account-layer input[name="clientSecret"]').val().trim();
                        var scope = $('#admin-account-layer select[name="scope"]').val();
                        var authorizedGrantTypes = $('#admin-account-layer select[name="authorizedGrantTypes"]').val();
                        var webServerRedirectUri = $('#admin-account-layer input[name="webServerRedirectUri"]').val().trim();
                        var authorities = $('#admin-account-layer input[name="authorities"]').val().trim();
                        var accessTokenValidity = $('#admin-account-layer input[name="accessTokenValidity"]').val().trim();
                        var refreshTokenValidity = $('#admin-account-layer input[name="refreshTokenValidity"]').val().trim();
                        var additionalInformation = $('#admin-account-layer input[name="additionalInformation"]').val().trim();
                        var autoapprove = $('#admin-account-layer input[name="autoapprove"]').val().trim();
                        if(!clientId){
                            layui.layer.msg("请输入客户端ID", {icon: 2,time:3000});
                            return false;
                        }
                       
                        $.post('/clientDetails/add',{
                        	clientId : clientId,
                        	clientSecret : clientSecret,
                            resourceIds:resourceIds.join(","),
                            scope:scope.join(","),
                            authorizedGrantTypes:authorizedGrantTypes.join(","),
                            webServerRedirectUri : webServerRedirectUri,
                            authorities : authorities,
                            accessTokenValidity : accessTokenValidity,
                            refreshTokenValidity : refreshTokenValidity,
                            additionalInformation : additionalInformation,
                            autoapprove : autoapprove
                        }, function(data) {
                            if(data.code == 1000){
                                layui.layer.msg('新增客户端成功', {icon: 1,time:3000});
                                layer.close(index); //如果设定了yes回调，需进行手工关闭
                                $("#query-btn").trigger("click");
                            }else if(data.code == 1006){
                                layui.layer.msg('用户中心不存在', {icon: 2,time:3000});
                            }else if(data.code == 1009){
                                layui.layer.msg('该用户已存在', {icon: 2,time:3000});
                            }else{
                                layui.layer.msg('添加失败', {icon: 2,time:3000});
                            }
                        });
                        return false;
                    }
                    ,cancel: function(index, layero){
                        layer.close(index);
                    }
                });
            });

            //初始化查询按钮
            form.on('submit(query)', function(data){
                table.reload("account-table",{
                    url:"/clientDetails/query",
                    method:"get",
                    where:{
                        query:data.field.query
                    },
                    response:{
                        statusName: 'code',
                        statusCode: 1000,
                        msgName:"message",
                        countName:"count",
                        dataName:"data"
                    }
                });
            });
            
            table.on('tool(accountTable)', function(obj){
                var data = obj.data;
                if(obj.event == 'deleteUser'){
                    layui.layer.msg('您确定要删除该客户端['+data.clientId+']吗？', {
                        time: 0 //不自动关闭
                        ,icon: 6
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            $.post("/clientDetails/del?clientId="+data.clientId,function(data){
                                if(!data || data.code != 1000 ){
                                    layui.layer.msg('删除失败', {icon: 2});
                                }else{
                                    layui.layer.msg('删除成功', {icon: 1});
                                    $("#query-btn").trigger("click");
                                }
                            });
                        }
                    });
                }else if(obj.event == 'updateUser'){
                    layui.layer.open({
                        type : 1
                        ,title: "修改客户端" //不显示标题栏
                        ,area:  ['600px', '800px']
                        ,offset: 'auto'
                        ,id: 'admin-account-layer' //设定一个id，防止重复弹出
                        ,btn: ['提交', '取消']
                        ,btnAlign: 'r'
                        ,shade:0
                        ,moveType: 1 //拖拽模式，0或者1
                        ,zIndex:999
                        ,content: $('#admin-account-new').html()
                        ,success: function(layero, index){
                            $($('#admin-account-layer .layui-form-item')[1]).remove();
                            $("#admin-account-layer select[name='resourceIds']").empty();
                            $("#admin-account-layer select[name='resourceIds']").append('<option value="">请选择</option>');
                            $.ajax({
                                type: "POST",
                                url:"/resourceServers/query",
                                cache: false,
                                dataType: "json",
                                async: false,
                                success: function(response){
                                    if(response.data && response.data.length > 0){
                                        $(response.data).each(function(){
                                            $("#admin-account-layer select[name='resourceIds']").append('<option value="'+this.resourceId+'">'+this.resourceId+'</option>');
                                        });
                                        var resourceIds = (data.resourceIds || '' ).split(",");
                                        var temps = [];
                                        $(resourceIds).each(function () {
                                            if(this && this.length > 0){
                                                var resourceId = this.replace(/,/g,'').trim();
                                                if(resourceId){
                                                    temps.push(resourceId);
                                                }
                                            }
                                        });
                                        $('#admin-account-layer select[name="resourceIds"]').val(temps);
                                    }
                                }
                            });
                            $("#admin-account-layer select[name='scope']").empty();
                            $("#admin-account-layer select[name='scope']").append('<option value="">请选择</option>');
                            $.ajax({
                                type: "POST",
                                url:"/resourceScopes/query",
                                cache: false,
                                dataType: "json",
                                async: false,
                                success: function(response){
                                    if(response.data && response.data.length > 0){
                                        $(response.data).each(function(){
                                            $("#admin-account-layer select[name='scope']").append('<option value="'+this.scope+'">'+this.scope+'</option>');
                                        });
                                        var scope = (data.scope || '' ).split(",");
                                        var temps = [];
                                        $(scope).each(function () {
                                            if(this && this.length > 0){
                                                var scope1 = this.replace(/,/g,'').trim();
                                                if(scope1){
                                                    temps.push(scope1);
                                                }
                                            }
                                        });
                                        $('#admin-account-layer select[name="scope"]').val(temps);
                                        
                                    }
                                }
                            });
                            $("#admin-account-layer select[name='authorizedGrantTypes']").empty();
                            $("#admin-account-layer select[name='authorizedGrantTypes']").append('<option value="">请选择</option>');
                            $.ajax({
                                type: "POST",
                                url:"/grantTypes/query",
                                cache: false,
                                dataType: "json",
                                async: false,
                                success: function(response){
                                    if(response.data && response.data.length > 0){
                                        $(response.data).each(function(){
                                            $("#admin-account-layer select[name='authorizedGrantTypes']").append('<option value="'+this.grantTypes+'">'+this.meaning+'</option>');
                                        });
                                        var authorizedGrantTypes = (data.authorizedGrantTypes || '' ).split(",");
                                        var temps = [];
                                        $(authorizedGrantTypes).each(function () {
                                            if(this && this.length > 0){
                                                var authorizedGrantType = this.replace(/,/g,'').trim();
                                                if(authorizedGrantType){
                                                    temps.push(authorizedGrantType);
                                                }
                                            }
                                        });
                                        $('#admin-account-layer select[name="authorizedGrantTypes"]').val(temps);
                                    }
                                }
                            });
                            $('#admin-account-layer input[name="clientId"]').val(data.clientId);
                            $('#admin-account-layer input[name="clientSecret"]').val(data.clientSecret);
                            $('#admin-account-layer input[name="webServerRedirectUri"]').val(data.webServerRedirectUri);
                            $('#admin-account-layer input[name="authorities"]').val(data.authorities);
                            $('#admin-account-layer input[name="accessTokenValidity"]').val(data.accessTokenValidity);
                            $('#admin-account-layer input[name="refreshTokenValidity"]').val(data.refreshTokenValidity);
                            $('#admin-account-layer input[name="additionalInformation"]').val(data.additionalInformation);
                            $('#admin-account-layer input[name="autoapprove"]').val(data.autoapprove);
                            form.render();
                        }
                        ,yes: function(index, layero){
                        	
                            var clientId = $('#admin-account-layer input[name="clientId"]').val().trim();
                            var resourceIds = $('#admin-account-layer select[name="resourceIds"]').val();
                            var scope = $('#admin-account-layer select[name="scope"]').val();
                            var authorizedGrantTypes = $('#admin-account-layer select[name="authorizedGrantTypes"]').val();
                            var webServerRedirectUri = $('#admin-account-layer input[name="webServerRedirectUri"]').val().trim();
                            var authorities = $('#admin-account-layer input[name="authorities"]').val().trim();
                            var accessTokenValidity = $('#admin-account-layer input[name="accessTokenValidity"]').val().trim();
                            var refreshTokenValidity = $('#admin-account-layer input[name="refreshTokenValidity"]').val().trim();
                            var additionalInformation = $('#admin-account-layer input[name="additionalInformation"]').val().trim();
                            var autoapprove = $('#admin-account-layer input[name="autoapprove"]').val().trim();
                            /*if(!name){
                                layui.layer.msg("请输入用户标识", {icon: 2,time:3000});
                                return false;
                            }
                            if(!roles ){
                                layui.layer.msg("请选择用户角色", {icon: 2,time:3000});
                                return false;
                            }*/
                            $.post('/clientDetails/update',{
                                oldClientId : data.clientId,
                                newClientId : clientId,
                                newResourceIds : resourceIds.join(","),
                                newScope : scope.join(","),
                                newAuthorizedGrantTypes : authorizedGrantTypes.join(","),
                                newWebServerRedirectUri : webServerRedirectUri,
                                newAuthorities : authorities,
                                newAccessTokenValidity : accessTokenValidity,
                                newRefreshTokenValidity : refreshTokenValidity,
                                newAdditionalInformation : additionalInformation,
                                newAutoapprove : autoapprove
                            }, function(data) {
                                if(data.code == 1000){
                                    layui.layer.msg('修改成功', {icon: 1,time:3000});
                                    layer.close(index); //如果设定了yes回调，需进行手工关闭
                                    $("#query-btn").trigger("click");
                                }else{
                                    layui.layer.msg('修改失败' + (data.cause ? "，"+data.cause : ""), {icon: 2,time:3000});
                                }
                            });
                            return false;
                        }
                        ,cancel: function(index, layero){
                            layer.close(index);
                        }
                    });
                }
            });
            $("#query-btn").trigger("click");


        }
    };

    exports('clientDetails', function () {
        adminAccount.init();
    });
});