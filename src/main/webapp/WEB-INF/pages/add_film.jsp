<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>

    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <form:form method="post" action="/films/add" commandName="add_film">
                <div class="form-group">
                    <form:label path="name">Название:</form:label>
                    <form:input path="name" class="form-control"/>
                </div>
                <div class="form-group">
                    <form:label path="description">Описание:</form:label>
                    <form:textarea path="description" class="form-control" rows="12"/>
                </div>
                <div class="form-group">
                    <form:label path="duration">Продолжительность:</form:label>
                    <form:input path="duration" class="form-control" placeholder="HH:MM"/>
                </div>
                <button type="submit" class="btn btn-default">Добавить</button>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>