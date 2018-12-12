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
            /*分页插件*/
                $("#pagination").twbsPagination({
                    totalPages:${pageInfo.pages},
                    visiblePages:5,
                    startPage:${pageInfo.pageNum},
                    first:"首页",
                    prev:"上一页",
                    next:"下一页",
                    last:"尾页",
                    onPageClick:function(event,page){
                        $("#currentPage").val(page);
                        $("#searchForm").submit();
                    }
                });

                /*时间控件*/
                $("#schedule").click(function () {
                    WdatePicker({
                        readOnly:true,
                        maxDate:new Date()
                    })
                })

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

                /*改变状态*/
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

            /*推荐操作*/
            $(".CommendBtn").click(function () {
                console.log(this);
                var data = $(this).data("commend_json");
                // console.log(data);
                $("#travelCommendModal").renderValues(data);
                $("#travelCommendModal").modal("show");
            });

            $("#saveCommendBtn").click(function () {
                $("#commendForm").ajaxSubmit(function (data) {
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
    <style type="text/css">
        .content img{
            width: 100%;
        }
    </style>
</head>

<body>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
		<#assign currentMenu = "travel" />
        <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>平台用户管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/travel/list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>关键字</label>
                        <input class="form-control" type="text" name="keywords" value="${(qo.keywords)!''}">
                    </div>
                    <div class="form-group">
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
                        <th>发布时间</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
					<#list pageInfo.list as entity>
                    <tr>
                        <td>${entity.title!""}</td>
                        <td><img src='${entity.coverUrl}' width="50px"/></td>
                        <td>${entity.place.name!""}</td>
                        <td>${(entity.author.name)!}</td>
                        <td>${(entity.releaseTime?string("yyyy-MM-dd"))!}</td>
                        <td>${(entity.stateName)!}</td>
                        <td>
                            <a href="javascript:void(0);" class="look_Btn" data-id="${entity.id}">查看游记</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" data-id="${entity.id}" data-state="1" class="changeState">取消发布</a>
                        </td>
                        <td>
                            <a href="javascript:void(0);"class="CommendBtn" data-commend_json='${entity.commendJson}'>推荐</a>
                        </td>
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

<#--查看文章模态框-->
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

<#--推荐模态框-->
<div id="travelCommendModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="commendForm" class="form-horizontal" method="post" action="/travelCommend/saveOrUpdate.do" enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="travelId" type="hidden" render-value="travelId" name="travel.id"/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" render-value="title" placeholder="标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">副标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="subTitle" placeholder="副标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面</label>
                        <div class="col-sm-6">
                            <img width="200px" render-src="coverUrl" />
                            <input render-value="coverUrl" type="hidden" name="coverUrl">
                            <input type="file" class="form-control"  name="file" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐时间</label>
                        <div class="col-sm-6">
                            <input id="schedule" type="text" name="schedule" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐类型</label>
                        <div class="col-sm-6">
                            <select class="form-control" autocomplete="off" name="type" >
                                <option value="1">每月推荐</option>
                                <option value="2">每周推荐</option>
                                <option value="3">攻略推荐</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveCommendBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>