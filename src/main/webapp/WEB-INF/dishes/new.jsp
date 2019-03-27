<%--
  Created by IntelliJ IDEA.
  User: yiangelov
  Date: 2019-03-25
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5 col-6">
    <h1 class="text-primary">${paramDay} ${paramMeal}</h1>
    <h1 class="mt-3">Add a dish</h1>
    <h1 class="mt-3">Search dishes by ingredient</h1>
    <form:form action="/${paramDay}/${paramMeal}/new" method="post" modelAttribute="dish" class="mt-5">
        <p class="form-group row">
            <form:label path="name" class="col-sm-2 col-form-label">Name</form:label>
            <form:errors path="name" class="text-danger"/>
            <form:input path="name" class="form-control col-sm-10"/>
        </p>
        <p class="form-group row">
        <form:label path="url" class="col-sm-2 col-form-label">Url</form:label>
        <form:errors path="url" class="text-danger"/>
        <form:input path="url" class="form-control col-sm-10"/>
        </p>
        <p class="form-group row">
            <form:label path="image" class="col-sm-2 col-form-label">Image</form:label>
            <form:errors path="image" class="text-danger"/>
            <form:input path="image" class="form-control col-sm-10"/>
        </p>
        <input type="submit" value="Add To Plan" class="btn btn-primary float-right"/>
    </form:form>
</div>
</body>
</html>
