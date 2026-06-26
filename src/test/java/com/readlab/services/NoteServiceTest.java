package com.readlab.services;

import com.readlab.models.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {

    private NoteService noteService;
    private Reader reader;
    private Reader otherReader;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        noteService = new NoteService();
        reader = new Reader("testReader", "pass");
        otherReader = new Reader("otherReader", "pass");
        book1 = new Book("B001", "Test Book One", "Author A", "Fiction");
        book2 = new Book("B002", "Test Book Two", "Author B", "Non-Fiction");
    }

    @Test
    void TambahNote_shouldStartNoteIdAt100() {
        noteService.TambahNote(book1, reader, "First note");
        assertEquals(100, noteService.getNotelist().get(0).getNote_id());
    }

    @Test
    void TambahNote_shouldIncrementNoteId() {
        noteService.TambahNote(book1, reader, "First note");
        noteService.TambahNote(book2, reader, "Second note");
        assertEquals(101, noteService.getNotelist().get(1).getNote_id());
    }

    @Test
    void TambahNote_shouldStoreContent() {
        noteService.TambahNote(book1, reader, "My important note");
        assertEquals("My important note", noteService.getNotelist().get(0).getNote());
    }

    @Test
    void TambahNote_shouldLinkToCorrectBook() {
        noteService.TambahNote(book1, reader, "Note for book1");
        assertSame(book1, noteService.getNotelist().get(0).getBook());
    }

    @Test
    void TambahNote_shouldLinkToCorrectReader() {
        noteService.TambahNote(book1, reader, "Note by reader");
        assertSame(reader, noteService.getNotelist().get(0).getReader());
    }

    @Test
    void TambahNote_shouldHandleMultipleReaders() {
        noteService.TambahNote(book1, reader, "Reader's note");
        noteService.TambahNote(book1, otherReader, "Other's note");
        assertEquals(2, noteService.getNotelist().size());
    }

    @Test
    void DeleteNote_shouldRemoveExistingNote() {
        noteService.TambahNote(book1, reader, "To delete");
        int noteId = noteService.getNotelist().get(0).getNote_id();
        assertEquals(1, noteService.getNotelist().size());

        noteService.DeleteNote(noteId);
        assertTrue(noteService.getNotelist().isEmpty());
    }

    @Test
    void DeleteNote_shouldNotThrowOnNonExistentId() {
        noteService.TambahNote(book1, reader, "Some note");
        assertDoesNotThrow(() -> noteService.DeleteNote(999));
        assertEquals(1, noteService.getNotelist().size());
    }

    @Test
    void DeleteNote_shouldNotThrowOnEmptyList() {
        assertDoesNotThrow(() -> noteService.DeleteNote(1));
        assertTrue(noteService.getNotelist().isEmpty());
    }

    @Test
    void printallNote_shouldPrintOnlyReadersNotes() {
        noteService.TambahNote(book1, reader, "Reader's note");
        noteService.TambahNote(book2, otherReader, "Other's note");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            noteService.printallNote(reader);
            String output = outContent.toString();
            assertTrue(output.contains("Reader's note"));
            assertFalse(output.contains("Other's note"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void printallNote_shouldNotPrintWhenReaderHasNoNotes() {
        noteService.TambahNote(book1, otherReader, "Only other's note");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            noteService.printallNote(reader);
            String output = outContent.toString();
            assertEquals(0, output.trim().length());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void printallNote_shouldNotPrintWhenListIsEmpty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            noteService.printallNote(reader);
            String output = outContent.toString();
            assertEquals(0, output.trim().length());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void getNotelist_shouldReturnInternalList() {
        assertTrue(noteService.getNotelist().isEmpty());
        noteService.TambahNote(book1, reader, "A note");
        assertEquals(1, noteService.getNotelist().size());
    }

    @Test
    void DeleteNote_shouldOnlyRemoveSpecifiedNote() {
        noteService.TambahNote(book1, reader, "First");
        noteService.TambahNote(book2, reader, "Second");
        noteService.TambahNote(book1, reader, "Third");

        noteService.DeleteNote(100);
        assertEquals(2, noteService.getNotelist().size());
        assertEquals(101, noteService.getNotelist().get(0).getNote_id());
        assertEquals(102, noteService.getNotelist().get(1).getNote_id());
    }
}
