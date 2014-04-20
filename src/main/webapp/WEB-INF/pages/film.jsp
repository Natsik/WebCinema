<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${requestedFilm.name}</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>
</head>

<body>
<% Integer activeTab = 3; %>
<jsp:include page="navbar_header.jsp">
    <jsp:param name="activeTab" value="<%=activeTab%>"/>
</jsp:include>
<div class="container" id="container">
    <div class="row">
        <div class="col-md-12">
            <h3>${requestedFilm.name}</h3>

            <p>${requestedFilm.description}</p>
            <p>Продолжительность: <span class="convertToHHMM">${requestedFilm.duration}</span></p>

            <c:forEach items="${shows}" var="show">
                <p><a href="/filmshow/${show[0]}">${show[1]}, ${show[2]}</a></p>
            </c:forEach>

        </div>
    </div>
</div>

</body>
</html>