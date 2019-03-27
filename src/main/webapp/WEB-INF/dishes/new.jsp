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
<div class="container mt-5 col-8">
    <h3 class="mt-3">Search dishes for <span class="text-primary">${paramDay} ${paramMeal}</span></h3>
    <form action="/${paramDay}/${paramMeal}/new" method="post" class="form-inline mt-4">
        <input class="form-control" type="text" name="search" placeholder="ingredient">
        <input type="submit" value="Search" class="btn btn-primary offset-1">
    </form>
    <c:if test="${resultDishes.size() > 0}">
        <div class="d-flex flex-wrap justify-content-around">
            <c:forEach var="dish" items="${resultDishes}">
                <div style="width:200px; height:300px" class="text-center m-4">
                    <form:form action="/${paramDay}/${paramMeal}/add" method="post" modelAttribute="dish" class="mt-5">
                        <form:input path="name" type="hidden" value="${dish.name}"/>
                        <form:input path="url" type="hidden" value="${dish.url}"/>
                        <form:input path="image" type="hidden" value="${dish.image}"/>

                        <c:forEach var="ingredient" items="${dish.ingredientList}" varStatus="status">
                            <input name="ingredientList[${status.index}].name"  type="hidden" value="${ingredient.name}" />
                        </c:forEach>

                        <div class="mb-2"><a href="${dish.url}" target="_blank">${dish.name}</a></div>
                        <img src="${dish.image}" alt="${dish.name}" width="200" height="200">
                        <input type="submit" value="Add" class="btn btn-primary mt-3"/>
                    </form:form>
                </div>

            </c:forEach>
        </div>
    </c:if>

</div>
</body>
</html>
