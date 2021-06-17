package info.library.service;

import info.library.dao.BookDao;
import info.library.dao.BookDaoImpl;
import info.library.model.books.Book;
import info.library.model.books.Books;

import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.List;

public class FillingDatabase {
    public static void fillIn() throws IOException {
        BookDao bookDao = new BookDaoImpl();
        Path path = Paths.get("/home/alexander/IdeaProjects/LibraryProject/src/main/java/files/File.xml") ;
        if (ValidationChecker.validateXMLSchema(path)) {
                    Books books = JaxbCreate.bookUnmarshalling(path);
                    List<Book> bookList = books.book;
            for (Book book: bookList)
                    try {
                        bookDao.create(book);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
 }
}
