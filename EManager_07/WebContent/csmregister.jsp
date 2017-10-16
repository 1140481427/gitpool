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
			height: 380px;
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
			margin-top:15px; 
		}
		
		.yzin {
			width: 80px;
			height: 20px;
		}
		
		.img {
			position: relative;
			top: 10px;
		}
		
	</style>
	<body background="img/jdbj.jpg">
		<form name="loginForm" action="addcsm" method="post">
			<div id="div_main">
				<div style="margin-left: 25px;">
					<h1 style="color: red;">帐号注册</h1><span style="color:red">${err==null?"":err }</span><span id="spanUserId"></span>
					<div><input class="input" id="username" name="username" placeholder="请输入用户名" onblur="validate(this)" /></div>
					<div><input class="input" id="password" name="password" placeholder="请输入密码" /></div>
					<div><input class="input" id="repassword" name="repassword" placeholder="确认密码" /></div>
					<div>
						<label>验证码:</label>
						<input type="text" class="yzin" name="validationCode"/>
						<img id="validationCode_img"  src="validateCode" class="img">
					</div>
					<input class="input_but" value="注册" onclick="login()" type="button"></input>
				</div>
				
			</div>
		</form>
		
	</body>
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript">
		function login() {
			var user = document.getElementById("username");
			var psw = document.getElementById("password");
			var repsw = document.getElementById("repassword");
			
			if(user.value.length == 0) {
				alert("用户名不能为空!");
				return;
			}
			
			if(psw.value.length == 0) {
				alert("密码不能为空!");
				return;
			}
			
			if(repsw.value.length == 0) {
				alert("确认密码不能为空!");
				return;
			}
			
			if(psw.value != repsw.value) {
				alert("俩次密码不一致");
				return;
			}
			
			
			//判断验证码
  			if($("input[name=validationCode]").val()==null || $("input[name=validationCode]").val()==""){
        		alert("验证码不能为空");
        		$("input[name=validationCode]").focus();
       			return;
    		}
			
			loginForm.submit();
		}
	/* 	  ------------------ajax----------------- */
		function validate(field) {
			if (field.value.length != 0) {
				/* =============AJAX=========== */
				var xmlHttp = null;
				if (window.XMLHttpRequest) {
					xmlHttp = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				var url = "uservalidate.jsp?username=" + field.value + "&time=" + new Date().getTime();
				//设置请求方式为GET，设置请求的URL，设置为异步提交
				xmlHttp.open("GET", url, true);
				
				//将方法地址复制给onreadystatechange属性
				//类似于电话号码
				xmlHttp.onreadystatechange=function() {
					//Ajax引擎状态为成功
					if (xmlHttp.readyState == 4) {
						//HTTP协议状态为成功
						if (xmlHttp.status == 200) {
							if (xmlHttp.responseText != "") {
								//alert(xmlHttp.responseText);
								document.getElementById("spanUserId").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>"
							}else {
								document.getElementById("spanUserId").innerHTML = "";
							}
						}else {
							alert("请求失败，错误码=" + xmlHttp.status);
						}
					}
				};
				
				//将设置信息发送到Ajax引擎
				xmlHttp.send(null);
			} else {
				document.getElementById("spanUserId").innerHTML = "";
			}	
		}
		
		
		
		
		$("#validationCode_img").click(function(){
    		$("#validationCode_img").attr("src","validateCode?"+Math.random());
 		});
		
		
	</script>
	
</html>

