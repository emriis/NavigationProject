package fr.eni.navigation.dal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.navigation.model.bll.bo.Role;
import fr.eni.navigation.model.bll.bo.User;
import fr.eni.navigation.model.bll.managers.UserManager;
import fr.eni.navigation.model.bll.managers.UserManagerImpl;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
	private static UserDAO instance = null;

	public static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}

	private UserDAOImpl() {

	}
	
	@Override
	public User save(User user) {
		try (Connection cnx = PoolConnection.getConnection(database, userBDD, passwordBDD);) {

			final String QUERY = "INSERT INTO siteUser (nom,prenom,datenaissance,email,password,dateheureinscription) VALUES (?,?,?,?,?,?);";
			PreparedStatement pstmt = cnx.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, user.getNom());
			pstmt.setString(2, user.getPrenom());
			pstmt.setDate(3, Date.valueOf(user.getDateNaissance()));
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword());
			pstmt.setTimestamp(6, Timestamp.valueOf(user.getDateHeureInscription()));

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));

		} catch (Exception e) {
			e.printStackTrace();

		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = new User();
		try (Connection cnx = PoolConnection.getConnection(database, userBDD, passwordBDD);) {

			final String QUERY = "SELECT * FROM siteUser JOIN Role ON siteUser.Role_id = Role.Role_id WHERE email = ?;";
			PreparedStatement pstmt = cnx.prepareStatement(QUERY);
			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setDateHeureInscription(rs.getTimestamp("dateHeureInscription").toLocalDateTime());
				user.setRole(Role.valueOf(rs.getString("Role").toUpperCase()));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		if (user.getId() != null) {
			return user;
		} else {
			return new User();
		}

	}

	@Override
	public User update(User userToModify) {
		try (Connection cnx = PoolConnection.getConnection(database, userBDD, passwordBDD);) {

			final String QUERY = "UPDATE siteUser SET nom = ?,prenom = ?,dateNaissance = ?,email = ?, password = ? WHERE id = ?;";
			PreparedStatement pstmt = cnx.prepareStatement(QUERY);

			pstmt.setString(1, userToModify.getNom());
			pstmt.setString(2, userToModify.getPrenom());
			pstmt.setDate(3, Date.valueOf(userToModify.getDateNaissance()));
			pstmt.setString(4, userToModify.getEmail());
			pstmt.setString(5, userToModify.getPassword());
			pstmt.setInt(6, userToModify.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return userToModify;
	}

	@Override
	public List<User> getAllUsers() {
		User user = new User();
		List<User> lstUsers = new ArrayList<>();
		try (Connection cnx = PoolConnection.getConnection(database, userBDD, passwordBDD);) {

			final String QUERY = "SELECT * FROM siteUser JOIN Role ON siteUser.Role_id = Role.Role_id";
			PreparedStatement pstmt = cnx.prepareStatement(QUERY);

			ResultSet rs = pstmt.executeQuery();
			System.out.println();
			while (rs.next()) {
				lstUsers.add(new User(
						rs.getInt("id"), 
						rs.getString("nom"), 
						rs.getString("prenom"),
						rs.getDate("dateNaissance").toLocalDate(), 
						rs.getString("email"), 
						rs.getString("password"),
						rs.getTimestamp("dateHeureInscription").toLocalDateTime(),
						Role.valueOf(rs.getString("Role").toUpperCase())));
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lstUsers;
	}

	@Override
	public void deleteById(int id) {
		try (Connection cnx = PoolConnection.getConnection(database, userBDD, passwordBDD);) {

			final String QUERY = "DELETE FROM siteUser WHERE id = ?;";
			PreparedStatement pstmt = cnx.prepareStatement(QUERY);

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (

		Exception e) {
			e.printStackTrace();

		}
	}
}
