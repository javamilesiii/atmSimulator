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
                "Hello "+card.getCardOwner()+"!",
                "What can I do for you today?",
                " ",
                "Your actual balance is: " + card.getBankAccount().getBalance() + " CHF"
        );
        List<String> menuLines = List.of(
                "1 - Withdraw",
                "2 - Deposit",
                "3 - Eject Card"
        );
        List<String> emptyLines = List.of(
                "\n".repeat(MessageDisplay.MAX_DISPLAY_LINES - headerLines.size() - menuLines.size())
        );
        List<String> menuDisplay = new ArrayList<>();
        menuDisplay.addAll(headerLines);
        menuDisplay.addAll(emptyLines);
        menuDisplay.addAll(menuLines);
        renderBorderedDisplay(menuDisplay);
    }

    private List<String> wrapText(String message) {
        List<String> lines = new ArrayList<>();

        if (message.length() <= MessageDisplay.OUTPUT_LENGTH) {
            lines.add(message);
            return lines;
        }
        if (message.contains("\n")) {
            String[] splitMessages = message.split("\n");
            for (String splitMessage : splitMessages) {
                if (splitMessage.length() <= MessageDisplay.OUTPUT_LENGTH) {
                    lines.add(splitMessage);
                    continue;
                }
                List<String> wrappedLines = wrapText(splitMessage);
                lines.addAll(wrappedLines);
                return lines;
            }
        }
        String[] words = message.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > MessageDisplay.OUTPUT_LENGTH) {
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