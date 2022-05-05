package fr.eni.navigation.model.bll.managers;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import fr.eni.navigation.model.bll.bo.User;

public class InscriptionManagerImpl implements InscriptionManager {
	private final Integer MAJORITE = 18;
	private Map<String, String> erreurs = new HashMap<>();
	private static InscriptionManager instance = null;

	/**
	 * Singleton
	 * @return
	 */
	public static InscriptionManager getInstance() {
		if (instance == null) {
			instance = new InscriptionManagerImpl();
		}
		return instance;
	}

	private InscriptionManagerImpl() {

	}
	

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public Integer getMAJORITE() {
		return MAJORITE;
	}

	@Override
	public void checkPassword(User user,String passwordConf) {
		
		if (!user.getPassword().equals(passwordConf)) {
			erreurs.put("password","Les mots de passe saisis sont différents");

		}
		
	}

	@Override
	public void checkEmail(User user) {
		if (!user.getEmail().endsWith(".bzh")) {
			erreurs.put("email","Les emails doivent se finir en .bzh");
		}
	

	}

	@Override
	public void checkAge(User user) {
		
		erreurs.clear(); //réinitialisation des contrôles
		
		if (Period.between(user.getDateNaissance(), LocalDate.now()).getYears() < MAJORITE) {
			erreurs.put("age","Vous devez être majeur pour vous inscrire");
	
		}
		
	}


	@Override
	public Map<String,String> check(User user,String passwordConf) {
		checkAge(user);
		//checkEmail(user);
		checkPassword(user, passwordConf);
		System.out.println(erreurs);
		return erreurs ;
	}

}
