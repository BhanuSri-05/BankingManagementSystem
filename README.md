# BankingManagementSystem
A Core Java Banking Management System using JDBC and MySQL with customer, account, transaction, and reporting features.
# Banking Management System

## Description

The Banking Management System is a console-based Java application developed using Core Java, JDBC, and MySQL. The project simulates basic banking operations such as customer management, account handling, deposits, withdrawals, fund transfers, transaction history, and report generation.

The application follows a layered architecture using Model, DAO, Service, and Menu packages. It demonstrates practical implementation of Object-Oriented Programming concepts, database connectivity, exception handling, and modular software design.

---

## Technologies Used

- Java
- Core Java (OOP Concepts)
- JDBC
- MySQL
- SQL
- Collections Framework
- Exception Handling
- Git & GitHub

---

## Features

### Customer Management
- Add new customers
- View customer details
- Search customers
- Update customer information
- Delete customer records

### Account Management
- Create bank accounts
- View account details
- Check account balance
- Manage account information

### Transaction Management
- Deposit money
- Withdraw money
- Transfer money between accounts
- View transaction history

### Reports
- Total customers count
- Total accounts count
- Highest account balance
- Lowest account balance
- Total deposits
- Total withdrawals
- Today's transactions
- Monthly transactions

### Security & Validation
- Custom exception handling
- Input validation
- Login validation
- Invalid amount handling
- Duplicate customer prevention

---

## Project Structure

```
BankingManagementSystem
│
├── src
│   │
│   ├── database
│   │   └── DBConnection.java
│   │
│   ├── model
│   │   ├── Customer.java
│   │   ├── Account.java
│   │   ├── Transaction.java
│   │   └── Admin.java
│   │
│   ├── dao
│   │   ├── CustomerDAO.java
│   │   ├── AccountDAO.java
│   │   ├── TransactionDAO.java
│   │   └── ReportDAO.java
│   │
│   ├── service
│   │   ├── CustomerService.java
│   │   ├── AccountService.java
│   │   ├── TransactionService.java
│   │   └── ReportService.java
│   │
│   ├── menu
│   │   ├── MainMenu.java
│   │   ├── CustomerMenu.java
│   │   ├── AccountMenu.java
│   │   ├── TransactionMenu.java
│   │   └── ReportMenu.java
│   │
│   ├── exception
│   │   ├── DuplicateCustomerException.java
│   │   ├── InvalidLoginException.java
│   │   └── InvalidAmountException.java
│   │
│   └── util
│       ├── ValidationUtil.java
│       └── LoggerUtil.java
│
├── lib
│   └── mysql-connector-j.jar
│
├── .gitignore
└── README.md
```

---

## Database

Database used:

```
MySQL
```

Main tables:

- customer
- account
- transactions
- admin

---

## Application Architecture

```
Menu Layer
     |
     ↓
Service Layer
     |
     ↓
DAO Layer
     |
     ↓
Database
```

### Layer Responsibilities

**Menu Layer**
- Handles user interaction
- Displays menus and outputs

**Service Layer**
- Contains business logic
- Validates operations

**DAO Layer**
- Performs database operations
- Executes SQL queries

**Model Layer**
- Represents database entities

---

## How to Run

### 1. Clone Repository

```bash
git clone <repository-url>
```

### 2. Configure MySQL Database

- Create the required database
- Execute SQL scripts
- Update database credentials in `DBConnection.java`

### 3. Add MySQL Connector

Add MySQL JDBC driver:

```
lib/mysql-connector-j.jar
```

### 4. Compile and Run

Compile all Java files and run:

```
Main.java
```

---

## Future Enhancements

- Add GUI using JavaFX/Swing
- Add password encryption
- Add online banking features
- Add customer notifications
- Convert into Spring Boot application

---

## Author

**Tavva Bhanu Sri**

GitHub: https://github.com/BhanuSri-05
