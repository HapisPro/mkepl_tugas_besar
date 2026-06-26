package com.readlab.models;

public class BukuGratis extends Book {

    public BukuGratis(String book_id, String book_title, String book_author, String book_genre) {
        super(book_id, book_title, book_author, book_genre);
    }

    @Override
    public void getBookDetails() {
        System.out.println("Buku Gratis!.");
        super.getBookDetails();
    }
}
