package dao;

import database.DBConnection;
import model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // Generate Next Account Number
    private int generateAccountNumber(Connection con) throws SQLException {

        String sql = "SELECT IFNULL(MAX(account_number),100000) + 1 AS next_account FROM account";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("next_account");
            }
        }

        return 100001;
    }

    // Create Account
    public boolean createAccount(Account account) {

        String sql = "INSERT INTO account(account_number, customer_id, account_type, balance, status, created_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int accountNumber = generateAccountNumber(con);

            account.setAccountNumber(accountNumber);

            ps.setInt(1, account.getAccountNumber());
            ps.setInt(2, account.getCustomerId());
            ps.setString(3, account.getAccountType());
            ps.setDouble(4, account.getBalance());
            ps.setString(5, account.getStatus());
            ps.setDate(6, account.getCreatedDate());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Close Account
    public boolean closeAccount(int accountNumber) {

        String sql = "UPDATE account SET status='Closed' WHERE account_number=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountNumber);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Search Account
    public Account getAccount(int accountNumber) {

        String sql = "SELECT * FROM account WHERE account_number=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Account(
                        rs.getInt("account_number"),
                        rs.getInt("customer_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance"),
                        rs.getString("status"),
                        rs.getDate("created_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // View All Accounts
    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();

        String sql = "SELECT * FROM account";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                accounts.add(new Account(
                        rs.getInt("account_number"),
                        rs.getInt("customer_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance"),
                        rs.getString("status"),
                        rs.getDate("created_date")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    // Update Balance
    public boolean updateBalance(int accountNumber, double balance) {

        String sql = "UPDATE account SET balance=? WHERE account_number=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, balance);
            ps.setInt(2, accountNumber);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
