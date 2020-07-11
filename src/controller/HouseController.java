package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import model.House;
import model.Users;

@Controller
public class HouseController {
	@RequestMapping("Page")
	public String page(int pageNo, Model model) {
		int pageSize = 5;
		int totalRow;
		try {
			totalRow = new House().showall().size();
			int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;
			pageNo = pageNo < 1 ? 1 : pageNo;
			pageNo = pageNo > totalPage ? totalPage : pageNo;
			ArrayList<House> list = new House().page(pageNo, pageSize);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("totalRow", totalRow);
			model.addAttribute("totalPage", totalPage);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "showall.jsp";
	}

	@RequestMapping("Proadd")
	public String proadd(Model model) throws ClassNotFoundException, SQLException {
		ArrayList<Users> list = new Users().showall();
		model.addAttribute("u_list", list);
		return "add.jsp";
	}

	@RequestMapping("Add")
	public String add(@RequestParam("h_contract") CommonsMultipartFile h_contract, String h_name, double h_area,
			int u_id, String h_adress, String Ztime, String Dtime, double h_price, HttpServletRequest request)
			throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		House h = new House();
		if (!h_contract.isEmpty()) {
			String path = request.getServletContext().getRealPath("/") + "image\\" + h_contract.getOriginalFilename();
			File newFile = new File(path);
			h_contract.transferTo(newFile);
			h.setH_contract(h_contract.getOriginalFilename());
		}
		h.setH_name(h_name);
		h.setH_area(h_area);
		Users users = new Users();
		users.setU_id(u_id);
		h.setUsers(users);
		h.setH_adress(h_adress);
		Date h_Ztime, h_Dtime;
		if (!Ztime.equals("") && !Dtime.equals("")) {
			h_Ztime = Date.valueOf(Ztime);
			h_Dtime = Date.valueOf(Dtime);
			h.setH_Ztime(h_Ztime);
			h.setH_Dtime(h_Dtime);
		}
		h.setH_price(h_price);
		h.add();
		return "forward:Page?pageNo=1";
	}

	@RequestMapping("Delete")
	public String delete(int h_id) throws ClassNotFoundException, SQLException {
		House h = new House();
		h.setH_id(h_id);
		h.delete();
		return "forward:Page?pageNo=1";
	}

	@RequestMapping("Proupdate")
	public String proupdate(int h_id, Model model) throws ClassNotFoundException, SQLException {
		House h = new House();
		h.setH_id(h_id);
		h.queryId();
		ArrayList<Users> list = new Users().showall();
		model.addAttribute("u_list", list);
		model.addAttribute("house", h);
		return "update.jsp";
	}

	@RequestMapping("Update")
	public String update(@RequestParam("h_contract") CommonsMultipartFile h_contract, int h_id, String h_name,
			double h_area, int u_id, String h_adress, String Ztime, String Dtime, HttpServletRequest request,
			double h_price) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		House h = new House();
		if (!h_contract.isEmpty()) {
			String path = request.getServletContext().getRealPath("/") + "image\\" + h_contract.getOriginalFilename();
			File newFile = new File(path);
			h_contract.transferTo(newFile);
			h.setH_contract(h_contract.getOriginalFilename());
		}
		h.setH_id(h_id);
		h.setH_name(h_name);
		h.setH_area(h_area);
		Users users = new Users();
		users.setU_id(u_id);
		h.setUsers(users);
		h.setH_adress(h_adress);
		Date h_Ztime, h_Dtime;
		if (Ztime != null && Dtime != null) {
			h_Ztime = Date.valueOf(Ztime);
			h_Dtime = Date.valueOf(Dtime);
			h.setH_Ztime(h_Ztime);
			h.setH_Dtime(h_Dtime);
		}
		h.setH_price(h_price);
		h.update();
		return "forward:Page?pageNo=1";
	}
	@RequestMapping("queryName")
	public String queryName(String name,Model model) {
		House h=new House();
		try {
			ArrayList<House> list=h.queryName(name);
			model.addAttribute("list", list);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "query.jsp";
	}
}
