package com.readlab.models;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkTest {

    @Test
    void constructor_shouldSetFields() {
        Book book = new Book("B001", "Test Book", "Author", "Fiction");
        Reader reader = new Reader("user", "pass");
        Bookmark bookmark = new Bookmark(book, reader, 5);

        assertSame(book, bookmark.getBook());
        assertSame(reader, bookmark.getReader());
        assertEquals(5, bookmark.getBookmark_id());
    }

    @Test
    void PrintBookmark_shouldOutputBookNameAndId() {
        Book book = new Book("B001", "My Book", "Author", "Fiction");
        Reader reader = new Reader("user", "pass");
        Bookmark bookmark = new Bookmark(book, reader, 3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            bookmark.PrintBookmark();
            String output = outContent.toString();
            assertTrue(output.contains("My Book"));
            assertTrue(output.contains("3"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void setBookmark_id_shouldUpdateId() {
        Book book = new Book("B001", "Test", "Author", "Fiction");
        Reader reader = new Reader("user", "pass");
        Bookmark bookmark = new Bookmark(book, reader, 1);
        bookmark.setBookmark_id(10);
        assertEquals(10, bookmark.getBookmark_id());
    }
}
