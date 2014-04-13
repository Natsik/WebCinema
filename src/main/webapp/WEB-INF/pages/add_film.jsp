<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Добавить фильм</title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">

    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>
    <script src="<c:url value="/resources/js/nod.js" />"></script>

    <script>
        var metrics = [
            [ '#name_input', 'presence', 'Cannot be empty' ]
        ];
        $("#film_form").nod(metrics);
    </script>
</head>

<body>

<div class="container">

    <div class="page-header">
        <h3>Добавить фильм</h3>
    </div>

    <div class="row">
        <div class="col-md-6">
            <c:if test="${not empty message}">
                <div class="alert alert-dismissable alert-success">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                        ${message}
                </div>
            </c:if>

            <form:form modelAttribute="film" id="film_form">
                <div class="form-group">
                    <form:label path="name">Название:</form:label>
                    <form:input path="name" class="form-control" value="" id="name" type="text"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Описание:</form:label>
                    <form:textarea path="description" class="form-control" rows="12" value="" id="description" type="text"/>
                </div>
                <div class="form-group">
                    <form:label path="duration">Продолжительность:</form:label>
                    <form:input path="duration" class="form-control" placeholder="HH:MM" value="" id="duration"/>
                </div>
                <button type="submit" class="btn btn-default">Добавить</button>
            </form:form>

            <script>
                var metrics = [
                    ['#name', 'presence', ''],
                    ['#description', 'presence', ''],
                    ['#duration', /[0-1]?[0-9]:[0-5][0-9]/, 'Введите время в формате HH:MM']
                ];
                var options = {
                    'groupSelector': '.form-group',
                    'groupClass': 'has-error',
                    'delay': 300
                };
                $("#film_form").nod(metrics, options);
            </script>
        </div>
    </div>
</div>

</body>
</html>