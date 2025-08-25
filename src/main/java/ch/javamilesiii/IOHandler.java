package main.java.ch.javamilesiii;

import java.util.Scanner;

public class IOHandler {
    private final Scanner scanner;

    public IOHandler() {
        scanner = new Scanner(System.in);
    }

    public int getMenuChoice() {
        while (true) {
            try {
                System.out.print("Choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public String getPinInput() {
        System.out.print("PIN: ");
        return scanner.nextLine().trim();
    }

    public double getAmount() {
        while (true) {
            try {
                System.out.print("Amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount > 0) {
                    return amount;
                }
                System.out.println("Amount must be positive.");
            } catch (Exception e) {
                System.out.println("Please enter a valid amount.");
                scanner.nextLine();
            }
        }
    }
}