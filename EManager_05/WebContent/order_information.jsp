<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		input {
			height: 25px;
			border: 1px solid #D3D3D3;
		}
		span {
			background-color: #EEEEEE;
			display: inline-block;
			height: 26px;
			padding-top:1px;
			border: 1px solid #D3D3D3;
		}
		td {
			border: 1px solid #F4F4F4;
		}
	</style>
	<body>
		<form>
			订单总数:${pm.totalRecords }
			<div>
				<input style="width: 45%;" placeholder="可以输入:收件人姓名 收件人电话 订单号" />
				<span>订单时间</span><input style="width: 10%;" />
				<span>到</span><input style="width: 10%;" />
				<input style="background-color: #6FB3E0;color: white;" type="button" value="查询" />
				<input style="background-color: #6FB3E0;color: white;" type="button" value="导出Excl" />
			</div>
			<table style="width: 100%;">
				<tr style="background-color: #EFEFEF;font-weight: bold;">
					<td>订单状态</td>
					<td>物流状态</td>
					<td>订单编号</td>
					<td>商品数量</td>
					<td>订单金额</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${pm.list }" var="order">
				<tr>
					<td>${order.orderstatus==1?"未付款":"已付款" }</td>
					<td>未发送</td>
					<td><a href="showorderbyid?id=${order.id }">${order.orderno }</a></td>
					<td>${order.num }</td>
					<td>${order.totalprice }</td>
					<td><a href="#"> 删除 </a><img src="img/del.gif" width="14px" height="14px"/></td>
				</tr>
				</c:forEach>
			</table>
			<div style="margin-left:120px" >
				共${pm.totalRecords }条记录 ${pm.pageNo }/${pm.totalPageNo }页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'showorder?pageNo=' + this.value }">
				<!-- if(isNaN(this.value)){location='page?pageNo=this.value'}}this.disabled='disabled'   window.self.location = "showcate?pageNo=";-->
			  	<c:forEach begin="1" end="${pm.totalPageNo }" step="1" var="i">
				  	<c:choose>
				  		<c:when test="${i == pm.pageNo}">
				  		<option value="${i }" selected="selected">${i }</option> 
				  		</c:when>
				  		<c:otherwise>
				  		<option value="${i }" >${i }</option> 
				  		</c:otherwise>
				  	</c:choose>
			  	</c:forEach>
						</select>页
		</div>
		</form>
	</body>
	<script type="text/javascript">
	function topPage() {
		window.self.location = "showorder?pageNo=${pm.topPageNo}";
		
	}
	
	function previousPage() {
		window.self.location = "showorder?pageNo=${pm.previousPageNo}";
	}
	
	function nextPage() {
		window.self.location = "showorder?pageNo=${pm.nextPageNo}";
	}
	
	function bottomPage() {
		window.self.location = "showorder?pageNo=${pm.bottonPageNo}";
	}
	</script>
</html>
