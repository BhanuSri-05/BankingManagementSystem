package menu;

import service.AdminService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private Scanner sc = new Scanner(System.in);

    private AdminService adminService = new AdminService();

    private CustomerMenu customerMenu = new CustomerMenu();
    private AccountMenu accountMenu = new AccountMenu();
    private TransactionMenu transactionMenu = new TransactionMenu();
    private ReportMenu reportMenu = new ReportMenu();

    public void showMenu() {

        while (true) {

            System.out.println("\n==============================");
            System.out.println("   BANKING MANAGEMENT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Admin Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            try {

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        login();
                        break;

                    case 2:
                        System.out.println("\nThank you for using Banking Management System.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid Choice.");
                }

            } catch (InputMismatchException e) {

                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }
    }

    // Admin Login
    private void login() {

        sc.nextLine();

        System.out.print("Enter Username : ");
        String username = sc.nextLine();

        System.out.print("Enter Password : ");
        String password = sc.nextLine();

        if (adminService.login(username, password)) {

            System.out.println("\nLogin Successful.");

            adminDashboard();

        } else {

            System.out.println("\nInvalid Username or Password.");
        }
    }

    // Admin Dashboard
    private void adminDashboard() {

        while (true) {

            System.out.println("\n================================");
            System.out.println("        ADMIN DASHBOARD");
            System.out.println("================================");
            System.out.println("1. Customer Management");
            System.out.println("2. Account Management");
            System.out.println("3. Banking Operations");
            System.out.println("4. Reports");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");

            try {

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        customerMenu.showMenu();
                        break;

                    case 2:
                        accountMenu.showMenu();
                        break;

                    case 3:
                        transactionMenu.showMenu();
                        break;

                    case 4:
                        reportMenu.showMenu();
                        break;

                    case 5:
                        System.out.println("\nLogged Out Successfully.");
                        return;

                    default:
                        System.out.println("Invalid Choice.");
                }

            } catch (InputMismatchException e) {

                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }
    }
}
