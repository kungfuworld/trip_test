<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/jrender/jrender.min.js"></script>
    <script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        //编辑器对象
        var ck;

        $(function(){

            //模态框
            $(".btn-input").click(function () {
                //先清空模态框
                ck.setData("");
                $(".render").renderValues("");//不能
                $("#coverImg").attr("src","");
                var json = $(this).data("json");
                if(json){
                    //修改的时候将攻略类别查询出来
                    $.get("/strategyCatalog/listByStrategyId.do",{strategyId:json.catalog.strategy.id},function (data) {
                        temp = "";
                        $.each(data,function (index,ele) {
                            temp = temp + "<option value='"+ele.id+"'>"+ele.name+"</option>"
                        });
                        $("#catalog").html(temp);
                    });

                    //修改的时候将文章内容查询出来
                    $.get("/strategyContent/getById.do",{id:json.id},function (data) {
                        console.log(data);
                        ck.setData(data.content);
                    });

                    $(".render").renderValues(json);
                }
                $("#strategyDetailModal").modal("show");
            });

            //改变所属攻略引发二级联动
            $("#strategy").change(function () {
                var strategyId = $(this).val();
                $.get("/strategyCatalog/listByStrategyId.do",{strategyId:strategyId},function (data) {
                    temp = "";
                    $.each(data,function (index,ele) {
                        temp = temp + "<option value='"+ele.id+"'>"+ele.name+"</option>"
                    });
                    $("#catalog").html(temp);
                });
            });

            $("#saveBtn").click(function () {
                $("#editArea").html(ck.getData());
                $("#strategyDetailForm").ajaxSubmit(function (data) {
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
		<#assign currentMenu = "strategyDetail" />
        <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>攻略文章管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/strategyDetail/list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>关键字搜索</label>
                        <input class="form-control" type="text" name="keywords" value="${(qo.keywords)!''}">
                    </div>
                    <div class="form-group">
                        <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                        <a href="javascript:;" class="btn btn-success btn-input" id="addStrategyDetailBtn">添加攻略</a>
                    </div>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th>标题</th>
                            <th>封面</th>
                            <th>发布时间</th>
                            <th>排序</th>
                            <th>攻略类别</th>
                            <th>状态</th>
                        </tr>
                    </thead>
                    <tbody>
					<#list pageInfo.list as entity>
                    <tr>
                        <td>${entity.title!}</td>
                        <td><img src='${entity.coverUrl!}' width="50px"/></td>
                        <td>${(entity.releaseTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                        <td>${(entity.sequence)!}</td>
                        <td>
                            ${(entity.catalog.name)!}
                        </td>
                        <td>
                            ${(entity.stateName)!}
                        </td>
                        <td>
                            <a href="javascript:void(-1);" class="btn-input"  data-json='${entity.strategyDetailJson!}'>修改</a>
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
<div id="strategyDetailModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body render">
                <form id="strategyDetailForm" class="form-horizontal" method="post" action="/strategyDetail/saveOrUpdate.do" enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="strategyDetailId" type="hidden" render-value="id" name="id"/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" render-value="title" placeholder="标题">
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
                        <label class="col-sm-4 control-label">所属攻略</label>
                        <div class="col-sm-6">
                            <select id="strategy" class="form-control" render-value="catalog.strategy.id" autocomplete="off">
                                <#list strategies as strategy>
                                    <option value="${strategy.id}">${strategy.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">攻略类别</label>
                        <div class="col-sm-6">
                            <select  id="catalog" class="form-control" autocomplete="off" name="catalog.id" >
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">是否发布</label>
                        <div class="col-sm-6">
                            <select class="form-control" render-value="state" autocomplete="off" name="state" >
                                <option value="0">存为草稿</option>
                                <option value="1">发布</option>
                                <option value="-1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">排序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence" render-value="sequence" placeholder="排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <textarea id="editArea" rows="10" cols="200" name="strategyContent.content">
                        </textarea>
                        <script type="text/javascript">
                            ck = CKEDITOR.replace("editArea");
                        </script>
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