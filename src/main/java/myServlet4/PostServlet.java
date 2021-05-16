package myServlet4;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.sqlite.JDBC; // подключение драйвера JDBC

import java.sql.*;


public class PostServlet extends HttpServlet {
    // Класс сервелата должен наследовать класс HttpServlet
    // В классе должны быть переопределены методы init, destroy, и либо service либо конкретные методы
    // для обработки определенных типов запросов
    private Driver driver; // драйвер jdbc

    @Override
    public void init(ServletConfig conf) throws ServletException // метод инит может выбросить исключение сервлета,
    // необходимо пробросить его на уровень родитесколького класса
    {
        // необходимо переопределить метод init родителя - он будет использован веб-сервером для создания объекта сервелета
        super.init(conf); // согласно спецификации нужно вызвать конструктор суперкласса и передать ему настройки веб-сервера
        try {
            this.driver = new JDBC(); // создание объекта драйвера jdbc и его регистарция в менеджере драйверов
            DriverManager.registerDriver(this.driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        String name = request.getParameter("name"); //// из объекта запроса получается парметр с именем Name,
        // который может быть передан вместе с GET или POST запросом, если данный пареметр не задан - возвращается null
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String about = request.getParameter("about");
        String[] skills = request.getParameterValues("skills");
        String country = request.getParameter("country");
        String town = request.getParameter("town");

        String allSkills = "";
        for (String skill: skills) {
            allSkills += skill + ", ";
        }
        // подключение к БД
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:/database.db";
            connection = DriverManager.getConnection(url); // получение объекта соединения с базой из менеджера драйверов
            if (connection != null) {
                System.out.println("Соединение успешно");
            }
        } catch (Exception ex) {
            System.err.println(ex.getClass().getName() + " : " + ex.getMessage());
            System.out.println("Ошбика подключения к базе!");
        }
        // запрос на инсерт к базе
        String sql = "INSERT INTO person(name, email, password, gender, about, skills, country, town) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql); // подготовленный запрос
            stmt.setString(1, name); //подстановка параметров в подготовленный sql запрос
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, gender);
            stmt.setString(5, about);
            stmt.setString(6, allSkills);
            stmt.setString(7, country);
            stmt.setString(8, town);
            stmt.executeUpdate();
        } catch(SQLException ex) {
            System.err.println(ex.getClass().getName() + " : " + ex.getMessage());
            System.out.println("Ошбика запроса к базе!");
        }
        // вывод переменных на страницу
        PrintWriter out = response.getWriter(); // из ответа сервера вызывается объект-обертка для вывода, представляющий из себя поток
        out.println("<html>"); // в данный поток отправляется тело HTTP ответа - Html-теги
        out.println("<head>");
        out.println("<title> MyServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>" + name + "</p>");
        out.println("<p>" + email + "</p>");
        out.println("<p>" + password + "</p>");
        out.println("<p>" + gender + "</p>");
        out.println("<p>" + about + "</p>");
        out.println("<p>" + allSkills + "</p>");
        out.println("<p>" + country + "</p>");
        out.println("<p>" + town + "</p>");
        out.println("</body>");
        out.println("</html>");
        out.close(); // поток вывода явно закрывается чтобы избежать расходования ресурсов памяти для объекта находящегося в системе,
        // и ожидающего сборки мусора виртупльной машиной Java
    }
    @Override
    public void destroy() {
        try {
            DriverManager.deregisterDriver(this.driver); // удаление драйвера из списка зарегистрированных,
            // сделано для того чтобы драйвер подключения не был зарегистрирован несколько раз
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // согласно спецификации данный метод должен быть переопределен, так как веб-сервер вызывает его в случае бездействия сервлета
    }
}
