package com.readlab.models;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BukuGratisTest {

    @Test
    void constructor_shouldInheritBookFields() {
        BukuGratis gratis = new BukuGratis("FB001", "Free Book", "Author", "Education");
        assertEquals("FB001", gratis.getBook_id());
        assertEquals("Free Book", gratis.getBook_title());
        assertEquals("Author", gratis.getBook_author());
        assertEquals("Education", gratis.getBook_genre());
    }

    @Test
    void getBookDetails_shouldPrependGratisLabel() {
        BukuGratis gratis = new BukuGratis("FB001", "Free Book", "Author", "Education");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            gratis.getBookDetails();
            String output = outContent.toString();
            assertTrue(output.contains("Buku Gratis!"));
            assertTrue(output.contains("Free Book"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void readBook_shouldWorkWithoutPurchase() {
        BukuGratis gratis = new BukuGratis("FB001", "Free Book", "Author", "Education");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            gratis.readBook();
            String output = outContent.toString();
            assertTrue(output.contains("Membaca Buku"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void downloadBook_shouldWorkWithoutPurchase() {
        BukuGratis gratis = new BukuGratis("FB001", "Free Book", "Author", "Education");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            gratis.downloadBook();
            String output = outContent.toString();
            assertTrue(output.contains("Mengunduh Buku"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
