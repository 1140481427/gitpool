<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="css/index.css" />
		<script type="text/javascript" src="js/index.js" ></script>
	</head>
	<body onload="_onload()">
		<div id="_heard">
			<!--title  logo  左-->
			<div id="_logo" onclick="showp()">
				<a href="#" >
					LOGO 后台管理
				</a>
			</div>
			
			<!--标题   中      导航栏-->
			<div id="_navb">
				<ul>
					<li><a href="#">系统</a></li>
					<li><a href="#">采购</a></li>
					<li><a href="#">销售</a></li>
					<li><a href="#">库存</a></li>
					<li><a href="#">用户</a></li>
					<li><a href="#">商品</a></li>
					<li><a href="#">订单</a></li>
					<li><a href="#">客服</a></li>
					<li><a href="#">商家</a></li>
					<li><a href="#">财务</a></li>
					<li><a href="#">数据统计</a></li>
				</ul>
			</div>
			
			<!--右    登陆-->
			<div id="_login">
				
				<div id="_img">
				</div>
				
				<div id="_use">
					<ul>
						<li><a href="#">胡言</a></li>
						<li>|</li>
						<li><a href="#">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
		
		
		<!--左侧 导航-->
			
		<div id="_left_main">
			<!--搜索框-->
			<div id="_serch">
				<input id="_text" placeholder="搜索..." /><div id="_pull_left" onclick="concealLeft()"></div>
			</div>
			<!--帐号管理-->
			<!--<div class="_safety_main" onclick="change()">
				<div class="_sf_img">
				</div>
				<div class="_sf_txt">
					安全管理
				</div>
			</div>
			
			<div id="all" style="height: 60px;overflow: hidden;transition: height 1s;">
				<div class="pull_zh">
						<ul>
						<li>帐号列表</li>
						<li>角色管理</li>
					</ul>	
				</div>
			</div>-->
			
			<!--安全管理-->
			<!--<div class="_safety_main">
				<div class="_sf_img">
				</div>
				<div class="_sf_txt">
					安全管理
				</div>
			</div>-->
		</div>
		
		
		<!--右  正文内容-->
		<div id="_right_main">
			<div id="top" style="display: flex;">
				<div style="display: flex;margin-right: 25px;">
				</div>
				<!--<div class="_div_main">
					<div>
						帐号管理
					</div>		
					
					<div class="tu" >
						
					</div>
				</div>-->
			</div>
			<div id="botton">
				<iframe id="iframeHtml" src="" style="width: 100%; height: 100%;"></iframe>
			</div>
		</div>
		
	</body>
</html>
