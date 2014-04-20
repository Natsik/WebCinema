<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Фильмы</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">

    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>
</head>

<body>
<% Integer activeTab = 1; %>
<jsp:include page="navbar_header.jsp">
    <jsp:param name="activeTab" value="<%=activeTab%>"/>
</jsp:include>


<div class="container" id="container">
    <div class="row">
        <div class="col-md-12">
            <h3>Фильмы</h3>
            <security:authorize access="isAuthenticated()">
                <td><a href="/films/add" class="btn btn-default" role="button">Добавить фильм</a></td>
            </security:authorize>
            <br/>
            <c:if test="${!empty filmsList}">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th style="width:10em;">Название</th>
                        <th>Описание</th>
                        <th>Продолжительность</th>
                        <security:authorize access="isAuthenticated()">
                            <th>Изменить детали</th>
                        </security:authorize>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${filmsList}" var="film">
                        <tr>
                            <td style="width:10em;"><a href="/films/${film.id}">${film.name}</a></td>
                            <td>${film.description}</td>
                            <td class="convertToHHMM">${film.duration}</td>
                            <security:authorize access="isAuthenticated()">
                                <td><a href="/films/modify/${film.id}" class="btn btn-default" role="button">Изменить</a></td>
                            </security:authorize>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>