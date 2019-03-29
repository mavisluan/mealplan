<%--
  Created by IntelliJ IDEA.
  User: yiangelov
  Date: 2019-03-25
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div class="container mt-5 col-8">
    <div class="mb-5">
        <span class="h2">Welcome, ${user.name}!</span>
        <a href="/logout" role="button" class="float-right"><span class="h4">Logout</span></a>
    </div>
    <%--<a href="/menus/new" role="button" class="btn btn-primary float-right mr-3">Post My Menu</a>--%>
    <div class="h3">My Plans</div>
    <table class="table mt-3">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Plan</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="plan" items="${plans}">
            <tr>
                <td>${plan.name}</td>
                <td>
                    <a href="/plans/${plan.id}/add" role="button" class="h4 ml-3"><i class="fas fa-edit"></i></a>
                    <a href="/plans/${plan.id}/delete" role="button" class="h4 ml-3"><i class="fas fa-trash-alt"></i></a>
                    <a href="/plans/${plan.id}/share" role="button" class="h4 ml-3"><i class="fas fa-share-square"></i></a>
                    <a href="/plans/${planId}/shoppinglist" role="button" class="h4 ml-3"><i class="fas fa-shopping-cart"></i></a>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form:form method="POST" action="/plans" modelAttribute="plan" class="form-inline mt-5">
        <form:label path="name"><span class="h3 mr-4">New Plan</span></form:label>
        <form:input path="name" class="form-control mb-2 mr-4 col-4"/>
        <form:errors path="name" class="text-danger mb-2 mr-3"></form:errors>
        <form:input type="hidden" path="user" value="${userId}"/>
        <button type="submit" class="btn btn-primary mb-2">Create</button>
    </form:form>
    <div class="h3 mt-5">Communtiy Board</div>
    <ul class="list-group">
        <li class="list-group-item border-0">
            <a href="#">User1's weekly plan</a>
            <a href="#" class="btn btn-dark offset-5">Add to my plan</a>
        </li>
        <li class="list-group-item border-0">
            <a href="#">User2's weekly plan</a>
            <a href="#" class="btn btn-dark offset-5">Add to my plan</a>
        </li>
        <li class="list-group-item border-0">
            <a href="#">User3's weekly plan</a>
            <a href="#" class="btn btn-dark offset-5">Add to my plan</a>
        </li>
    </ul>
</div>
<%--<div class="h3 mt-5">Post My Menu</div>--%>
<%--<form:form method="POST" action="/registration" modelAttribute="menu" class="mt-5">--%>
<%--<p class="form-group row">--%>
<%--<form:label path="name" class="col-sm-3 col-form-label">Name</form:label>--%>
<%--<form:input path="name" class="form-control col-sm-7"/>--%>
<%--<form:errors path="name" class="text-danger col-sm-9 offset-3"></form:errors>--%>
<%--</p>--%>
<%--<p class="form-group row">--%>
<%--<form:label path="tag" class="col-sm-2 col-form-label">Recipe</form:label>--%>
<%--<form:errors path="tag" class="text-danger"/>--%>
<%--<form:errors path="tag" class="text-danger col-sm-9 offset-3"></form:errors>--%>
<%--</p>--%>
<%--<p class="form-group row">--%>
<%--<form:label path="ingredients" class="col-sm-3 col-form-label">Ingredients</form:label>--%>
<%--<form:input path="ingredients" class="form-control col-sm-5"/>--%>
<%--<form:errors path="ingredients" class="text-danger col-sm-9 offset-3"></form:errors>--%>
<%--</p>--%>
<%--<p class="form-group row">--%>
<%--<form:label path="recipe" class="col-sm-2 col-form-label">Recipe</form:label>--%>
<%--<form:input path="recipe" class="form-control col-sm-5"/>--%>
<%--<form:errors path="recipe" class="text-danger col-sm-9 offset-3"></form:errors>--%>
<%--</p>--%>
<%--<input type="submit" value="Post" class="btn btn-primary offset-6"/>--%>
<%--</form:form>--%>
</body>
</html>
