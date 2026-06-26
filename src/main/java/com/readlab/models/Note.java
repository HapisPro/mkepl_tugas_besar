/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.readlab.models;

/**
 *
 * @author ACER
 */
public class Note {
    private Reader reader;
    private Book book;
    private int note_id;
    private String note;

    public Note(Book book,Reader reader, int note_id, String note) {
        this.book = book;
        this.reader = reader;
        this.note_id = note_id;
        this.note = note;
    }

    public void PrintNote(){
        System.out.println("Book Name : " + book.getBook_title());
        System.out.println("Note id : " + this.note_id);
        System.out.println("isi Note : " + this.note);
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }
    
}
