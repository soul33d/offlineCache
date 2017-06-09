package com.homelearning;

import java.util.Scanner;

public class TerminalHelper {
    public static final String EXIT_INPUT_KEY = "exit";
    private BooksLibrary booksLibrary;
    private Scanner scanner;

    public TerminalHelper() {
        booksLibrary = new BooksLibrary();
        scanner = new Scanner(System.in);
    }

    public void execute(){
        printLibrary();
        printMenu();
        printBiggestWord(readBookNameFromInput());
    }

    @SuppressWarnings("InfiniteRecursion")
    private void printBiggestWord(String bookName) {
        if (bookName.equals(EXIT_INPUT_KEY)) {
            scanner.close();
            System.exit(0);
        }
        try {
            System.out.println(booksLibrary.getBiggestWord(bookName));
        } catch (NoSuchBookException e) {
            System.err.println("There is no " + e.getBookName() + " in library!!!");
            printBiggestWord(readBookNameFromInput());
        }
        printBiggestWord(readBookNameFromInput());
    }

    private String readBookNameFromInput() {
        return scanner.nextLine();
    }

    public void printLibrary(){
        System.out.println("Books in the library:");
        System.out.println(booksLibrary.getTitles());
    }

    private void printMenu() {
        System.out.println
                ("Enter name of the book to get the biggest word or '" + EXIT_INPUT_KEY + "' - to close program.");
    }
}
