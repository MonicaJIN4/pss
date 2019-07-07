<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>

	//修改url里的某个查询变量的值，如果不存在这个查询变量，则添加这个变量和值
	function changeUrlArg(url, arg, val) {
		var pattern = arg + '=([^&]*)';
		var replaceText = arg + '=' + val;
		return url= url.match(pattern) ? url.replace(eval('/(' + arg
				+ '=)([^&]*)/gi'), replaceText) : (url.match('[\?]') ? url
				+ '&' + replaceText : url + '?' + replaceText);
	}
	
	
</script>

<div class="paging">

	<div class="aleft" style="display: inline-block; float: left;">
		<span>共${pagerItem.rowCount }行，</span> </span><span><select
			onchange="location.href=changeUrlArg(changeUrlArg(location.href,'${pagerItem.paramPageSize }', this.options[this.options.selectedIndex].value),'${pagerItem.paramPageNum }', '1');">

				<c:forEach var="i" begin="1" end="100" step="1">

					<option value="${i }" ${pagerItem.pageSize==i?"selected":""}>${i }</option>
				</c:forEach>

		</select>行/页</span>
	</div>
	<div class="aright" style="display: inline-block; float: right;">


		<c:set value="${pagerItem.pageNum == 1}" var="isFisrtPage"
			scope="request" />
		<c:set value="${pagerItem.pageNum == pagerItem.pageCount}"
			var="isLastPage" scope="request" />


		<c:choose>
			<c:when test="${isFisrtPage}">
				&nbsp;<span style="color:#ccc">首页</span>
				&nbsp;<span style="color:#ccc">上一页</span>
			</c:when>
			<c:otherwise>
				&nbsp;<span><a href="${pagerItem.firstPageUrl }">首页</a></span>
				&nbsp;<span><a href="${pagerItem.prevPageUrl }">上一页</a></span>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${isLastPage}">
				&nbsp;<span style="color:#ccc">下一页 </span>
				&nbsp;<span style="color:#ccc">尾页</span>
			</c:when>
			<c:otherwise>
				&nbsp;<span><a href="${pagerItem.nextPageUrl }">下一页</a></span>
				&nbsp;<span><a href="${pagerItem.lastPageUrl }">尾页</a></span>
			</c:otherwise>
		</c:choose>


		<span>&nbsp;&nbsp;&nbsp;&nbsp;跳到第 <select
			onchange="location.href=changeUrlArg(location.href,'${pagerItem.paramPageNum }', this.options[this.options.selectedIndex].value);">

				<c:forEach var="i" begin="1" end="${pagerItem.pageCount }">

					<option value="${i }"${pagerItem.pageNum==i?"selected":""}>${i }</option>
				</c:forEach>

		</select>页 /共${pagerItem.pageCount }页
	</div>
</div>


