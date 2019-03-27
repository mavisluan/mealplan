<%--
  Created by IntelliJ IDEA.
  User: yiangelov
  Date: 2019-03-19
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
<h1>Login</h1>
<p class="text-danger"><c:out value="${error}" /></p>
<form method="post" action="/login">
    <p class="form-group row">
        <label for="email" class="col-sm-2 col-form-label">Email</label>
        <input type="text" id="email" name="email" class="form-control col-sm-8"/>
    </p>
    <p class="form-group row">
        <label for="password" class="col-sm-2 col-form-label">Password</label>
        <input type="password" id="password" name="password" class="form-control col-sm-8"/>
    </p>
    <input type="submit" value="Login!" class="btn btn-primary offset-9"/>
</form>

</div>
</body>
</html>
