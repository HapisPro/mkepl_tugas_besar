package com.readlab.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    private Reader reader;

    @BeforeEach
    void setUp() {
        reader = new Reader("reader1", "pass");
    }

    @Test
    void getOwnedBooks_shouldStartEmpty() {
        assertTrue(reader.getOwnedBooks().isEmpty());
    }

    @Test
    void addBook_shouldIncreaseOwnedBooks() {
        Book book = new Book("B001", "My Book", "Author", "Fiction");
        reader.addBook(book);
        assertEquals(1, reader.getOwnedBooks().size());
        assertSame(book, reader.getOwnedBooks().get(0));
    }

    @Test
    void addBook_shouldHandleMultipleBooks() {
        reader.addBook(new Book("B001", "Book 1", "Author", "Fiction"));
        reader.addBook(new Book("B002", "Book 2", "Author", "Non-Fiction"));
        assertEquals(2, reader.getOwnedBooks().size());
    }

    @Test
    void showMenu_shouldNotThrow() {
        assertDoesNotThrow(() -> reader.showMenu());
    }

    @Test
    void constructor_shouldSetUsername() {
        assertEquals("reader1", reader.getUsername());
    }

    @Test
    void authenticate_shouldWorkForReader() {
        assertTrue(reader.authenticate("pass"));
        assertFalse(reader.authenticate("wrong"));
    }
}
