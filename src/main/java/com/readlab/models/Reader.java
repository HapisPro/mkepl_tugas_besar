package com.readlab.models;

import java.util.ArrayList;
import java.util.List;

public class Reader extends User {
    private List<Book> ownedBooks;

    public Reader(String username, String password) {
        super(username, password);
        this.ownedBooks = new ArrayList<>();
    }

    public List<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public void addBook(Book book) {
        ownedBooks.add(book);
    }

    @Override
    public void showMenu() {
        System.out.println("=========== [Menu Pembaca] ===========");
        System.out.println("1. Lihat daftar buku");
        System.out.println("2. Beli buku");
        System.out.println("3. Daftar buku yang dimiliki");
        System.out.println("4. Baca buku");
        System.out.println("5. Download buku");
        System.out.println("6. Lihat detail buku");
        System.out.println("7. Tambah note");
        System.out.println("8. Print semua note");
        System.out.println("9. Tambah bookmark");
        System.out.println("10. Print semua bookmark");
        System.out.println("11. Logout");
        System.out.println("======================================");
    }
}
