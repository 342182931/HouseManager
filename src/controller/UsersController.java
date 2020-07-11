package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Users;


@Controller
public class UsersController {
	@RequestMapping("/Login")
	public String login(String u_name, String u_password,HttpServletRequest request) throws ClassNotFoundException, SQLException {
		Users users=new Users();
		users.setU_name(u_name);
		users.setU_password(u_password);
		users.login();
		HttpSession session=request.getSession();
		session.setAttribute("users", users);
		return "main.jsp";
	}
	@RequestMapping("/Register")
	public String register(String u_name, String u_password,String u_truename) throws ClassNotFoundException, SQLException {
		Users users=new Users();
		users.setU_name(u_name);
		users.setU_password(u_password);
		users.setU_truename(u_truename);
		users.setU_right(0);
		users.register();
		return "login.jsp";
	}
	@RequestMapping("/UpdateUser")
	public String update(int u_id,int u_right,String u_name, String u_password,String u_truename,HttpServletRequest request) throws ClassNotFoundException, SQLException {
		Users users=new Users();
		users.setU_id(u_id);
		users.setU_right(u_right);
		users.setU_name(u_name);
		users.setU_password(u_password);
		users.setU_truename(u_truename);
		users.update();
		HttpSession session=request.getSession();
		session.setAttribute("users", users);
		return "main.jsp";
	}
	@RequestMapping("/Quit")
	public String quit(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.removeAttribute("users");
		return "login.jsp";
	}
	@RequestMapping("/PageUser")
	public String page(int pageNo, Model model) throws ClassNotFoundException, SQLException {
		int pageSize = 5;
		int totalRow;
		try {
			totalRow = new Users().showall().size();
			int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize : totalRow / pageSize + 1;
			pageNo = pageNo < 1 ? 1 : pageNo;
			pageNo = pageNo > totalPage ? totalPage : pageNo;
			ArrayList<Users> list = new Users().page(pageNo, pageSize);
			model.addAttribute("list", list);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("totalRow", totalRow);
			model.addAttribute("totalPage", totalPage);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "u_showall.jsp";
	}
	@RequestMapping("/DeleteUsers")
	public String deleteusers(int u_id) throws ClassNotFoundException, SQLException {
		Users users=new Users();
		users.setU_id(u_id);
		users.delete();
		return "forward:PageUser?pageNo=1";
	}
	@RequestMapping("/UpdateUsers")
	public String updateusers(int u_id) throws ClassNotFoundException, SQLException {
		Users users=new Users();
		users.setU_id(u_id);
		users.queryId();
		users.setU_right(1);
		users.update();
		return "forward:PageUser?pageNo=1";
	}
}
