<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.neusoft.entity.T_Pro_Category"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%
	T_Pro_Category tc = (T_Pro_Category) request.getAttribute("tc");
	Map<Integer,String> map =(Map<Integer,String>) request.getAttribute("map");
	Iterator iter = map.entrySet().iterator();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<style>
		.div_top {
			width: 800px;
			height: 40px;
			margin: 0px auto;
			border-bottom:1px solid darkgray ;			
		}
		
		.div_centet {
			margin: 0px auto;
			width: 300px;
			height: 40px;
			margin-top: 20px;
		}
		
		.div_down {
			width: 300px;
			height: 40px;
			margin: 0px auto;
			margin-top: 10px;
		}
		
		.div_bt {
			width: 130px;
			height: 30px;
			margin-left: 900px;
			margin-top: 25px;
		}
		
		select {
			width: 200px;
			margin-left: 25px;
		}
		
		input {
			width: 195px;
			margin-left: 25px;
		}
		
		button {
			background-color: #D3D3D3;
			width: 60px;
			height: 27px;
			margin-right: 5px;
			color: white;
		}
		
	</style>
	<body>
		<form action="modiflyCate">
		<input type="hidden" name="cid" value="<%=tc.getCate_id() %>" />
			<div class="div_top">
				<span>修改分类信息</span>
			</div>
			<div class="div_centet">
				分类名称<input name="cname" id="cname" type="text" value="<%=tc.getCate_name() %>" />
			</div>
			<div class="div_down">
				上级分类<select name="selepro" id="selepro">
							<option value="1">
								顶级分类
							</option>
							<%
							
								while(iter.hasNext()){
									Map.Entry<Integer,String> entry =(Map.Entry<Integer,String>) iter.next();
									//if(tc.getCate_pid()==entry.getKey()){
							%>
							<!-- <option value="<%=entry.getKey() %>" selected = "selected" >
									 <%=entry.getValue() %> 
							</option> -->
							
							
							<%
							
							%>
									<!--  }else{  -->
								<option value="<%=entry.getKey() %>">
										 <%=entry.getValue() %> 
								</option>
							<%
							%>
							
							<%
									//}
								}
							%>
						</select>
			</div>
			<div class="div_bt">
			<button style="background-color: #428bca;" type="submit">确定</button><button type="reset">取消</button>
			</div>
		</form>
	</body>
</html>
