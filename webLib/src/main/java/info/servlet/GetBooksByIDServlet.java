package info.servlet;

import info.library.dao.BookDao;
import info.library.dao.BookDaoImpl;
import info.library.model.books.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="id", value="/id")
public class GetBooksByIDServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/getBooksByID.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        BookDao bookDao = new BookDaoImpl();
        try {
            List<Book> book = new ArrayList<>();
            book.add(bookDao.getBookByID(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("books", book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/getBooks.jsp").forward(req,resp);
    }
}
