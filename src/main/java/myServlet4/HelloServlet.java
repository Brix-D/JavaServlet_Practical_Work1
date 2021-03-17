package myServlet4;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    // Сервлет для стартовой страницы /
    private String message;
    @Override
    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        message = "Root page. Welcome :)";
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }
    @Override
    public void destroy() {
    }
}