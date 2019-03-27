<%--
  Created by IntelliJ IDEA.
  User: yiangelov
  Date: 2019-03-25
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <a href="/plans/shoppinglist" class="btn btn-primary float-right" role="button">Shopping List</a>

    <div class="h3 mb-4">Create a Week Plan</div>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col"></th>
            <th scope="col">Breakfast</th>
            <th scope="col">Lunch</th>
            <th scope="col">Dinner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${days}" var="day">
            <tr>
                <th scope="row"><c:out value="${day.name}"/></th>
                <c:choose>
                    <c:when test="${day.dishes.size() == 0}">
                        <td><a href="/${day.name}/breakfast/new"><i class="fas fa-utensils h3"></i></a></td>
                        <td><a href="/${day.name}/lunch/new"><i class="fas fa-utensils h3"></i></a></td>
                        <td><a href="/${day.name}/dinner/new"><i class="fas fa-utensils h3"></i></a></td>
                    </c:when>
                    <c:when test="${day.dishes.size() == 1}">
                        <td>
                            <a href="${day.dishes.get(0).url}">${day.dishes.get(0).name}</a>
                            <a href="/dishes/${day.dishes.get(0).id}/delete"><i class="fas fa-trash-alt"></i></a>
                        </td>
                        <td><a href="/${day.name}/lunch/new"><i class="fas fa-utensils h3"></i></a></td>
                        <td><a href="/${day.name}/dinner/new"><i class="fas fa-utensils h3"></i></a></td>
                    </c:when>
                    <c:when test="${day.dishes.size() == 2}">
                        <td>
                            <a href="${day.dishes.get(0).url}">${day.dishes.get(0).name}</a>
                            <a href="/dishes/${day.dishes.get(0).id}/delete"><i class="fas fa-trash-alt"></i></a>
                        </td>
                        <td>
                            <a href="${day.dishes.get(1).url}" >${day.dishes.get(1).name}</a>
                            <a href="/dishes/${day.dishes.get(1).id}/delete"><i class="fas fa-trash-alt"></i></a>
                        </td>
                        <td><a href="/${day.name}/dinner/new"><i class="fas fa-utensils h3"></i></a></td>
                    </c:when>
                    <c:when test="${day.dishes.size() == 3}">
                        <td>
                            <a href="${day.dishes.get(0).url}">${day.dishes.get(0).name}</a>
                            <a href="/dishes/${day.dishes.get(0).id}/delete"><i class="fas fa-trash-alt"></i></a>
                        </td>
                        <td>
                            <a href="${day.dishes.get(1).url}" >${day.dishes.get(1).name}</a>
                            <a href="/dishes/${day.dishes.get(1).id}/delete"><i class="fas fa-trash-alt"></i></a>
                        </td>
                        <td>
                            <a href="${day.dishes.get(2).url}" >${day.dishes.get(2).name}</a>
                            <a href="/dishes/${day.dishes.get(2).id}/delete"><i class="fas fa-trash-alt"></i></a>
                        </td>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
