package info.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(name="movie", value="/movie")
public class MyFirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      BufferedReader reader=new BufferedReader(new FileReader("/home/alexander/IdeaProjects/testproject/testpriject/src/main/resources/page.html" ));
      while (reader.ready()) {
          response.getWriter().write(reader.readLine());
      }

    }
}
