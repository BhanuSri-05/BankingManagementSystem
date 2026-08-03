package menu;

import model.Account;
import service.AccountService;

import java.util.List;
import java.util.Scanner;

public class AccountMenu {

    private Scanner sc = new Scanner(System.in);
    private AccountService accountService = new AccountService();

    public void showMenu() {

        while (true) {

            System.out.println("\n===== ACCOUNT MANAGEMENT =====");
            System.out.println("1. Open Account");
            System.out.println("2. Close Account");
            System.out.println("3. Search Account");
            System.out.println("4. View All Accounts");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    openAccount();
                    break;

                case 2:
                    closeAccount();
                    break;

                case 3:
                    searchAccount();
                    break;

                case 4:
                    viewAllAccounts();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    // Open Account
    private void openAccount() {

        Account account = new Account();

        System.out.print("Enter Customer ID: ");
        account.setCustomerId(sc.nextInt());

        sc.nextLine();

        System.out.print("Enter Account Type (Savings/Current): ");
        account.setAccountType(sc.nextLine());

        System.out.print("Enter Initial Balance: ");
        account.setBalance(sc.nextDouble());

        if (accountService.createAccount(account)) {

            System.out.println("\nAccount Created Successfully.");

        } else {

            System.out.println("\nFailed to Create Account.");
        }
    }

    // Close Account
    private void closeAccount() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        if (accountService.closeAccount(accountNumber)) {

            System.out.println("\nAccount Closed Successfully.");

        } else {

            System.out.println("\nAccount Not Found.");
        }
    }

    // Search Account
    private void searchAccount() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        Account account = accountService.getAccount(accountNumber);

        if (account != null) {

            System.out.println("\n========== ACCOUNT DETAILS ==========");
            System.out.println("Account No   : " + account.getAccountNumber());
            System.out.println("Customer ID  : " + account.getCustomerId());
            System.out.println("Type         : " + account.getAccountType());
            System.out.printf("Balance      : %.2f%n", account.getBalance());
            System.out.println("Status       : " + account.getStatus());
            System.out.println("Created Date : " + account.getCreatedDate());

        } else {

            System.out.println("\nAccount Not Found.");
        }
    }

    // View All Accounts
    private void viewAllAccounts() {

        List<Account> accounts = accountService.getAllAccounts();

        if (accounts.isEmpty()) {

            System.out.println("No Accounts Found.");
            return;
        }

        System.out.println("\n==================== ACCOUNT LIST ====================");
        System.out.printf("%-15s %-15s %-15s%n",
                "Account No", "Customer ID", "Balance");
        System.out.println("------------------------------------------------------");

        for (Account account : accounts) {

            System.out.printf("%-15d %-15d %,.2f%n",
                    account.getAccountNumber(),
                    account.getCustomerId(),
                    account.getBalance());
        }
    }
}
