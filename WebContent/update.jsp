<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>修改房产信息</h3>
<form action="Update">
<input type=hidden name=h_id value=${house.h_id } />
名称：<input type=text name=h_name value=${house.h_name } /><br>
面积：<input type=text name=h_area value=${house.h_area } /><br>
单价：<input type=text name=h_price value=${house.h_price } /><br>
持有人：<select name=u_id >
<c:forEach var="users" items="${u_list }">
<option value=${users.u_id } <c:if test="${users.u_id==house.users.u_id }">selected</c:if>>${users.u_truename }
</c:forEach>
</select>
地址：<input type=text name=h_adress value=${house.h_adress } /><br>
开始时间：<input type=date name=Ztime value=${house.h_Ztime } /><br>
到期时间：<input type=date name=Dtime value=${house.h_Dtime } /><br>
合同：<input type=file name=h_contract value=${house.h_contract } /><br>
<input type=submit value=提交 />
</form>