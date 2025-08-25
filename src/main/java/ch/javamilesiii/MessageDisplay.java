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

    public void showMenu(Card card) {
        List<String> headerLines = List.of(
                "Hello " + card.getCardOwner() + "!",
                "What can I do for you today?",
                " ",
                "Balance: " + card.getBankAccount().getBalance() + " CHF"
        );
        List<String> menuLines = List.of(
                "1 - Withdraw",
                "2 - Deposit",
                "3 - Eject Card"
        );
        showMenuLayout(headerLines, menuLines);
    }

    public void showWithdrawalMenu(Card card) {
        List<String> headerLines = List.of(
                "Withdrawal Menu",
                " ",
                "Balance: " + card.getBankAccount().getBalance() + " CHF"
        );
        List<String> menuLines = List.of(
                "1 - CHF 10",
                "2 - CHF 20",
                "3 - CHF 50",
                "4 - CHF 100",
                "5 - Custom amount",
                "6 - Cancel"
        );
        showMenuLayout(headerLines, menuLines);
    }

    private void showMenuLayout(List<String> headerLines, List<String> menuLines) {
        clear();

        List<String> allLines = new ArrayList<>(headerLines);

        int totalLines = MAX_DISPLAY_LINES - HEADER_LINES - 1;
        int spacingNeeded = totalLines - headerLines.size() - menuLines.size();
        for (int i = 0; i < spacingNeeded; i++) {
            allLines.add(" ");
        }

        allLines.addAll(menuLines);

        List<String> paddedLines = padLines(allLines);
        renderBorderedDisplay(paddedLines);
    }

    private List<String> wrapText(String message) {
        List<String> lines = new ArrayList<>();

        if (message == null || message.isEmpty()) {
            lines.add("");
            return lines;
        }

        if (message.contains("\n")) {
            String[] parts = message.split("\n");
            for (String part : parts) {
                lines.addAll(wrapText(part));
            }
            return lines;
        }

        if (message.length() <= OUTPUT_LENGTH) {
            lines.add(message);
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
        int padding = OUTPUT_LENGTH - line.length();
        return line + " ".repeat(Math.max(0, padding));
    }

    private void renderBorderedDisplay(List<String> contentLines) {
        System.out.println(BORDER);
        System.out.println(EMPTY_LINE);

        contentLines.forEach(line -> System.out.println("#    " + line + "    #"));

        int remainingLines = calculateRemainingEmptyLines(contentLines.size());
        for (int i = 0; i < remainingLines; i++) {
            System.out.println(EMPTY_LINE);
        }

        System.out.println(BORDER);
    }

    private int calculateRemainingEmptyLines(int contentLines) {
        int totalContentLines = HEADER_LINES + contentLines;
        return Math.max(0, MAX_DISPLAY_LINES - totalContentLines);
    }

    void clear() {
        System.out.println("\n".repeat(10));
    }
}