package com.readlab;

import com.readlab.models.*;
import com.readlab.services.*;
import com.readlab.utils.MenuUtils;
import com.readlab.exceptions.InvalidUserException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadLab {
    // test
    public static void main(String[] args) {
        UserService userService = new UserService();
        BookService bookService = new BookService();
        NoteService noteService = new NoteService();
        BookmarkService bookmarkRervice = new BookmarkService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            MenuUtils.printMainMenu();
            System.out.print("> ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1 -> {
                        User user = userService.register(scanner);

                        if (user instanceof Author) {
                            MenuUtils.authorMenu((Author) user, scanner, bookService);
                        } else if (user instanceof Reader) {
                            MenuUtils.readerMenu((Reader) user, scanner, bookService, noteService, bookmarkRervice);
                        } else {
                            throw new InvalidUserException("User tipe tidak valid.");
                        }
                    }
                    case 2 -> {
                        User user = userService.login(scanner);
                        if (user instanceof Author) {
                            MenuUtils.authorMenu((Author) user, scanner, bookService);
                        } else if (user instanceof Reader) {
                            MenuUtils.readerMenu((Reader) user, scanner, bookService,noteService,bookmarkRervice);
                        } else {
                            throw new InvalidUserException("User tipe tidak valid.");
                        }
                    }
                    case 3 -> {
                        System.out.println("Terima kasih telah menggunakan ReadLab!");
                        scanner.close();
                        return; // Keluar dari program
                    }
                    default -> System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Clear buffer
            } catch (InvalidUserException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Kesalahan tidak terduga: " + e.getMessage());
            }
        }
    }
}
