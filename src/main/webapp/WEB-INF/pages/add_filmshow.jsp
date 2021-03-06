<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Добавить сеанс</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-datetimepicker.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/bootstrap-datetimepicker.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap-datetimepicker.ru.js" />"></script>
    <script src="<c:url value="/resources/js/nod.js" />"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>

    <script>
        function deleteFilm() {
            document.getElementById('deleteShow').submit()
        }
    </script>
</head>

<body>
<% Integer activeTab = 6; %>
<jsp:include page="navbar_header.jsp">
    <jsp:param name="activeTab" value="<%=activeTab%>"/>
</jsp:include>
<div class="container" id="container">
    <div class="page-header">
        <h3>Добавить сеанс</h3>
    </div>
    <div class="row">
        <div class="col-md-6">
            <c:if test="${not empty message}">
                <div class="alert alert-dismissable alert-success">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                        ${message}
                </div>
            </c:if>
            <c:if test="${not empty error_message}">
                <div class="alert alert-dismissable alert-danger">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                        ${error_message}
                </div>
            </c:if>

            <form:form modelAttribute="filmShow" id="filmshow_form">
                <div class="form-group">
                    <form:label path="filmId">Фильм:</form:label>
                    <form:select  class="form-control" path="filmId" id="filmId">
                        <form:option value="NONE" label="--- Выберите фильм ---"/>
                        <form:options items="${filmsList}" />
                    </form:select>
                </div>

                <div class="form-group">
                    <form:label path="hallId">Зал:</form:label>
                    <form:select  class="form-control" path="hallId">
                        <form:option value="NONE" label="--- Выберите зал ---" id="hallId"/>
                        <form:options items="${hallsList}" />
                    </form:select>
                </div>

                <div class="form-group">
                    <form:label path="startTime">Время сеанса:</form:label>
                    <div class="input-group date form_datetime"  data-link-field="startTime">
                        <input class="form-control" size="16" type="text" value="" id="time_input" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                    </div>
                    <form:input path="startTime" value="" type="hidden"/>
                </div>

                <script type="text/javascript">
                    $(".form_datetime").datetimepicker({
                        language:  'ru',
                        format: "dd MM yyyy - hh:ii",
                        startDate: "+0d"
                    });
                </script>

                <div class="form-group">
                    <form:label path="price">Цена:</form:label>
                    <form:input path="price" class="form-control" value="" id="price_input"/>
                </div>

                <button type="submit" class="btn btn-default">Добавить</button>
            </form:form>

            <script>
                var metrics = [
                    ['#filmId', 'not:NONE', ''],
                    ['#hallId', 'not:NONE', ''],
                    ['#time_input', 'presence', ''],
                    ['#price_input', 'float', ''],
                    ['#price_input', 'presence', ''],
                ];
                var options = {
                    'groupSelector': '.form-group',
                    'groupClass': 'has-error',
                    'delay': 300
                };
                $("#filmshow_form").nod(metrics, options);
            </script>
        </div>
        <c:if test="${canDelete}">
            <form:form id="deleteShow" action="/filmshows/delete" method="post" modelAttribute="idForm">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
</div>

</body>
</html>