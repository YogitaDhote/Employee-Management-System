
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcStatementExample {

    Scanner sc;

    int id;
    String name;
    int age;
    String dp;

    public JdbcStatementExample(Scanner sc) {
        this.sc = sc;
    }

    private Connection getConnection()
            throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/companydb",
                "root",
                "cdacacts"
        );
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer!");
            }
        }
    }

    private int readPositiveInt(String message) {
        while (true) {
            int value = readInt(message);

            if (value > 0) {
                return value;
            }

            System.out.println("Value must be greater than zero!");
        }
    }

    private String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty!");
        }
    }

    public void selectById()
            throws SQLException, ClassNotFoundException {

        id = readPositiveInt("Enter Employee ID: ");

        String q = "SELECT * FROM employee WHERE EmpID = ?";

        try (Connection con = getConnection();
             PreparedStatement pmt = con.prepareStatement(q)) {

            pmt.setInt(1, id);

            try (ResultSet rs = pmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nEmployee ID: " + rs.getInt("EmpID"));
                    System.out.println("Employee Name: " + rs.getString("EmpName"));
                    System.out.println("Employee Age: " + rs.getInt("EmpAge"));
                    System.out.println("Employee Department: " + rs.getString("EmpDept"));
                } else {
                    System.out.println("Employee ID not found!");
                }
            }
        }
    }

    public void insert()
            throws SQLException, ClassNotFoundException {

        id = readPositiveInt("Enter Employee ID: ");
        name = readText("Enter Employee Name: ");
        age = readPositiveInt("Enter Employee Age: ");
        dp = readText("Enter Department: ");

        String q = "INSERT INTO employee "
                + "(EmpID, EmpName, EmpAge, EmpDept) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pmt = con.prepareStatement(q)) {

            pmt.setInt(1, id);
            pmt.setString(2, name);
            pmt.setInt(3, age);
            pmt.setString(4, dp);

            int row = pmt.executeUpdate();

            if (row > 0) {
                System.out.println("Employee inserted successfully!");
            } else {
                System.out.println("Employee was not inserted.");
            }
        }
    }

    public void update()
            throws SQLException, ClassNotFoundException {

        while (true) {
            System.out.println("\n1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Department");
            System.out.println("4. Exit");
            
            int op = readInt("Enter your choice: ");

            switch (op) {
                case 1:
                    updateName();
                    break;

                case 2:
                    updateAge();
                    break;

                case 3:
                    updatedepartment();
                    break;

                case 4:
                    System.out.println("Returning to main menu!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void updateName()
            throws SQLException, ClassNotFoundException {

        id = readPositiveInt("Enter Employee ID: ");
        name = readText("Enter New Name: ");

        String q = "UPDATE employee SET EmpName = ? WHERE EmpID = ?";

        try (Connection con = getConnection();
             PreparedStatement pmt = con.prepareStatement(q)) {

            pmt.setString(1, name);
            pmt.setInt(2, id);

            int row = pmt.executeUpdate();

            if (row > 0) {
                System.out.println("Employee name updated successfully!");
            } else {
                System.out.println("Employee ID not found!");
            }
        }
    }

    public void updateAge()
            throws SQLException, ClassNotFoundException {

        id = readPositiveInt("Enter Employee ID: ");
        age = readPositiveInt("Enter New Age: ");

        String q = "UPDATE employee SET EmpAge = ? WHERE EmpID = ?";

        try (Connection con = getConnection();
             PreparedStatement pmt = con.prepareStatement(q)) {

            pmt.setInt(1, age);
            pmt.setInt(2, id);

            int row = pmt.executeUpdate();

            if (row > 0) {
                System.out.println("Employee age updated successfully!");
            } else {
                System.out.println("Employee ID not found!");
            }
        }
    }

    public void updatedepartment()
            throws SQLException, ClassNotFoundException {

        id = readPositiveInt("Enter Employee ID: ");
        dp = readText("Enter New Department: ");

        String q = "UPDATE employee SET EmpDept = ? WHERE EmpID = ?";

        try (Connection con = getConnection();
             PreparedStatement pmt = con.prepareStatement(q)) {

            pmt.setString(1, dp);
            pmt.setInt(2, id);

            int row = pmt.executeUpdate();

            if (row > 0) {
                System.out.println("Employee department updated successfully!");
            } else {
                System.out.println("Employee ID not found!");
            }
        }
    }

    public void delete()
            throws SQLException, ClassNotFoundException {

        id = readPositiveInt("Enter Employee ID: ");

        String q = "DELETE FROM employee WHERE EmpID = ?";

        try (Connection con = getConnection();
             PreparedStatement pmt = con.prepareStatement(q)) {

            pmt.setInt(1, id);

            int row = pmt.executeUpdate();

            if (row > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee ID not found!");
            }
        }
    }

    public void displayAll()
            throws SQLException, ClassNotFoundException {

        String q = "SELECT * FROM employee";

        try (Connection con = getConnection();
             PreparedStatement pmt = con.prepareStatement(q);
             ResultSet rs = pmt.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("\nEmployee ID: " + rs.getInt("EmpID"));
                System.out.println("Employee Name: " + rs.getString("EmpName"));
                System.out.println("Employee Age: " + rs.getInt("EmpAge"));
                System.out.println("Employee Department: " + rs.getString("EmpDept"));
                System.out.println("-----------------------------");
            }

            if (!found) {
                System.out.println("No employee records found!");
            }
        }
    }
}
