<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/jrender/jrender.min.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function(){
            <#--console.log("${strategyQueryObject}")-->//可以拿到
            //模态框
            $(".btn-input").click(function () {
                //先清空模态框
                $(".render").renderValues("");//不能
                $("#coverImg").attr("src","");
                var json = $(this).data("json");
                if(json){
                    $(".render").renderValues(json);
                }
                $("#strategyModal").modal("show");
            })

            $("#saveBtn").click(function () {
                $("#strategyForm").ajaxSubmit(function (data) {
                    if(data.success){
                        $.messager.alert("温馨提醒","操作成功,1.5秒后刷新");
                        setTimeout(function () {
                            window.location.reload();
                        },1500);
                    }
                });
            });
        });
    </script>
</head>

<body>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
		<#assign currentMenu = "strategy" />
        <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>平台用户管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/strategy/list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>关键字</label>
                        <input class="form-control" type="text" name="keywords" value="${(qo.keywords)!''}">
                    </div>
                    <div class="form-group">
                        <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                        <a href="javascript:;" class="btn btn-success btn-input" id="addStrategyBtn">添加攻略</a>
                    </div>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                    <tr>
                        <th>封面</th>
                        <th>攻略标题</th>
                        <th>副标题</th>
                        <th>所属地区</th>
                        <th>状态</th>

                    </tr>
                    </thead>
                    <tbody>
					<#list pageInfo.list as entity>
                    <tr>
                        <td><img src='${entity.coverUrl!}' width="50px"/></td>
                        <td>${entity.title}</td>
                        <td>${entity.subTitle!}</td>
                        <td>${(entity.place.name)!}</td>
                        <td>${(entity.stateName)!}</td>
                        <td>
                            <a href="javascript:void(0);" class="btn-input" data-json='${entity.strategyJson}'>修改</a>
                        </td>
                    </tr>
					</#list>
                    </tbody>
                </table>

                 <#include "../common/page.ftl"/>

            </div>
        </div>
    </div>
</div>

<#--推荐模态框-->
<div id="strategyModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body render">
                <form id="strategyForm" class="form-horizontal" method="post" action="/strategy/saveOrUpdate.do" enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="strategyId" type="hidden" render-value="id" name="id"/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" render-value="title" placeholder="标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">副标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" render-value="subTitle" name="subTitle" placeholder="副标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img id="coverImg" width="200px" render-src="coverUrl" />
                            <input render-value="coverUrl" type="hidden" name="coverUrl">
                            <input type="file" class="form-control"  name="file" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属地区</label>
                        <div class="col-sm-6">
                            <select class="form-control" render-value="place.id" autocomplete="off" name="place.id" >
                                <#list regions as region>
                                    <option value="${region.id}">${region.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态</label>
                        <div class="col-sm-6">
                            <select class="form-control" render-value="state" autocomplete="off" name="state" >
                                <option value="0">普通</option>
                                <option value="1">热门</option>
                                <option value="-1">攻略推荐</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>