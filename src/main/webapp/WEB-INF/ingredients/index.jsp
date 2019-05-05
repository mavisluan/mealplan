<%--
  Created by IntelliJ IDEA.
  User: yiangelov
  Date: 2019-03-27
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shopping List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5 col-4">
    <a href="/plans/${planId}/edit" class="float-right text-white p-3">Back To Plan</a>
    <h5 class="p-3 bg-dark text-white">
        Shopping List
        <a href="/plans" role="button" class="h3 ml-5"><i class="fas fa-tachometer-alt"></i></a>
    </h5>
    <ul class="list-group">
        <c:forEach var="ingredient" items="${ingredients}">
            <li class="list-group-item">${ingredient.name}</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
