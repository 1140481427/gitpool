<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		#main {
		margin-left: 34%;		
		}
		
		.k {
			width: 70%
		}
		
		.ipy {
			width: 50%;
			height: 25px;
			margin-bottom: 15px;
		}
		
		
	</style>
	
	
	<body> 
	<h3>修改密码</h3>
	<hr />
		<div id="main">
			<form name="modiflypasswordForm" method="post" action="updatecsmpassword">
				<input type="hidden" value="${user.id }" id="id" name="id" />
				<div class="k">原密码: <input class="ipy" name="oldpasswrod" onblur="validate(this)" /></div>
				<span id="spanUserId"></span>
				<div class="k">新密码: <input class="ipy" name="newpassword" /></div>
				<span>${err==null?"":err }</span>
				<input style="width: 260px;height: 35px;" onclick="modiflypassword()" type="button" value="提交" />
			</form>
		</div>
	</body>
	
	<script type="text/javascript">
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
				var url = "passwordvalidate.jsp?oldpasswrod=" + field.value + "&id=" +${user.id }+ "&time=" + new Date().getTime();
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
	
		function modiflypassword(){
			modiflypasswordForm.submit();
		}
	</script>
</html>
 