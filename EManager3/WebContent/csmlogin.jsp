<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		body {
			background-repeat: no-repeat;
			background-size: 100%;
		}
		
		#div_main {
			position: absolute;
			top: 25%;
			left: 60%;
			background-color: white;
			width: 350px;
			height: 350px;
		}
		
		.input {
			width: 90%;
			height: 35px;
			margin-top:15px ;
		}
		
		.input_but {
			width: 90%;
			height: 35px;
			background-color: red;
			color: white;
			font-size: 20px;
			text-align: center;
			cursor: pointer;
		}
	</style>
	<script>
		function login() {
			var user = document.getElementById("username");
			var psw = document.getElementById("password");
			
			
			if(user.value.length == 0) {
				alert("用户名不能为空!");
				return;
			}
			
			if(psw.value.length == 0) {
				alert("密码不能为空!");
				return;
			}
			
			loginForm.submit();
			
			
		}
	</script>
	<body background="img/jdbj.jpg">
		<form name="loginForm" action="acccsmlogin" method="post">
		<input type="hidden" name="command" value="login" />
			<div id="div_main">
				<div style="margin-left: 25px;">
					<h1 style="color: red;">帐号登陆</h1>
					<span id="error" style="color: red" >${error==null?"":error }</span>
					
					<div><input class="input" id="username" name="username" placeholder="请输入用户名" /></div>
					<div><input class="input" id="password" name="password" placeholder="请输入密码" /></div>
					<div style="margin-top: 20px;margin-bottom: 20px;"><input name="autologin" type="checkbox" value="1" />自动登陆 <a style="margin-left: 145px;" href="#">免费注册</a></div>
					<input class="input_but" value="登陆" onclick="login()" type="button"></input>
				</div>
			</div>
		</form>
		
	</body>
</html>
