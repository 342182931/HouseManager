<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>您好，${users.u_truename}，欢迎使用本系统</h1>
<h2>功能导航</h2>
<c:if test="${users.u_right=='1' }">
	<a href="PageUser?pageNo=1">用户信息管理</a>
</c:if>
<a href="u_modify.jsp">修改个人信息</a>
<a href="Page?pageNo=1">查询所有房产信息</a>
<a href="Quit">退出</a>
<hr color="blue" size="2" />