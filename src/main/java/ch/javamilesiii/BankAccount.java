package main.java.ch.javamilesiii;

public class BankAccount {
    private final String iban;
    private final String owner;
    private double balance;

    public BankAccount(String iban, String owner, double initialBalance) {
        this.iban = iban;
        this.owner = owner;
        this.balance = initialBalance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount must be positive");
            return false;
        }
        if (amount > balance) {
            System.out.println("Error: Insufficient funds");
            return false;
        }
        balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount must be positive");
            return false;
        }
        balance += amount;
        return true;
    }

    public String getIban() { return iban; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
}