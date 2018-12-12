<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼</title>
    <#include "../common/header.ftl"/>
    <#--给页面的插值设值-->
    <#assign currentMenu="department"/>
    <link rel="stylesheet" href="/js/plugins/treeview/bootstrap-treeview.min.css" type="text/css" />
    <script type="text/javascript" src="/js/plugins/treeview/bootstrap-treeview.min.js"></script>
    <script src="/js/plugins/jrender/jrender.min.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery/bootstrapMessage/jquery.bootstrap.min.js"></script>
    <script>
        $(function () {
            $.messager.model = {
                ok:{ text: "关闭"},
                cancel: { text: "取消"}
            }
            //设置全局的parentId和parentName
            var parentName;
            var parentId;
            //显示页面列表
            $.get("/region/listByParentId.do",function (data) {
                $('#regionTreeview').treeview({
                    data: [{
                        text: '全部地区',
                        nodes: data
                    }],
                    showTags: true,
                    lazyLoad:function (node) {
                        $.get("/region/listByParentId.do",{"parentId":node.id},function(data){
                            $('#regionTreeview').treeview('addNode', [ data, node]);
                        })
                    },
                    onNodeSelected: function(event, data) {//设置点击节点右边显示列表
                        // data.text;这是上级名称
                        parentName = data.text;
                        parentId = data.id;
                        //data是点击的对象
                        $.get("/region/listByParentId.do",{"parentId":data.id,"type":"tbody"},function (data) {
                            //循环前先清空
                            $("#tbody").empty();
                            //循环遍历返回的data数组(JSON)
                            $.each(data,function (index,value) {
                                var $tr = $("#template tr").clone(true);
                                $tr.find("td:nth-child(1)").text(index+1);
                                $tr.find("td:nth-child(2)").text(value.name);
                                $tr.find("td:nth-child(3) a").attr("parentName",parentName);
                                $tr.find("td:nth-child(3) a").attr("parentId",parentId);
                                $tr.find("td:nth-child(3) a").attr('data-json',JSON.stringify(value.regionJson));
                                if(value.state==1){
                                    $tr.find("td:nth-child(4) a").removeClass("btn-success").addClass("btn-danger");
                                    /*可以用last选择器->$tr.find("td:nth-child(4) a:last")*/
                                    $tr.find("td:nth-child(4) a").html("<span class=\"glyphicon glyphicon-trash\"></span>取消推荐")
                                }
                                $tr.find("td:nth-child(4) a").attr("thisId",value.id);
                                $tr.find("td:nth-child(4) a").attr("state",value.state);
                                $("#tbody").append($tr);
                            });
                            $("#listTable").css("display",'');
                        })

                    }
                })
            });

            //保存和编辑操作
            $(".btn-input").click(function () {
                $(".myInputModal input").val("");
                var json = $(this).data("json");
                $(".myInputModal #parentName").val(parentName);
                $(".myInputModal #parentId").val(parentId);
                if(json){
                    $(".myInputModal input[name=name]").val(json.name);
                    $(".info").renderValues(json);
                }
                $(".myInputModal").modal("show");
            });


            $(".btn-submit").click(function () {
                $("#editForm").ajaxSubmit(function (data) {
                    if(data.success){
                        $.messager.alert("温馨提醒","恭喜你，提交成功,1.5秒后刷新页面");
                        setTimeout(function () {
                            window.location.reload();
                        },1500);
                    }
                });
            });


            //改变推荐状态
            $(".btn-state").click(function () {
                var id = $(this).attr("thisId");
                var state = $(this).attr("state");
                if(state == 1){
                    state = 0;
                } else{
                    state = 1;
                }
                $.get($(this).data("url"),{state:state,id:id},function (data) {
                    if(data.success){
                        $.messager.alert("温馨提醒","恭喜你，提交成功,1.5秒后刷新页面");
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
<table id="template" style="display: none">
    <tr>
    <td></td>
    <td></td>
    <td>
        <a class="btn btn-info btn-xs btn-input" data-json='' parentName="" parentId="">
            <span class="glyphicon glyphicon-pencil"></span>编辑
        </a>
    </td>
    <td>
        <a class="btn btn-success btn-xs btn-state" data-url="/region/updateState.do" thisId="" state="" >
            <span class="glyphicon glyphicon-trash"></span>设为推荐
        </a>
    </td>
    </tr>
</table>
<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#assign currentMenu = "user" />
            <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">地区管理</h1>
                </div>
            </div>
            <div class="row">
                <div class="form-group" style="margin-left: 20px">
                    <a href="javascript:void(-1);" class="btn btn-success btn-input" id="addRegionBtn">添加地区</a>
                </div>
            </div>
            <div class="col-sm-4">
                <div id="regionTreeview"></div>
            </div>
            <div class="col-sm-8">
                <div>
                    <table id="listTable" class="table table-striped table-hover" style="display:none">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                        </tr>
                        </thead>
                        <tbody id="tbody">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade myInputModal">
    <div class="modal-dialog">
        <div class="modal-content info">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">地区编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/region/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id" render-value="id" />
                    <input type="hidden" name="parentId" id="parentId" />
                    <div class="form-group" >
                        <label for="parentName" class="col-sm-3 control-label">上级地区：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="parentName" disabled/>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label for="name" class="col-sm-3 control-label">地区名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入地区名称">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-submit">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>