package fr.eni.navigation.model.bll.managers;

import java.util.Map;

import fr.eni.navigation.model.bll.bo.User;

public interface InscriptionManager {
	void checkAge(User user);
	void checkEmail(User user);
	void checkPassword(User user, String passwordConf);
	Map<String, String> check(User user, String passwordConf);
	
}
