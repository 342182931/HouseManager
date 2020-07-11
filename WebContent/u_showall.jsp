<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>用户信息</h3>
<table border=1>
<tr>
    <td>id</td> 
    <td>name</td>
    <td>password</td>
    <td>truename</td>
    <td>right</td>
    <td>操作</td>
    <td>操作</td>
</tr>
<c:forEach var="users" items="${list }">
<tr>
<td>${users.u_id }</td>
<td>${users.u_name }</td>
<td>${users.u_password }</td>
<td>${users.u_truename }</td>
<td><c:if test="${users.u_right==1 }">管理员</c:if>
<c:if test="${users.u_right==0 }">用户</c:if></td>
<td><a href="DeleteUsers?u_id=${users.u_id }">删除</a></td>
<td><a href="UpdateUsers?u_id=${users.u_id }">变成管理员</a></td>
</tr>
</c:forEach>
</table>
<a href="PageUser?pageNo=1">首页</a>
<a href="PageUser?pageNo=${pageNo-1 }">上一页</a>
<a href="PageUser?pageNo=${pageNo+1 }">下一页</a>
<a href="PageUser?pageNo=${totalPage }">尾页</a>共${totalRow}条记录，共${totalPage}页。
