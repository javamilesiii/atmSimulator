package main.java.ch.javamilesiii;

import java.time.LocalDateTime;

public class Card {
    private final String pin;
    private final String cardNumber;
    private final LocalDateTime expirationDate;
    private final String cvv;
    private final BankAccount bankAccount;

    public Card(String pin, String cardNumber, LocalDateTime expirationDate, String cvv, BankAccount account) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.pin = pin;
        this.bankAccount = account;
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
    public boolean validateCVV(String cvv) {
        return this.cvv.equals(cvv);
    }
    public String getCardOwner() {
        return bankAccount.getOwner();
    }
    public BankAccount getBankAccount() {
        return bankAccount;
    }
}