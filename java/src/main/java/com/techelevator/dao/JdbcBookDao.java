package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBookDao implements BookDao {
    JdbcTemplate jdbcTemplate;


    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook (Book newBook) {
        String sql = "INSERT INTO book (author, title, isbn) VALUES (?,?,?) RETURNING book_id";
        Integer newBookId;
        newBookId = jdbcTemplate.queryForObject(sql, Integer.class, newBook.getAuthor(), newBook.getTitle(), newBook.getIsbn());
   //     return getBookById(newBookId);
    }

    @Override
    public void deleteBook(String isbn) {
        String sql = "DELETE FROM book WHERE isbn = ?;";
        jdbcTemplate.update(sql, isbn);

    }

    @Override
    public Book getByTile(String title) {
        Book book = null;
        String sql = "SELECT * FROM book WHERE title ILIKE ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, title);
        if (rowSet.next()) {
            book = mapRowToBook(rowSet);
        }
        return book;
    }

    @Override
    public Book getByAuthor(String author) {
        Book book = null;
        String sql = "SELECT * FROM book WHERE author = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, author);
        if (rowSet.next()) {
            book = mapRowToBook(rowSet);
        }
        return book;
    }

    @Override
    public Book getByIsbn(String isbn) {
        Book book = null;
        String sql = "SELECT * FROM book WHERE isbn = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, isbn);
        if (rowSet.next()) {
            book = mapRowToBook(rowSet);
        }
        return book;
    }

    @Override
    public Book getBookById(int bookId) {
        Book book;
        String sql = "SELECT * FROM book WHERE book_id = ?; ";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,bookId);
        if (rowSet.next()) {
            book = mapRowToBook (rowSet);
            return book;
        }  else return null;
    }

    @Override
    public List<Book> getBooksByUsername(String username) {
        List <Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book JOIN book_users ON book_users.book_id = book.book_id JOIN users ON book_users.user_id = users.user_id WHERE username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);

        // delete the next two lines and rename rowSet2 back to rowSet once you have a book_users table with information in it
        String sql2 = "SELECT * FROM book";
        SqlRowSet rowSet2 = jdbcTemplate.queryForRowSet(sql2);
        while (rowSet2.next()) {
            Book book2 = mapRowToBook(rowSet2);
            books.add(book2);
        }
        return books;
    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            books.add(mapRowToBook(rowSet));
        }
        return books;
    }

    private Book mapRowToBook (SqlRowSet rs) {
        Book book = new Book();
        if(rs.getInt("book_Id")>0){
            book.setBookId(rs.getInt("book_Id"));
        }
        if(rs.getString("author").length()>0){
            book.setAuthor(rs.getString("author"));
        }
        if(rs.getString("title").length()>0) {
            book.setTitle(rs.getString("title"));
        }
        if(rs.getString("isbn").length()>0) {
            book.setIsbn(rs.getString("isbn"));
        } else {
            book.setIsbn("not found");
        }
        return book;
    }

}
