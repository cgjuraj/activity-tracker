package com.techelevator.dao;

import com.techelevator.model.Book;

import java.util.List;

public interface BookDao {
    void addBook (Book newBook);
    void deleteBook (String isbn);
    Book getByTile (String title);
    Book getByAuthor (String author);
    Book getByIsbn (String isbn);
    Book getBookById (int bookId);
    List<Book> getBooksByUsername (String username);
    List<Book> getAllBooks();

    }
