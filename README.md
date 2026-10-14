
# Employee Management System

A menu-driven Employee Management System developed using Java, JDBC, and MySQL. This project allows users to manage employee records through a console-based application.

## Features

- Insert new employee records
- Display all employee records
- Search employees by ID
- Update employee name, age, and department
- Delete employee records by ID
- Input validation and exception handling
- Parameterized SQL queries using PreparedStatement
- Automatic closing of database resources using try-with-resources

## Technologies Used

- Java
- JDBC (Java Database Connectivity)
- MySQL
- SQL
- MySQL Connector/J
- Visual Studio Code

## Project Structure

Employee-Management-System/
|
|-- CallingJdbcMethods.java
|-- JdbcStatementExample.java
|-- README.md

## Database Setup

1. Open MySQL Workbench or your MySQL command-line client.
2. Create the database:

```sql
CREATE DATABASE companydb;
USE companydb;
```

3. Create the employee table:

```sql
CREATE TABLE employee (
    EmpID INT PRIMARY KEY,
    EmpName VARCHAR(100) NOT NULL,
    EmpAge INT NOT NULL,
    EmpDept VARCHAR(100) NOT NULL
);
```

## Prerequisites

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J JDBC driver
- Visual Studio Code or another Java IDE

## Database Configuration

Update the database connection settings in `JdbcStatementExample.java` according to your local MySQL configuration.

```java
"jdbc:mysql://localhost:3306/companydb",
"root",
"YOUR_MYSQL_PASSWORD"
```

Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password. Do not commit real passwords to a public repository.

## How to Run

1. Install the JDK and MySQL Server.
2. Add the MySQL Connector/J library to your Java project's classpath.
3. Create the `companydb` database and `employee` table.
4. Configure your database credentials.
5. Compile both Java files with the MySQL JDBC driver available on the classpath.
6. Run the `CallingJdbcMethods` class.
7. Select an operation from the main menu and follow the instructions.

## Learning Outcomes

- Understanding JDBC architecture and database connectivity
- Executing SQL queries using PreparedStatement
- Performing CRUD operations
- Working with Connection, ResultSet, and SQLException
- Handling exceptions and validating user input
- Managing database resources efficiently

## Author

Yogita Dhote

GitHub: https://github.com/YogitaDhote
