﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<!--[if IE 6]>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
	<style type="text/css">
		td
		{
			white-space: nowrap;
		}
	</style>
<title>采购入库管理</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	采购管理 <span class="c-gray en">&gt;</span> 采购入库
	<a class="btn btn-success radius r"
	   style="line-height:1.6em;margin-top:3px"
	   href="javascript:location.replace(location.href);" title="刷新" >
		<i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<div class="text-c">
		<form action="Purchase" method="get">
			<input type="hidden" name="oper" value="listDeal" />

			<span>商品名称：</span>
			<input type="text" class="input-text" value="${searchName}"
				style="width:250px;" placeholder="输入商品名称" id="searchName" name="searchName" >

			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>

			<button type="button" class="btn btn-success"
				id="clearSearch" name="">
				<i class="Hui-iconfont">&#xe665;</i> 清空
			</button>
		</form>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="datadel()"
			   class="btn btn-danger radius">
				<i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
			<a href="javascript:;" onclick="item_add('添加采购入库','Purchase?oper=insert','800','500')"
			   class="btn btn-primary radius" type="POST">
				<i class="Hui-iconfont">&#xe600;</i> 添加</a>
			<button class="btn btn-primary" style="border-radius: 5px">
				<a href="Purchase?oper=excel" download="采购报表.xlsx" style="color: #ffffff">导出数据</a>
			</button>
			</span>

			<span class="r">共有数据：<strong>${pagerItem.rowCount}</strong> 条</span> </div>
	<div class="mt-20">
	<table id="datalist" class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr>
				<th scope="col" colspan="9">数据列表</th>
			</tr>
			<tr class="text-c">
				<th width="40px"><input type="checkbox" name="" value=""></th>
				<th >采购单单号</th>
				<th >商品名称</th>
				<th >数量</th>
				<th >采购日期</th>
				<th colspan="2">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${DataList}"> <tr class="text-c">
				<td><input type="checkbox" value="${item.purId }" name=""></td>
				<td>${item.purId }</td>
				<td>${item.cargoName }</td>
				<td>${item.number } ${item.unit}</td>
				<td>${item.date }</td>

				<td width="100px">
					<a title="删除" href="javascript:;" onclick="item_del(this,${item.purId})" class="ml-5"
					   style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i>
					</a>

					<a title="编辑" href="javascript:;" onclick="item_edit('编辑[id=${item.purId}]','Purchase?oper=update&id=${item.purId}','1','800','500')"
						class="ml-5" style="text-decoration: none">
						<i class="Hui-iconfont">&#xe6df;</i>
					</a>

					<a title="查看" href="javascript:;" onclick="item_detail('查看[id=${item.purId}]','Purchase?oper=detail&id=${item.purId }','4','800','500')"
					   class="ml-5" style="text-decoration: none">
						<i class="Hui-iconfont">&#xe707;</i>
					</a>
				</td>

				<td width="100px">
					<c:if test="${item.status == 0}">
						<button class="btn radius btn-primary size-S"
								onclick="item_return('退货[purId=${item.purId}]',
										'ReturnNote?oper=insert&purId=${item.purId}','500','500')">退货
						</button>
					</c:if>

					<c:if test="${item.status == 1}">
						<button class="btn radius btn-danger size-S" id="reDetail" type="button"
						<a title="退货详情" href="javascript:;" onclick="item_returnDetail('退货详情[purId=${item.purId}]',
								'Purchase?oper=redetail&id=${item.purId }','4','800','500')"
						   class="ml-5" style="text-decoration: none" ></a>
						退货详情
						</button>
					</c:if>
				</td>
			</tr>

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
/*清空查询*/
$(function () {
	$("#clearSearch").click(function () {
		location.href = "Purchase?oper=list";
	});
});


/*项目-添加*/
function item_add(title,url,w,h){
	layer_show(title,url,w,h);

}

/*项目-退货详情*/
function  item_returnDetail(title,url,id,w,h) {
    layer_show(title,url,w,h);
}



/*用户-删除*/
function item_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'Purchase?oper=deleteDeal&&id=' + id,
			//dataType: 'json',
			success: function(data){
				if(data=="ok"){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					layer.msg('删除失败！',{
						icon:1,time:1000
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
					url : 'Purchase',
					async : false,//是否异步
					data : {"oper":"deleteDeal","id": id},
					//dataType :'json",
					success : function (data) {
						if (data == "ok") {
							$(obj).parents("tr").remove();
							num++;//删除成功的数+1
						} else {

						}
					},
					error : function (data) {

					},
				});
				// End : ajax方式，一行一行删除
			}

		});
		layer.msg("要删除"+total+"行记录，成功删除"+ num + "行记录。",{
			icon : 1,
			time : 1000
		});
	});

}
/*项目-编辑*/
function item_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*项目—查看*/
function  item_detail(title,url,id,w,h) {
	layer_show(title,url,w,h);

}
function item_return(title,url,id,w,h){
	layer_show(title,url,w,h);
}

</script>

</body>
</html>