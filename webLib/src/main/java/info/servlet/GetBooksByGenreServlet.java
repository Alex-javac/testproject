package info.servlet;

import info.library.dao.BookDao;
import info.library.dao.BookDaoImpl;
import info.library.model.books.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet(name="genre", value="/genre")
public class GetBooksByGenreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/getBookByGenre.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookDao bookDao = new BookDaoImpl();
            try {
                req.setAttribute("books", bookDao.getBooksByGenre(Genre.valueOf(req.getParameter("genre").toUpperCase())));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/getBooks.jsp").forward(req,resp);

    }
}
