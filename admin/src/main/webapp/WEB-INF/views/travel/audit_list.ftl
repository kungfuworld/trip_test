<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
    <#include "../common/header.ftl"/>

    <style>
        .modal-body img{
            width:100%
        }
    </style>

    <script type="text/javascript">
        $(function(){
            $("#auditState").val(${(qo.state)!});
                $("#pagination").twbsPagination({
                    totalPages:${(pageInfo.pages==0)?string(1,pageInfo.pages)},
                    visiblePages:5,
                    startPage:${(pageInfo.pageNum==0)?string(1,pageInfo.pageNum)},
                    first:"首页",
                    prev:"上一页",
                    next:"下一页",
                    last:"尾页",
                    onPageClick:function(event,page){
                        $("#currentPage").val(page);
                        $("#searchForm").submit();
                    }
                });

                $("#query").click(function(){
                    $("#currentPage").val(1);
                    $("#searchForm").submit();
                });

                $(".look_Btn").click(function () {
                    //发送ajax请求去查询文章
                    $.get("/travel/getTravelContent.do",{id:$(this).data("id")},function (data) {
                        $("#travelModal .content").html(data.content);
                        $("#travelModal").modal("show");
                    })
                });

                $(".changeState").click(function () {
                    $.get("/travel/changeState.do",{id:$(this).data("id"),state:$(this).data("state")},function (data) {
                        if(data.success){
                            $.messager.alert("温馨提醒","操作成功,1.5秒后刷新");
                            setTimeout(function () {
                                window.location.reload();
                            },1500);
                        }
                    })
                });
        });
    </script>
</head>

<body>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
		<#assign currentMenu = "audit_list" />
        <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>平台用户管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/travel/audit_list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>关键字</label>
                        <input class="form-control" type="text" name="keywords">
                    </div>
                    <div class="form-group">
                        <select id="auditState" value="${(qo.state)!}" class="form-control" autocomplete="off" name="state">
                            <option value="-2">全部</option>
                            <option value="1">待发布</option>
                            <option value="-1">已拒绝</option>
                        </select>
                        <button id="query" type="button" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                    </div>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                    <tr>
                        <th>标题</th>
                        <th>封面</th>
                        <th>地点</th>
                        <th>作者</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
					<#list pageInfo.list as entity>
                    <tr>
                        <td>${entity.title!""}</td>
                        <td><img src='${entity.coverUrl}' width="50px"/></td>
                        <td>${entity.place.name!""}</td>
                        <td>${(entity.author.nickName)!}</td>
                        <td>${(entity.stateName)!}</td>
                        <td>
                            <a href="javascript:void(0);" data-id="${entity.id}" data-state="2" class="changeState" >发布</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" data-id="${entity.id}" data-state="-1" class="changeState" >拒绝</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" class="look_Btn" data-id="${entity.id}">查看文章</a>
                        </td>
                    </tr>

                    </tr>
					</#list>
                    </tbody>
                </table>

                <div style="text-align: center;">
                    <ul id="pagination" class="pagination"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="travelModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看内容</h4>
            </div>
            <div class="modal-body content">
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>


</body>
</html>