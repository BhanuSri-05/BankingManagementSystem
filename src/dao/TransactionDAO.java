package dao;

import database.DBConnection;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Deposit
    public boolean deposit(Transaction transaction) {

        String sql = "INSERT INTO transactions(account_number, transaction_type, amount, transaction_date, remarks) VALUES(?,?,?,?,?)";

        return saveTransaction(transaction, sql);
    }

    // Withdraw
    public boolean withdraw(Transaction transaction) {

        String sql = "INSERT INTO transactions(account_number, transaction_type, amount, transaction_date, remarks) VALUES(?,?,?,?,?)";

        return saveTransaction(transaction, sql);
    }

    // Transfer
    public boolean transfer(Transaction transaction) {

        String sql = "INSERT INTO transactions(account_number, transaction_type, amount, transaction_date, remarks) VALUES(?,?,?,?,?)";

        return saveTransaction(transaction, sql);
    }

    private boolean saveTransaction(Transaction transaction, String sql) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transaction.getAccountNumber());
            ps.setString(2, transaction.getTransactionType());
            ps.setDouble(3, transaction.getAmount());
            ps.setTimestamp(4, transaction.getTransactionDate());
            ps.setString(5, transaction.getRemarks());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Transaction History
    public List<Transaction> getTransactionHistory(int accountNumber) {

        List<Transaction> list = new ArrayList<>();

        String sql = "SELECT * FROM transactions WHERE account_number=? ORDER BY transaction_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountNumber);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getInt("account_number"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("transaction_date"),
                        rs.getString("remarks")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
