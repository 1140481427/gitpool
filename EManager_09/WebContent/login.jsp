<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登陆</title>
	</head>
	<script>
		function judge(){
			var _username = document.getElementById("username");
			var _errorSpan = document.getElementById("errorSpan");
			var _password = document.getElementById("password");
			if(_username.value == null || _username.value == ""){
				_errorSpan.innerHTML = "用户名不能为空";
				return;
			}else{
				_errorSpan.innerHTML = "";
			}
			if(_password.value == null || _password.value == ""){
				_errorSpan.innerHTML = "密码不能为空";
				return;
			}else{
				_errorSpan.innerHTML = "";
				loginForm.submit();
			}
		}
	</script>
	<body background="img/timg.jpg" style="background-size: 100%;background-repeat: no-repeat;">
	<form name="loginForm" action="login" method="get">
		<input type="hidden" name="command" value="login" />
		<div style="position: relative;top: 300px;left: 43%;">
			<input id="username" name="username" style="border: 1px solid silver; border-radius: 5px;background-color: aliceblue;width: 200px;height: 25px;" placeholder="请输入用户名" /><br />
			<input id="password" name="password" style="border: 1px solid silver; border-radius: 5px;background-color: aliceblue;width: 200px;height: 25px;margin-top: 7px;" placeholder="请输入密码" /><br />
			<span id="errorSpan" style="color: red;" value="1" ></span><br />
			<input type="checkbox" name="check" value="1" /><font style="color:white">记住密码</font><p />
			<input  onclick="judge()" style="border: 1px solid silver;width: 100px;height: 30px;margin-top: 7px;margin-left: 51px;background-color: aliceblue;text-align: center;"value="提交"></input>
		</div>
		</form>
	</body>
</html>
