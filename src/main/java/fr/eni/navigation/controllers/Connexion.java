package fr.eni.navigation.controllers;

import java.io.IOException;

import fr.eni.navigation.model.bll.bo.User;
import fr.eni.navigation.model.bll.managers.ManagerFactory;
import fr.eni.navigation.model.bll.managers.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/connexion")
public class Connexion extends HttpServlet {
	private UserManager userManager = ManagerFactory.getUserManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/jsp/connexion.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("mdp");
		User user = userManager.getUserByEmail(email);
		String erreur = userManager.check(user, email, password);

		if (user.getId() > 0 && erreur.isBlank()) {
			req.getSession().setAttribute("user", user);
			this.getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
		} else {
			req.setAttribute("erreur", "Mail ou mot de passe incorrect");
			this.getServletContext().getRequestDispatcher("/jsp/connexion.jsp").forward(req, resp);
		}
	}

}
