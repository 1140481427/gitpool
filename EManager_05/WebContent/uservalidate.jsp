<%@page import="com.neusoft.acc_csmservice.Acc_CsmService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String userId = request.getParameter("username");
	if (Acc_CsmService.getInstance().showAcc_CsmByUser(userId) != null) {
		out.println("用户代码已经存在");
	}	
%>