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
<div class="container mt-5 col-10">
    <h1 class="mt-3">Search dishes by ingredient</h1>
    <form action="/dishes/search" method="post">
        <input type="text" name="search">
        <input type="submit" value="Search">
    </form>
    <c:if test="${resultDishes.size() > 0}">
        <div class="d-flex flex-wrap justify-content-around">
        <c:forEach var="dish" items="${resultDishes}">
            <div style="width:200px; height:300px" class="text-center mt-3">
                <div class="mb-2"><a href="${dish.url}" target="_blank">${dish.name}</a></div>
                <img src="${dish.image}" alt="${dish.name}" width="200" height="200">
                <a href="#" role="button" class="btn btn-primary mt-3">Add</a>
            </div>
        </c:forEach>
        </div>
    </c:if>
</div>
</body>
</html>
