# Student Expense and Performance Tracker

A *CLI-based Java application* designed to help students efficiently manage and track their *academic performance* and *daily expenses*. This project aims to simplify student life by providing a unified platform for logging academic scores, monitoring expenses, and generating reports.

---

## Features

- *User Registration & Login*
  - Secure login system using JDBC and MySQL.
  
- *Academic Performance Tracking*
  - Add, update, and view subject-wise marks and performance.
  - Store performance data semester-wise.

- *Expense Management*
  - Add and track daily expenses.
  - Categorize expenses (e.g., Food, Transport, Study Materials).
  
- *Comprehensive Reports*
  - Generate academic and financial reports.
  - Visual summary (CLI-based) of academic progress and budget status.

- *Interactive CLI Menus*
  - Easy navigation through logical, menu-driven interface.

---

## Technologies Used

- *Java* (Core + JDBC)
- *MySQL* (Database)
- *IntelliJ IDEA / Eclipse* (IDE)
- *Git* (Version Control)

---

## Project Structure
<pre>
text
StudentExpensePerformanceTracker/
│
├── src/
│   ├── JdbcUtil.java              # Database connection utility class
│   ├── User_Login.java            # Handles user login authentication
│   ├── LoginOrRegister.java       # Entry point for login or registration logic
│   ├── User.java                  # User model class with basic details
│   ├── PostLogin.java             # Post-login options and routing
│   ├── MainMenu.java              # Main menu interface shown after login
│   ├── Student.java               # Student-specific information and methods
│   ├── Performance.java           # Academic performance input and display logic
│   ├── Expense.java               # Expense input, management, and summary
│   └── Report.java                # Generates combined performance and expense reports
│
├── README.md                      # Project overview and setup instructions
├── LICENSE                        # MIT License file
└── .gitignore                     # Optional - ignore compiled files, DB config, etc.

</pre>
