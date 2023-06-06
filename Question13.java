package java_final_assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Question13 {
    // Database credentials
    static final String URL = "jdbc:postgresql://localhost:5432/mydatabase";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            
            Class.forName("org.postgresql.Driver");

            
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

         
            String inputFile = "data.txt";
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;

       
            String insertQuery = "INSERT INTO employees (id, name, age) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);

            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, age);
                preparedStatement.addBatch();
            }

           
            preparedStatement.executeBatch();
            System.out.println("Batch update executed successfully!");

          
            reader.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
         
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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

