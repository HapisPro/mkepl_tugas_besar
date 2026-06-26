package com.readlab.utils;

import com.readlab.models.*;
import com.readlab.services.*;
import com.readlab.exceptions.BookNotFoundException;
import java.util.InputMismatchException;

import java.util.Scanner;

public class MenuUtils {
    public static void printMainMenu() {
        System.out.println("========== [ReadLab] ==========");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Keluar");
        System.out.println("===============================");
    }

    public static void readerMenu(Reader reader, Scanner scanner, BookService bookService, NoteService noteservice, BookmarkService bookmarkservice) {
        while (true) {
            reader.showMenu();
            System.out.print("> ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                switch (choice) {
                    case 1 -> {
                        System.out.println("Daftar Buku:");
                        for (Book book : bookService.getBooks()) {
                            String bookType = book instanceof BukuPremium ? "[Premium]" : book instanceof BukuGratis ? "[Gratis]" : "";
                            System.out.println(book.getBook_title() + " " + bookType);
                        }
                    }
                    case 2 -> {
                        try {
                            System.out.println("Daftar Buku Premium:");
                            for (Book book : bookService.getBooks()) {
                                if (book instanceof BukuPremium) {
                                    System.out.println(book.getBook_title());
                                }
                            }

                            System.out.print("Masukkan judul buku: ");
                            String title = scanner.nextLine();
                            boolean found = false;
                            for (Book book : bookService.getBooks()) {
                                if (book.getBook_title().equalsIgnoreCase(title) && book instanceof BukuPremium) {
                                    ((BukuPremium) book).purchaseBook();
                                    reader.addBook(book);
                                    System.out.println("Buku premium berhasil dibeli!");
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                throw new BookNotFoundException("Buku tidak ditemukan atau bukan buku premium.");
                            }
                        } catch (BookNotFoundException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    case 3 -> {
                        System.out.println("Daftar Buku yang Dimiliki:");
                        if (reader.getOwnedBooks().isEmpty()) {
                            System.out.println("Anda belum memiliki buku.");
                        } else {
                            for (Book book : reader.getOwnedBooks()) {
                                String bookType = book instanceof BukuPremium ? "[Premium]" : book instanceof BukuGratis ? "[Gratis]" : "";
                                System.out.println(book.getBook_title() + " " + bookType);
                            }
                        }
                    }
                    case 4 -> {
                        System.out.print("Masukkan judul buku untuk dibaca: ");
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
                            System.err.println("Buku tidak ditemukan.");
                        }
                    }
                    case 5 -> {
                        System.out.print("Masukkan judul buku untuk didownload: ");
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
                            System.err.println("Buku tidak ditemukan.");
                        }
                    }
                    case 6 -> {
                        System.out.print("Masukkan judul buku untuk melihat detail: ");
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
                            System.err.println("Buku tidak ditemukan.");
                        }
                    }
                    case 7 -> {
                        try{
                            System.out.print("Masukkan judul buku untuk melihat detail: ");
                            String title = scanner.nextLine();
                            boolean found = false;
                            for (Book book : bookService.getBooks()) {
                                if (book.getBook_title().equalsIgnoreCase(title)) {
                                    if (book instanceof BukuPremium) {
                                        if( ((BukuPremium) book).isBukuPremium_purchasedStatus() == false){
                                            throw new IllegalStateException("Anda harus membeli buku ini.");
                                        }else{
                                            System.out.print("Masukkan isi dari note: ");
                                            String note = scanner.nextLine();
                                            noteservice.TambahNote(book, reader, note);
                                            found = true;
                                            break;
                                        }
                                    }else{
                                            System.out.print("Masukkan isi dari note: ");
                                            String note = scanner.nextLine();
                                            noteservice.TambahNote(book, reader, note);
                                            found = true;
                                            break;
                                    }
                                }
                            }
                            if (!found) {
                                throw new Exception("Buku tidak ditemukan.");
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 8 ->{
                        noteservice.printallNote(reader);
                    }case 9 ->{
                        try{
                            System.out.print("Masukkan judul buku: ");
                            String title = scanner.nextLine();
                            boolean found = false;
                            for (Book book : bookService.getBooks()) {
                                if (book.getBook_title().equalsIgnoreCase(title)) {
                                    bookmarkservice.TambahBookmark(book, reader);
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                throw new Exception("Buku tidak ditemukan.");
                            }
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }case 10 ->{
                        bookmarkservice.printallBookmark(reader);
                    }case 11 ->{
                        System.out.println("Logout berhasil.");
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    public static void authorMenu(Author author, Scanner scanner, BookService bookService) {
        while (true) {
            author.showMenu();
            try {
                System.out.print("> ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1 -> {
                        System.out.println("Pilih jenis buku: [premium/gratis]");
                        String bookType = scanner.nextLine().trim().toLowerCase();

                        System.out.print("Judul buku: ");
                        String title = scanner.nextLine();
                        System.out.print("Genre: ");
                        String genre = scanner.nextLine();

                        if (bookType.equals("premium")) {
                            System.out.print("Harga buku: ");
                            int price = scanner.nextInt();
                            scanner.nextLine(); // Clear buffer

                            BukuPremium premiumBook = new BukuPremium("id", title, author.getUsername(), genre, price);
                            author.addBook(premiumBook);
                            bookService.addBook(premiumBook);

                            System.out.println("Buku Premium berhasil ditambahkan!");
                        } else if (bookType.equals("gratis")) {
                            // Tambahkan buku gratis
                            BukuGratis freeBook = new BukuGratis("id", title, author.getUsername(), genre);
                            author.addBook(freeBook);
                            bookService.addBook(freeBook);

                            System.out.println("Buku Gratis berhasil ditambahkan!");
                        } else {
                            System.out.println("Jenis buku tidak valid. Silakan coba lagi.");
                        }
                    }
                    case 2 -> {
                        System.out.println("Daftar Buku yang Dipublikasikan:");
                        if(author.getPublishedBooks().isEmpty()){
                            System.out.println("Anda belum publikasi buku.");   
                        } else {
                            for (Book book : author.getPublishedBooks()) {
                            String bookType = book instanceof BukuPremium ? "[Premium]" : book instanceof BukuGratis ? "[Gratis]" : "";
                            System.out.println(book.getBook_title() + " " + bookType);
                        }
                        }
                    }
                    case 3 -> {
                        System.out.println("Logout berhasil.");
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            }catch (InputMismatchException e) {
                System.err.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Clear buffer
            }
        }    
    }
}
