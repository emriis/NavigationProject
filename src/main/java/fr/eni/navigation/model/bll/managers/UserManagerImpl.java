package fr.eni.navigation.model.bll.managers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Random;



import fr.eni.navigation.dal.dao.DAOFactory;
import fr.eni.navigation.dal.dao.UserDAO;
import fr.eni.navigation.model.bll.bo.Encryption;
import fr.eni.navigation.model.bll.bo.User;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public class UserManagerImpl implements UserManager {
	private UserDAO userDAO = DAOFactory.getUserDAO();
	private final int PASSWORD_LENGTH = 20;
	private final String USERNAME_MAILSERVER = "userName_MailServer";
	private final String PASSWORD_MAILSERVER ="password_MailServer";
	private static UserManager instance = null;

	/**
	 * Singleton
	 * @return
	 */
	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManagerImpl();
		}
		return instance;
	}

	private UserManagerImpl() {

	}
/**
 * Chiffre le mot de passe
 * puis
 * Enregistre l'utilisateur en base
 */
	@Override
	public User save(User user) {
		encryptPassword(user);
		user.setDateHeureInscription(LocalDateTime.now());
		userDAO.save(user);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userDAO.getUserByEmail(email);
		return user;
	}

	/**
	 * verifie que l'utilisateur existe en BDD
	 * et que le mot de passe en base et le mot de passe saisi 
	 * correspondent lors de la connexion 
	 */
	@Override
	public String check(User user, String email, String password) {
		String erreur = "";
		try {
			if (isEmailfalse(user, email) || isPasswordfalse(user, password)) {
				erreur = "votre email ou votre mot de passe est incorrect";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return erreur;
	}

	/**
	 * V�rif du mot de passe
	 * @param user
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private boolean isPasswordfalse(User user, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (Encryption.getInstance().validatePassword(password, user.getPassword())) {
			return false;
		}
		return true;
	}
/**
 * verif de l'email
 * @param user
 * @param email
 * @return
 */
	private boolean isEmailfalse(User user, String email) {
		if (user.getEmail().equals(email)) {
			return false;
		}
		return true;
	}

	@Override
	public User update(User userToModify) {
		encryptPassword(userToModify);
		return userDAO.update(userToModify);
	}
/**
 * Chiffre le mot de passe et le set pour l'utilisateur
 * @param userToModify
 */
	private void encryptPassword(User userToModify) {
		String hash;
		try {
			hash = Encryption.getInstance().createHash(userToModify.getPassword());
			userToModify.setPassword(hash.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {

		return userDAO.getAllUsers();
	}

	@Override
	public void deleteById(int id) {
		userDAO.deleteById(id);
	}

	/**
	 * g�n�re un mot de passe al�atoire sur un nombre de caract�re donn�
	 * pour une r�initialisation
	 * @return
	 */
	public String generatePassword() {
		Random random = new Random();
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		String password = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			password += (chars.charAt(random.nextInt(chars.length())));
		}
		return password;
	}

	/**
	 * r�cup�re l'utilisateur dans la base gr�ce � son mail
	 * g�n�re un nouveau mot de passe
	 * lui attribue et lui envoie un mail
	 */
	public User reinitPasswordAndSendMail(String email) {
		User user = getUserByEmail(email);
		String password = generatePassword();
		user.setPassword(password);
		sendEmailWithPassword(user);
		update(user);
		return user;
	}

	
	/**
	 * m�thode pour envoyer le mail
	 * @param user
	 */
	public void sendEmailWithPassword(User user) {
		String username = USERNAME_MAILSERVER;
		String password = PASSWORD_MAILSERVER;

		/* L'adresse IP de votre serveur SMTP */
		String smtpServer = "smtp.gmail.com";
		/* Le port de votre serveur SMTP */
		String smtpPort = "587";
		/* L'adresse de l'exp�diteur */
		String from = "monAdresse@monDomaine.fr";
		/* L'adresse du destinataire */
		String to = user.getEmail();
		/* L'objet du message */
		String objet = "R�initialisation du mot de passe";
		/* Le corps du mail */
		String texte = "Bonjour " + user.getNom() + " " + user.getPrenom()
				+ ". Vous avez demand� � r�initialiser votre mot de passe." + "Voici votre nouveau mot de passe : "
				+ user.getPassword();
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		/*
		 * Session encapsule pour un client donn� sa connexion avec le serveur de mails.
		 */
		Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		/* Cr�ation du message */
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setSubject(objet);
			msg.setText(texte);
			msg.setHeader("X-Mailer", "LOTONtechEmail");
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}