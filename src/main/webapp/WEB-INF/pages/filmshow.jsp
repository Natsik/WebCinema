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
    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>
</head>

<body>

<div class="container">
    <p>Цена: ${price}</p>
    <c:forEach items="${tickets}" var="ticket">
        <p>${ticket.rowId}, ${ticket.seat}, ${ticket.isOrdered}</p>
    </c:forEach>
</div>

</body>
</html>