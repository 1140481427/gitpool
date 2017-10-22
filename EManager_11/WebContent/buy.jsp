<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<style>
	img {
		width:100px;
		height: 60px;
	}
</style>
<body>
	<form name="buyForm" action="order.jsp" method="post" >
		<table class="table table-bordered table-hover table-condensed"  >
			<tr>
				<td>编号</td>
				<td>商品名</td>
				<td>图示</td>
				<td>数量</td>
				<td>总价</td>
			</tr>
			
			<c:forEach items="${cart.items }" var="cart" >
				<tr>
					<td>${cart.pro_id }</td>
					<td>${cart.pname }</td>
					<td><img src="upload/${cart.prc_src }" /></td>
					<td><input id="${cart.pro_id }" name="${cart.pro_id }" style="width: 50px" id="num" value="${cart.num }"  /><input type="button" onclick="modifly('${cart.pro_id }')" value="修改"></td>
					<td><input name="totalprice" value="${cart.totalPrice }" /></td>
				</tr>
			</c:forEach>
			
		</table>
		 
		<input style="margin-left: 40%" onclick="tijiao()" class="btn btn-info" type="button" value="提交订单" />
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