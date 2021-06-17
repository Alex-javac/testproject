package info.library.dao;

import info.library.model.books.Book;
import info.library.model.books.Genre;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    Book getBookByID(int id) throws SQLException;

    List<Book> getBooksByGenre(Genre genre) throws SQLException;

    List<Book> getBooksByTitle(String title) throws SQLException;

    List<Book> getBooksByAuthor(String author) throws SQLException;

    List<Book> getAllBooks() throws SQLException;

    boolean delete(Book book) throws SQLException;

    boolean update(Book book) throws SQLException;

    boolean create(Book book) throws SQLException;
}
