<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		#main {
			width: 700px;
			height: 600px;
		}
		
		#d1 {
			width: 200px;
			height: 300px;
			display: inline-block;
			margin-left: 20px;
		}
		
		img {
			width: 195px;
			height: 150px;
		}
		
	</style>
	<script type="text/javascript">
		function topPage() {
			window.self.location = "showhot?pageNo=${pm.topPageNo}";
			
		}
		
		function previousPage() {
			window.self.location = "showhot?pageNo=${pm.previousPageNo}";
		}
		
		function nextPage() {
			window.self.location = "showhot?pageNo=${pm.nextPageNo}";
		}
		
		function bottomPage() {
			window.self.location = "showhot?pageNo=${pm.bottonPageNo}";
		}
	
		function buy(obj){
			var nextnode = obj.nextElementSibling;
			var prenode = obj.previousElementSibling;
			//alert(nextnode.value);
			//alert(prenode.value)
			window.self.location ="additem?pid="+nextnode.value+"&num="+prenode.value;
		}
	</script>
	<body>
		<div id="main">
			<c:forEach items="${pm.list }" var="list">
				<div id="d1">
					<table style="border: 1px solid black;">
						<tr>
							<td><img src="img/${list.pro_img_src }" /></td>
						</tr>
						<tr>					
							<td><h4>${list.pro_name }</h4></td>
						</tr>
						<tr>
							<td>${list.pro_price }</td>
						</tr>
						<tr>
							<td>数量:<input type="text" id="num" name="num" style="width: 40px;" />
							<input style="margin-left: 60px;" onclick="buy(this)" type="button" value="购买" />
							<input type="hidden" value="${list.pid }" />
							</td>
						</tr>
					</table>
				</div>
			</c:forEach>
		</div>
		<div style="margin-left:120px" >
				共${pm.totalRecords }条记录 ${pm.pageNo }/${pm.totalPageNo }页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'showhot?pageNo=' + this.value }">
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
	</body>
</html>
 