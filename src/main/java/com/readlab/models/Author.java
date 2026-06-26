package com.readlab.models;

import java.util.ArrayList;
import java.util.List;

public class Author extends User {
    private List<Book> publishedBooks;

    public Author(String username, String password) {
        super(username, password);
        this.publishedBooks = new ArrayList<>();
    }

    public List<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void addBook(Book book) {
        publishedBooks.add(book);
    }

    @Override
    public void showMenu() {
        System.out.println("=========== [Menu Penulis] ===========");
        System.out.println("1. Tambah buku");
        System.out.println("2. Lihat daftar buku saya");
        System.out.println("3. Logout");
        System.out.println("======================================");
    }
}
