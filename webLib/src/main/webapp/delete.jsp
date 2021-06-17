<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>delete</title>
</head>
<body>
<h1>Введите ID книги которую хотите удалить:</h1><form action="delete" method="post">
    <input type="number" name="id">
   <input type="submit" value = "удалить" name="submit">
   </form>
<c:if test="${book != null}">
    <h2>
            ${book.getTitle()}
        (${book.getGenre().toString()})<br>
        Автор: ${book.getAuthor()}
        <br>---------------------------------<br>
    </h2>
</c:if>
<c:if test="${isBook != null}">
    <h1><c:out value="${isBook}"/></h1>
</c:if>
<br>
<form action="index.jsp">
    <input type="submit" name="submit" value="Главное меню">
</form>
</body>
</html>
