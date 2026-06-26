package com.readlab.services;

import com.readlab.models.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkServiceTest {

    private BookmarkService bookmarkService;
    private Reader reader;
    private Reader otherReader;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        bookmarkService = new BookmarkService();
        reader = new Reader("testReader", "pass");
        otherReader = new Reader("otherReader", "pass");
        book1 = new Book("B001", "Test Book One", "Author A", "Fiction");
        book2 = new Book("B002", "Test Book Two", "Author B", "Non-Fiction");
    }

    @Test
    void TambahBookmark_shouldStartBookmarkIdAt1() {
        bookmarkService.TambahBookmark(book1, reader);
        assertEquals(1, bookmarkService.getBookmarklist().get(0).getBookmark_id());
    }

    @Test
    void TambahBookmark_shouldIncrementBookmarkId() {
        bookmarkService.TambahBookmark(book1, reader);
        bookmarkService.TambahBookmark(book2, reader);
        assertEquals(2, bookmarkService.getBookmarklist().get(1).getBookmark_id());
    }

    @Test
    void TambahBookmark_shouldLinkToCorrectBook() {
        bookmarkService.TambahBookmark(book1, reader);
        assertSame(book1, bookmarkService.getBookmarklist().get(0).getBook());
    }

    @Test
    void TambahBookmark_shouldLinkToCorrectReader() {
        bookmarkService.TambahBookmark(book1, reader);
        assertSame(reader, bookmarkService.getBookmarklist().get(0).getReader());
    }

    @Test
    void TambahBookmark_shouldPreventDuplicateBookmark() {
        bookmarkService.TambahBookmark(book1, reader);
        bookmarkService.TambahBookmark(book1, reader);
        assertEquals(1, bookmarkService.getBookmarklist().size());
    }

    @Test
    void TambahBookmark_shouldAllowSameBookForDifferentReaders() {
        bookmarkService.TambahBookmark(book1, reader);
        bookmarkService.TambahBookmark(book1, otherReader);
        assertEquals(2, bookmarkService.getBookmarklist().size());
    }

    @Test
    void TambahBookmark_shouldAllowDifferentBooksForSameReader() {
        bookmarkService.TambahBookmark(book1, reader);
        bookmarkService.TambahBookmark(book2, reader);
        assertEquals(2, bookmarkService.getBookmarklist().size());
    }

    @Test
    void DeleteBookmark_shouldRemoveExistingBookmark() {
        bookmarkService.TambahBookmark(book1, reader);
        assertEquals(1, bookmarkService.getBookmarklist().size());

        bookmarkService.DeleteBookmark(1);
        assertTrue(bookmarkService.getBookmarklist().isEmpty());
    }

    @Test
    void DeleteBookmark_shouldNotThrowOnNonExistentId() {
        bookmarkService.TambahBookmark(book1, reader);
        assertDoesNotThrow(() -> bookmarkService.DeleteBookmark(999));
        assertEquals(1, bookmarkService.getBookmarklist().size());
    }

    @Test
    void DeleteBookmark_shouldNotThrowOnEmptyList() {
        assertDoesNotThrow(() -> bookmarkService.DeleteBookmark(1));
        assertTrue(bookmarkService.getBookmarklist().isEmpty());
    }

    @Test
    void DeleteBookmark_shouldOnlyRemoveSpecifiedBookmark() {
        bookmarkService.TambahBookmark(book1, reader);
        bookmarkService.TambahBookmark(book2, reader);

        bookmarkService.DeleteBookmark(1);
        assertEquals(1, bookmarkService.getBookmarklist().size());
        assertEquals(2, bookmarkService.getBookmarklist().get(0).getBookmark_id());
    }

    @Test
    void printallBookmark_shouldPrintOnlyReadersBookmarks() {
        bookmarkService.TambahBookmark(book1, reader);
        bookmarkService.TambahBookmark(book2, otherReader);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            bookmarkService.printallBookmark(reader);
            String output = outContent.toString();
            assertTrue(output.contains("Test Book One"));
            assertFalse(output.contains("Test Book Two"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void printallBookmark_shouldNotPrintWhenReaderHasNone() {
        bookmarkService.TambahBookmark(book1, otherReader);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            bookmarkService.printallBookmark(reader);
            String output = outContent.toString();
            assertEquals(0, output.trim().length());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void printallBookmark_shouldNotPrintWhenListIsEmpty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            bookmarkService.printallBookmark(reader);
            String output = outContent.toString();
            assertEquals(0, output.trim().length());
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void getBookmarklist_shouldReturnInternalList() {
        assertTrue(bookmarkService.getBookmarklist().isEmpty());
        bookmarkService.TambahBookmark(book1, reader);
        assertEquals(1, bookmarkService.getBookmarklist().size());
    }
}
