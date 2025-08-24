package main.java.ch.javamilesiii;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.startSimulation();
    }
    private Card[] setupTestData(){
        return new Card[]{
                new Card("738143", "4010412137105680", LocalDateTime.of(2026, 8, 31, 23, 59), "511", new BankAccount("CH9300762011623852957", "Aubrey Thomas", 1000.0)),
                new Card("612543", "3554555817992544", LocalDateTime.of(2028, 8, 31, 23, 59), "101", new BankAccount("CH5604835012345678009", "Layla Roberts", 5100.0)),
                new Card("867826", "5358964681364927", LocalDateTime.of(2030, 8, 31, 23, 59), "057", new BankAccount("CH6509000000001234567", "Abigail Peterson", 1250.0)),
                new Card("874256", "3561077130453977", LocalDateTime.of(2026, 8, 31, 23, 59), "641", new BankAccount("CH5604835012345678010", "Avery Phillips", 250.0)),
                new Card("989631", "3584633661427269", LocalDateTime.of(2030, 8, 31, 23, 59), "523", new BankAccount("CH5604835012345678011", "Sarah Taylor", 1000.0))
        };
    }
    private void startSimulation() throws InterruptedException {
        ATM atm = new ATM("ATM1");
        atm.run(setupTestData());
    }
}
