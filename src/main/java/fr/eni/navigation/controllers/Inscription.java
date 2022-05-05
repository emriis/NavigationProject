package fr.eni.navigation.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import fr.eni.navigation.model.bll.bo.User;
import fr.eni.navigation.model.bll.managers.InscriptionManager;
import fr.eni.navigation.model.bll.managers.ManagerFactory;
import fr.eni.navigation.model.bll.managers.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/inscription")
public class Inscription extends HttpServlet {
	private InscriptionManager inscriptionManager = ManagerFactory.getInscriptionManager();
	private UserManager userManager = ManagerFactory.getUserManager();
	private User user = new User();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/jsp/inscription.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setUser(req);
		String passwordConf = req.getParameter("mdpConfirm");
		req.setAttribute("user", user);
		req.setAttribute("passwordConf" ,passwordConf);
		
		Map<String,String> erreurs = inscriptionManager.check(user,passwordConf);
		if (inscriptionManager.check(user,passwordConf).isEmpty()) {
			this.getServletContext().getRequestDispatcher("/jsp/connexion.jsp").forward(req, resp);
			userManager.save(user);
		} else {
			req.setAttribute("erreurs", erreurs);
			this.getServletContext().getRequestDispatcher("/jsp/inscription.jsp").forward(req, resp);
		}
	}

	private void setUser(HttpServletRequest req) {
		user.setNom(req.getParameter("nom"));
		user.setPrenom(req.getParameter("prenom"));
		user.setDateNaissance(LocalDate.parse((String) req.getParameter("dateNaissance")));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("mdp"));
	}
}
