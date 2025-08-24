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
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }
    public String getIban() {
        return iban;
    }
    public String getOwner() {
        return owner;
    }
    public double getBalance() {
        return balance;
    }
}
