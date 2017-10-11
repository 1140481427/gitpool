<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		* {
			margin: 5px;
			padding: 0px;
		}
		table {
			width: 80%;
			border: 1px solid darkgray;
			margin-left: 25px;
			margin-bottom: 15px;
		}
	</style>
	<body>
		<form>
			<div style="margin-top: 25px;margin-left: 25px;">
				订单状态: ${oi.orderstatus==1?"未付款":"已付款" }
			</div>
			
			<table>
				<tr style="background-color: lavender">
					<td colspan="2">订单信息</td>
				</tr>
				<tr>
					<td width="7%">
						订单编号:
					</td>
					<td>
						${oi.orderno }
					</td>
				</tr>
				
				<tr>
					<td width="7%">
						支付方式:
					</td>
					<td>
						${oi.paystatus==1?"线上支付":"货到付款" }
					</td>
				</tr>
				
				<tr>
					<td width="7%">
						下单时间:
					</td>
					<td>
						${oi.ordertime }
					</td>
				</tr>
				
				<tr>
					<td width="7%">
						付款时间:
					</td>
					<td>
						<c:if test="${oi.orderstatus==2 }" var="result">
						${oi.paytime }
						</c:if>
						<c:if test="${oi.orderstatus==1 }" var="result">
						</c:if>
						
					</td>
				</tr>
				
				</tr>
				
			</table>
			<table>
				<tr style="background-color: lavender" >
					<td colspan="2">收货人信息</td>
					
				</tr>
				<tr>
					<td width="7%">
						收货人姓名: 
					</td>
					<td>
						${ca.name }
					</td>
				</tr>
				<tr>
					<td width="7%">
						地址: 
					</td>
					<td>
						${ca.addr }
					</td>
				</tr>
				<tr>
					<td width="7%">
						联系电话:: 
					</td>
					<td>
						${ca.phone }
					</td>
					
				</tr>
			</table>
			
			<table>
				<tr style="background-color: lavender" >
					<td colspan="2">物流信息</td>
					
				</tr>
				<tr>
					<td width="7%">
						物流公司: 
					</td>
					<td>
						<input type="text" />
					</td>
				</tr>
				<tr>
					<td width="7%">
						物流单号: 
					</td>
					<td>
						<input type="text" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button style="background-color: #87B87F;height: 25px;width: 70px;color: white;margin-left: 110px;">确认发货</button> 
					</td>
					
				</tr>
			</table>
			
			
			<table>
				<tr style="background-color: lavender">
					<td>备注信息</td>
				</tr>
				<tr>
					<td>
						<input style="width: 100%;height: 100%;border: 0px;"  value="${oi.mask }" />
					</td>
				</tr>
			</table>
			
			<table>
				<tr style="background-color: lavender">
					<td>商品编号</td>
					<td>商品名称</td>
					<td>商品数量</td>
					<td>商品单价</td>
				</tr>
				<tr>
					<td>
						123123123123
					</td>
					<td>
						神舟笔记本 z7m 黑色
					</td>
					<td>
						1						
					</td>
					<td>
						5500
					</td>
				</tr>
				
				<tr>
					<td>
						123123123123
					</td>
					<td>
						神舟笔记本 z7m 黑色
					</td>
					<td>
						1						
					</td>
					<td>
						5500
					</td>
				</tr>
			</table>
			
		</form>
	</body>
</html>
