package com.homelearning;

public class NoSuchBookException extends Exception {
    private String bookName;

    public NoSuchBookException(String bookName) {
        super(bookName);
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }
}
