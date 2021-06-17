<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>create</title>
</head>
<body>
<h1>Добавить новую книгу</h1>
<form action="create" method="post">
    <b>Автор:</b><br>
        <input type="text" name="author"><br>
    <b>Название:</b><br>
    <input type="text" name="title"><br>
    <b>Жанр:</b><br>
        <input type="radio" name="genre" value="CRIME"> CRIME(Криминальная проза, детектив)<Br>
        <input type="radio" name="genre" value="DETECTIVE"> DETECTIVE(Детектив)<Br>
        <input type="radio" name="genre" value="POST_APOCALYPTIC"> POST_APOCALYPTIC(Постапокалипсис)<Br>
        <input type="radio" name="genre" value="COOKBOOK">COOKBOOK(Кулинарная книга)<Br>
        <input type="radio" name="genre" value="TECHNICAL_WRITING">TECHNICAL_WRITING(Техническая литература)<Br>
        <input type="radio" name="genre" value="DICTIONARY">DICTIONARY(Словарь)<Br>
        <input type="radio" name="genre" value="ENCYCLOPEDIA">ENCYCLOPEDIA(Энциклопедия)<Br>
        <input type="radio" name="genre" value="REFERENCE_BOOK">REFERENCE_BOOK(Справочник)<Br>
        <input type="radio" name="genre" value="BIOGRAPHY">BIOGRAPHY(Биография)<Br>
        <input type="radio" name="genre" value="THRILLER">THRILLER(Триллер)<Br>
        <input type="radio" name="genre" value="HUMOR">HUMOR(Юмористическая проза)<Br>
        <input type="radio" name="genre" value="HISTORICAL">HISTORICAL(Историческая проза)<Br>
        <input type="radio" name="genre" value="FAIRY_TALE">FAIRY_TALE(Сказка)<Br>
        <input type="radio" name="genre" value="CLASSIC">CLASSIC(Классическая литература)<Br>
        <input type="radio" name="genre" value="HORROR">HORROR(Ужасы)<Br>
        <input type="radio" name="genre" value="WESTERN">WESTERN(Вестерн)<Br>
        <input type="radio" name="genre" value="ROMANCE">ROMANCE(Любовный роман)<Br>
        <input type="radio" name="genre" value="FANTASY">FANTASY(Фэнтези)<Br>
        <input type="radio" name="genre" value="SCIENCE">SCIENCE(Научная фантастика)<Br>
    <input type="submit" name="submit">
</form>
<c:if test="${book != null}">
    <h1><c:out value="${book}"/></h1>
</c:if>
</body>
<br>
<form action="index.jsp">
    <input type="submit" name="submit" value="Главное меню">
</form>
</html>
