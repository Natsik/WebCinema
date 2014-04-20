<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    <script>
        function submit(id) {
            document.getElementById('buy_ticket_' + id.toString()).submit()
        }
    </script>
</head>

<body>
<% Integer activeTab = 2; %>
<jsp:include page="navbar_header.jsp">
    <jsp:param name="activeTab" value="<%=activeTab%>"/>
</jsp:include>

<div class="container" id="container">
    <p>Фильм: ${requestedFilm.name}</p>
    <p>Время: ${requestedFilmShow.startTime}</p>
    <p>Зал: ${hall.name}</p>
    <p>Цена: ${price}</p>

    <c:forEach items="${tickets}" var="ticket" varStatus="status">
        <c:if test="${status.count == 0}">
            Ряд ${rows[0].number}:
        </c:if>
        <c:if test="${(status.count - 1) % rowLength == 0}">
            Ряд ${rows[(status.count - 1) / rowLength].number}:
        </c:if>

        <c:choose>
            <c:when test="${!ticket.isOrdered}">
                <form name="buy_ticket_${ticket.id}" id="buy_ticket_${ticket.id}" method="POST" action="/buy_ticket" style="display: inline;">
                    <input type="hidden" name="id" value="${ticket.id}">
                    <a href="javascript:submit(${ticket.id})" class="btn btn-default btn-sm" role="button">${ticket.seat}</a>
                </form>
            </c:when>
            <c:otherwise>
                <a href="#" class="btn btn-danger btn-sm" role="button">${ticket.seat}</a>
            </c:otherwise>
        </c:choose>

        <c:if test="${status.count % rowLength == 0}">
            <br/>
        </c:if>
    </c:forEach>
</div>

</body>
</html>