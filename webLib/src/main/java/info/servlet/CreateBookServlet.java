package info.servlet;

import info.library.dao.BookDao;
import info.library.dao.BookDaoImpl;
import info.library.model.books.Book;
import info.library.model.books.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "create", value = "/create")
public class CreateBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        BookDao bookDao = new BookDaoImpl();

        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        String title = req.getParameter("title");

        Book book = new Book();

        if (author != null) {
            book.setAuthor(author);
        }
        if (genre != null) {
            book.setGenre(Genre.valueOf(genre.toUpperCase()));
        }
        if (title != null) {
            book.setTitle(title);
        }

        if (book.getAuthor() != null && book.getGenre() != null && book.getTitle() != null) {
            try {
                if (bookDao.create(book)) {
                    req.setAttribute("book", "книга успешно дабавлена");

                } else {
                    req.setAttribute("book", "такая книга есть в библиотеке");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("book", "заполните все поля");
        }
        getServletContext().getRequestDispatcher("/create.jsp").forward(req, resp);
    }
}
