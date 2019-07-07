<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>

    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->

    <title>添加采购入库 - H-ui.admin v3.1</title>
    <meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
    <form action="Purchase"  method="post" class="form form-horizontal" id="objForm">
        <input type="hidden" name="oper" value="insertDeal" />
        <input type="hidden" name="cargoId" id="cargoId" value="${cargoId}" />

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>供应商名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="supId" name="supId" class="select-box" style="width: 200px">
                    <option value="0">请选择</option>
                    <c:forEach var="item" items="${supList}">
                        <option value="${item.supId}">${item.name}</option>
                    </c:forEach>
                </select>
                <button class="btn radius btn-primary size-S" onclick="item_add('添加供应商','Supplier?oper=insert','600','400')">添加供应商</button>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">商品名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
				<span id="cargoName" >
                    ${cargoName}</span>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>数量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${number}" style="width: 200px;" placeholder="" id="number" name="number">
                <span id="unit" name="unit" >
					</span>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${buyPrice}"
                       style="width: 200px;" placeholder="" id="buyPrice" name="buyPrice">
                元
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">指导进货价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
				<span id="adviceBuyPrice" style="font-weight: bold;">${adviceBuyPrice}
				</span>&nbsp;元/<span name="unit">${unit}</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>采购日期：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="date" class="input-text" style="width: 200px;" value=""  id="date" name="date">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"></label>
            <div class="formControls col-xs-8 col-sm-9">
                <span style="color:red;font-weight: bold;">${msg}</span>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
    $().ready(function() {
        $("#objForm").validate({
            rules: {
                supId: {
                    required: true,
                },
                date: {
                    required: true,
                },
                cargoId: {
                    required: true,
                },
                number: {
                    required: true,
                    number: true
                },
                buyPrice: {
                    required: true,
                    number: true
                }

            },
            messages: {
                supId: {
                    required: "供应商必填",
                },
                date: {
                    required: "采购日期必填",
                },
                cargoId: {
                    required: "商品必填",
                },
                number: {
                    required: "数量必填",
                    number: "请输入数字"
                },
                buyPrice: {
                    required: "单价必填",
                    number: "请输入数字"
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                form.submit();
            }
        });
    });


    function item_add(title,url,w,h){
        layer_show(title,url,w,h);
    }

    $(function () {

        var infoDate = document.getElementById('date');
        var now = new Date();
        <%--${'#date'}.val('2018-11-1');--%>
        var year = now.getFullYear();
        var day = now.getDate();
        var monthReady = now.getMonth()+1;
        var month = monthReady < 10? '0'+monthReady : monthReady;
        day = day < 10? '0'+day : day;
        infoDate.value = year+'-'+month+'-'+day;
    });
</script>
<script type="text/javascript">
    var advicePrice = document.getElementById("adviceBuyPrice");
    var unit = document.getElementsByName("unit");

    function fun (x,y) {
        advicePrice.innerText = x ;
        unit[0].innerText =y;
        unit[1].innerText =y;
    }
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>