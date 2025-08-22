package main.java.ch.javamilesiii;

import java.time.LocalDateTime;

public class Card {
    private String cardNumber;
    private String cardHolder;
    private LocalDateTime expirationDate;
    private int cvv;
    private int pin;
    private BankAccount bankAccount;

    public Card(String cardNumber, String cardHolder, LocalDateTime expirationDate, int cvv, int pin) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.pin = pin;
        this.bankAccount = new BankAccount(0, "0000000000", "No Name", "0000", "CH0000000000000000000", "No Bank");
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public String getCardHolder() {
        return cardHolder;
    }
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }
    public int getCvv() {
        return cvv;
    }
    public int getPin() {
        return pin;
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
