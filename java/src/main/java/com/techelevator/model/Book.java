package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    private String author;
    private String title;
    private int bookId;
    @JsonProperty("isbn")
    private String isbn;

    public Book(String author, String title, int bookId, String isbn) {
        this.author = author;
        this.title = title;
        this.bookId = bookId;
        this.isbn = isbn;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
