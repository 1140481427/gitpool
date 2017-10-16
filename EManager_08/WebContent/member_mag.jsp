<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" href="css/member_mag.css" />
	<body>
		<form>
			<input type="hidden" name="type" value="${type }"/>
			<div style="margin-top: 40px;"></div>
			<div style="width: 70%;margin: 0px auto;">
			<input type="button" onclick="serch('1')" value="按消费日期排序" style="cursor: pointer;" />
			<input type="button" onclick="serch('2')" value="按订单总数排序" style="cursor: pointer;" />
			<input type="button" onclick="serch('3')" value="按消费金额排序" style="cursor: pointer;" />
			</div>
			<div style="margin-top: 5px;"></div>
			<table>
				<tr style="background-color: aliceblue;color: black;font-weight: bold;">
					<td>会员ID</td>
					<td>用户名</td>
					<td>已完成订单金额</td>
					<td>已完成订单总数</td>
					<td>最近消费</td>
					<td>操作</td>
				</tr>
				
				<c:forEach items="${pm.list }" var="pm">
				<tr>
					<td>${pm.aid }</td>
					<td>${pm.nickname }</td>
					<td>${pm.money }</td>
					<td>${pm.count }</td>
					<td>${pm.lasttime }</td>
					<td>
						<a href="showshopcsmbyid?type=1&id=${pm.id }"> 修改 </a><img src="img/upd.gif" width="14px" height="14px"/>
						<a href="delcsm?aid=${pm.aid }&id=${pm.id}"> 删除 </a><img src="img/del.gif" width="14px" height="14px"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			<div style="margin-left:30%" >
				共${pm.totalRecords }条记录 ${pm.pageNo }/${pm.totalPageNo }页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'pageshop?pageNo=' + this.value }">
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
		window.self.location = "pageshop?pageNo=${pm.topPageNo}";
		
	}
	
	function previousPage() {
		window.self.location = "pageshop?pageNo=${pm.previousPageNo}";
	}
	
	function nextPage() {
		window.self.location = "pageshop?pageNo=${pm.nextPageNo}";
	}
	
	function bottomPage() {
		window.self.location = "showhot?pageNo=${pm.bottonPageNo}";
	}
	
	function serch(type) {
		window.self.location = "pageshop?type=" + type;
	}
	</script>
</html>
