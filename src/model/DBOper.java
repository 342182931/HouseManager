package model;

import java.sql.*;
public class DBOper {
	 private String uri="jdbc:sqlserver://localhost:1433;databaseName=HouseManager";  
	 private String user="sa";  
	 private String password="123";
	 //你自己的密码     
	 private Connection conn;    
	 private Statement st;     
	 private ResultSet rs;     
	 //构造 
	 public DBOper() throws ClassNotFoundException, SQLException {   
		 //加载驱动  
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   
		 //连接数据库   
		 conn=DriverManager.getConnection(uri, user, password);  
		 }  
	 //查询 
	 
	 public ResultSet query(String sql) throws SQLException{    
		 st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);  
		 rs=st.executeQuery(sql);   
		 return rs;  }  
	 //更新（增、删、改）
	 public int update(String sql) throws SQLException{   
		 st=conn.createStatement();  
		 System.out.println(sql);
		 return st.executeUpdate(sql);  
		 }  
}