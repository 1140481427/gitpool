<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>只卖电脑</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="css/bootstrap.css" />
		<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
	</head>
	
	<style>
		* {
			margin: 0px;
			padding: 0px;
		}
		
		body {
			background-color: darkgray;	
		}
		
	</style>
	
	<body>
		
		<div class="container-fluid">
			<!--头-->
			<nav class="navbar navbar-default navbar-inverse">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
     				</button>
						<a class="navbar-brand" href="#">只买电脑~~</a>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li onclick="login(this)">
								<a>${user.loginname==null?"登陆":user.loginname }</a>
							</li>
							<li onclick="register(this)">
								<a>${user.loginname==null?"注册":"管理" }</a>
							</li>
							<li onclick="cart()">
								<a>购物车</a>
							</li>
							<li onclick="window.location.href='acmlogout'">
								<a>注销</a>
							</li>
							<li>
								<a>联系我们</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>


		<div class="row">
				<div class="col-md-2">
					<div class="list-group">
						<a href="#" class="list-group-item active">
							分类
						</a>
					<a onclick="Hot()" class="list-group-item" >个人资料</a>
					<a onclick="Zk()" class="list-group-item" >修改密码</a>
					<a onclick="Online()" class="list-group-item" >继续购物</a>
					</div>
				</div>
				<div class="col-md-10">
					<div class="embed-responsive embed-responsive-16by9">
						<iframe id="iframe" class="embed-responsive-item" src=""></iframe>
					</div>

				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function login(obj) {
			if(obj.innerText == '登陆') {
				window.self.location = "csmlogin.jsp";
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
			iframe.setAttribute("src","showshopcsmbyid?type=2");
		}
		
		function Zk() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src","modiflypassword.jsp");
		}
		
		function Online() {
			window.self.location = "shop_index.jsp";
		}

		function cart() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src","buy.jsp");
		}
	</script>
</html>
