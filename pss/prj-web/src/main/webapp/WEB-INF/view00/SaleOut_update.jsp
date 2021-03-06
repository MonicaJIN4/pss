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
	<form action="SaleOut"  method="post" class="form form-horizontal" id="objForm">
		<input type="hidden" name="oper" value="updatedeal" />
		<input type="hidden" name="saleId" value="${saleId}" />
		<input type="hidden" name="id" value="${saleId}" />
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>商品ID：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="hidden" class="input-text" value="${cargoId}" placeholder="" id="cargoId" name="cargoId">
			<span>${cargoId}</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>数量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 200px" value="${number}" placeholder="" id="number" name="number">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>销售单价：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  style="width: 200px"value="${price}" placeholder="" id="price" name="price">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>员工编号：</label>
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
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>客户编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select id="cusId" name="cusId" class="select-box" style="width: 200px">
					<option value="">请选择</option>
					<c:forEach var="item" items="${cusList}">
						<option value="${item.cusId}">${item.cusName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>销售日期：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="date"  class="input-text"  style="width: 200px"value="${outDate}" placeholder="" id="outDate" name="outDate">

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
			cargoId:{
				required:true,
			},
			number:{
				required:true,
				min:0,

			},
			price:{
				required:true,
				min:0,

			},
			staffId:{
				required:true,
			},
			cusId:{
				required:true,
			}
		},
		messages: {
			cargoId:{
				required: "商品ID不能为空",
			},

			number:{
				required: "数量不能为空",

			},
			price:{
				required: "销售价格不能为空",

			},
			staffId:{
				required: "员工编号不能为空",
			},
			cusId:{
				required: "客户编号不能为空",
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
<!--使下拉框显示之前的值-->
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