<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Сеансы</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>
    <script src="<c:url value="/resources/js/date_formatter.js" />"></script>

    <script>
        function next() {
            var form = document.getElementById('day_form');
            var day_input = document.getElementById('day');
            day_input.value = ${curDayOffset} + 1;
            form.submit();
        }
        function previous() {
            var form = document.getElementById('day_form');
            var day_input = document.getElementById('day');
            day_input.value = ${curDayOffset} - 1;
            form.submit();
        }
        function day(day_offset) {
            var form = document.getElementById('day_form');
            var day_input = document.getElementById('day');
            day_input.value = day_offset;
            form.submit();
        }
        function printDay(day_offset) {
            var d = new Date(new Date().getTime() + day_offset * (24 * 60 * 60 * 1000));
            var a = document.getElementById("a_" + day_offset.toString());
            a.innerHTML = d.format("ddd, d MMM");
        }
        function deleteShow(id) {
            var id_input = document.getElementById('id');
            id_input.value = id;
            document.getElementById('deleteShow').submit();
        }
    </script>
</head>
<body>
    <% Integer activeTab = 0; %>
    <jsp:include page="navbar_header.jsp">
        <jsp:param name="activeTab" value="<%=activeTab%>"/>
    </jsp:include>

    <div class="container" id="container">
        <div class="row">
            <div class="col-md-12">

                <h3>Сеансы</h3>
                <security:authorize access="isAuthenticated()">
                    <a href="/filmshows/add" class="btn btn-default" role="button">Добавить сеанс</a>
                </security:authorize>
                <br/>
                <ul class="pagination">
                    <li <c:if test="${curDayOffset == 0}"> class="disabled" </c:if>><a href="#" onclick="javascript:previous();">&laquo;</a></li>
                    <li <c:if test="${curDayOffset == 0}"> class="active" </c:if>><a href="#" onclick="javascript:day(0);">Сегодня</a></li>
                    <li <c:if test="${curDayOffset == 1}"> class="active" </c:if>><a href="#" onclick="javascript:day(1);">Завтра</a></li>
                    <li <c:if test="${curDayOffset == 2}"> class="active" </c:if>><a href="#" id="a_2" onclick="javascript:day(2);"></a></li>
                    <li <c:if test="${curDayOffset == 3}"> class="active" </c:if>><a href="#" id="a_3" onclick="javascript:day(3);"></a></li>
                    <li <c:if test="${curDayOffset == 4}"> class="active" </c:if>><a href="#" id="a_4" onclick="javascript:day(4);"></a></li>
                    <li <c:if test="${curDayOffset == 5}"> class="active" </c:if>><a href="#" id="a_5" onclick="javascript:day(5);"></a></li>
                    <li <c:if test="${curDayOffset == 6}"> class="active" </c:if>><a href="#" id="a_6" onclick="javascript:day(6);"></a></li>
                    <li <c:if test="${curDayOffset == 6}"> class="disabled" </c:if>><a href="#" onclick="javascript:next();">&raquo;</a></li>
                </ul>
                <script>
                    for (var i = 2; i <= 6; i++) {
                        printDay(i);
                    }
                </script>
                <form action="/" method="post" id="day_form" name="day_form">
                    <input type="hidden" id="day" name="day" value=""/>
                </form>

                <c:if test="${!empty showsInfoList}">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Время начала</th>
                            <th>Фильм</th>
                            <th>Зал</th>
                            <th>Продолжительность</th>
                            <th>Стоимость</th>
                            <security:authorize access="isAuthenticated()">
                                <th>Удалить</th>
                            </security:authorize>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${showsInfoList}" var="showInfo">
                            <tr>
                                <td><a href="/filmshows/${showInfo.showId}">${showInfo.showStartTime}</a></td>
                                <td><a href="/films/${showInfo.filmId}">${showInfo.filmName}</a></td>
                                <td>${showInfo.hallName}</td>
                                <td class="convertToHHMM">${showInfo.filmDuration}</td>
                                <td class="convertToHHMM">${showInfo.price}</td>
                                <security:authorize access="isAuthenticated()">
                                    <td><button type="button" class="btn btn-danger" onclick="deleteShow(${showInfo.showId});">Удалить</button></td>
                                </security:authorize>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <security:authorize access="isAuthenticated()">
                        <form:form id="deleteShow" action="/filmshows/delete" method="post" modelAttribute="idForm">
                            <form:hidden path="id" />
                        </form:form>
                    </security:authorize>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
