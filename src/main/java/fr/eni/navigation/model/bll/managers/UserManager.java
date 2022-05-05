package fr.eni.navigation.model.bll.managers;

import java.util.List;

import fr.eni.navigation.model.bll.bo.User;

public interface UserManager {

	User save(User user);

	String check(User user, String email, String password);

	User update(User userToModify);

	List<User> getAllUsers();

	void deleteById(int id);

	User getUserByEmail(String email);

	User reinitPasswordAndSendMail(String email);

}
