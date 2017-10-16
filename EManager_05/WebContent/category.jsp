<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@page import="com.neusoft.utils.PageModel"%>
<%@ page import="com.neusoft.entity.T_Pro_Category"%>
<%
	PageModel<T_Pro_Category> pm = (PageModel<T_Pro_Category>) request.getAttribute("pm");
	List<T_Pro_Category> cateList = pm.getList();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<link rel="stylesheet" href="css/category.css" />
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
			
			window.self.location = "showmodifly?cid=" + selectFlags[j].value;
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
					window.self.location = "del?cid=" + selectFlags[j].value;
				}
		}
		
	  	function topPage() {
			window.self.location = "showcate?pageNo=<%=pm.getTopPageNo()%>";
			
		}
		
		function previousPage() {
			window.self.location = "showcate?pageNo=<%=pm.getPreviousPageNo()%>";
		}
		
		function nextPage() {
			window.self.location = "showcate?pageNo=<%=pm.getNextPageNo()%>";
		}
		
		function bottomPage() {
			window.self.location = "showcate?pageNo=<%=pm.getBottonPageNo()%>";
		}
		
		function tijiao(){
			
		}
		
	</script>
	<body>
		<form action="">
			<a href="showadd"><input class="addC" type="button" value="添加类别" /></a>
			<table>
				<tr style="background-color: aliceblue;">
					<td>
					<input type="checkbox" />
					</td>
					<td>
						编号
					</td>
					<td>
						类别名称
					</td>
					<td>
						父id
					</td>
					<td>
						操作
					</td>
				</tr>
				
				<%
					for(int i=0; i<cateList.size(); i++){
						T_Pro_Category cate = cateList.get(i);	
				%>
				
				<tr>
					<td>
					<input type="checkbox" name="selectFlag" value="<%=cate.getCate_id() %>" />
					</td>
					<td id = "cid">
						<%=cate.getCate_id() %>
					</td>
					<td>
						<%=cate.getCate_name() %>
					</td>
					<td>
						<%=cate.getCate_pid()%>
					</td>
					<td>
						<a href="#" onclick="modifly()"> 修改 </a><img src="img/upd.gif" width="14px" height="14px"/><a href="#" onclick="deleteItem()"> 删除 </a><img src="img/del.gif" width="14px" height="14px"/>
					</td>
						
				</tr>
				
				<% 
					}
				%>
			</table>
			
				<div style="margin-left:120px" >
				共<%=pm.getTotalRecords() %>条记录 <%=pm.getPageNo() %>/<%=pm.getTotalPageNo() %>页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'showcate?pageNo=' + this.value }">
				<!-- if(isNaN(this.value)){location='page?pageNo=this.value'}}this.disabled='disabled'   window.self.location = "showcate?pageNo=";-->
			  	<%
			  		for(int i=0; i<pm.getTotalPageNo(); i++){
			  			if(pm.getPageNo()==(i+1)){%>
			  				<option value="<%=i+1 %>" selected="selected"><%=i+1 %></option>  				
			  			<% }else {%>
			  				<option value="<%=i+1 %>" ><%=i+1 %></option>  
			  			<% }
			  			
			  		}
			  	%>
						</select>页
				</div>
		</form>
	</body>
</html>
