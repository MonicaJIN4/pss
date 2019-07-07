<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/html5shiv.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/style.css" />

	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
	<!--[if IE 6]>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>订单列表</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	销售管理 <span class="c-gray en">&gt;</span> 订单列表
	<a class="btn btn-success radius r"
	   style="line-height:1.6em;margin-top:3px"
	   href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<div class="text-c" style="position:relative;display:inline-block;margin:0 auto;width:100%;">
		<form action="Order" method="get"  style="margin:30px 0;">

			<input type="hidden" name="oper" value="listDeal" />

			<div class="c-dropdown js-dropdown">
				<input type="hidden" name="Framework" id="Framework" class="js-dropdown__input">
				<span class="c-button c-button--dropdown js-dropdown__current">选择查询条件</span>
				<ul class="c-dropdown__list">
					<li class="c-dropdown__item" id="btn1" data-dropdown-value="time">按时间段查询</li>
					<li class="c-dropdown__item" id="btn2" data-dropdown-value="name">按名称查询</li>
				</ul>
			</div>
			<script src='${pageContext.request.contextPath}/static/js/jquery.min.js'></script>
			<script  src="${pageContext.request.contextPath}/static/js/index.js"></script>


			<div id="date_pick" style="display:inline-block">
				选择查询时间段：
				<input type="text" id="start" class="input-text" style="width:150px;" name="start" placeholder="年/月/日">
				<input type="text" id="end" class="input-text" style="width:150px;" name="end" placeholder="年/月/日">
				<button type="submit" class="btn btn-success" id="find" name="">
					<i class="Hui-iconfont">&#xe665;</i> 查询
				</button>

				<button type="button" class="btn btn-success"
						id="clearSearch1" name="">
					<i class="Hui-iconfont">&#xe665;</i> 清空
				</button>

			</div>


			<div id="search" style="display:none;">
				<span>名称：</span>
				<input type="text" class="input-text" value="${searchName}"
					   style="width:250px;" placeholder="输入发票" id="searchName" name="searchName" >
				<button type="submit" class="btn btn-success" id="" name="">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>

				<button type="button" class="btn btn-success"
						id="clearSearch" name="">
					<i class="Hui-iconfont">&#xe665;</i> 清空
				</button>
			</div>
			<script type="text/javascript">
				$('#btn1').click(function () {
					$('#date_pick').show();
					$('#search').hide();
				});
				$('#btn2').click(function () {
					$('#search').show();
					$('#date_pick').hide();
					$('#search').css('display','inline-block');
				});
			</script>
		</form>
	</div>

	<script>

	</script>

	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="datadel()"
			   class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
			<a href="javascript:;"
			   onclick="item_add('添加','Order?oper=insert','800','500')"
			   class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加
			</a>
			</span>
		<span class="r">共有数据：<strong>${pagerItem.rowCount}</strong> 条</span> </div>
	<div class="mt-20">
		<table id="datalist" class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
			<tr class="text-c">
				<th width="40px"><input type="checkbox" name="" value=""></th>
				<th >订单号</th>
				<th >客户名称</th>
				<th >员工名称</th>
				<th >送货地址</th>
				<th >发票号</th>
				<th >总交易金额</th>
				<th >订单日期</th>
				<th >操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${DataList }"> <tr class="text-c">
				<td><input type="checkbox" value="${item.orderId }" name=""></td>
				<td>${item.orderId }</td>
				<td>${item.cusName }</td>
				<td>${item.cName }</td>
				<td>${item.sentAdd }</td>
				<td>${item.invNum }</td>
				<td>${item.money } 元</td>
				<td>${item.orderDate }</td>
				<td>
					<a title="删除" href="javascript:;"
					   onclick="item_del(this,${item.orderId})" class="ml-5"
					   style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>
					<a title="编辑" href="javascript:;"
					   onclick="item_edit('编辑[id=${item.orderId}]','Order?oper=update&id=${item.orderId}','1','800','500')"
					   class="ml-5" style="text-decoration: none">
						<i class="Hui-iconfont">&#xe6df;</i></a>
					<a title="查看"
					   href="javascript:;"
					   onclick="item_detail('查看[Id=${item.orderId}]','Order?oper=detail&id=${item.orderId }','4','800','500')"
					   class="ml-5" style="text-decoration: none">
						<i class="Hui-iconfont">&#xe707;</i></a>
				</td>
				</c:forEach>
			</tbody>
		</table>
		<jsp:include page="__pager.jsp" flush="true"/>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
	$(function(){
		$("#clearSearch").click(function () {
			location.href="Order?oper=list";

		});

	});
	$(function(){
		$("#clearSearch1").click(function () {
			location.href="Order?oper=list";

		});

	});
	/*用户-添加*/
	function item_add(title,url,w,h){
		layer_show(title,url,w,h);
	}
	/*用户-查看*/
	function item_show(title,url,id,w,h){
		layer_show(title,url,w,h);
	}
	/*用户-停用*/
	function item_stop(obj,id){
		layer.confirm('确认要停用吗？',function(index){
			$.ajax({
				type: 'POST',
				url: 'Order',
				dataType: 'json',
				success: function(data){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="item_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
					$(obj).remove();
					layer.msg('已停用!',{icon: 5,time:1000});
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		});
	}

	/*用户-启用*/
	function item_start(obj,id){
		layer.confirm('确认要启用吗？',function(index){
			$.ajax({
				type: 'POST',
				url: '',
				dataType: 'json',
				success: function(data){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="item_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
					$(obj).remove();
					layer.msg('已启用!',{icon: 6,time:1000});
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		});
	}
	/*用户-编辑*/
	function item_edit(title,url,id,w,h){
		layer_show(title,url,w,h);
	}
	/*密码-修改*/
	function change_password(title,url,id,w,h){
		layer_show(title,url,w,h);
	}
	/*项目—查看*/
	function  item_detail(title,url,id,w,h) {
		layer_show(title,url,w,h);

	}

	/*用户-删除*/
	function item_del(obj,id){
		layer.confirm('确认要删除吗？',function(index){
			$.ajax({
				type: 'POST',
				url: 'Order?oper=deleteDeal&&id=' + id,
				//dataType: 'json',
				success: function(data){
					if(data=="ok"){
						$(obj).parents("tr").remove();
						layer.msg('已删除!',{icon:1,time:1000});
					}else{
						layer.msg('删除失败！',{
							icon:1,
							time:1000
						});
					}
				},
				error:function(data) {
					console.log(data.msg);
				},
			});
		});
	}
	/*批量-删除*/
	function datadel() {
		layer.confirm('确定要删除选中数据吗？',function (index) {
			var num=0;//记录，删除成功的行数
			var total=0;//记录，要删除的总行数
			var obj=null;//记录，当前对象
			var id =0;//记录，当前要删除的主键值
			$("#datalist input[type=checkbox]:checked").each(function () {
				obj=this;
				id=$(this).val();//取得，当前复选框的主键值

				//排除全选或反选的复选框
				if (id != null && id!="" && id!="0"){
					total++;//总行数
					//start  ： ajax方式，一行一行删除
					$.ajax({
						type : 'POST',
						url : 'Order',
						async : false,//是否异步
						data : {"order":"deleteDeal","id": id},
						//dataType :'json",
						success : function (data) {
							if (data == "ok") {
								$(obj).parents("tr").remove();
								num++;//删除成功的数+1
							} else {

							}
						},
						error : function (data) {

							console.log(data.msg);
						},
					});
				}

			});
			layer.msg("要删除"+total+"行记录，成功删除"+ num + "行记录。",{
				icon : 1,
				time : 1000
			});
		});
	}

</script>
<%--		js下拉框选择时间--%>
<script src="${pageContext.request.contextPath}/static/laydate/laydate.js"></script>
<script>
	laydate.render({
		elem: '#start'
		,format: 'yyyy-MM-dd'
	});
	laydate.render({
		elem: '#end'
		,format: 'yyyy-MM-dd'
	});
</script>
</body>
</html>