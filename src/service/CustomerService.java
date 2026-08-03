
package service;

import dao.CustomerDAO;
import model.Customer;
import util.ValidationUtil;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Add Customer
    public boolean addCustomer(Customer customer) {

        if (!ValidationUtil.isValidName(customer.getName())) {
            System.out.println("Invalid customer name.");
            return false;
        }

        if (!ValidationUtil.isValidPhone(customer.getPhone())) {
            System.out.println("Invalid phone number.");
            return false;
        }

        if (!ValidationUtil.isValidEmail(customer.getEmail())) {
            System.out.println("Invalid email.");
            return false;
        }

        if (!ValidationUtil.isValidAadhaar(customer.getAadhaar())) {
            System.out.println("Invalid Aadhaar.");
            return false;
        }

        return customerDAO.addCustomer(customer);
    }

    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    public boolean deleteCustomer(int customerId) {
        return customerDAO.deleteCustomer(customerId);
    }

    public Customer searchCustomer(int customerId) {
        return customerDAO.searchCustomer(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
