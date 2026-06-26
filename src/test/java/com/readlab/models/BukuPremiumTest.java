package com.readlab.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BukuPremiumTest {

    private BukuPremium premiumBook;

    @BeforeEach
    void setUp() {
        premiumBook = new BukuPremium("PB001", "Premium Test", "Author", "Science", 300);
    }

    @Test
    void constructor_shouldSetPrice() {
        assertEquals(300, premiumBook.getBukuPremium_harga());
    }

    @Test
    void constructor_shouldDefaultToNotPurchased() {
        assertFalse(premiumBook.isBukuPremium_purchasedStatus());
    }

    @Test
    void purchaseBook_shouldSetPurchasedToTrue() {
        premiumBook.purchaseBook();
        assertTrue(premiumBook.isBukuPremium_purchasedStatus());
    }

    @Test
    void purchaseBook_shouldNotThrowOnFirstPurchase() {
        assertDoesNotThrow(() -> premiumBook.purchaseBook());
    }

    @Test
    void purchaseBook_shouldHandleDoublePurchaseWithoutCrash() {
        premiumBook.purchaseBook();
        assertDoesNotThrow(() -> premiumBook.purchaseBook());
    }

    @Test
    void readBook_shouldWorkAfterPurchase() {
        premiumBook.purchaseBook();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            premiumBook.readBook();
            String output = outContent.toString();
            assertTrue(output.contains("Membaca buku"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void readBook_shouldFailWithoutPurchase() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            premiumBook.readBook();
            String output = errContent.toString();
            assertTrue(output.contains("harus membeli"));
        } finally {
            System.setErr(originalErr);
        }
    }

    @Test
    void downloadBook_shouldWorkAfterPurchase() {
        premiumBook.purchaseBook();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            premiumBook.downloadBook();
            String output = outContent.toString();
            assertTrue(output.contains("Mengunduh Buku"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void downloadBook_shouldFailWithoutPurchase() {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            premiumBook.downloadBook();
            String output = errContent.toString();
            assertTrue(output.contains("harus membeli"));
        } finally {
            System.setErr(originalErr);
        }
    }

    @Test
    void getBookDetails_shouldIncludePremiumLabelViaInheritance() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            premiumBook.getBookDetails();
            String output = outContent.toString();
            assertTrue(output.contains("PB001"));
            assertTrue(output.contains("Premium Test"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void setBukuPremium_purchasedStatus_shouldUpdateStatus() {
        premiumBook.setBukuPremium_purchasedStatus(true);
        assertTrue(premiumBook.isBukuPremium_purchasedStatus());
    }

    @Test
    void setBukuPremium_harga_shouldUpdatePrice() {
        premiumBook.setBukuPremium_harga(500);
        assertEquals(500, premiumBook.getBukuPremium_harga());
    }
}
