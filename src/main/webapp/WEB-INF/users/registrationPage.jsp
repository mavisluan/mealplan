<%--
  Created by IntelliJ IDEA.
  User: yiangelov
  Date: 2019-03-19
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="d-inline-block p-2 align-top col-5">
        <h1>Registration</h1>
        <form:form method="POST" action="/" modelAttribute="user" class="mt-5">
            <p class="form-group row">
                <form:label path="name" class="col-sm-3 col-form-label">Name:</form:label>
                    <form:input path="name" class="form-control col-sm-9"/>
            <p class="offset-3"><form:errors path="name" class="text-danger col-sm-5"></form:errors></p>
            </p>
            <p class="form-group row">
                <form:label path="email" class="col-sm-3 col-form-label">Email:</form:label>
                    <form:input type="email" path="email" class="form-control col-sm-9"/>
            <p class="offset-3"><form:errors path="email" class="text-danger col-sm-5"></form:errors></p>
            </p>
            <p class="form-group row">
                <form:label path="password" class="col-sm-3 col-form-label">Password:</form:label>
                <form:password path="password" class="form-control col-sm-9"/>
            <p class="offset-3"><form:errors path="password" class="text-danger col-sm-5"></form:errors></p>

            </p>
            <p class="form-group row">
                <form:label path="passwordConfirmation"
                            class="col-sm-3 col-form-label">Password Confirmation:</form:label>
                <form:password path="passwordConfirmation" class="form-control col-sm-9"/>
            <p class="offset-3"><form:errors path="passwordConfirmation" class="text-danger col-sm-5"></form:errors></p>
            </p>
            <input type="submit" value="Register!" class="btn btn-primary offset-6"/>
        </form:form>
    </div>
    <div class="d-inline-block p-2 align-top offset-2 col-4">
        <h1>Login</h1>
        <p class="text-danger"><c:out value="${error}"/></p>
        <form method="post" action="/login">
            <p class="form-group row">
                <label for="email" class="col-sm-3 col-form-label">Email</label>
                <input type="text" id="email" name="email" class="form-control col-sm-8"/>
            </p>
            <p class="form-group row">
                <label for="password" class="col-sm-3 col-form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control col-sm-8"/>
            </p>
            <input type="submit" value="Login!" class="btn btn-primary offset-9"/>
        </form>
    </div>
</div>
</body>
</html>

