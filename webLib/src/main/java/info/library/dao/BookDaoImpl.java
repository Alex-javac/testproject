package info.library.dao;

import info.library.model.books.Book;
import info.library.model.books.Genre;
import info.library.util.DataSourceUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Таблицы в БД
 *
 * ---------------------------таблица Автор--------------------------------------------------------------------------
 * CREATE TABLE author
 * ( Id INT AUTO_INCREMENT PRIMARY KEY,
 * author varchar(100) UNIQUE CHECK(author !=''));
 *
 * ----------------------------таблица жанры-------------------------------------------------------------------------
 *CREATE TABLE genre
 * (
 * Id INT AUTO_INCREMENT PRIMARY KEY,
 * genre ENUM('CRIME',
 * 'DETECTIVE',
 * 'SCIENCE',
 * 'POST_APOCALYPTIC',
 * 'FANTASY',
 * 'ROMANCE',
 * 'WESTERN',
 * 'HORROR',
 * 'CLASSIC',
 * 'FAIRY_TALE',
 * 'HISTORICAL',
 * 'HUMOR',
 * 'THRILLER',
 * 'BIOGRAPHY',
 * 'REFERENCE_BOOK',
 * 'ENCYCLOPEDIA',
 * 'DICTIONARY',
 * 'TECHNICAL_WRITING',
 * 'COOKBOOK') NOT NULL);
 *
 *----------------------------таблица книги-------------------------------------------------------------------------
 *CREATE TABLE books
 * (Id INT AUTO_INCREMENT PRIMARY KEY,
 * genreID int not null,
 * title varchar(100) CHECK(title !=''),
 * authorId int not null,
 * FOREIGN KEY (genreID) REFERENCES genre(Id) ON DELETE CASCADE,
 * FOREIGN KEY (authorId) REFERENCES author(Id) ON DELETE CASCADE);
 *
 *
 */

public class BookDaoImpl implements BookDao {
    private static DataSourceUtil dataSourceUtil = new DataSourceUtil();

    @Override
    public Book getBookByID(int id) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        PreparedStatement statement = connection.
                prepareStatement("select books.id, books.title, genre.genre, author.author " +
                        "from books, genre, author " +
                        "where books.genreID = genre.Id " +
                        "and books.authorId = author.Id " +
                        "and books.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        List<Book> list = createModelBooks(resultSet, connection);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        PreparedStatement statement = connection.
                prepareStatement("select books.id, books.title, genre.genre, author.author " +
                        "from books, genre, author " +
                        "where books.genreID = genre.Id " +
                        "and books.authorId = author.Id " +
                        "and genre.genre = ?");
        statement.setString(1, String.valueOf(genre));
        ResultSet resultSet = statement.executeQuery();
        return createModelBooks(resultSet, connection);
    }

    @Override
    public List<Book> getBooksByTitle(String title) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        PreparedStatement statement = connection.
                prepareStatement("select books.id, books.title, genre.genre, author.author " +
                        "from books, genre, author " +
                        "where books.genreID = genre.Id " +
                        "and books.authorId = author.Id " +
                        "and books.title = ?");
        statement.setString(1, title);
        ResultSet resultSet = statement.executeQuery();
        return createModelBooks(resultSet, connection);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        PreparedStatement statement = connection.
                prepareStatement("select books.id, books.title, genre.genre, author.author " +
                        "from books, genre, author " +
                        "where books.genreID = genre.Id " +
                        "and books.authorId = author.Id " +
                        "and author.author = ?");
        statement.setString(1, author);
        ResultSet resultSet = statement.executeQuery();
        return createModelBooks(resultSet, connection);

    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select books.id, books.title, genre.genre, author.author " +
                "from books, genre, author " +
                "where books.genreID = genre.Id and books.authorId = author.Id");

        return createModelBooks(resultSet, connection);
    }

    @Override
    public boolean delete(Book book) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id = ?");
        ps.setInt(1, book.getId());
        int rowsAffected = ps.executeUpdate();
        dataSourceUtil.closeConnect(connection);
        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Book book) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        PreparedStatement ps1 = connection.prepareStatement("INSERT IGNORE author(author) values (?)");
        ps1.setString(1, book.getAuthor());
        ps1.executeUpdate();
        PreparedStatement ps2 = connection.prepareStatement("update books set title = ?, " +
                "genreId =(select id from genre where genre =  ?) , " +
                "authorId = (select id from author where author = ?) " +
                "where id = ?");
        ps2.setString(1, book.getTitle());
        ps2.setString(2, String.valueOf(book.getGenre()));
        ps2.setString(3, book.getAuthor());
        ps2.setInt(4, book.getId());
        int rowsAffected = ps2.executeUpdate();
        dataSourceUtil.closeConnect(connection);
        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Book book) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        int rowsAffected = 0;
        if (checkNotExistence(book)) {
            PreparedStatement ps1 = connection.prepareStatement("INSERT IGNORE author(author) values (?)");
            ps1.setString(1, book.getAuthor());
            ps1.executeUpdate();
            PreparedStatement ps2 = connection.prepareStatement("insert books(genreID, title, authorID) " +
                    "values ((Select Id from genre where genre= ?), ? , (Select Id from author where author = ?))");
            ps2.setString(1, String.valueOf(book.getGenre()));
            ps2.setString(2, book.getTitle());
            ps2.setString(3, book.getAuthor());
            rowsAffected = ps2.executeUpdate();
        } else {
//            System.out.println("Такая книга уже есть в библиотеке ");
            PreparedStatement statement = connection.
                    prepareStatement("select books.id from books " +
                            "where books.genreID = (select id from genre where genre = ? )" +
                            "and books.authorId = (select id from author where author = ? )" +
                            "and books.title = ?");
            statement.setString(1, String.valueOf(book.getGenre()));
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getTitle());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
//            System.out.println(getBookByID(resultSet.getInt("books.id")));
        }
        dataSourceUtil.closeConnect(connection);
        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }

    private List<Book> createModelBooks(ResultSet rs, Connection connection) throws SQLException {
        List<Book> users = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("books.id"));
            book.setGenre(Genre.valueOf(rs.getString("genre.genre").toUpperCase()));
            book.setTitle(rs.getString("books.title"));
            book.setAuthor(rs.getString("author.author"));
            users.add(book);
        }
        dataSourceUtil.closeConnect(connection);
        return users;
    }

    private boolean checkNotExistence(Book book) throws SQLException {
        Connection connection = dataSourceUtil.getConnect();
        PreparedStatement statement = connection.
                prepareStatement("select exists(select 1 " +
                        "from books, genre, author " +
                        "where books.genreID = genre.Id " +
                        "and books.authorId = author.Id " +
                        "and books.title = ? " +
                        "and genre.genre = ?" +
                        "and author.author = ?)");
        statement.setString(1, book.getTitle());
        statement.setString(2, String.valueOf(book.getGenre()));
        statement.setString(3, book.getAuthor());
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            dataSourceUtil.closeConnect(connection);
            return true;
        } else {
            dataSourceUtil.closeConnect(connection);
            return false;
        }
    }
}
