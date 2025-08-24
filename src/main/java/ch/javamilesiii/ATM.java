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
        messageDisplay.showMessage("Please insert your card:");
        System.out.println("Enter 1 to insert a card, 0 to exit:");
        int choice = ioHandler.getMenuChoice();
        if (choice == 0){
            System.out.println("Goodbye!");
            return;
        }
        int cardChoice = 0;
        do {
            messageDisplay.clear();
            messageDisplay.showMessage("Please insert your card:");
            for (int i = 0; i < cards.length; i++) {
                System.out.println("Card " + (i+1) + ": " + cards[i].getBankAccount().getOwner());
            }
            cardChoice = ioHandler.getMenuChoice();
            if (cardChoice > cards.length || cardChoice < 1){
                messageDisplay.showMessage("Invalid card choice. Please try again.");
                continue;
            }
        } while (cardChoice > cards.length || cardChoice < 1);
        insertCard(cards[cardChoice-1]);
        boolean passed = false;
        do {
            messageDisplay.showMessage("Please enter your PIN:");
            if (authenticateUser()){
                messageDisplay.showMessage("Invalid PIN. Please try again.");
                continue;
            }
            passed = true;
        } while (!passed);
        while (true) {
            messageDisplay.showMenu(currentCard);
            //messageDisplay.showMessage("this is a test to see if the messageDisplay works while cutting into 44 symbols per line");
            int menuChoice = ioHandler.getMenuChoice();
            switch (menuChoice) {
                case 1:
                    withdrawTransaction();
                    break;
                case 2:
                    depositTransaction();
                    break;
                case 3:
                    ejectCard();
                    messageDisplay.showMessage("Goodbye!");
                    return;
                default:
                    messageDisplay.showMessage("Invalid choice. Please try again.");
                    Thread.sleep(2000);
                    break;
            }
        }
    }
    public boolean insertCard(Card card){
        if (card == null) {
            return false;
        }
        this.currentCard = card;
        return true;
    }
    public boolean authenticateUser(){
        if (currentCard == null) {
            return true;
        }
        return !currentCard.validatePin(ioHandler.getPinInput());
    }
    public void withdrawTransaction() throws InterruptedException {
        messageDisplay.showWithdrawalMenu(currentCard);
        int choice = ioHandler.getMenuChoice();
        switch (choice) {
            case 1:
                currentCard.getBankAccount().withdraw(10);
                messageDisplay.showMessage("Transaction successful. Please take your cash.\nYour new balance is: " + currentCard.getBankAccount().getBalance() + " CHF");
                Thread.sleep(5000);
                break;
            case 2:
                currentCard.getBankAccount().withdraw(20);
                messageDisplay.showMessage("Transaction successful. Please take your cash.\nYour new balance is: " + currentCard.getBankAccount().getBalance() + " CHF");
                Thread.sleep(5000);
                break;
            case 3:
                currentCard.getBankAccount().withdraw(50);
                messageDisplay.showMessage("Transaction successful. Please take your cash.\nYour new balance is: " + currentCard.getBankAccount().getBalance() + " CHF");
                Thread.sleep(5000);
                break;
            case 4:
                currentCard.getBankAccount().withdraw(100);
                messageDisplay.showMessage("Transaction successful. Please take your cash.\nYour new balance is: " + currentCard.getBankAccount().getBalance() + " CHF");
                Thread.sleep(5000);
                break;
            case 5:
                messageDisplay.showMessage("Please enter the amount you want to withdraw:");
                double amount = ioHandler.getAmount();
                currentCard.getBankAccount().withdraw(amount);
                messageDisplay.showMessage("Transaction successful. Please take your cash.\nYour new balance is: " + currentCard.getBankAccount().getBalance() + " CHF");
                Thread.sleep(5000);
                break;
            case 6:
                messageDisplay.showMessage("Transaction canceled.");
                Thread.sleep(2000);
                break;
            default:
                messageDisplay.showMessage("Invalid choice. Please try again.");
                Thread.sleep(2000);
                break;
        }

    }
    public void depositTransaction() throws InterruptedException {
        messageDisplay.showMessage("Please enter the amount you want to deposit:");
        double amount = ioHandler.getAmount();
        currentCard.getBankAccount().deposit(amount);
        messageDisplay.showMessage("Transaction successful. Thank you for your deposit.\nYour new balance is: " + currentCard.getBankAccount().getBalance() + " CHF");
        Thread.sleep(5000);

    }
    public void ejectCard(){
        this.currentCard = null;
    }
}
