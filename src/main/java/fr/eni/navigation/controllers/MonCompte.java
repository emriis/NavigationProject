package fr.eni.navigation.controllers;

import java.io.IOException;
import java.time.LocalDate;

import fr.eni.navigation.model.bll.bo.User;
import fr.eni.navigation.model.bll.managers.ManagerFactory;
import fr.eni.navigation.model.bll.managers.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/connect/mon-compte")
public class MonCompte extends HttpServlet {

	private UserManager userManager = ManagerFactory.getUserManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/jsp/monCompte.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		LocalDate dateNaissance = (LocalDate.parse((String) req.getParameter("dateNaissance")));
		String email = req.getParameter("email");
		String password = req.getParameter("mdp");

		User userToModify = new User(nom, prenom, dateNaissance, email, password);
		User connectedUser = (User) req.getSession().getAttribute("user");

		userToModify.setId(connectedUser.getId());
		User modifiedUser = userManager.update(userToModify);

		req.getSession().setAttribute("user", modifiedUser);

		this.getServletContext().getRequestDispatcher("/jsp/monCompte.jsp").forward(req, resp);

	}

}
