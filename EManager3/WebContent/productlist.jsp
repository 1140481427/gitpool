<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.neusoft.entity.T_Product_List"%>
<%@ page import="com.neusoft.utils.PageModel"%>
<%
	PageModel<T_Product_List> pm = (PageModel<T_Product_List>)request.getAttribute("pagemodel");
	List<T_Product_List> proList = pm.getList();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/productlist.css" />
		<link rel="stylesheet" href="css/pagination.css" />
	</head>
	<script type="text/javascript">
		function modifly(){
			var selectFlags = document.getElementsByName("sp");
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
			
			window.self.location = "showmodiflypro?pid=" + selectFlags[j].value;
		}
		
		function deleteItem() {
			var selectFlags = document.getElementsByName("sp");
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
				alert(selectFlags[j].value);
				if(window.confirm("你确定要删除吗?")){
					window.self.location = "delpro?pid=" + selectFlags[j].value;
				}
		}
		
	  	function topPage() {
			window.self.location = "PageModelShowPro?pageNo=<%=pm.getTopPageNo()%>";
			
		}
		
		function previousPage() {
			window.self.location = "PageModelShowPro?pageNo=<%=pm.getPreviousPageNo()%>";
		}
		
		function nextPage() {
			window.self.location = "PageModelShowPro?pageNo=<%=pm.getNextPageNo()%>";
		}
		
		function bottomPage() {
			window.self.location = "PageModelShowPro?pageNo=<%=pm.getBottonPageNo()%>";
		}
		
		
	</script>
	<body style="background-color: white;">
	<form action="" method="post">
		<div style="margin-left: 50px;">
		<a href="addproduct.jsp" ><input class="bor" type="button" value="添加" /></a>
		<input class="inp" type="text" placeholder="商品编号/名称" /><input class="serch" type="button" value="搜索" />
		<table >
			<tr style="background-color: aliceblue;color: black;font-weight: bold;">
				<td ><input type="checkbox" name="sp" id="all"  /></td>
				<td>编号</td>
				<td colspan="2">名称</td>
				<td>库存</td>
				<td>上架</td>
				<td>排序</td>
				<td>操作</td>
			</tr>
			
			<%
				for(int i=0; i<proList.size();i++){
					System.out.println(proList.size());
					T_Product_List pro= proList.get(i);
				%>
			<tr style="background-color: white;" id="d1">
				<td><input type="checkbox" name="sp" value="<%=pro.getPid() %>" /></td>
				<td><%=pro.getPro_item_no()%></td>
				<td><img src="img/<%=pro.getPro_img_src() %>" width="40px" height="40px"/></td>
				<td><%=pro.getPro_name() %></td>
				<td><%=pro.getPro_spec_no() %></td>
				<td>√</td>
				<td>1</td>
				<td><a href="#" onclick="modifly()"> 修改 </a><img src="img/upd.gif" width="14px" height="14px"/><a href="#" onclick="deleteItem()"> 删除 </a><img src="img/del.gif" width="14px" height="14px"/></td>
			</tr>
			
			<%
			}
			%>
		</table>
		</div>
		
		
		<div style="margin-left:360px" >
				共<%=pm.getTotalRecords() %>条记录 <%=pm.getPageNo() %>/<%=pm.getTotalPageNo() %>页
				&nbsp;<a href="#" onclick="topPage()" >首页</a> 
				<a href="#" onclick="previousPage()">上一页</a>&nbsp;
				<a href="#" onclick="nextPage()">下一页</a> 
				<a href="#" onclick="bottomPage()" >尾页</a>
				&nbsp;第<select onChange="if(!isNaN(this.value)){window.self.location = 'PageModelShowPro?pageNo=' + this.value }">
				<!-- if(isNaN(this.value)){location='page?pageNo=this.value'}}this.disabled='disabled'   window.self.location = "page?pageNo=";-->
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
