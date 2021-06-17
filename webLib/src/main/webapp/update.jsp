<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<h1>Редактирование книги</h1>
<c:if test="${book != null}">
    <h2>
            ${book.getTitle()}
        (${book.getGenre().toString()})<br>
        Автор: ${book.getAuthor()}
        <br>---------------------------------<br>
    </h2>
</c:if>
<form action="update" method="post">
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
    <input type="submit" name="submit" value="Редактировать">
</form>
<br>
<form action="whichBookUpdate.jsp">
    <input type="submit" name="submit" value="Выбрать другую книгу">
</form>
<br>
<form action="index.jsp">
    <input type="submit" name="submit" value="Главное меню">
</form>
</body>
</html>
