<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>NavProject - Connexion</title>
		<link rel="stylesheet" href="css/main.css" />
	</head>

	<body>
		<%@include file="header.jsp" %>
			<main>
				<section>
					<h1>Connexion</h1>
					<form action="connexion" method="post">
						<span class="erreur">${ erreur }</span>
						<label for="email">e-mail :</label>
						<input id="email" type="email" name="email" value="${user.email }" required />
						<br>
						<label for="mdp">Password :</label>
						<input id="mdp" type="password" name="mdp" maxlength="20" required />
						<img id="oeil" onmousedown="printPassword()" onmouseup="printPassword()" class="icon"
							alt="logo oeil fermé" src="img/oeilferme.png" title="logo_oeil">
						<br>
						
						
						
						<input type="submit" value="Connectez-vous" />
					</form>
					
					<a href="${pageContext.request.contextPath }/reinitialisation" >Réinitialiser son mot de passe</a>
	
				</section>
			</main>
			<footer></footer>
	</body>
	<script src="js/connexion.js"></script>

	</html>