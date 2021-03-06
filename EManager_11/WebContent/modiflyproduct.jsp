<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="javax.swing.text.html.parser.Entity"%>
<%@ page import="com.neusoft.productservice.ProductService"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<script type="text/javascript" src="js/jquery-3.2.1.js" ></script>
	<script type="text/javascript">
	function selectProv(obj)	{
		/* =============AJAX=========== */
		var xmlHttp = null;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = "SelectProServlet?regionid=" + obj.value +"&time=" + new Date().getTime();
		
		//设置请求方式为GET，设置请求的URL，设置为异步提交
		xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4) {
				//Http协议状态为成功
					if (xmlHttp.status == 200) {
						var doc = xmlHttp.responseXML;
						var items = doc.getElementsByTagName("item");
						//取得省下拉列表
						var provSelect = document.getElementById("province");
						//清除省下拉列表中的值
						provSelect.options.length = 0;
						var o = new Option("---全部---",0);
						provSelect.add(o);
						//alert(items.length);
						for(var i=0; i<items.length; i++){
							var id = items[i].childNodes[0].childNodes[0].nodeValue;
							var name = items[i].childNodes[1].childNodes[0].nodeValue;
							//alert(name);
							var o = new Option(name,id);
							provSelect.add(o);						
						}
					} else {
								alert("请求失败，错误码=" + xmlHttp.status);
						}
				}
				
			};
			//将设置信息发送到Ajax引擎
			xmlHttp.send(null);
	}
	
	
	$(function(){
		$("#province").change(function(){
			var val = $("#province").val() 
			/* var name = $("#province").find("option:selected").text(); */ 
			 $.ajax({
				   type: "POST",		//请求类型
				   url: "ajaxrule",	//请求到哪
				   data: "cid="+val,	//参数
				   success: function(msg){
				     //讲得到的json数据转换为js数据
				     var jsonObj =  JSON.parse(msg);
				   	 	$("#rl").empty();
					     for(var i=0; i<jsonObj.length; i++) {
					    	
					     	$("#rl").append("<option value='"+ jsonObj[i].id +"'>"+jsonObj[i].pm_size+"</option>");
					     }
				   }
				}); 
		})
		
		
		
	})
	</script>
	<link rel="stylesheet" href="css/addproduct.css" />
	<body style="margin-left: 25px;margin-top: 25px;">
		<form action="modiflypro" enctype="multipart/form-data" method="post">
			<input type="hidden" name="pid" value="${pro.pid }" />
			<table class="tb">
				<tr>
					<td class="td_left">商品名称</td>    <span value="${error==null?"":error }"></span>
					<td><input class="_input" name="pro_name" type="text" value="${pro.pro_name }"/></td>
				</tr>
				
				<tr>
					<td class="td_left">商品类别</td>
					<td>
						<select name="region" id="region" onchange="selectProv(this)">
						
							<option>类别</option>
				<%
				Map map = new ProductService().showProOneCate();	
				for(Iterator iter = map.entrySet().iterator();iter.hasNext();){
					Map.Entry entry = (Map.Entry)iter.next();
				%>
					<option value="<%=entry.getKey()%>"><%=entry.getValue() %></option>
				<% 
				}
				%>
						</select>
						
						<select name="province" id="province">
								<option value="0">
									--全部--
								</option>

							</select>
					</td>
				</tr>
				
				<tr>
					<td class="td_left">货号</td>
					<td><input class="_input" name="pro_no" type="text" value="${pro.pro_item_no }" /></td>
				</tr>
				
				<tr>
					<td class="td_left">规格</td>
					<td>
						<select id="rl" name="pro_spec">
						</select>
					</td>
				</tr>
				
				<tr>
					<td class="td_left">上传照片</td>
					<td><input type="file" name="pro_img_src" value="上传照片" />${pro.pro_img_src }</td>
				</tr>
				
				<tr>
					<td class="td_left">商品价格</td>
					<td><input class="_input" name="pro_price" type="text" value="${pro.pro_price }" /></td>
				</tr>
				
				<tr>
					<td class="td_left">点击数</td>
					<td><input class="_input" name="pro_hits" type="text" value="${pro.pro_hits }" /></td>
				</tr>
				
				<tr>
					<td class="td_left">是否推荐</td>
					
					<td>
						<input name="pro_rem" type="radio" value="1" />是<input name="pro_rem" type="radio" value="2" />否</td>
				</tr>
				
				<tr>
					<td class="td_left">是否特价</td>
					<td><input name="pro_tj" value="1" type="radio" />是<input name="pro_tj" value="2" type="radio" />否</td>
				</tr>
				
				<tr>
					<td class="td_left">是否上线</td>
					<td><input name="pro_sx" value="1" type="radio" />是<input name="pro_sx" value="2" type="radio" />否</td>
				</tr>
				
				<tr>
					<td class="td_left_last">商品详情</td>
					<td>
					
						<textarea id="edit" name="edit"  >
						${pro.pro_paritculars }
						</textarea>
						
					</td>
				</tr>
			</table>
			<button>提交</button>
		</form>
	</body>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace("edit");
	</script>
	
</html>
