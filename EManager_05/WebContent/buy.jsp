<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style>
	table {
		width:80%;
	}
</style>
<body>
	<form name="buyForm" action="order.jsp" method="post" >
		<table>
			<tr>
				<td>编号</td>
				<td>商品名</td>
				<td>数量</td>
				<td>总价</td>
			</tr>
			
			<c:forEach items="${cart.items }" var="cart" >
				<tr>
					<td>${cart.pro_id }</td>
					<td>${cart.pname }</td>
					<td><input id="${cart.pro_id }" name="${cart.pro_id }" style="width: 50px" id="num" value="${cart.num }"  /><input type="button" onclick="modifly('${cart.pro_id }')" value="修改"></td>
					<td>${cart.totalPrice }</td>
				</tr>
			</c:forEach>
			
		</table>
		
		<input onclick="tijiao()" type="button" value="提交订单" />
	</form>
</body>
	<script type="text/javascript">
		function tijiao(){
			buyForm.submit();
		}
		
		function modifly(p){
			var num = document.getElementById(p);
			window.self.location = 'modiflynum?pid='+ p + '&num=' + num.value;
		}
		
		
	</script>
</html>