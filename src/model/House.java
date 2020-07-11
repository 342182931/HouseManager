package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class House {
	private int h_id;
	private Users users;
	private double h_area, h_price;
	private String h_adress, h_contract, h_name;
	private Date h_Ztime, h_Dtime;
	private DBOper db;

	public int getH_id() {
		return h_id;
	}

	public void setH_id(int h_id) {
		this.h_id = h_id;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public double getH_area() {
		return h_area;
	}

	public void setH_area(double h_area) {
		this.h_area = h_area;
	}

	public double getH_price() {
		return h_price;
	}

	public void setH_price(double h_price) {
		this.h_price = h_price;
	}

	public String getH_adress() {
		return h_adress;
	}

	public void setH_adress(String h_adress) {
		this.h_adress = h_adress;
	}

	public String getH_contract() {
		return h_contract;
	}

	public void setH_contract(String h_contract) {
		this.h_contract = h_contract;
	}

	public Date getH_Ztime() {
		return h_Ztime;
	}

	public void setH_Ztime(Date h_Ztime) {
		this.h_Ztime = h_Ztime;
	}

	public Date getH_Dtime() {
		return h_Dtime;
	}

	public void setH_Dtime(Date h_Dtime) {
		this.h_Dtime = h_Dtime;
	}

	public ArrayList<House> showall() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<House> list = new ArrayList<House>();
		db = new DBOper();
		ResultSet rs = db.query("select * from House");
		list = this.trans(rs);
		return list;
	}

	public ArrayList<House> trans(ResultSet rs) throws SQLException, ClassNotFoundException {
		ArrayList<House> list = new ArrayList<House>();
		while (rs.next()) {
			House h = new House();
			h.setH_id(rs.getInt("h_id"));
			h.setH_name(rs.getString("h_name"));
			h.setH_area(rs.getDouble("h_area"));
			Users user = new Users();
			user.setU_id(rs.getInt("h_user"));
			user.queryId();
			h.setUsers(user);
			h.setH_adress(rs.getString("h_adress"));
			h.setH_Ztime(rs.getDate("h_Ztime"));
			h.setH_Dtime(rs.getDate("h_Dtime"));
			h.setH_contract(rs.getString("h_contract"));
			h.setH_price(rs.getDouble("h_price"));
			list.add(h);
		}
		return list;
	}

	public ArrayList<House> page(int pageNo, int pageSize) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<House> list = new ArrayList<House>();
		db = new DBOper();
		ResultSet rs = db.query("select * from House");
		int start = (pageNo - 1) * pageSize;// 结果集开始读取位置，pageNo为当前页号，pageSize每页显示多少
		rs.absolute(start); // 定位到开始位置
		int i = 0; // 控制读取几条记录
		while (rs.next() && i < pageSize) {
			House h = new House();
			h.setH_id(rs.getInt("h_id"));
			h.setH_name(rs.getString("h_name"));
			h.setH_area(rs.getDouble("h_area"));
			Users user = new Users();
			user.setU_id(rs.getInt("h_user"));
			user.queryId();
			h.setUsers(user);
			h.setH_adress(rs.getString("h_adress"));
			h.setH_Ztime(rs.getDate("h_Ztime"));
			h.setH_Dtime(rs.getDate("h_Dtime"));
			h.setH_contract(rs.getString("h_contract"));
			h.setH_price(rs.getDouble("h_price"));
			list.add(h);
			i++;
		}
		return list;
	}

	public void add() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		db = new DBOper();
		db.update("insert into House values('" + h_name + "'," + h_area + "," + users.getU_id() + ",'" + h_adress + "','"
				+ h_Ztime + "','" + h_Dtime + "','" + h_contract + "'," + h_price + ")");
	}

	public void delete() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		db = new DBOper();
		db.update("delete from House where h_id="+h_id);
	}

	public void queryId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		db = new DBOper();
		ResultSet rs=db.query("select * from House where h_id="+h_id);
		if(rs.next()) {
			this.setH_id(rs.getInt("h_id"));
			this.setH_name(rs.getString("h_name"));
			this.setH_area(rs.getDouble("h_area"));
			Users user = new Users();
			user.setU_id(rs.getInt("h_user"));
			user.queryId();
			this.setUsers(user);
			this.setH_adress(rs.getString("h_adress"));
			this.setH_Ztime(rs.getDate("h_Ztime"));
			this.setH_Dtime(rs.getDate("h_Dtime"));
			this.setH_contract(rs.getString("h_contract"));
			this.setH_price(rs.getDouble("h_price"));
		}
	}

	public void update() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		db = new DBOper();
		db.update("update House set h_name='" + h_name + "',h_area=" + h_area + ",h_user=" + users.getU_id() + ",h_adress='" + h_adress + "',h_Ztime='"
				+ h_Ztime + "',h_Dtime='" + h_Dtime + "',h_contract='" + h_contract + "',h_price=" + h_price + " where h_id="+h_id);
	}

	public ArrayList<House> queryName(String name) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<House> list = new ArrayList<House>();
		db = new DBOper();
		ResultSet rs = db.query("select * from House where h_name like '%"+name+"%'");
		list = this.trans(rs);
		return list;
	}
}
