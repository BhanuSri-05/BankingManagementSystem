package dao;

import database.DBConnection;
import model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    // Total Customers
    public int getTotalCustomers() {

        String sql = "SELECT COUNT(*) FROM customer";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    // Total Accounts
    public int getTotalAccounts() {

        String sql = "SELECT COUNT(*) FROM account";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    // Highest Balance
    public double getHighestBalance() {

        String sql = "SELECT IFNULL(MAX(balance),0) FROM account";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    // Lowest Balance
    public double getLowestBalance() {

        String sql = "SELECT IFNULL(MIN(balance),0) FROM account";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    // Total Deposits
    public double getTotalDeposits() {

        String sql =
                "SELECT IFNULL(SUM(amount),0) FROM transactions WHERE transaction_type='Deposit'";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    // Total Withdrawals
    public double getTotalWithdrawals() {

        String sql =
                "SELECT IFNULL(SUM(amount),0) FROM transactions WHERE transaction_type='Withdraw'";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }



    // Today's Transactions
    public List<Transaction> getTodaysTransactions() {

        List<Transaction> list = new ArrayList<>();

        String sql =
                "SELECT * FROM transactions " +
                "WHERE DATE(transaction_date)=CURDATE() " +
                "ORDER BY transaction_date";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {

                Transaction t = new Transaction();

                t.setTransactionId(rs.getInt("transaction_id"));
                t.setAccountNumber(rs.getInt("account_number"));
                t.setTransactionType(rs.getString("transaction_type"));
                t.setAmount(rs.getDouble("amount"));
                t.setTransactionDate(rs.getTimestamp("transaction_date"));
                t.setRemarks(rs.getString("remarks"));

                list.add(t);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }



    // Monthly Transactions
    public List<Transaction> getMonthlyTransactions() {

        List<Transaction> list = new ArrayList<>();

        String sql =
                "SELECT * FROM transactions " +
                "WHERE MONTH(transaction_date)=MONTH(CURDATE()) " +
                "AND YEAR(transaction_date)=YEAR(CURDATE()) " +
                "ORDER BY transaction_date";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {

                Transaction t = new Transaction();

                t.setTransactionId(rs.getInt("transaction_id"));
                t.setAccountNumber(rs.getInt("account_number"));
                t.setTransactionType(rs.getString("transaction_type"));
                t.setAmount(rs.getDouble("amount"));
                t.setTransactionDate(rs.getTimestamp("transaction_date"));
                t.setRemarks(rs.getString("remarks"));

                list.add(t);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

}
