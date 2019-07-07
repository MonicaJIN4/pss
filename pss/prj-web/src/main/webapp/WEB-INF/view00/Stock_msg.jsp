<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/html5shiv.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/H-ui.admin/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/skin/default/skin.css"
          id="skin"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>库存</title>
</head>
<body>

<nav class="breadcrumb">
    <i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 库存预警
    <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="Hui-iconfont">&#xe68f;</i>
    </a>
</nav>

<div class="cl pd-5 bg-1 bk-gray mt-20">

    <span class="l">
            <button class="btn btn-primary" style="border-radius: 5px">
			<a href="Stock?oper=excel" download="库存预警报表.xlsx" style="color: #ffffff">导出数据</a>
			</button>
        </span>
    <span class="r">共有<strong style="color: #ab1e1e">${pagerItem.rowCount}</strong>条未处理的数据。</span>
</div>

<div class="mt-20">
    <table id="datalist" class="table table-border table-bordered table-hover table-bg table-sort">
        <thead>
        <tr>
            <th scope="col" colspan="9">数据列表</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" name="" value=""></th>
            <th width="40">库存编号</th>
            <th width="150">商品名称</th>
            <th width="150">当前数量</th>
            <th width="100">安全存量</th>
            <th width="100">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${DataList}">
            <tr class="text-c">
                <td><input type="checkbox" value="${item.stockId}" name=""></td>
                <td>${item.stockId}</td>
                <td>${item.cargoName}</td>
                <td>${item.number} ${item.unit}</td>
                <td>${item.safetyStock} ${item.unit}</td>
                <td>
                        <%--<a title="查看详情" href="javascript:;" onclick="item_detail('查看[Id=${item.stockId}]',--%>
                        <%--'Stock?oper=detail&id=${item.stockId }','4','500','400')" class="ml-5" style="text-decoration: none">--%>
                        <%--<i class="Hui-iconfont">&#xe707;</i>--%>
                        <%--</a>--%>
                    <a title="采购" href="javascript:;"
                       onclick="item_skip('采购','Purchase?oper=insert&stockId=${item.stockId}','500','400')"
                       class="btn btn-primary radius">
                        采购
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="__pager.jsp" flush="true"/>
</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    /*清空查询*/
    $(function () {
        $("#clearSearch").click(function () {
            location.href = "Stock?oper=list";
        });
    });

    /*项目-添加*/
    function item_skip(title, url, w, h) {
        layer_show(title, url, w, h);
    }

    /*用户-删除*/
    function item_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: 'Stock?oper=deleteDeal&&id=' + id,
                //dataType: 'json',
                success: function (data) {
                    if (data == "ok") {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
                    } else {
                        layer.msg('删除失败！', {
                            icon: 1, time: 1000
                        });
                    }
                },
                error: function (data) {
                    console.log(data.msg);
                },
            });
        });
    }

    /*批量-删除*/
    function datadel() {
        layer.confirm('确定要删除选中数据吗？', function (index) {
            var num = 0;//记录，删除成功的行数
            var total = 0;//记录，要删除的总行数
            var obj = null;//记录，当前对象
            var id = 0;//记录，当前要删除的主键值
            $("#datalist input[type=checkbox]:checked").each(function () {
                obj = this;
                id = $(this).val();//取得，当前复选框的主键值

                //排除全选或反选的复选框
                if (id != null && id != "" && id != "0") {
                    total++;//总行数
                    //start  ： ajax方式，一行一行删除
                    $.ajax({
                        type: 'POST',
                        url: 'Stock',
                        async: false,//是否异步
                        data: {"oper": "deleteDeal", "id": id},
                        //dataType :'json",
                        success: function (data) {
                            if (data == "ok") {
                                $(obj).parents("tr").remove();
                                num++;//删除成功的数+1
                            } else {
                                layer.msg('删除失败！', {
                                    icon: 1, time: 1000
                                });
                            }
                        },
                        error: function (data) {

                        },
                    });
                    // End : ajax方式，一行一行删除
                }

            });
            layer.msg("要删除" + total + "行记录，成功删除" + num + "行记录。", {
                icon: 1,
                time: 1000
            });
        });

    }

    /*项目-编辑*/
    function item_edit(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }

    /*项目—查看*/
    function item_detail(title, url, id, w, h) {
        layer_show(title, url, w, h);

    }

</script>
</body>
</html>