<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>all_books</title>
</head>
<body>

    <c:forEach var="book" items="${books}">
            <h2>
                ${book.getTitle()}
                (${book.getGenre().toString()})<br>
                    Автор: ${book.getAuthor()}
                    <br>---------------------------------<br>
            </h2>
    </c:forEach>
    <form action="index.jsp">
        <input type="submit" name="submit" value="Главное меню">
    </form>
    <form action="showBooks.jsp">
        <input type="submit" name="submit" value="Поиск книг">
    </form>
</body>
</html>
