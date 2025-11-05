<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="${pageContext.request.contextPath}/view/style/stylePoste.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/view/style/styleCommentaire.css" rel="stylesheet" />
	</head>
	<body>
		<%@ include file="deconnecte.jsp" %>
		<div class="post-container">
	            <div class="post-header">
	                <div class="post-title">${poste.titel}</div>
	                <div class="post-author">Auteur: ${poste.auteur}</div>
	            </div>
	            <div class="post-content">
	                ${poste.content}
	            </div>
	            <div class="post-dates">
	                <p>Date de création: ${poste.dateCreation}</p>
	                <c:if test="${poste.dateModification != null}">
	                    <p>Dernière modification: ${poste.dateModification}</p>
	                </c:if>
	            </div>
	        </div>
	        <c:forEach var="commentaire" items="${commentaires}">
		        <div class="post-container">
		            <div class="post-header">
		                <div class="post-author">Auteur: ${commentaire.auteur}</div>
		            </div>
		            <div class="post-content">
		                ${commentaire.contene}
		            </div>
		            <div class="post-dates"> 
		                <p>Date de commentaire: ${commentaire.dateCreation}</p>
		            </div>
		        </div>
	        <div class="post-actions">
	            <c:if test="${user.name == commentaire.auteur}">
	                <a class="delete-link" href="${pageContext.request.contextPath}/livreDor?action=deleteCommentaire&idCommentaire=${commentaire.id}&idPoste=${commentaire.idPoste}">Suprrémer Commentaire</a>
	            </c:if>
	        </div>
	    </c:forEach>
	       <form action="${pageContext.request.contextPath}/livreDor" method="post">
			<input type="hidden" name="action" value="addCommentaire" />
			<input type="hidden" name="auteur" value="${user.name}" />
			<div class="commentaire">
				<input type="hidden" name="idPoste" value="${poste.id}" />
				<input type="text" name = "contene"/> <button type="submit">Ajouter</button>			
			</div>
		</form>
	</body>
</html>