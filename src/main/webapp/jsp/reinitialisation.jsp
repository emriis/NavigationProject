<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>NavProject - Mot de passe oublié</title>
<link rel="stylesheet" href="css/main.css" />
</head>

	<body>
		<%@include file="header.jsp" %>
			<main>
				<section>
					<h1>Réinitialisation</h1>
					<form action="reinitialisation" method="post">
						<span class="erreur">${ erreur }</span>
						<span class="reinitialisation">${ reinitialisation }</span>
						<label for="email">e-mail :</label>
						<input id="email" type="email" name="email" value="${user.email }" required />
						<input type="submit" value="Réinitialisez votre mot de passe" />
					</form>
					
	
				</section>
			</main>
			<footer></footer>
	</body>
	

	</html>