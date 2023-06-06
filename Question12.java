package java_final_assignment;

import java.sql.*;

public class Question12 {
    // Database credentials
    static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");

         
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

           
            statement = connection.createStatement();

            
            String insertQuery = "INSERT INTO employees (id, name, age) VALUES (1, 'John Doe', 30)";
            statement.executeUpdate(insertQuery);
            System.out.println("Record inserted successfully!");

         
            String selectQuery = "SELECT * FROM employees";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

          
            String updateQuery = "UPDATE employees SET age = 31 WHERE id = 1";
            statement.executeUpdate(updateQuery);
            System.out.println("Record updated successfully!");

        
            String deleteQuery = "DELETE FROM employees WHERE id = 1";
            statement.executeUpdate(deleteQuery);
            System.out.println("Record deleted successfully!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
          
            try {
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
