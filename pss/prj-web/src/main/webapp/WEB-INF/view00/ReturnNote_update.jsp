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
	<form action="ReturnNote"  method="post" class="form form-horizontal" id="objForm">
		<input type="hidden" name="oper" value="updateDeal" />
		<input type="hidden" name="returnId" value="${returnId}" />
		<input type="hidden" name="id" value="${returnId}" />


		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">商品名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span id="cargoName" >
					${cargoName}</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>退货数量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 200px" value="${number}" placeholder="" id="number" name="number">kg
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>退货单价：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" style="width: 200px" value="${returnPrice}" placeholder="" id="returnPrice" name="returnPrice"> 元
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">进货价格：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span id="buyPrice" >
				${buyPrice} 元</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>退货日期：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="date" class="input-text" style="width: 200px" value=""  id="returnDate" name="returnDate">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>经办人：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select id="staffId" name="staffId" style="width: 200px" >
					<option value="0">请选择</option>
					<c:forEach var="item" items="${staffList}">
						<option value="${item.staffId}">${item.cName}</option>
					</c:forEach>
				</select>
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

$("#objForm").validate({
	rules:{
		supId:{
			required:true,
		},
		date:{
			required:true,
		},
		cargoId:{
			required:true,
		},
		number:{
			required:true,
			number:true
		},
		returnPrice:{
			required:true,
			number:true
		}

	},
	messages: {
		supId:{
			required:"供应商必填",
		},
		date:{
			required:"采购日期必填",
		},
		cargoId:{
			required:"商品必填",
		},
		number:{
			required:"数量必填",
			number:"应该填数字哦"
		},
		returnPrice:{
			required:"单价必填",
			number:"应该填数字哦"
		}
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		form.submit();
	}
});



$(function () {
	$("#cargoId").click;

	// 选中当前类目

	$("#cargoId").val("${cargoId}");
	$("#supId").val("${supId}");
	$("#staffId").val("${staffId}");
	$("#returnDate").val("${returnDate}");

});

</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>