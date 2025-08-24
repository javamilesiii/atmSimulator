package main.java.ch.javamilesiii;

public class ATM {
    private Card currentCard;
    private final MessageDisplay messageDisplay;
    private final IOHandler ioHandler;

    public ATM(String atmId) {
        this.messageDisplay = new MessageDisplay();
        this.ioHandler = new IOHandler();
    }
    public void run(Card[] cards){
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
    public void withdrawTransaction(){

    }
    public void depositTransaction(){

    }
    public void ejectCard(){
        this.currentCard = null;
    }
}
