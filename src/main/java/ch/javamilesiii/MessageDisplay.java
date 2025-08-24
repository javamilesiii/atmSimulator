package main.java.ch.javamilesiii;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MessageDisplay {
    private static final int OUTPUT_LENGTH = 60;
    private static final int MAX_DISPLAY_LINES = 19;
    private static final int HEADER_LINES = 2;
    private static final String EMPTY_LINE = "#" + " ".repeat(68) + "#";
    private static final String BORDER = "#".repeat(70);

    public void showMessage(String message) {
        clear();
        List<String> wrappedLines = wrapText(message);
        List<String> paddedLines = padLines(wrappedLines);
        renderBorderedDisplay(paddedLines);
    }

    public void showMenu(Card card){
        clear();
        List<String> headerLines = List.of(
                "Hello " + card.getCardOwner() + "!",
                "What can I do for you today?",
                " ",
                "Your actual balance is: " + card.getBankAccount().getBalance() + " CHF"
        );
        List<String> menuLines = List.of(
                "1 - Withdraw",
                "2 - Deposit",
                "3 - Eject Card"
        );
        int totalContentLines = MAX_DISPLAY_LINES - HEADER_LINES - 1;
        int emptyLinesNeeded = totalContentLines - headerLines.size() - menuLines.size();
        List<String> menuDisplay = new ArrayList<>(headerLines);
        for (int i = 0; i < emptyLinesNeeded; i++) {
            menuDisplay.add(" ");
        }
        menuDisplay.addAll(menuLines);

        List<String> paddedLines = padLines(menuDisplay);
        renderBorderedDisplay(paddedLines);
    }

    public void showWithdrawalMenu(Card card){
        clear();
        List<String> headerLines = List.of(
                "How much do you want to withdraw?",
                " ",
                " ",
                "Your actual balance is: " + card.getBankAccount().getBalance() + " CHF"
        );
        List<String> menuLines = List.of(
                "1 - CHF 10.-",
                "2 - CHF 20.-",
                "3 - CHF 50.-",
                "4 - CHF 100.-",
                "5 - Custom amount",
                "6 - Cancel"
        );
        int totalContentLines = MAX_DISPLAY_LINES - HEADER_LINES - 1;
        int emptyLinesNeeded = totalContentLines - headerLines.size() - menuLines.size();
        List<String> menuDisplay = new ArrayList<>(headerLines);
        for (int i = 0; i < emptyLinesNeeded; i++) {
            menuDisplay.add(" ");
        }
        menuDisplay.addAll(menuLines);

        List<String> paddedLines = padLines(menuDisplay);
        renderBorderedDisplay(paddedLines);
    }

    private List<String> wrapText(String message) {
        List<String> lines = new ArrayList<>();

        if (message.length() <= OUTPUT_LENGTH) {
            lines.add(message);
            return lines;
        }

        if (message.contains("\n")) {
            String[] splitMessages = message.split("\n");
            for (String splitMessage : splitMessages) {
                lines.addAll(wrapText(splitMessage));
            }
            return lines;
        }

        String[] words = message.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > OUTPUT_LENGTH) {
                if (!currentLine.isEmpty()) {
                    lines.add(currentLine.toString());
                    currentLine = new StringBuilder();
                }
            }

            if (!currentLine.isEmpty()) {
                currentLine.append(" ");
            }
            currentLine.append(word);
        }

        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    private List<String> padLines(List<String> lines) {
        return lines.stream()
                .map(this::padLine)
                .collect(Collectors.toList());
    }

    private String padLine(String line) {
        int paddingNeeded = MessageDisplay.OUTPUT_LENGTH - line.length();
        return line + " ".repeat(Math.max(0, paddingNeeded));
    }

    private void renderBorderedDisplay(List<String> contentLines) {
        printBorder();
        printEmptyLine();

        contentLines.forEach(this::printContentLine);

        int remainingLines = calculateRemainingEmptyLines(contentLines.size());
        printEmptyLines(remainingLines);

        printBorder();
    }

    private void printBorder() {
        System.out.println(BORDER);
    }

    private void printEmptyLine() {
        System.out.println(EMPTY_LINE);
    }

    private void printContentLine(String content) {
        System.out.println("#    " + content + "    #");
    }

    private void printEmptyLines(int count) {
        for (int i = 0; i < count; i++) {
            printEmptyLine();
        }
    }

    private int calculateRemainingEmptyLines(int contentLines) {
        int totalContentLines = HEADER_LINES + contentLines;
        return Math.max(0, MAX_DISPLAY_LINES - totalContentLines);
    }

    void clear() {
        /*System.out.print("\033[H\033[2J");
        System.out.flush();*/
        System.out.println("\n".repeat(10));
    }
}