package com.readlab.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author("auth1", "pass123");
    }

    @Test
    void getPublishedBooks_shouldStartEmpty() {
        assertTrue(author.getPublishedBooks().isEmpty());
    }

    @Test
    void addBook_shouldIncreasePublishedBooks() {
        Book book = new Book("B001", "My Book", "auth1", "Fiction");
        author.addBook(book);
        assertEquals(1, author.getPublishedBooks().size());
        assertSame(book, author.getPublishedBooks().get(0));
    }

    @Test
    void addBook_shouldHandleMultipleBooks() {
        author.addBook(new Book("B001", "Book 1", "auth1", "Fiction"));
        author.addBook(new Book("B002", "Book 2", "auth1", "Non-Fiction"));
        author.addBook(new Book("B003", "Book 3", "auth1", "Science"));
        author.addBook(new Book("B004", "Book 4", "auth1", "Fiction"));
        assertEquals(4, author.getPublishedBooks().size());
    }

    @Test
    void getPublishedBooks_shouldReturnModifiableList() {
        Book book = new Book("B001", "My Book", "auth1", "Fiction");
        author.addBook(book);
        assertDoesNotThrow(() -> author.getPublishedBooks().add(book));
    }

    @Test
    void showMenu_shouldNotThrow() {
        assertDoesNotThrow(() -> author.showMenu());
    }

    @Test
    void constructor_shouldSetUsername() {
        assertEquals("auth1", author.getUsername());
    }

    @Test
    void authenticate_shouldWorkForAuthor() {
        assertTrue(author.authenticate("pass123"));
        assertFalse(author.authenticate("wrong"));
    }
}
