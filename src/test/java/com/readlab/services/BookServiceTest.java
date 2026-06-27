package com.readlab.services;

import com.readlab.models.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void constructor_shouldPrepopulateSixBooks() {
        assertEquals(10, bookService.getBooks().size());
    }

    @Test
    void constructor_shouldHaveThreeFreeBooks() {
        long freeCount = bookService.getBooks().stream()
                .filter(b -> b instanceof BukuGratis)
                .count();
        assertEquals(10, freeCount);
    }

    @Test
    void constructor_shouldHaveThreePremiumBooks() {
        long premiumCount = bookService.getBooks().stream()
                .filter(b -> b instanceof BukuPremium)
                .count();
        assertEquals(3, premiumCount);
    }

    @Test
    void addBook_shouldIncreaseCatalogSize() {
        Book book = new Book("B004", "Test Book", "Test Author", "Fiction");
        int before = bookService.getBooks().size();
        bookService.addBook(book);
        assertEquals(before + 1, bookService.getBooks().size());
        assertTrue(bookService.getBooks().contains(book));
    }

    @Test
    void addBukuGratis_shouldIncreaseCatalogSize() {
        BukuGratis gratis = new BukuGratis("FB004", "Free Test", "Author", "Education");
        int before = bookService.getBooks().size();
        bookService.addBukuGratis(gratis);
        assertEquals(before + 1, bookService.getBooks().size());
    }

    @Test
    void addBukuPremium_shouldIncreaseCatalogSize() {
        BukuPremium premium = new BukuPremium("PB004", "Premium Test", "Author", "Science", 250);
        int before = bookService.getBooks().size();
        bookService.addBukuPremium(premium);
        assertEquals(before + 1, bookService.getBooks().size());
    }

    @Test
    void getBooks_shouldReturnAllBooks() {
        assertEquals(6, bookService.getBooks().size());
        assertTrue(bookService.getBooks().stream().anyMatch(b -> "Free Java Programming".equals(b.getBook_title())));
        assertTrue(bookService.getBooks().stream().anyMatch(b -> "Advanced Algorithms".equals(b.getBook_title())));
    }
}
