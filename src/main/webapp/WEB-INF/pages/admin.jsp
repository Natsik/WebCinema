<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>
<html>
<body>
    <div class="link">
        <h3>This is resource is protected.</h3>
        <security:authorize access="isAuthenticated()">
            Logged in as user <b><security:authentication property="principal.username" /></b>,
            Click to <a href="<c:url value='/logout' />" class="sign_ic">Logout</a>
        </security:authorize>
    </div>
</body>
</html>
