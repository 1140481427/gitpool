<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		table {
			margin-left:25px;
			margin-top:25px;
			border: 1px solid darkgray;
			width: 50%;
		}
		
		td {
			border: 1px dashed darkgray;
		}
		input {
			border: 0px;
			width: 100%;
			height: 100%;
		}
		button {
			width: 200px;
			hight: 35px;
			margin-left: 15%;
			margin-top: 20px;
		}
		
	</style>
	<body>
		<form action="modiflyshopcsm" method="get">
		<input type="hidden" name="type"  value="${type }" />
		<table>
			<tr>
				<td colspan="2" style="text-align: center;"><h3>个人信息</h3></td>
			</tr>
			
			<tr>
				<td>编号:</td>
				<td><input name="id" id="id" value="${sc.id }" readonly="readonly" /></td>
			</tr>
			
			<tr>
				<td>aid:</td>
				<td><input name="aid" id="aid" value="${sc.aid }" readonly="readonly" /> </td>
			</tr>
			
			<tr>
				<td>昵称:</td>
				<td><input name="nickname" id="nickname" value="${sc.nickname }"  /></td>
			</tr>			
			
			<tr>
				<td>已消费金额:</td>
				<td><input name="money" id="money" value="${sc.money }"  /></td>
			</tr>
			
			<tr>
				<td>订单数量:</td>
				<td><input name="count" id="count" value="${sc.count }"  /></td>
			</tr>
			
			<tr>
				<td>最后一次登陆时间:</td>
				<td><input name="lasttime" id="lasttime" value="${sc.lasttime }" readonly="readonly"  /></td>
			</tr>
		</table>
		<span>${err==null?"":err }</span>
		<button type="sumbit">修改</button>
		</form>
	</body>
</html>
