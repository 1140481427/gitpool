<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="css/bootstrap.css" />
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
	<form name="loginForm" action="login" method="post" class="form-inline">
		<input type="hidden" name="command" value="login" />
		<div style="position: relative;top: 320px;left: 44%;">
			<div class="form-group">
    			<input id="username" class="form-control" name="username"  placeholder="请输入用户名" />
  			</div>
  			<p />
  			<div class="form-group">
    			<input id="password" class="form-control" name="password" placeholder="请输入密码" /><br />
  			</div>
  			
  			<div>
  				<span id="errorSpan" style="color: red;" value="1" ></span>
  			</div>
			
			 <div class="checkbox">
			    <label>
			      <input type="checkbox" name="check" value="1"> 记住密码
			    </label>
			 </div>
			 <br />
			 
			<input class="btn btn-primary"  onclick="judge()" value="提交"></input>
		</div>
		</form>
	</body>
</html>
