package com.skybug.dto;

public class BankAccount {
    private double balance;

    public BankAccount() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean validateWithdrawal(double amount) {
        return amount <= balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}