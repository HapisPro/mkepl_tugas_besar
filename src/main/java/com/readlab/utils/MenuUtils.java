package com.readlab.utils;

import com.readlab.models.*;
import com.readlab.services.*;
import com.readlab.exceptions.BookNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUtils {
    private static final String RST = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GRN = "\u001B[32m";
    private static final String YLW = "\u001B[33m";
    private static final String BLU = "\u001B[34m";
    private static final String MGT = "\u001B[35m";
    private static final String CYN = "\u001B[36m";
    private static final String BLD = "\u001B[1m";

    private static final String H = "\u2501";
    private static final String V = "\u2503";
    private static final String TL = "\u250F";
    private static final String TR = "\u2513";
    private static final String BL = "\u2517";
    private static final String BR = "\u251B";
    private static final String ML = "\u2523";
    private static final String MR = "\u252B";

    private static final int W = 48;

    private static String c(String color, String s) {
        return color + s + RST;
    }

    private static void p(String s) {
        System.out.println(s);
    }

    private static void success(String s) {
        p(c(GRN, "  \u2713 " + s));
    }

    private static void err(String s) {
        p(c(RED, "  \u2717 " + s));
    }

    private static void info(String s) {
        p(c(BLU, "  \u24D8 " + s));
    }

    private static void prompt(String s) {
        System.out.print(c(YLW, "  " + s + " > "));
    }

    private static void rule(String left, String fill, String right) {
        p(c(CYN, left + fill.repeat(W - 2) + right));
    }

    private static void boxLine(String left, String content, String right) {
        int pad = W - 2 - content.length();
        p(c(CYN, left) + " " + content + " ".repeat(Math.max(0, pad - 1)) + c(CYN, right));
    }

    private static void boxTitle(String title) {
        int pad = (W - 4 - title.length()) / 2;
        int rpad = W - 4 - title.length() - pad;
        p(c(CYN, V) + " " + " ".repeat(Math.max(0, pad))
          + c(BLD + CYN, title)
          + " ".repeat(Math.max(0, rpad)) + " " + c(CYN, V));
    }

    private static void boxOpen(String... titleLines) {
        rule(TL, H, TR);
        for (String t : titleLines) boxTitle(t);
        rule(ML, H, MR);
    }

    private static void boxClose() {
        rule(BL, H, BR);
    }

    private static void menuItem(int n, String label) {
        boxLine(V, n + ". " + label, V);
    }

    private static void section(String title) {
        int lineLen = W - 6 - title.length();
        String line = "\u2501".repeat(Math.max(0, lineLen / 2));
        String extra = (lineLen % 2 == 0) ? "" : "\u2501";
        p(c(BLD + CYN, "\n  " + line + " " + title + " " + line + extra));
    }

    public static void printMainMenu() {
        p("");
        boxOpen("ReadLab", "Your Reading Companion");
        menuItem(1, "Register");
        menuItem(2, "Login");
        menuItem(3, "Keluar");
        boxClose();
    }

    public static void readerMenu(Reader reader, Scanner scanner,
                                  BookService bookService, NoteService noteservice,
                                  BookmarkService bookmarkservice) {
        while (true) {
            reader.showMenu();
            prompt("Pilih");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        section("Daftar Buku");
                        for (Book book : bookService.getBooks()) {
                            String badge = book instanceof BukuPremium ? c(MGT, "[Premium]")
                                       : book instanceof BukuGratis ? c(GRN, "[Gratis]")
                                       : "";
                            p("    " + book.getBook_title() + " " + badge);
                        }
                    }
                    case 2 -> {
                        section("Beli Buku Premium");
                        p("  " + c(BLU, "Daftar Buku Premium:"));
                        for (Book book : bookService.getBooks()) {
                            if (book instanceof BukuPremium) {
                                p("    " + book.getBook_title());
                            }
                        }
                        try {
                            prompt("Masukkan judul buku");
                            String title = scanner.nextLine();
                            boolean found = false;
                            for (Book book : bookService.getBooks()) {
                                if (book.getBook_title().equalsIgnoreCase(title) && book instanceof BukuPremium) {
                                    ((BukuPremium) book).purchaseBook();
                                    reader.addBook(book);
                                    success("Buku premium berhasil dibeli!");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                throw new BookNotFoundException("Buku tidak ditemukan atau bukan buku premium.");
                            }
                        } catch (BookNotFoundException e) {
                            err(e.getMessage());
                        }
                    }
                    case 3 -> {
                        section("Buku Saya");
                        if (reader.getOwnedBooks().isEmpty()) {
                            info("Anda belum memiliki buku.");
                        } else {
                            for (Book book : reader.getOwnedBooks()) {
                                String badge = book instanceof BukuPremium ? c(MGT, "[Premium]")
                                           : book instanceof BukuGratis ? c(GRN, "[Gratis]")
                                           : "";
                                p("    " + book.getBook_title() + " " + badge);
                            }
                        }
                    }
                    case 4 -> {
                        prompt("Masukkan judul buku untuk dibaca");
                        String title = scanner.nextLine();
                        boolean found = false;
                        for (Book book : bookService.getBooks()) {
                            if (book.getBook_title().equalsIgnoreCase(title)) {
                                if (book instanceof BukuPremium) {
                                    ((BukuPremium) book).readBook();
                                } else {
                                    book.readBook();
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            err("Buku tidak ditemukan.");
                        }
                    }
                    case 5 -> {
                        prompt("Masukkan judul buku untuk didownload");
                        String title = scanner.nextLine();
                        boolean found = false;
                        for (Book book : bookService.getBooks()) {
                            if (book.getBook_title().equalsIgnoreCase(title)) {
                                if (book instanceof BukuPremium) {
                                    ((BukuPremium) book).downloadBook();
                                } else {
                                    book.downloadBook();
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            err("Buku tidak ditemukan.");
                        }
                    }
                    case 6 -> {
                        prompt("Masukkan judul buku untuk detail");
                        String title = scanner.nextLine();
                        boolean found = false;
                        for (Book book : bookService.getBooks()) {
                            if (book.getBook_title().equalsIgnoreCase(title)) {
                                book.getBookDetails();
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            err("Buku tidak ditemukan.");
                        }
                    }
                    case 7 -> {
                        section("Tambah Note");
                        try {
                            prompt("Masukkan judul buku");
                            String title = scanner.nextLine();
                            boolean found = false;
                            for (Book book : bookService.getBooks()) {
                                if (book.getBook_title().equalsIgnoreCase(title)) {
                                    if (book instanceof BukuPremium
                                        && !((BukuPremium) book).isBukuPremium_purchasedStatus()) {
                                        throw new IllegalStateException("Anda harus membeli buku ini.");
                                    }
                                    prompt("Masukkan isi note");
                                    String note = scanner.nextLine();
                                    noteservice.TambahNote(book, reader, note);
                                    success("Note berhasil ditambahkan!");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                err("Buku tidak ditemukan.");
                            }
                        } catch (IllegalStateException e) {
                            err(e.getMessage());
                        }
                    }
                    case 8 -> {
                        section("Daftar Note");
                        noteservice.printallNote(reader);
                    }
                    case 9 -> {
                        section("Tambah Bookmark");
                        try {
                            prompt("Masukkan judul buku");
                            String title = scanner.nextLine();
                            boolean found = false;
                            for (Book book : bookService.getBooks()) {
                                if (book.getBook_title().equalsIgnoreCase(title)) {
                                    bookmarkservice.TambahBookmark(book, reader);
                                    success("Bookmark berhasil ditambahkan!");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                err("Buku tidak ditemukan.");
                            }
                        } catch (Exception e) {
                            err(e.getMessage());
                        }
                    }
                    case 10 -> {
                        section("Daftar Bookmark");
                        bookmarkservice.printallBookmark(reader);
                    }
                    case 11 -> {
                        success("Logout berhasil.");
                        return;
                    }
                    default -> err("Pilihan tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                err("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine();
            }
        }
    }

    public static void authorMenu(Author author, Scanner scanner, BookService bookService) {
        while (true) {
            author.showMenu();
            prompt("Pilih");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        section("Tambah Buku Baru");
                        prompt("Jenis buku [premium/gratis]");
                        String bookType = scanner.nextLine().trim().toLowerCase();

                        prompt("Judul buku");
                        String title = scanner.nextLine();
                        prompt("Genre");
                        String genre = scanner.nextLine();

                        if (bookType.equals("premium")) {
                            prompt("Harga");
                            int price = scanner.nextInt();
                            scanner.nextLine();

                            BukuPremium premiumBook = new BukuPremium("id", title, author.getUsername(), genre, price);
                            author.addBook(premiumBook);
                            bookService.addBook(premiumBook);
                            success("Buku Premium berhasil ditambahkan!");
                        } else if (bookType.equals("gratis")) {
                            BukuGratis freeBook = new BukuGratis("id", title, author.getUsername(), genre);
                            author.addBook(freeBook);
                            bookService.addBook(freeBook);
                            success("Buku Gratis berhasil ditambahkan!");
                        } else {
                            err("Jenis buku tidak valid. Silakan coba lagi.");
                        }
                    }
                    case 2 -> {
                        section("Buku Saya");
                        if (author.getPublishedBooks().isEmpty()) {
                            info("Anda belum mempublikasi buku.");
                        } else {
                            for (Book book : author.getPublishedBooks()) {
                                String badge = book instanceof BukuPremium ? c(MGT, "[Premium]")
                                           : book instanceof BukuGratis ? c(GRN, "[Gratis]")
                                           : "";
                                p("    " + book.getBook_title() + " " + badge);
                            }
                        }
                    }
                    case 3 -> {
                        success("Logout anda berhasil.");
                        return;
                    }
                    default -> err("Pilihan tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                err("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine();
            }
        }
    }
}
