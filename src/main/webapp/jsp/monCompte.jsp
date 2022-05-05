<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>NavProject - Mon Compte</title>
	</head>

	<body>
		<%@include file="header.jsp" %>
			<main>
				<section>
				<h1>Bienvenue sur votre compte</h1>
				
				<form action="mon-compte" method="post">
					<label for="prenom">Prénom :</label>
					<input id="prenom" type="text" name="prenom" value="${sessionScope.user.prenom }" maxlength="50"/>
					<span class="erreur" id="spanPrenom"></span> <br> <br>

					<label for="nom">Nom :</label>
					<input id="nom" type="text" name="nom" value="${user.nom }" maxlength="50"/> <br> <br>

					<label for="dateNaissance">Date de naissance :</label>
					<input id="dateNaissance" type="date" name="dateNaissance" value="${user.dateNaissance }" /><span
						class="erreur">${erreurs.age }</span> <br> <br>

					<label for="email">e-mail :</label>
					<input id="email" type="email" name="email" maxlength="100" value="${user.email }"/><span
						class="erreur">${erreurs.email }</span><br> <br>

					<label for="mdp">Password :</label>
					<input id="mdp" type="password" name="mdp" maxlength="20"/>
					<br>
					
					<input type="submit" value="modifier"/>
				</form>
							
				</section>
			</main>
			<footer></footer>
	</body>

	</html>