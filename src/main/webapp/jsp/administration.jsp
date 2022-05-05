<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administration</title>
<link rel="stylesheet" href="css/main.css" />
</head>
<body>
	<%@include file="header.jsp"%>
	<main>
		<h1>Vous êtes sur la page d'administration</h1>

		<table border="1">
			<thead>
				<tr>
					<th>UserID</th>
					<th>User surname</th>
					<th>User name</th>
					<th>User birth date</th>
					<th>User email</th>
					<th>User password</th>
					<th>User register date</th>
					<th>User role</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lstUsers }" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.nom}</td>
						<td>${user.prenom}</td>
						<td>${user.dateNaissance}</td>
						<td>${user.email}</td>
						<td>${user.password}</td>
						<td>${user.dateHeureInscription}</td>
						<td>${user.role}</td>
						<td>
							<form action="administration" method="post">
								<input type="hidden" name="id" value="${user.id }" /> <input
									type="submit" value="delete user" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</main>
	<footer></footer>
</body>
</html>