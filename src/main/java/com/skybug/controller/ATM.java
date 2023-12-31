package com.skybug.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.skybug.dto.BankAccount;

/**
 * Servlet implementation class ATM
 */

@WebServlet("/ATM")
public class ATM extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BankAccount userAccount;

    public ATM() {
        super();
        userAccount = new BankAccount(); // Assuming BankAccount class is implemented
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("withdraw".equals(action)) {
            withdraw(request, response);
        } else if ("deposit".equals(action)) {
            deposit(request, response);
        } else if ("checkBalance".equals(action)) {
            checkBalance(request, response);
        }
    }

    private void withdraw(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve amount from the request
        double amount = Double.parseDouble(request.getParameter("amount"));

        // Validate and process withdrawal
        if (userAccount.validateWithdrawal(amount)) {
            userAccount.withdraw(amount);
            response.getWriter().write("Withdrawal successful. Remaining balance: " + userAccount.getBalance());
        } else {
            response.getWriter().write("Insufficient funds for withdrawal.");
        }
    }

    private void deposit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve amount from the request
        double amount = Double.parseDouble(request.getParameter("amount"));

        // Process deposit
        userAccount.deposit(amount);
        response.getWriter().write("Deposit successful. New balance: " + userAccount.getBalance());
    }

    private void checkBalance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display current balance
        response.getWriter().write("Current balance: " + userAccount.getBalance());
    }
}