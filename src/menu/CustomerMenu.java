package menu;

import model.Customer;
import service.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    private Scanner sc = new Scanner(System.in);
    private CustomerService customerService = new CustomerService();

    public void showMenu() {

        while (true) {

            System.out.println("\n===== CUSTOMER MANAGEMENT =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Search Customer");
            System.out.println("5. View All Customers");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addCustomer();
                    break;

                case 2:
                    updateCustomer();
                    break;

                case 3:
                    deleteCustomer();
                    break;

                case 4:
                    searchCustomer();
                    break;

                case 5:
                    viewAllCustomers();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    // Add Customer
    private void addCustomer() {

        Customer customer = new Customer();

        System.out.print("Enter Name: ");
        customer.setName(sc.nextLine());

        System.out.print("Enter Phone: ");
        customer.setPhone(sc.nextLine());

        System.out.print("Enter Email: ");
        customer.setEmail(sc.nextLine());

        System.out.print("Enter Address: ");
        customer.setAddress(sc.nextLine());

        System.out.print("Enter Aadhaar: ");
        customer.setAadhaar(sc.nextLine());

        if (customerService.addCustomer(customer)) {
            System.out.println("Customer Added Successfully.");
        } else {
            System.out.println("Failed to Add Customer.");
        }
    }

    // Update Customer
    private void updateCustomer() {

        Customer customer = new Customer();

        System.out.print("Enter Customer ID: ");
        customer.setCustomerId(sc.nextInt());
        sc.nextLine();

        System.out.print("Enter New Name: ");
        customer.setName(sc.nextLine());

        System.out.print("Enter New Phone: ");
        customer.setPhone(sc.nextLine());

        System.out.print("Enter New Email: ");
        customer.setEmail(sc.nextLine());

        System.out.print("Enter New Address: ");
        customer.setAddress(sc.nextLine());

        System.out.print("Enter New Aadhaar: ");
        customer.setAadhaar(sc.nextLine());

        if (customerService.updateCustomer(customer)) {
            System.out.println("Customer Updated Successfully.");
        } else {
            System.out.println("Customer Not Found.");
        }
    }

    // Delete Customer
    private void deleteCustomer() {

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        if (customerService.deleteCustomer(id)) {
            System.out.println("Customer Deleted Successfully.");
        } else {
            System.out.println("Customer Not Found.");
        }
    }

    // Search Customer
    private void searchCustomer() {

        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();

        Customer customer = customerService.searchCustomer(id);

        if (customer != null) {

            System.out.println("\n========== CUSTOMER DETAILS ==========");
            System.out.println("Customer ID : " + customer.getCustomerId());
            System.out.println("Name        : " + customer.getName());
            System.out.println("Phone       : " + customer.getPhone());
            System.out.println("Email       : " + customer.getEmail());
            System.out.println("Address     : " + customer.getAddress());
            System.out.println("Aadhaar     : " + customer.getAadhaar());

        } else {
            System.out.println("Customer Not Found.");
        }
    }

    // View All Customers
    private void viewAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No Customers Found.");
            return;
        }

        System.out.println("\n==================== CUSTOMER LIST ====================");

        System.out.printf("%-5s %-20s %-15s %-30s%n",
                "ID", "Name", "Phone", "Email");

        System.out.println("--------------------------------------------------------------------------");

        for (Customer customer : customers) {

            System.out.printf("%-5d %-20s %-15s %-30s%n",
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getPhone(),
                    customer.getEmail());
        }
    }
}
