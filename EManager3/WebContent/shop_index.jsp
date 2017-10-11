<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>只卖电脑</title>
	</head>
	
	<style>
		* {
			margin: 0px;
			padding: 0px;
		}
		
		body {
			background-color: darkgray;	
		}
		
		#header {
			background-color: black;
			height: 50px;
		}
		
		#header_left {
			width: 200px;
			margin: 0px auto;
			float: left;
			margin-left: 20%;
			
		}
		
		#header_right {
			width: 600px;
			height: 50px;
			background-color: black;
			margin-left: 150px;
			float: left;
		}
		
		#header_right ul {
			color: white;
			height: 50px;
			line-height: 50px;
			text-align: center;
		}
		
		#header_right ul li {
			margin-left: 5px;
			height: 50px;
			width: 80px;
			display: inline-block;	
			background-color: red;
			border-radius: 10px;
		}
		
		#header_right ul li:hover {
			background-color: darkgray;	
		}
		
		#main {
			margin: 0px auto;
			width: 80%;
			height: 200px;
			margin-top: 25px;
		}
		
		#left {
			width: 20%;
			height: 220px;
			background-color: black;
			float: left;
		}
		
		#left ul {
			list-style: none;
			height: 200px;
			color: white;
			text-align: center;
		}
		
		#left ul li {
			background-color: red;
			height: 60px;
			line-height: 60px;
			margin-top: 10px;
			border-radius: 1em;
		}
		
		#left ul li:hover {
			background-color: silver;
		}
		
		#right {
			width: 70%;
			height: 700px;
			background-color: white;
			display: flex;
			float: left;
		}
		
		
		
	</style>
	
	<body>
		<!--头-->
		<div id="header">
			
			<div id="header_left"><h1 style="color: red;">只卖笔记本</h1></div>
			<div id="header_right">
				<ul>
					<li onclick="login(this)">${user.loginname==null?"登陆":user.loginname }</li>
					<li onclick="register(this)">${user.loginname==null?"注册":"管理" }</li>
					<li  onclick="cart()">购物车</li>
					<li>联系我们</li>
				</ul>
				
			</div>
		</div>
		
		<!--主-->
		<div id="main">
			<!--左-->
			<div id="left">
				<ul>
					<a onclick="Hot()"><li>热卖商品</li></a>
					<a onclick="Zk()"><li>折扣商品</li></a>
					<a onclick="Online()"><li>即将上线</li></a>
				</ul>
			</div>
			
			<!--右-->
			<div id="right">
				<iframe id="iframe" style="width: 100%;" src="hot.jsp">
					
				</iframe>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function login(obj) {
			if(obj.innerText == '登陆') {
				window.self.location = "csmlogin.jsp";
			} else {
				alert(2);
			}
		}
		
		function register(obj) {
			if(obj.innerText == '注册') {
				window.self.location = "csmlogin.jsp";
			} else {
				window.self.location = "manager_index.jsp";
			}
		}
		
		function Hot() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src","showhot");
		}
		
		function Zk() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src","showzk");
		}
		
		function Online() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src","showonline");
		}
		
		function cart() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src","buy.jsp");
		}

	</script>
</html>
