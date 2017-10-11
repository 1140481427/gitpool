<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<script type="text/javascript">
		function modifly(){
			var selectFlags = document.getElementsByName("selectFlag");
			//统计选择的个数
			var count = 0;
			var j = 0;
				for(var i=0; i<selectFlags.length; i++){
					//判断是否被选中
					if(selectFlags[i].checked){
						j = i;
						count++;
					}
				}
				if(count == 0){
					alert("至少选择一个修改对象");
					return;
				}
				
				if(count > 1){
					alert("一次只能修改一个");
					return;
				}
			
			window.self.location = "showmodiflyrule?id=" + selectFlags[j].value;
		}
		
		function deleteItem() {
			var selectFlags = document.getElementsByName("selectFlag");
				var num = 0;
				var j = 0;
				for(var i=0; i<selectFlags.length; i++){
					if(selectFlags[i].checked == true){
						j=i;
						num++;
					}
				}
				if(num == 0){
					alert("至少选择一个删除对象");
					return;
				}
				if(window.confirm("你确定要删除吗?")){
					window.self.location = "delrule?id=" + selectFlags[j].value;
				}
		}
		
	  	function topPage() {
			window.self.location = "showrule?pageNo=${pm.topPageNo}";
			
		}
		
		function previousPage() {
			window.self.location = "showrule?pageNo=${pm.previousPageNo}";
		}
		
		function nextPage() {
			window.self.location = "showrule?pageNo=${pm.nextPageNo}";
		}
		
		function bottomPage() {
			window.self.location = "showrule?pageNo=${pm.bottonPageNo}";
		}
		
		function tijiao(){
			
		}
		
	</script>
	<link rel="stylesheet" href="css/category.css" />
	<body>
		<form action="">
			<a href="addspec.jsp"><input class="addC" type="button" value="添加规格" name="selectFlag" /></a>
			<table>
				<tr style="background-color: aliceblue;">
					<td>
					<input type="checkbox" />
					</td>
					<td>
						编号
					</td>
					<td>
						屏幕尺寸
					</td>
					
					<td>
						操作
					</td>
				</tr>
				
				<c:forEach items="${pm.list }" var="rul">
				<tr>
					<td>
					<input type="checkbox" name="selectFlag" value="${rul.id }" />
					
					</td>
					<td>
						${rul.id }
					</td>
					<td>
						${rul.pm_size }
					</td>
					
					<td>
						<a href="#" onclick="modifly()"> 修改 </a><img src="img/upd.gif" width="14px" height="14px"/><a href="#" onclick="deleteItem()"> 删除 </a><img src="img/del.gif" width="14px" height="14px"/>
					</td>        
				</tr>
				</c:forEach>
			</table>
			<div style="margin-left:120px" >
				共${pm.totalRecords }条记录 ${pm.pageNo }/${pm.totalPageNo }页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'showrule?pageNo=' + this.value }">
				<!-- if(isNaN(this.value)){location='page?pageNo=this.value'}}this.disabled='disabled'   window.self.location = "showcate?pageNo=";-->
			  	<c:forEach begin="1" end="${pm.totalPageNo }" step="1" var="i">
				  	<c:choose>
				  		<c:when test="${i == pm.pageNo}">
				  		<option value="${i }" selected="selected">${i }</option> 
				  		</c:when>
				  		<c:otherwise>
				  		<option value="${i }" >${i }</option> 
				  		</c:otherwise>
				  	</c:choose>
			  	</c:forEach>
						</select>页
		</div>
		</form>
	</body>
</html>
