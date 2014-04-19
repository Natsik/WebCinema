<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <title>Войти</title>

    <link href="//netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-2.0.1.js" />"></script>
    <script src="<c:url value="/resources/js/utils.js" />"></script>
    <style type="text/css">
        .modal-footer {   border-top: 0px; }
    </style>
</head>

<body>

    <c:if test="${not empty message}">
        <div class="alert alert-dismissable alert-danger">
            <button type="button" class="close" data-dismiss="alert">×</button>
                ${message}
        </div>
    </c:if>
    <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="text-center">Войти</h1>
                </div>
                <div class="modal-body">
                    <form id="login_form" name="login_form" class="form col-md-12 center-block"
                          action="j_spring_security_check" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Логин"
                                   id="j_username" name="j_username">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="Пароль"
                                   id="j_password" name="j_password">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">Войти</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <div class="col-md-12">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>