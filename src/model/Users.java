package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Users {
private int u_id,u_right;
private String u_name,u_password,u_truename;
private DBOper db;
public int getU_id() {
	return u_id;
}
public void setU_id(int u_id) {
	this.u_id = u_id;
}
public int getU_right() {
	return u_right;
}
public void setU_right(int u_right) {
	this.u_right = u_right;
}
public String getU_name() {
	return u_name;
}
public void setU_name(String u_name) {
	this.u_name = u_name;
}
public String getU_password() {
	return u_password;
}
public void setU_password(String u_password) {
	this.u_password = u_password;
}
public String getU_truename() {
	return u_truename;
}
public void setU_truename(String u_truename) {
	this.u_truename = u_truename;
}
public void login() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	db=new DBOper();
	ResultSet rs=db.query("select * from Users where u_name='"+u_name+"' and u_password='"+u_password+"'");
	if(rs.next()) {
		this.setU_id(rs.getInt("u_id"));
		this.setU_truename(rs.getString("u_truename"));
		this.setU_right(rs.getInt("u_right"));
	}
}
public void register() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	db=new DBOper();
	db.update("insert into Users values('"+u_name+"','"+u_password+"','"+u_truename+"',"+u_right+")");
}
public void update() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	db=new DBOper();
	db.update("update Users set u_name='"+u_name+"',u_password='"+u_password+"',u_truename='"+u_truename+"',u_right="+u_right+" where u_id="+u_id);
}
public ArrayList<Users> page(int pageNo, int pageSize) throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	ArrayList<Users> list=new ArrayList<Users>();
	DBOper db=new DBOper();
	ResultSet rs=db.query("select * from Users");
	int start=(pageNo-1)*pageSize;//结果集开始读取位置，pageNo为当前页号，pageSize每页显示多少
	rs.absolute(start); //定位到开始位置
	int i=0; //控制读取几条记录
	while (rs.next()&&i<pageSize) {
		Users u=new Users();
		u.setU_id(rs.getInt("u_id"));
		u.setU_name(rs.getString("u_name"));
		u.setU_password(rs.getString("u_password"));
		u.setU_truename(rs.getString("u_truename"));
		u.setU_right(rs.getInt("u_right"));
		list.add(u);
		 i++;
	}
	return list;
}
public ArrayList<Users> trans(ResultSet rs) throws SQLException{
	ArrayList<Users> list=new ArrayList<Users>();
	while(rs.next()) {
		Users u=new Users();
		u.setU_id(rs.getInt("u_id"));
		u.setU_name(rs.getString("u_name"));
		u.setU_password(rs.getString("u_password"));
		u.setU_truename(rs.getString("u_truename"));
		u.setU_right(rs.getInt("u_right"));
		list.add(u);
	}
	return list;
}
public ArrayList<Users> showall() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	ArrayList<Users> list=new ArrayList<Users>();
	DBOper db=new DBOper();
	ResultSet rs=db.query("select * from Users");
	list=this.trans(rs);
	return list;
}
public void delete() throws ClassNotFoundException, SQLException {
	// TODO Auto-generated method stub
	db=new DBOper();
	db.update("delete from Users where u_id="+u_id);
}
public void queryId() throws SQLException, ClassNotFoundException {
	// TODO Auto-generated method stub
	db=new DBOper();
	ResultSet rs=db.query("select * from Users where u_id="+u_id);
	if(rs.next()) {
		this.setU_id(rs.getInt("u_id"));
		this.setU_name(rs.getString("u_name"));
		this.setU_password(rs.getString("u_password"));
		this.setU_truename(rs.getString("u_truename"));
		this.setU_right(rs.getInt("u_right"));
	}
}
}
