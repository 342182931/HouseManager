<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h3>用户注册</h3>
<form action=UpdateUser>
<input type=hidden name=u_id value=${users.u_id } />
<input type=hidden name=u_right value=${users.u_right } />
用户名：<input type=text name=u_name value=${users.u_name } /><br/>
密码：<input type=text name=u_password value=${users.u_password } /><br/>
姓名：<input type=text name=u_truename value=${users.u_truename } /><br/>
<input type=submit value=提交 />
</form>