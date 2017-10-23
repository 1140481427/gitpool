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
						<a class="navbar-brand" href="#">只卖电脑~~</a>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#myModal">
								${user.loginname==null?"登陆":user.loginname }
								</a>
								
								<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">登陆</h4>
					</div>
					<div class="modal-body">

						<form name="loginForm" action="acccsmlogin" method="post">
							<input type="hidden" name="command" value="login" />
							<div class="form-group">
								<label for="exampleInputEmail1">用户名:</label>
								<input type="text" class="form-control" id="username" name="username" placeholder="用户名">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">密码:</label>
								<input type="password" class="form-control" id="password" name="password" placeholder="密码">
							</div>
							<div class="checkbox">
								<label>
      							<input name="autologin" type="checkbox" value="1"> 7天免登陆
  							</label>
							</div>
						</form>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" onclick="login()"  class="btn btn-primary">登陆</button>
					</div>
				</div>
			</div>
		</div>
								
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
			
			
			
			

			<!--轮播-->

			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="img/t1.jpg" width="100%"  alt="...">
						<div class="carousel-caption">
						</div>
					</div>
					<div class="item">
						<img src="img/t2.jpg" width="100%" alt="...">
						<div class="carousel-caption">
						</div>
					</div>
					<div class="item">
						<img src="img/t3.jpg" width="100%" alt="...">
						<div class="carousel-caption">
						</div>
					</div>

					...
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>

			<div class="jumbotron">
				<h1>只卖电脑，从始至终！</h1>
				<p>不要再问了！</p>
				<p>
					<a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
				</p>
			</div>

			<div class="row">
				<div class="col-md-2">
					<div class="list-group">
						<a href="#" class="list-group-item active">
							分类
						</a>
						<a onclick="Hot()" class="list-group-item">热卖商品</a>
						<a onclick="Zk()" class="list-group-item">折扣商品</a>
						<a onclick="Online()" class="list-group-item">即将上线</a>
					</div>
				</div>
				<div class="col-md-10">
					<div class="embed-responsive embed-responsive-16by9">
						<iframe id="iframe" class="embed-responsive-item" src="showhot"></iframe>
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
			iframe.setAttribute("src", "showhot");
		}

		function Zk() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src", "showzk");
		}

		function Online() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src", "showonline");
		}

		function cart() {
			var iframe = document.getElementById("iframe");
			iframe.setAttribute("src", "buy.jsp");
		}
		
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

</html>