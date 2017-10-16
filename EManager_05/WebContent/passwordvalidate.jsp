<%@page import="com.neusoft.utils.MD5Utils"%>
<%@page import="com.neusoft.acc_csmservice.Acc_CsmService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String userId = request.getParameter("id");
	String password =  request.getParameter("oldpasswrod");
	if (!Acc_CsmService.getInstance().showAcc_CsmById(Integer.parseInt(userId)).getPassword().equals(MD5Utils.GetMD5Code(password))) {
		//out.println(Acc_CsmService.getInstance().showAcc_CsmById(Integer.parseInt(userId)).getPassword() );
		//out.println(MD5Utils.GetMD5Code(password));
		out.println("原密码不正确请重新输入");
	}	
%>