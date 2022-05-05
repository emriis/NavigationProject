<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>NavProject - Inscription</title>
		<link rel="stylesheet" href="css/main.css" />
	</head>

	<body>
		<%@include file="header.jsp" %>
			<main>
				<section>
					<h1>Inscrivez vous</h1>
				</section>
				<form action="inscription" method="post">
					<label for="prenom">Prénom :</label>
					<input id="prenom" type="text" name="prenom" value="${user.prenom }" maxlength="50"
						onkeyup="verifPrenom()" required /><span class="erreur" id="spanPrenom"></span> <br> <br>

					<label for="nom">Nom :</label>
					<input id="nom" type="text" name="nom" value="${user.nom }" maxlength="50" required /> <br> <br>

					<label for="dateNaissance">Date de naissance :</label>
					<input id="dateNaissance" type="date" name="dateNaissance" value="${user.dateNaissance }" /><span
						class="erreur">${erreurs.age }</span> <br> <br>

					<label for="email">e-mail :</label>
					<input id="email" type="email" name="email" maxlength="100" value="${user.email }" required /><span
						class="erreur">${erreurs.email }</span><br> <br>

					<label for="mdp">Password :</label>
					<input id="mdp" type="password" name="mdp" maxlength="20" required />
					<img id="oeil" onmousedown="printPassword()" onmouseup="printPassword()" class="icon"
						alt="logo oeil fermï¿½" src="img/oeilferme.png" title="logo_oeil">
					<span class="erreur">${erreurs.password }</span>
					<br>

					<label for="mdpConf">Confirmer le Password :</label>
					<input id="mdpConf" type="password" name="mdpConfirm" maxlength="20" required />
					<img id="oeilConf" onmousedown="printPasswordConf()" onmouseup="printPasswordConf()" class="icon"
						alt="logo oeil fermï¿½" src="img/oeilferme.png" title="logo_oeil">
					<br> <br>

					<input type="submit" value="S'inscrire" />
				</form>
			</main>
			<footer></footer>
	</body>

	<script src="js/inscription.js"></script>

	</html>