<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link href="${pageContext.request.contextPath}/view/style/styleLogin.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/view/style/style.css" rel="stylesheet" />
</head>
<body>

	<c:if test="${user != null }">
		<c:redirect url="http://localhost:8080/Livre_Dor/livreDor?action=poste"/>
	</c:if>
    <form action="${pageContext.request.contextPath}/livreDor">
        <input type="hidden" name="action" value="login">
        
        <label for="name">Nom:</label><br/>
        <input type="text" id="name" name="name"/><br/>
        
        <label for="pwd">Mot de passe:</label><br/>
        <input type="password" id="pwd" name="pwd"/><br/>
        
        <label for="isExiste">Reconnecter:</label>
        <input type="checkbox" checked="checked" id="isExiste" name="isExiste" value="true"/><br/>
        
        <p class="message">${message}</p>
        <input type="submit" value="Connexion"/>
    </form>
</body>
</html>
