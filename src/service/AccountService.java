package service;

import dao.AccountDAO;
import dao.CustomerDAO;
import model.Account;
import model.Customer;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    // Open Account
    public boolean createAccount(Account account) {

        Customer customer =
                customerDAO.searchCustomer(account.getCustomerId());

        if (customer == null) {
            System.out.println("Customer not found.");
            return false;
        }

        account.setAccountNumber(generateAccountNumber());
        account.setStatus("Active");
        account.setCreatedDate(new Date(System.currentTimeMillis()));

        return accountDAO.createAccount(account);
    }

    private int generateAccountNumber() {
        return 100000 + new Random().nextInt(900000);
    }

    public boolean closeAccount(int accountNumber) {
        return accountDAO.closeAccount(accountNumber);
    }

    public Account getAccount(int accountNumber) {
        return accountDAO.getAccount(accountNumber);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }
}
