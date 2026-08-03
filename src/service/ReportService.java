package service;

import dao.ReportDAO;
import model.Transaction;

import java.util.List;

public class ReportService {

    private ReportDAO reportDAO = new ReportDAO();

    public int getTotalCustomers() {
        return reportDAO.getTotalCustomers();
    }

    public int getTotalAccounts() {
        return reportDAO.getTotalAccounts();
    }

    public double getHighestBalance() {
        return reportDAO.getHighestBalance();
    }

    public double getLowestBalance() {
        return reportDAO.getLowestBalance();
    }

    public double getTotalDeposits() {
        return reportDAO.getTotalDeposits();
    }

    public double getTotalWithdrawals() {
        return reportDAO.getTotalWithdrawals();
    }

    public List<Transaction> getTodaysTransactions() {
        return reportDAO.getTodaysTransactions();
    }

    public List<Transaction> getMonthlyTransactions() {
        return reportDAO.getMonthlyTransactions();
    }
}
