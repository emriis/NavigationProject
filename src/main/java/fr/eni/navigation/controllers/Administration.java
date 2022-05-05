package fr.eni.navigation.controllers;

import java.io.IOException;
import java.util.List;

import fr.eni.navigation.model.bll.bo.User;
import fr.eni.navigation.model.bll.managers.ManagerFactory;
import fr.eni.navigation.model.bll.managers.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/connect/admin/administration")
public class Administration extends HttpServlet{
	private UserManager userManager = ManagerFactory.getUserManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> lstUsers =  userManager.getAllUsers();
		req.setAttribute("lstUsers", lstUsers);
		
		this.getServletContext().getRequestDispatcher("/jsp/administration.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.valueOf(req.getParameter("id"));
		User user = (User) req.getSession().getAttribute("user");
		if (id != user.getId()) {
			userManager.deleteById(id);
			List<User> lstUsers =  userManager.getAllUsers();
			req.setAttribute("lstUsers", lstUsers);
		}
		this.getServletContext().getRequestDispatcher("/jsp/administration.jsp").forward(req, resp);
	}
	
	
	
}
