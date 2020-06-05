import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:3306/javaee_database", "root","123456");
            Statement statement = conn.createStatement();

            String sql = "select * from user where name='" + username + "' and password='" + password + "'";
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                createCookie(response, username, password);
                Object num = getServletContext().getAttribute("number");
                writer.println("在线人数:"+num+ "<br>");

                writer.println("用户名：" + username + "<br>" + "密码：" + password + "<br>");
            }
            else {
                writer.println("用户名或者密码错误");
            }
            result.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCookie(HttpServletResponse response, String username, String password) {
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setMaxAge(30 * 24 * 60 * 60);
        Cookie passwordCookie = new Cookie("password", password);
        passwordCookie.setMaxAge(30 * 24 * 60 * 60);

        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
