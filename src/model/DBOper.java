package model;

import java.sql.*;
public class DBOper {
	 private String uri="jdbc:sqlserver://localhost:1433;databaseName=HouseManager";  
	 private String user="sa";  
	 private String password="123";
	 //���Լ�������     
	 private Connection conn;    
	 private Statement st;     
	 private ResultSet rs;     
	 //���� 
	 public DBOper() throws ClassNotFoundException, SQLException {   
		 //��������  
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");   
		 //�������ݿ�   
		 conn=DriverManager.getConnection(uri, user, password);  
		 }  
	 //��ѯ 
	 
	 public ResultSet query(String sql) throws SQLException{    
		 st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);  
		 rs=st.executeQuery(sql);   
		 return rs;  }  
	 //���£�����ɾ���ģ�
	 public int update(String sql) throws SQLException{   
		 st=conn.createStatement();  
		 System.out.println(sql);
		 return st.executeUpdate(sql);  
		 }  
}