
import java.sql.SQLException;
import java.util.Scanner;

public class CallingJdbcMethods {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        JdbcStatementExample jm = new JdbcStatementExample(sc);

        while (true) {
            System.out.println("\n===== EMPLOYEE MANAGEMENT SYSTEM =====");
            System.out.println("1. Insert Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Update Employee Details");
            System.out.println("5. Delete Employee by ID");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice: ");
            String input = sc.nextLine().trim();

            int op;

            try {
                op = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid menu number!");
                continue;
            }

            try {
                switch (op) {
                    case 1:
                        jm.insert();
                        break;

                    case 2:
                        jm.displayAll();
                        break;

                    case 3:
                        jm.selectById();
                        break;

                    case 4:
                        jm.update();
                        break;

                    case 5:
                        jm.delete();
                        break;

                    case 6:
                        System.out.println("Exiting Employee Management System!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Select 1 to 6.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());

                if ("23000".equals(e.getSQLState())) {
                    System.out.println(
                            "Check whether the employee ID already exists "
                            + "or another database constraint was violated."
                    );
                }

            } catch (ClassNotFoundException e) {
                System.out.println(
                        "MySQL JDBC driver not found. "
                        + "Check your Connector/J dependency."
                );
            }
        }
    }
}
