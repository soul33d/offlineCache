package com.homelearning;

import java.io.IOException;
import java.util.*;

public class BooksLibrary {
    private Map<String, String> books;
    private FileHelper fileHelper;
    private LibraryCache<String, String> cache;

    public BooksLibrary() {
        books = new HashMap<String, String>(){
            @Override
            public String put(String key, String value) {
                return super.put(key.toUpperCase(), value);
            }

            @Override
            public String get(Object key) {
                if (key instanceof String) key = ((String) key).toUpperCase();
                return super.get(key);
            }
        };
        fileHelper = new FileHelper();
        cache = new LibraryCache<>(3);
        books.put("1984", "assets/1984 by George Orwell");
        books.put("Dorian Gray", "assets/O Wilde The picture of Dorian Gray");
        books.put("Harry Potter", "assets/Rowling_J._Harry_Potter_1.txt");
        books.put("Three Men in a Boat", "assets/Three Men in a Boat by Jerome K. Jerome.txt");
        books.put("jls8", "assets/jls8");
        books.put("jvms8", "assets/jvms8");
    }

    public Set<String> getTitles(){
        return books.keySet();
    }

    public String getBiggestWord(String bookName) throws NoSuchBookException {
        long start = System.currentTimeMillis();
        if (books.get(bookName) == null) throw new NoSuchBookException(bookName);
        bookName = bookName.toUpperCase();
        String result = cache.get(bookName);
        if (result != null) {
            System.err.println("From cache.");
            System.err.println((System.currentTimeMillis() - start) + " ms.");
            return result;
        }
        String bookText = readTextFromFile(bookName);
        bookText = bookText.replaceAll("[\n\t]|[-]|[—]", " ");
        bookText = bookText.replaceAll("[\\p{Punct}]|[«]|[»]|[”]|[“]", "");
        result = Arrays.stream(bookText.split(" ")).max(Comparator.comparingInt(String::length)).orElse("");
        cache.put(bookName, result);
        System.err.println("Calculated.");
        System.err.println((System.currentTimeMillis() - start) + " ms.");
        return result;
    }

    private String readTextFromFile(String bookName) {
        String bookText = "";
        try {
             bookText = fileHelper.readFile(books.get(bookName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookText;
    }
}
