package com.readlab.models;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    @Test
    void constructor_shouldSetFields() {
        Book book = new Book("B001", "Test Book", "Author", "Fiction");
        Reader reader = new Reader("user", "pass");
        Note note = new Note(book, reader, 100, "My note content");

        assertSame(book, note.getBook());
        assertSame(reader, note.getReader());
        assertEquals(100, note.getNote_id());
        assertEquals("My note content", note.getNote());
    }

    @Test
    void PrintNote_shouldOutputBookNameIdAndContent() {
        Book book = new Book("B001", "My Book", "Author", "Fiction");
        Reader reader = new Reader("user", "pass");
        Note note = new Note(book, reader, 42, "Important thoughts");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            note.PrintNote();
            String output = outContent.toString();
            assertTrue(output.contains("My Book"));
            assertTrue(output.contains("42"));
            assertTrue(output.contains("Important thoughts"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void setNote_id_shouldUpdateId() {
        Book book = new Book("B001", "Test", "Author", "Fiction");
        Reader reader = new Reader("user", "pass");
        Note note = new Note(book, reader, 1, "content");
        note.setNote_id(200);
        assertEquals(200, note.getNote_id());
    }

    @Test
    void setNote_shouldUpdateContent() {
        Book book = new Book("B001", "Test", "Author", "Fiction");
        Reader reader = new Reader("user", "pass");
        Note note = new Note(book, reader, 1, "old content");
        note.setNote("new content");
        assertEquals("new content", note.getNote());
    }
}
