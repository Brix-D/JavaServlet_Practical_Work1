package myServlet4;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PostServlet extends HttpServlet {
    // Класс сервелата должен наследовать класс HttpServlet
    // В классе должны быть переопределены методы init, destroy, и либо service либо конкретные методы
    // для обработки определенных типов запросов
    @Override
    public void init(ServletConfig conf) throws ServletException // метод инит может выбросить исключение сервлета,
    // необходимо пробросить его на уровень родитесколького класса
    {
        // необходимо переопределить метод init родителя - он будет использован веб-сервером для создания объекта сервелета
        super.init(conf); // согласно спецификации нужно вызвать конструктор суперкласса и передать ему настройки веб-сервера
        // иначе - 500 Internal Server Error
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // переопределенный метод вызываается при получении GET запроса от клиента
        // веб-сервер передает 2 объекта - request и response
        // request содержит заголовки, и параметры HTTP запроса
        // response отвечает за формирование HTTP ответа сервера, в том числе заголовков, кода ответа, и тела ответа
        request.getRequestDispatcher("index.jsp").forward(request, response); // из запроса получается объект-обертка для представления index.jsp
        // затем страница считывается и вывод перенаправляется клиенту с учетом текущих параметров запроса и ответа
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // данный метод вызывается при получении POST-запроса
        response.setContentType("text/html"); // согласно спецификации HTTP тип ответа должен быть явно установлен сервером
        // в виде заголовка Content-Type
        PrintWriter out = response.getWriter(); // из ответа сервера вызывается объект-обертка для вывода, представляющий из себя поток
        out.println("<html>"); // в данный поток отправляется тело HTTP ответа - Html-теги
        out.println("<head>");
        out.println("<title> MyServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Reservation</h1>");
        out.println(request.getParameter("Name") + "'s You has been reserved."); // из объекта запроса получается парметр с именем Name,
        // который может быть передан вместе с GET или POST запросом, если данный пареметр не задан - возвращается null
        out.println("</body>");
        out.println("</html>");
        out.close(); // поток вывода явно закрывается чтобы избежать расходования ресурсов памяти для объекта находящегося в системе,
        // и ожидающего сборки мусора виртупльной машиной Java
    }
    @Override
    public void destroy() {
        // согласно спецификации данный метод должен быть переопределен, так как веб-сервер вызывает его в случае бездействия сервлета
    }
}
