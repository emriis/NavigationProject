package fr.eni.navigation.dal.dao;

import java.util.List;

import fr.eni.navigation.model.bll.bo.User;

public interface UserDAO {

	User save(User user);

	User update(User userToModify);

	List<User> getAllUsers();

	void deleteById(int id);

	User getUserByEmail(String email);

}
