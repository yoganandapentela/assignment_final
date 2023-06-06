import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Program15 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         
            statement = connection.createStatement();

         
            String query = "SELECT * FROM mytable";
            resultSet = statement.executeQuery(query);

          
            response.setContentType("text/html");

         
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Database Table</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Age</th></tr>");

       
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + age + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
