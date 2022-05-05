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

@WebServlet(urlPatterns = "/reinitialisation")
public class Reinitialisation extends HttpServlet {
	private UserManager userManager = ManagerFactory.getUserManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/jsp/reinitialisation.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		User user = userManager.reinitPasswordAndSendMail(email);
		if (user.getId() > 0) {
			req.setAttribute("reinitialisation", "vous allez bientôt recevoir un mail avec votre nouveau mot de passe");
			this.getServletContext().getRequestDispatcher("/jsp/connexion.jsp").forward(req, resp);
		} else {
			req.setAttribute("erreur", "ce mail n'existe pas");
			this.getServletContext().getRequestDispatcher("/jsp/reinitialisation.jsp").forward(req, resp);
		}
	}

}
