<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Postes</title>
    <link href="${pageContext.request.contextPath}/view/style/stylePoste.css" rel="stylesheet" />
</head>
<body>
<%@ include file="deconnecte.jsp" %>
    <h1>Liste des Postes</h1>

    <c:forEach var="poste" items="${postes}">
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
        
        <div class="post-actions">
            <c:if test="${user.name == poste.auteur}">
                <a class="edit-link" href="${pageContext.request.contextPath}/livreDor?action=updatePoste&idPoste=${poste.id}">Modifier Poste</a>
                <a class="delete-link" href="${pageContext.request.contextPath}/livreDor?action=deletePoste&idPoste=${poste.id}">Suprrémer Poste</a>
            </c:if>
            <a class="comments-link" href="${pageContext.request.contextPath}/livreDor?action=commentaires&idPoste=${poste.id}">Les commentaires</a>
        </div>
        <br/>
    </c:forEach>

    <div class="add-post-button">
        <button><a href="${pageContext.request.contextPath}/view/form.jsp">Ajouter un Post</a></button>
    </div>
</body>
</html>
