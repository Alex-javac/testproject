<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>which_book</title>
</head>
<body>
<h1>Введите ID книги которую хотите редактировать:</h1>
<form action="update" method="post">
        <input type="number" name="id">
        <input type="submit" name="submit">
    </form>
<c:if test="${isBook != null}">
    <h1><c:out value="${isBook}"/></h1>
</c:if>
</body>
</html>
