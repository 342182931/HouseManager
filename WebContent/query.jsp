<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>房产信息</h3>
<form action=queryName>
<input type=text name=name >
<input type=submit  value="提交"/>
</form>
<c:if test="${users.u_right=='1' }">
<a href="Proadd">添加</a>
</c:if>
<table border=1>
<tr>
    <td>id</td> 
    <td>name</td> 
    <td>area(m²)</td>
    <td>user</td>
    <td>address</td>
    <td>time</td>
    <td>contract</td>
    <td>price(元/m²)</td>
    <c:if test="${users.u_right=='1' }">
    <td>操作</td>
    <td>操作</td>
    </c:if>
</tr>
<c:forEach var="house" items="${list }">
<tr>
<td>${house.h_id }</td>
<td>${house.h_name }</td>
<td>${house.h_area }</td>
<td>${house.users.u_truename }</td>
<td>${house.h_adress }</td>
<td>${house.h_Ztime }到${house.h_Dtime }</td>
<td><img src="image/${house.h_contract }" width="100px" height="70px"/></td>
<td>${house.h_price }</td>
<c:if test="${users.u_right=='1' }">
<td><a href="Delete?h_id=${house.h_id }">删除</a></td>
<td><a href="Proupdate?h_id=${house.h_id }">修改</a></td>
</c:if>
</tr>
</c:forEach>
</table>

