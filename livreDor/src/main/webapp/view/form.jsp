<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter ou Modifier un Poste</title>
    <link href="${pageContext.request.contextPath}/view/style/styleForm.css" rel="stylesheet" />
</head>
<body>
<%@ include file="deconnecte.jsp" %>
    <div class="form-container">
        <h2>
            <c:choose>
                <c:when test="${poste != null}">Modifier un Poste</c:when>
                <c:otherwise>Ajouter un Poste</c:otherwise>
            </c:choose>
        </h2>

        <form action="${pageContext.request.contextPath}/livreDor" method="post">
            <input type="hidden" name="action" value="addPoste" />
            <input type="hidden" name="idPoste" value="${poste != null ? poste.id : ''}" />
            <input type="hidden" id="auteur" name="auteur" value="${user.name}">
            
            <label for="auteur">${user.name}</label>
            
            <label for="titel">Titre:</label>
            <input type="text" id="titel" name="titel" value="${poste != null ? poste.titel : ''}" required>
            
            <label for="content">Contenu:</label>
            <textarea id="content" name="content" rows="4" required>${poste != null ? poste.content : ''}</textarea>
            
            <button type="submit">${poste != null ? 'Mettre à jour le poste' : 'Ajouter le poste'}</button>
        </form>
    </div>
</body>
</html>
