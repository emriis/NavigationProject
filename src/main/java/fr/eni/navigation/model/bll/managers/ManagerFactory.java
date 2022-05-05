package fr.eni.navigation.model.bll.managers;

public abstract class ManagerFactory {
	public static InscriptionManager getInscriptionManager() {
		return InscriptionManagerImpl.getInstance();
	}
	public static UserManager getUserManager() {
		return UserManagerImpl.getInstance();
	}
}
