package main.java.ch.javamilesiii;

import java.util.Scanner;

public class IOHandler {
    private final Scanner scanner;

    public IOHandler() {
        scanner = new Scanner(System.in);
    }

    public int getMenuChoice() {
        return scanner.nextInt();
    }
    public String getPinInput() {
        return scanner.next();
    }
    public double getAmount(){
        return scanner.nextDouble();
    }
}
