package service;

import dao.AccountDAO;
import dao.TransactionDAO;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import model.Account;
import model.Transaction;
import util.ValidationUtil;

import java.sql.Timestamp;
import java.util.List;

public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAO();
    private AccountDAO accountDAO = new AccountDAO();

    // Deposit
    public boolean deposit(int accountNumber, double amount, String remarks) {

        if (!ValidationUtil.isValidAmount(amount)) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        Account account = accountDAO.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }

        if (!account.getStatus().equalsIgnoreCase("Active")) {
            throw new AccountNotFoundException("Account is closed.");
        }

        double newBalance = account.getBalance() + amount;

        if (!accountDAO.updateBalance(accountNumber, newBalance)) {
            return false;
        }

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType("Deposit");
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        transaction.setRemarks(remarks);

        return transactionDAO.deposit(transaction);
    }

    // Withdraw
    public boolean withdraw(int accountNumber, double amount, String remarks) {

        if (!ValidationUtil.isValidAmount(amount)) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        Account account = accountDAO.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }

        if (!account.getStatus().equalsIgnoreCase("Active")) {
            throw new AccountNotFoundException("Account is closed.");
        }

        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }

        double newBalance = account.getBalance() - amount;

        if (!accountDAO.updateBalance(accountNumber, newBalance)) {
            return false;
        }

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setTransactionType("Withdraw");
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        transaction.setRemarks(remarks);

        return transactionDAO.withdraw(transaction);
    }

    // Transfer
    public boolean transfer(int fromAccount, int toAccount, double amount) {

        if (!ValidationUtil.isValidAmount(amount)) {
            throw new InvalidAmountException("Amount must be greater than zero.");
        }

        if (fromAccount == toAccount) {
            throw new InvalidAmountException("Sender and Receiver accounts cannot be the same.");
        }

        Account sender = accountDAO.getAccount(fromAccount);
        Account receiver = accountDAO.getAccount(toAccount);

        if (sender == null) {
            throw new AccountNotFoundException("Sender account not found.");
        }

        if (receiver == null) {
            throw new AccountNotFoundException("Receiver account not found.");
        }

        if (!sender.getStatus().equalsIgnoreCase("Active")) {
            throw new AccountNotFoundException("Sender account is closed.");
        }

        if (!receiver.getStatus().equalsIgnoreCase("Active")) {
            throw new AccountNotFoundException("Receiver account is closed.");
        }

        if (sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }

        boolean debit = accountDAO.updateBalance(
                fromAccount,
                sender.getBalance() - amount);

        boolean credit = accountDAO.updateBalance(
                toAccount,
                receiver.getBalance() + amount);

        if (!(debit && credit)) {
            return false;
        }

        // Sender Transaction
        Transaction senderTransaction = new Transaction();
        senderTransaction.setAccountNumber(fromAccount);
        senderTransaction.setTransactionType("Transfer");
        senderTransaction.setAmount(amount);
        senderTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        senderTransaction.setRemarks("Transferred to Account " + toAccount);

        // Receiver Transaction
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setAccountNumber(toAccount);
        receiverTransaction.setTransactionType("Transfer");
        receiverTransaction.setAmount(amount);
        receiverTransaction.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        receiverTransaction.setRemarks("Received from Account " + fromAccount);

        transactionDAO.transfer(senderTransaction);
        transactionDAO.transfer(receiverTransaction);

        return true;
    }

    // Balance Enquiry
    public double getBalance(int accountNumber) {

        Account account = accountDAO.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }

        return account.getBalance();
    }

    // Mini Statement
    public List<Transaction> getMiniStatement(int accountNumber) {

        Account account = accountDAO.getAccount(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found.");
        }

        return transactionDAO.getTransactionHistory(accountNumber);
    }
}
