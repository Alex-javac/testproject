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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "update", value = "/update")
public class UpdateBookServlet extends HttpServlet {
    Book book = null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        String title = req.getParameter("title");

        if (req.getParameter("id")!= null) {
            session.setAttribute("id", req.getParameter("id"));
            book = booksByID(Integer.parseInt(req.getParameter("id")));
        }else {
            String id = (String) session.getAttribute("id");
                book = booksByID(Integer.parseInt(id));
        }

        if (book != null) {
            req.setAttribute("book", book);
            if (author != null) {
                book.setAuthor(author);
            }
            if (genre != null) {
                book.setGenre(Genre.valueOf(genre.toUpperCase()));
            }
            if (title != null) {
                book.setTitle(title);
            }
            updateBook(book);
        } else {
            req.setAttribute("isBook", "такой книги нет в библиотеке");
            getServletContext().getRequestDispatcher("/whichBookUpdate.jsp").forward(req, resp);
        }
        getServletContext().getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    private void updateBook(Book book) {
        BookDao bookDao = new BookDaoImpl();
        try {
            bookDao.update(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book booksByID(int id) {
        BookDao bookDao = new BookDaoImpl();
        Book book = null;
        try {
            book = bookDao.getBookByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }
}
