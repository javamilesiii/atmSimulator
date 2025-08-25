package main.java.ch.javamilesiii;

public class ATM {
    private Card currentCard;
    private final MessageDisplay messageDisplay;
    private final IOHandler ioHandler;

    public ATM(String atmId) {
        this.messageDisplay = new MessageDisplay();
        this.ioHandler = new IOHandler();
    }

    public void run(Card[] cards) throws InterruptedException {
        if (!selectCard(cards)) {
            return;
        }

        if (!authenticateUser()) {
            return;
        }
        if (isCardExpired()) {
            return;
        }

        runMainLoop();
        cleanup();
    }

    private boolean selectCard(Card[] cards) throws InterruptedException {
        messageDisplay.showMessage("Welcome! Insert your card:");
        System.out.println("1 - Insert card, 0 - Exit");

        if (ioHandler.getMenuChoice() == 0) {
            messageDisplay.showMessage("Goodbye!");
            return false;
        }

        int cardChoice;
        do {
            showCardOptions(cards);
            cardChoice = ioHandler.getMenuChoice();

            if (cardChoice < 1 || cardChoice > cards.length) {
                messageDisplay.showMessage("Invalid choice. Try again.");
                Thread.sleep(1500);
                continue;
            }
            break;
        } while (true);

        insertCard(cards[cardChoice - 1]);
        return true;
    }

    private void showCardOptions(Card[] cards) {
        messageDisplay.clear();
        messageDisplay.showMessage("Select your card:");
        for (int i = 0; i < cards.length; i++) {
            System.out.println((i + 1) + " - " + cards[i].getBankAccount().getOwner());
        }
    }

    private boolean authenticateUser() throws InterruptedException {
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            messageDisplay.showMessage("Enter your PIN:");
            String pin = ioHandler.getPinInput();

            if (currentCard.validatePin(pin)) {
                return true;
            }

            attempts++;
            if (attempts < MAX_ATTEMPTS) {
                messageDisplay.showMessage("Wrong PIN. " + (MAX_ATTEMPTS - attempts) + " attempts left.");
                Thread.sleep(2000);
            }
        }

        messageDisplay.showMessage("Too many wrong attempts. Card blocked.");
        return false;
    }

    private boolean isCardExpired() throws InterruptedException {
        if (!currentCard.isExpired()) return false;
        messageDisplay.showMessage("Your card is expired.");
        Thread.sleep(2000);
        return currentCard.isExpired();
    }

    private void runMainLoop() throws InterruptedException {
        while (true) {
            messageDisplay.showMenu(currentCard);
            int choice = ioHandler.getMenuChoice();

            switch (choice) {
                case 1 -> handleWithdrawal();
                case 2 -> handleDeposit();
                case 3 -> {
                    messageDisplay.showMessage("Thank you! Goodbye!");
                    return;
                }
                default -> {
                    messageDisplay.showMessage("Invalid choice. Try again.");
                    Thread.sleep(1500);
                }
            }
        }
    }

    private void handleWithdrawal() throws InterruptedException {
        messageDisplay.showWithdrawalMenu(currentCard);
        int choice = ioHandler.getMenuChoice();

        double amount = switch (choice) {
            case 1 -> 10.0;
            case 2 -> 20.0;
            case 3 -> 50.0;
            case 4 -> 100.0;
            case 5 -> {
                messageDisplay.showMessage("Enter withdrawal amount:");
                yield ioHandler.getAmount();
            }
            case 6 -> {
                messageDisplay.showMessage("Withdrawal cancelled.");
                Thread.sleep(1500);
                yield -1;
            }
            default -> {
                messageDisplay.showMessage("Invalid choice.");
                Thread.sleep(1500);
                yield -1;
            }
        };

        if (amount == -1) return;

        if (currentCard.getBankAccount().withdraw(amount)) {
            showSuccessMessage("Take your cash");
        } else {
            messageDisplay.showMessage("Transaction failed. Check your balance.");
            Thread.sleep(2000);
        }
    }

    private void handleDeposit() throws InterruptedException {
        messageDisplay.showMessage("Enter deposit amount:");
        double amount = ioHandler.getAmount();

        currentCard.getBankAccount().deposit(amount);
        showSuccessMessage("Deposit successful");
    }

    private void showSuccessMessage(String message) throws InterruptedException {
        double newBalance = currentCard.getBankAccount().getBalance();
        messageDisplay.showMessage(message + "\nNew balance: " + newBalance + " CHF");
        Thread.sleep(3000);
    }

    private void insertCard(Card card) {
        this.currentCard = card;
    }

    private void cleanup() {
        this.currentCard = null;
    }
}