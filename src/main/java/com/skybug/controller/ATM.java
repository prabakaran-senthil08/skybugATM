package com.skybug.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.skybug.dto.BankAccount;

@WebServlet("/ATM")
public class ATM extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BankAccount userAccount;

    public ATM() {
        super();
        userAccount = new BankAccount(); 
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
    	response.setContentType("text/html");
        double amount = Double.parseDouble(request.getParameter("amount"));
        if (userAccount.validateWithdrawal(amount)) {
            userAccount.withdraw(amount);
           response.getWriter().write("Withdrawal successful. Remaining balance: " + userAccount.getBalance() +"\n <a href='atm.html'>Home</a>");
        } else {
            response.getWriter().write("Insufficient funds for withdrawal.\n <a href='atm.html'>Home</a>");
        }
    }

    private void deposit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
        double amount = Double.parseDouble(request.getParameter("amount"));
        userAccount.deposit(amount);
        response.getWriter().write("Deposit successful. New balance: " + userAccount.getBalance()+"\n <a href='atm.html'>Home</a>");
    }

    private void checkBalance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html");
        response.getWriter().write("Current balance: " + userAccount.getBalance() + "\n <a href='atm.html'>Home</a>");
    }
}