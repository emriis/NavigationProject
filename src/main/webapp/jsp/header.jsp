<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<header>
	<nav>
		<ul>
			<li><a href="${pageContext.request.contextPath }/index">Accueil</a></li>
			<li><a href="${pageContext.request.contextPath }/inscription">Inscription</a></li>
		<c:if test="${ user == null}">
			<li><a href="${pageContext.request.contextPath }/connexion">Connexion</a></li>		
		</c:if>
			<li><a href="${pageContext.request.contextPath }/connect/mon-compte">Mon compte</a></li>
		<c:if test="${ user != null}">
			<li><a href="${pageContext.request.contextPath }/deconnexion">Déconnexion</a></li>
			<li><a href="${pageContext.request.contextPath }/connect/admin/administration">Administration</a></li>	
		</c:if>	
		</ul>
	</nav>
	<c:if test="${ user != null}">
		<p class="profile">Vous êtes connecté en tant que : ${user.prenom} ${user.nom }</p>
	</c:if>
</header>