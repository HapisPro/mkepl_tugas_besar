package com.readlab.services;

import com.readlab.models.Book;
import com.readlab.models.BukuGratis;
import com.readlab.models.BukuPremium;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> books;

    public BookService() {
        books = new ArrayList<>();
        books.add(new BukuGratis("FreeB001", "Free Java Programming", "John Doe", "Programming"));
        books.add(new BukuGratis("FreeB002", "Free Python Basics", "Jane Smith", "Programming"));
        books.add(new BukuGratis("FreeB003", "Free Web Development", "Alan Turing", "Technology"));
        
        books.add(new BukuPremium("PremB001", "Advanced Algorithms", "Jane Doe", "Computer Science", 300));
        books.add(new BukuPremium("PremB002", "Machine Learning", "John Smith", "Artificial Intelligence", 500));
        books.add(new BukuPremium("PremB003", "Data Structures", "Ada Lovelace", "Computer Science", 400));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBukuGratis(BukuGratis book) {
        books.add(book);
    }

    public void addBukuPremium(BukuPremium book) {
        books.add(book);
    }
}
