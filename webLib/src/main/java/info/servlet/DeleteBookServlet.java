package info.servlet;

import info.library.dao.BookDao;
import info.library.dao.BookDaoImpl;
import info.library.model.books.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "delete", value = "/delete")
public class DeleteBookServlet extends HttpServlet {
    Book book = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/delete.jsp").forward(req, resp);
//        String id = req.getParameter("id");
//        if (id != null) {
//            book = booksByID(id);
//
//            if (book != null) {
//                resp.getWriter().write("<h1>" + book.toString() + ": удалена</h1>");
//                deleteBook(book);
//            } else {
//               "<h1>В БД нет книги с таким ID </h1>"
//            }
//           "</body></html>");
//        } else {
//
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("id") != null) {
            session.setAttribute("id", req.getParameter("id"));
            book = booksByID(Integer.parseInt(req.getParameter("id")));
        } else {
            String id = (String) session.getAttribute("id");
            book = booksByID(Integer.parseInt(id));
        }
        if (book != null) {
            req.setAttribute("book", book);
            req.setAttribute("isBook", "Удалена");
            deleteBook(book);
        } else {
            req.setAttribute("isBook", "В БД нет книги с таким ID");
        }

        getServletContext().getRequestDispatcher("/delete.jsp").forward(req, resp);
    }

    private void deleteBook(Book book) {
        BookDao bookDao = new BookDaoImpl();
        try {
            bookDao.delete(book);
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
