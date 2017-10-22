<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>订单详情</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="css/bootstrap.css" />
	</head>
	<style>
		* {
			margin: 0px;
			padding: 0px;
		}
	</style>
	<body>
		<form>
			<div >
				<h3>订单状态: ${oi.orderstatus==1?"未付款":"已付款" }</h3>
			</div>
			
			<table class="table table-bordered table-hover" >
				<tr style="background-color: lavender" >
					<td colspan="2">订单信息</td>
				</tr>
				<tr>
					<td>
						订单编号:
					</td>
					<td>
						${oi.orderno }
					</td>
				</tr>
				
				<tr>
					<td>
						支付方式:
					</td>
					<td>
						${oi.paystatus==1?"线上支付":"货到付款" }
					</td>
				</tr>
				
				<tr>
					<td >
						下单时间:
					</td>
					<td>
						${oi.ordertime }
					</td>
				</tr>
				
				<tr>
					<td>
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
			<table class="table table-bordered table-hover" >
				<tr style="background-color: lavender" >
					<td colspan="2">收货人信息</td>
					
				</tr>
				<tr>
					<td >
						收货人姓名: 
					</td>
					<td>
						${ca.name }
					</td>
				</tr>
				<tr>
					<td>
						地址: 
					</td>
					<td>
						${ca.addr }
					</td>
				</tr>
				<tr>
					<td>
						联系电话:: 
					</td>
					<td>
						${ca.phone }
					</td>
					
				</tr>
			</table>
			
			<table class="table table-bordered table-hover" >
				<tr style="background-color: lavender" >
					<td colspan="2">物流信息</td>
					
				</tr>
				<tr>
					<td>
						物流公司: 
					</td>
					<td>
						<input type="text" />
					</td>
				</tr>
				<tr>
					<td>
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
			
			
			<table class="table table-bordered table-hover" >
				<tr style="background-color: lavender">
					<td>备注信息</td>
				</tr>
				<tr>
					<td>
						<input style="width: 100%;height: 100%;border: 0px;"  value="${oi.mask }" />
					</td>
				</tr>
			</table>
			
			<table class="table table-bordered table-hover" >
				<tr style="background-color: lavender">
					<td>商品编号</td>
					<td>商品名称</td>
					<td>商品数量</td>
					<td>商品单价</td>
				</tr>
				
				<c:forEach items="${proList }" var="poi">
					<tr>
						<td>
							${poi.pro_item_no }
						</td>
						<td>
							${poi.pname }
						</td>
						<td>
							${poi.num }					
						</td>
						<td>
							${poi.price }
						</td>
				</tr>
				</c:forEach>
				
			</table>
			
		</form>
	</body>
</html>
