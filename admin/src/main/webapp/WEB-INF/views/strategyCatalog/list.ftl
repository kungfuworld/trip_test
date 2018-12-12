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
            //模态框
            $(".btn-input").click(function () {
                //先清空模态框
                $(".render").renderValues("");//不能
                $("#coverImg").attr("src","");
                var json = $(this).data("json");
                if(json){
                    $(".render").renderValues(json);
                    $('#strategyCatalogState').val(json.state+"");
                }
                $("#strategyCatalogModal").modal("show");
            })

            $("#saveBtn").click(function () {
                $("#strategyCatalogForm").ajaxSubmit(function (data) {
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
		<#assign currentMenu = "strategyCatalog" />
        <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>平台用户管理</h3>
            </div>
            <div class="row">
                <!-- 提交分页的表单 -->
                <form id="searchForm" class="form-inline" method="post" action="/strategyCatalog/list.do">
                    <input type="hidden" name="currentPage" id="currentPage" value="1"/>
                    <div class="form-group">
                    </div>
                    <div class="form-group">
                        <label>攻略搜索</label>
                        <select id="strategySelect" name="type" class="form-control form-control-chosen" data-placeholder="请选择所属攻略" >
                            <#list strategies as strategy>
                                <option value="${strategy.id}">${strategy.title}</option>
                            </#list>
                        </select>
                        <script type="text/javascript">
                            $("#strategySelect").val("${qo.type!}")
                        </script>

                    </div>
                    <div class="form-group">
                        <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                        <a href="javascript:;" class="btn btn-success btn-input" id="addStrategyCatalogBtn">添加攻略</a>
                    </div>
                </form>
            </div>
            <div class="row">
                <table class="table">
                    <thead>
                    <tr>
                        <th>分类名称</th>
                        <th>所属攻略</th>
                        <th>排序</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
					<#list pageInfo.list as entity>
                    <tr>
                        <td>${entity.name!}</td>
                        <td>${entity.strategy.title!}</td>
                        <td>${(entity.sequence)!}</td>
                        <td>
                            ${(entity.state)?string("启用","禁用")}
                        </td>
                        <td>
                            <a href="javascript:void(-1);" class="btn-input"  data-json='${entity.strategyCatalogJson}'>修改</a>
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
<div id="strategyCatalogModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body render">
                <form id="strategyCatalogForm" class="form-horizontal" method="post" action="/strategyCatalog/saveOrUpdate.do" style="margin: -3px 118px">
                    <input id="strategyCatalogId" type="hidden" render-value="id" name="id"/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">分类名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name" render-value="name" placeholder="请输入分类名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略</label>
                        <div class="col-sm-6">
                            <select id="strategySelect" render-value="strategyId" name="strategy.id" class="form-control form-control-chosen" data-placeholder="请选择所属攻略" >
                            <#list strategies as strategy>
                                <option value="${strategy.id}">${strategy.title}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">排序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" render-value="sequence" name="sequence" placeholder="若不输入，系统默认将为最大值">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="strategyCatalogState" autocomplete="off" name="state" >
                                <option value="true">启用</option>
                                <option value="false">禁用</option>
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