<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		.div_top {
			width: 800px;
			height: 40px;
			margin: 0px auto;
			border-bottom:1px solid darkgray ;			
		}
		
		.div_centet {
			margin: 0px auto;
			width: 300px;
			height: 40px;
			margin-top: 20px;
		}
		
		.div_down {
			width: 300px;
			height: 40px;
			margin: 0px auto;
			margin-top: 10px;
		}
		
		.div_bt {
			width: 130px;
			height: 30px;
			margin-left: 900px;
			margin-top: 25px;
		}
		
		select {
			width: 200px;
			margin-left: 25px;
		}
		
		input {
			width: 195px;
			margin-left: 25px;
		}
		
		button {
			background-color: #D3D3D3;
			width: 60px;
			height: 27px;
			margin-right: 5px;
			color: white;
		}
		
	</style>
	<body>
		<form action="addrule">
			<div class="div_top">
				<span>添加规格</span>
			</div>
			<div class="div_centet">
				商品 &nbsp;&nbsp;ID<input name="pid" type="text" />   
			</div>
			<div class="div_down">
				规格尺寸<input name="pm_size" type="text" />
			</div>
			<div class="div_bt">
			<button style="background-color: #428bca;" type="submit">确定</button><button type="reset">取消</button>
			</div>
		</form>
	</body>
</html>
