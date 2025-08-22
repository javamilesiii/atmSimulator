package main.java.ch.javamilesiii;

public class BankAccount {
    private int balance;
    private String accountNumber;
    private String accountHolder;
    private String password;
    private String iban;
    private String bankName;

    public BankAccount(int balance, String accountNumber, String accountHolder, String password, String iban, String bankName) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.password = password;
        this.iban = iban;
        this.bankName = bankName;
    }

    public int getBalance() {
        return balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountHolder() {
        return accountHolder;
    }
    public String getPassword() {
        return password;
    }
    public String getIban() {
        return iban;
    }
    public String getBankName() {
        return bankName;
    }
}
