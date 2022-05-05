package fr.eni.navigation.model.bll.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
	private Integer id;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String email;
	private String password;
	private LocalDateTime dateHeureInscription;
	private Role role;


	public User() {
		this.id = 0;
	}

	public User(String nom, String prenom, LocalDate dateNaissance, String email, String password) {
		this(null,nom,prenom,dateNaissance,email,password,null,null);
	}
	

	public User(Integer id, String nom, String prenom, LocalDate dateNaissance, String email, String password, LocalDateTime dateHeureInscription, Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.password = password;
		this.dateHeureInscription = dateHeureInscription;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getDateHeureInscription() {
		return dateHeureInscription;
	}

	public void setDateHeureInscription(LocalDateTime dateInscription) {
		this.dateHeureInscription = dateInscription;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", email=" + email + ", password=" + password + ", dateInscription=" + dateHeureInscription + "]";
	}

}
