<script type="text/javascript">
    $(function () {//因为在<ul>的上面，所以要放进$()里面
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
    });

</script>
<div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
</div>