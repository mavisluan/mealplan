<%--
  Created by IntelliJ IDEA.
  User: yiangelov
  Date: 2019-03-25
  Time: 10:58
  To change divis template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <a href="/plans/shoppinglist" class="btn btn-primary float-right" role="button">Shopping List</a>
    <a href="/plans" class="btn btn-primary float-right mr-3" role="button">Back to Plans</a>

    <div class="h3 mb-4">Create a Week Plan</div>
    <div class="container p-4">
        <div class="row h5 bg-dark text-white pt-3 pb-3">
            <div class="col"></div>
            <div class="col">Breakfast</div>
            <div class="col">Lunch</div>
            <div class="col">Dinner</div>
        </div>
        <c:forEach items="${days}" var="day">
            <div class="row mt-3">
                <div class="col-3 h5"><c:out value="${day.name}"/></div>
                <div class="col-3">
                    <div class="container">
                        <p><a href="${day.dishes.get(0).url}">${day.dishes.get(0).name}</a></p>
                        <c:choose>
                            <c:when test="${day.dishes.get(0).name == null}">
                                <a href="/${day.name}/breakfast/new"><i class="fas fa-utensils h3"></i></a>
                            </c:when>
                            <c:otherwise>
                                <img src="${day.dishes.get(0).image}" width="100">
                                <a href="/dishes/${day.dishes.get(0).id}/delete" class="ml-3"><i class="fas fa-trash-alt"></i></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col-3">
                    <div class="container">
                    <p><a href="${day.dishes.get(1).url}">${day.dishes.get(1).name}</a></p>
                    <c:choose>
                        <c:when test="${day.dishes.get(1).name == null}">
                            <a href="/${day.name}/lunch/new"><i class="fas fa-utensils h3"></i></a>
                        </c:when>
                        <c:otherwise>
                            <img src="${day.dishes.get(1).image}" width="100">
                            <a href="/dishes/${day.dishes.get(1).id}/delete" class="ml-3"><i class="fas fa-trash-alt"></i></a>
                        </c:otherwise>
                    </c:choose>
                    </div>
                </div>
                <div class="col-3">
                    <div class="container">
                    <p><a href="${day.dishes.get(2).url}">${day.dishes.get(2).name}</a></p>
                    <c:choose>
                        <c:when test="${day.dishes.get(2).name == null}">
                            <a href="/${day.name}/dinner/new"><i class="fas fa-utensils h3"></i></a>
                        </c:when>
                        <c:otherwise>
                            <img src="${day.dishes.get(2).image}" width="100">
                            <a href="/dishes/${day.dishes.get(2).id}/delete" class="ml-3"><i class="fas fa-trash-alt"></i></a>
                        </c:otherwise>
                    </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
