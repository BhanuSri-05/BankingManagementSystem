package menu;

import service.ReportService;
import model.Transaction;

import java.util.List;
import java.util.Scanner;

public class ReportMenu {

    private Scanner sc = new Scanner(System.in);
    private ReportService reportService = new ReportService();


    public void showMenu() {

        while (true) {

            System.out.println("\n===== REPORTS =====");
            System.out.println("1. Total Customers");
            System.out.println("2. Total Accounts");
            System.out.println("3. Highest Balance");
            System.out.println("4. Lowest Balance");
            System.out.println("5. Total Deposits");
            System.out.println("6. Total Withdrawals");
            System.out.println("7. Today's Transactions");
            System.out.println("8. Monthly Transactions");
            System.out.println("9. Back");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Total Customers : "
                            + reportService.getTotalCustomers());
                    break;

                case 2:
                    System.out.println("Total Accounts : "
                            + reportService.getTotalAccounts());
                    break;

                case 3:
                    System.out.printf("Highest Balance : %.2f%n", reportService.getHighestBalance());
                    break;

                case 4:
                    System.out.printf("Lowest Balance : %.2f%n", reportService.getLowestBalance());
                    break;

                case 5:
                    System.out.printf("Total Deposits : Rs. %.2f%n",reportService.getTotalDeposits());
                    break;

                case 6:
                    System.out.printf("Total Withdrawals : Rs. %.2f%n",reportService.getTotalWithdrawals());
                    break;

                case 7:
                    List<Transaction> todays = reportService.getTodaysTransactions();

                     displayTransactions(todays);
                     break;

                case 8:
                    List<Transaction> monthly = reportService.getMonthlyTransactions();

                    displayTransactions(monthly);
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
           private void displayTransactions(List<Transaction> transactions) {

        if (transactions.isEmpty()) {

            System.out.println("No Transactions Found.");
            return;
        }


        System.out.println("\n==============================================================");
        System.out.printf("%-5s %-12s %-12s %-12s %-22s%n",
                "ID",
                "Account",
                "Type",
                "Amount",
                "Date");

        System.out.println("==============================================================");


        for (Transaction t : transactions) {

            System.out.printf("%-5d %-12d %-12s Rs.%-9.2f %-22s%n",
                    t.getTransactionId(),
                    t.getAccountNumber(),
                    t.getTransactionType(),
                    t.getAmount(),
                    t.getTransactionDate());
        }


        System.out.println("==============================================================");
    
    }
}
