package com.readlab.models;

public class Book {
    private String book_id;
    private String book_title;
    private String book_author;
    private String book_genre;

    public Book(String book_id, String book_title, String book_author, String book_genre) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_genre = book_genre;
    }
    public void demo1() {
        System.out.println("A");
        System.out.println("B");
        System.out.println("C");
        System.out.println("D");
        System.out.println("E");
        System.out.println("F");
        System.out.println("G");
    }

    public void demo2() {
        System.out.println("A");
        System.out.println("B");
        System.out.println("C");
        System.out.println("D");
        System.out.println("E");
        System.out.println("F");
        System.out.println("G");
    }
    public void demo1() {
        System.out.println("A");
        System.out.println("B");
        System.out.println("C");
        System.out.println("D");
        System.out.println("E");
        System.out.println("F");
        System.out.println("G");
    }

    public void demo2() {
        System.out.println("A");
        System.out.println("B");
        System.out.println("C");
        System.out.println("D");
        System.out.println("E");
        System.out.println("F");
        System.out.println("G");
    }
    
    // Method untuk menampilkan detail buku
    public void getBookDetails() {
        System.out.println("Book ID: " + book_id);
        System.out.println("Title: " + book_title);
        System.out.println("Author: " + book_author);
        System.out.println("Genre: " + book_genre);
    }

    // Metode untuk mendownload buku
    public void downloadBook() {
        try {
            System.out.println("Mengunduh Buku: " + book_title);
        } catch (Exception e) {
            System.out.println("Error saat mendownload buku");
        }
    }

    // Metode untuk membaca buku
    public void readBook() {
        try {
            System.out.println("Membaca Buku: " + book_title);
        } catch (Exception e) {
            System.out.println("Error saat membaca buku");
        }
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_genre() {
        return book_genre;
    }

    public void setBook_genre(String book_genre) {
        this.book_genre = book_genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id.equals(book.book_id);
    }

    @Override
    public int hashCode() {
        return book_id.hashCode();
    }
}
