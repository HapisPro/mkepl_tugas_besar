/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.readlab.models;

/**
 *
 * @author ACER
 */
public class Bookmark {
    private Reader reader;
    private Book book;
    private int bookmark_id;

    public Bookmark(Book book, Reader reader,int bookmark_id) {
        this.book = book;
        this.reader= reader;
        this.bookmark_id = bookmark_id;
    }

    public void PrintBookmark(){
        System.out.println("Book Name : " + book.getBook_title());
        System.out.println("Bookmark id : " + this.bookmark_id);
    }

    public int getBookmark_id() {
        return bookmark_id;
    }

    public void setBookmark_id(int bookmark_id) {
        this.bookmark_id = bookmark_id;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }
    

    
}
