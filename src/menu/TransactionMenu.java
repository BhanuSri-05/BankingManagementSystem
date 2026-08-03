package menu;

import model.Transaction;
import service.TransactionService;

import java.util.List;
import java.util.Scanner;

public class TransactionMenu {

    private Scanner sc = new Scanner(System.in);
    private TransactionService transactionService = new TransactionService();

    public void showMenu() {

        while (true) {

            System.out.println("\n===== BANKING OPERATIONS =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Balance Enquiry");
            System.out.println("5. Mini Statement");
            System.out.println("6. Back");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    deposit();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    transfer();
                    break;

                case 4:
                    balanceEnquiry();
                    break;

                case 5:
                    miniStatement();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    // Deposit
    private void deposit() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter Remarks: ");
        String remarks = sc.nextLine();

        if (transactionService.deposit(accountNumber, amount, remarks)) {

            System.out.println("Amount Deposited Successfully.");

        } else {

            System.out.println("Deposit Failed.");
        }
    }

    // Withdraw
    private void withdraw() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter Remarks: ");
        String remarks = sc.nextLine();

        if (transactionService.withdraw(accountNumber, amount, remarks)) {

            System.out.println("Withdrawal Successful.");

        } else {

            System.out.println("Withdrawal Failed.");
        }
    }

    // Transfer
    private void transfer() {

        System.out.print("Enter Sender Account Number: ");
        int fromAccount = sc.nextInt();

        System.out.print("Enter Receiver Account Number: ");
        int toAccount = sc.nextInt();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        if (transactionService.transfer(fromAccount, toAccount, amount)) {

            System.out.println("Transfer Successful.");

        } else {

            System.out.println("Transfer Failed.");
        }
    }

    // Balance Enquiry
    private void balanceEnquiry() {

        System.out.print("Enter Account Number: ");
        int accountNumber = sc.nextInt();

        double balance = transactionService.getBalance(accountNumber);

        if (balance >= 0) {

            System.out.printf("Current Balance : Rs. %.2f%n", balance);

        } else {

            System.out.println("Account Not Found.");
        }
    }

    // Mini Statement
    private void miniStatement() {

            System.out.print("Enter Account Number: ");
    int accountNumber = sc.nextInt();

    List<Transaction> transactions =
            transactionService.getMiniStatement(accountNumber);

    if (transactions.isEmpty()) {

        System.out.println("No Transactions Found.");
        return;
    }

    System.out.println("\n========================== MINI STATEMENT ==========================");

    System.out.printf("%-8s %-15s %-12s %-12s %-22s %-20s%n",
            "Txn ID", "Account No", "Type", "Amount", "Date & Time", "Remarks");

    System.out.println("-------------------------------------------------------------------------------"
            + "----------------");

    for (Transaction transaction : transactions) {

        System.out.printf("%-8d %-15d %-12s Rs. %-8.2f %-22s %-20s%n",
                transaction.getTransactionId(),
                transaction.getAccountNumber(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getRemarks());
    }
    }
}
