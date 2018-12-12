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
            //由于直接在select标签上使用JRendr无效，所以用此方式
            $("#qoType").val("${qo.type!"-1"}");
<#--
            /!*分页插件(最好放在最后)*!/
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
-->

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
            $(".edit").click(function () {
                var data = $(this).data("json");
                console.log(data);
                $("#travelCommendModal").renderValues(data);
                $("#skip").attr("href","/travelCommend/list.do?id=" + data.id);
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
		<#assign currentMenu = "travelCommend" />
        <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>平台用户管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/travelCommend/list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                        <label>关键字</label>
                        <input class="form-control" type="text" name="keywords" value="${(qo.keywords)!''}">
                    </div>
                    <div class="form-group">
                        <select id="qoType" class="form-control" autocomplete="off" name="type" >
                            <option value="-1">全部</option>
                            <option value="1">每月推荐</option>
                            <option value="2">每周推荐</option>
                            <option value="3">攻略推荐</option>

                        </select>
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
                        <th>封面</th>
                        <th>标题</th>
                        <th>副标题</th>
                        <th>推荐时间安排</th>
                        <th>推荐类型</th>
                    </tr>
                    </thead>
                    <tbody>
					<#list pageInfo.list as entity>
                    <tr>
                        <td>
                            <img src="${entity.coverUrl!""}" width="100px"/>
                        </td>
                        <td>${entity.title!}</td>
                        <td>${entity.subTitle!}</td>
                        <td>${(entity.schedule?string("yyyy-MM-dd"))!}</td>
                        <td>${(entity.typeName)!}</td>
                        <td>
                            <a href="javascript:void(0);" class="edit" data-json='${(entity.travelCommend)!}'>修改</a>
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
<div id="travelCommendModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="commendForm" class="form-horizontal" method="post" action="/travelCommend/saveOrUpdate.do" enctype="multipart/form-data" style="margin: -3px 118px">
                    <input id="id" type="hidden" render-value="id" name="id"/>
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
                            <img width="200px" render-src="coverUrl" />
                            <input render-value="coverUrl" type="hidden" name="coverUrl">
                            <input type="file" class="form-control"  name="file" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐时间</label>
                        <div class="col-sm-6">
                            <input id="schedule" render-value="schedule" type="text" name="schedule" class="form-control" >
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
                <div class="form-group" style="text-align: center">
                    <a href="javascript:;" target="_blank" id="skip">点击查看游记文章明细</a>
                </div>

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