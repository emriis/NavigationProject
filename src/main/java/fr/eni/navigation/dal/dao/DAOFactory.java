package fr.eni.navigation.dal.dao;

public abstract class DAOFactory {

	public static UserDAO getUserDAO() {

		return UserDAOImpl.getInstance();
	}
}
