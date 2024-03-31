package assiment5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDatabase {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:8080/departments";
    private static final String USERNAME = "narasimha";
    private static final String PASSWORD = "narasimha";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS department (" +"id INT AUTO_INCREMENT PRIMARY KEY," +"name VARCHAR(255))";
    private static final String INSERT_DEPARTMENT_QUERY = "INSERT INTO department (name) VALUES (?)";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            PreparedStatement createTableStatement = connection.prepareStatement(CREATE_TABLE_QUERY);
            createTableStatement.executeUpdate();
            System.out.println("Department table created (if not existed)");
            Department department = new Department(1, "IT");
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_DEPARTMENT_QUERY);
            insertStatement.setString(1, department.getName());
            int rowsInserted = insertStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Department inserted successfully");
            } else {
                System.out.println("Failed to insert department");
            }
            insertStatement.close();
            createTableStatement.close();
            connection.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    static class Department {
        private int id;
        private String name;

        public Department(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

