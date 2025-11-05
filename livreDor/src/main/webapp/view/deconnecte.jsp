<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${user == null }">
	<c:redirect url="http://localhost:8080/Livre_Dor/livreDor"/>
</c:if>
    <div>
    	<a href="${pageContext.request.contextPath}/livreDor?action=deconnect">Déconnect</a>
    </div>