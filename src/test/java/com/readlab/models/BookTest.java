package com.readlab.models;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void constructor_shouldSetFields() {
        Book book = new Book("B001", "Test Title", "Test Author", "Fiction");
        assertEquals("B001", book.getBook_id());
        assertEquals("Test Title", book.getBook_title());
        assertEquals("Test Author", book.getBook_author());
        assertEquals("Fiction", book.getBook_genre());
    }

    @Test
    void getBookDetails_shouldPrintAllFields() {
        Book book = new Book("B001", "My Book", "Me", "Horror");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            book.getBookDetails();
            String output = outContent.toString();
            assertTrue(output.contains("B001"));
            assertTrue(output.contains("My Book"));
            assertTrue(output.contains("Me"));
            assertTrue(output.contains("Horror"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void readBook_shouldPrintReadingMessage() {
        Book book = new Book("B001", "My Book", "Me", "Fiction");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            book.readBook();
            String output = outContent.toString();
            assertTrue(output.contains("My Book"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void downloadBook_shouldPrintDownloadingMessage() {
        Book book = new Book("B001", "My Book", "Me", "Fiction");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            book.downloadBook();
            String output = outContent.toString();
            assertTrue(output.contains("My Book"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void setters_shouldUpdateFields() {
        Book book = new Book("B001", "Old", "Old Author", "Old Genre");
        book.setBook_id("B002");
        book.setBook_title("New");
        book.setBook_author("New Author");
        book.setBook_genre("New Genre");

        assertEquals("B002", book.getBook_id());
        assertEquals("New", book.getBook_title());
        assertEquals("New Author", book.getBook_author());
        assertEquals("New Genre", book.getBook_genre());
    }

    @Test
    void equals_shouldReturnTrueForSameBookId() {
        Book book1 = new Book("ID001", "Title A", "Author A", "Genre A");
        Book book2 = new Book("ID001", "Title B", "Author B", "Genre B");
        assertEquals(book1, book2);
    }

    @Test
    void equals_shouldReturnFalseForDifferentBookId() {
        Book book1 = new Book("ID001", "Title", "Author", "Genre");
        Book book2 = new Book("ID002", "Title", "Author", "Genre");
        assertNotEquals(book1, book2);
    }

    @Test
    void equals_shouldReturnFalseForNull() {
        Book book = new Book("ID001", "Title", "Author", "Genre");
        assertNotEquals(null, book);
    }

    @Test
    void hashCode_shouldBeConsistentWithEquals() {
        Book book1 = new Book("ID001", "Title A", "Author A", "Genre A");
        Book book2 = new Book("ID001", "Title B", "Author B", "Genre B");
        assertEquals(book1.hashCode(), book2.hashCode());
    }
}
