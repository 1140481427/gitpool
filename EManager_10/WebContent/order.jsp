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
		table {
			width:70%;
			margin-top: 25px;
			margin-left: 25px;
			border: 1px solid darkgray
		}		
		
		td {
			text-align: center;
			border: 1px dashed darkgray;
		}
		textarea {
			height: 100%;
			width: 100%;
		}
		
		.ipt {
			height: 100%;
			width: 100%;
		}
		
		
	</style>
	<script type="text/javascript" src="js/jsAddress.js" ></script>
		
		<body>
			<form name="orderForm" action="addorder" method="post">
			<table>
				<tr>
					<td colspan="2"><h3>收货地址</h3></td>				
				</tr>
				<tr>
					<td>收货人姓名:</td>				
					<td><input type="text" name="name" id="name" /></td>
				</tr>
				<tr>
					<td>联系电话:</td>				
					<td><input type="text" name="phone" id="phone" /></td>
				</tr>
				<tr>
					<td>地址:</td>				
					<td>省：<select id="Select1" name="sel1"></select>
						市：<select id="Select2" name="sel2"></select>
						区：<select id="Select3" name="sel3"></select>
					</td>
				</tr>
				<tr>
					<td>详细地址</td>				
					<td><textarea name="order" id="order" ></textarea></td>
				</tr>
				
			</table>
			
			<table>
				<tr>
					<td colspan=""><h3>支付方式</h3></td>
				</tr>
				<tr>
					<td><select name="payment" id="payment" >
						<option value="1">在线支付</option>
						<option value="2">货到付款</option>
					</select></td>
				</tr>
				
			</table>
			
			<table>
				<tr>
					<td><h3>备注</h3></td>
				</tr>
				<tr>
					<td>
						<textarea name="remark" id="remark"></textarea>
					</td>
				</tr>
			</table>
			
			<table>
				<tr>
					<td colspan="4"><h3>商品信息</h3></td>
				</tr>
				<tr>
					<td>商品编号</td>
					<td>商品名称</td>
					<td>商品数量</td>
					<td>总价</td>
				</tr>
				
				<c:forEach items="${cart.items }" var="cart" >
					<tr>
						<td><input type="text" class="ipt" name="pid" id="pid" readonly="readonly" value="${cart.pro_id }" /></td>
						<td><input type="text" class="ipt" name="pname" id="pname" readonly="readonly" value="${cart.pname }"  /></td>
						<td><input type="text" class="ipt" name="num" id="num" readonly="readonly" value="${cart.num }"  /></td>
						<td><input type="text" class="ipt" name="totalprice" id="totalprice" readonly="readonly" value="${cart.totalPrice }"  /></td>
					</tr>
				</c:forEach>
				
			</table>
		<input style="margin-top: 25px;margin-left: 35%" type="button" name="tj" id="tj" onclick="tijiao()" value="确认订单" /><br />
		<span>${err==null?"":err }</span>
		</form>
	</body>
	<script type="text/javascript">
	addressInit('Select1', 'Select2', 'Select3');
	
	function tijiao(){
		orderForm.submit();
	}
	</script>
</html>
