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

	<title>修改 - H-ui.admin v3.1</title>
	<meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
	<meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<article class="page-container">
	<form action="Order"  method="post" class="form form-horizontal" id="objForm">
		<input type="hidden" name="oper" value="updatedeal" />
		<input type="hidden" name="orderId" value="${orderId}" />
		<input type="hidden" name="id" value="${orderId}" />
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>客户名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select class="select-box" style="width: 200px" id="cusId" name="cusId">
					<option style="width: 200px;" value="">请选择</option>
					<c:forEach var="item" items="${cusList}">
						<option value="${item.cusId}">${item.cusName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>员工名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select id="staffId" name="staffId" class="select-box" style="width: 200px">
					<option value="">请选择</option>
					<c:forEach var="item" items="${staffList}">
						<option value="${item.staffId}">${item.cName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发送地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 200px" value="${sentAdd}" placeholder="" id="sentAdd" name="sentAdd">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发票号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  style="width: 200px" value="${invNum}" placeholder="" id="invNum" name="invNum">
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>总交易金额：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text"  style="width: 200px" value="${money}" placeholder="" id="money" name="money">
		</div>
	</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>订单日期：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 200px" value="${orderDate}" placeholder="" id="orderDate" name="orderDate">
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
	$(function(){
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
	});

	$().ready(function () {
	$("#objForm").validate({
		rules:{
			staffId:{
				required:true,

			},
			sentAddr:{
				required:true,
			},
			invNum:{
				required:true,
				min:0,

			},
			money:{
				required:true,
				min:0,

			}
		},
		messages: {

			staffId:{
				required:"员工编号不为空",

			},
			sentAddr:{
				required:"发送地址不为空",
			},
			invNum:{
				required:"发票号不为空",

			},
			money:{
				required:"总交易金额不为空",


			}

		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			form.submit();
		}
	});
	});


</script>
<!--/请在上方写此页面业务相关的脚本-->
<script>
	$(function () {
		$("#cusId").val("${cusId}")

	})
	$(function () {
		$("#staffId").val("${staffId}")

	})
</script>
</body>
</html>