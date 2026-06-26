package com.readlab.services;

import com.readlab.models.*;
import java.util.ArrayList;

public class NoteService {
    private ArrayList<Note> Notelist = new ArrayList<Note>();

    public void TambahNote(Book book, Reader reader, String note) {
        if (Notelist.isEmpty()) {
            Note x = new Note(book, reader, 100, note);
            this.Notelist.add(x);
        } else {
            Note x = new Note(book, reader, Notelist.get(Notelist.size() - 1).getNote_id() + 1, note);
            this.Notelist.add(x);
        }
    }

    public void DeleteNote(int idNote) {
        if (Notelist.isEmpty()) {
            return;
        }
        for (int i = 0; i < Notelist.size(); i++) {
            if (Notelist.get(i).getNote_id() == idNote) {
                Notelist.remove(i);
                return;
            }
        }
    }

    public void printallNote(Reader reader) {
        int x = 0;
        if (Notelist.isEmpty()) {
            return;
        }
        for (int i = 0; i < Notelist.size(); i++) {
            if (Notelist.get(i).getReader().equals(reader)) {
                Notelist.get(i).PrintNote();
                x++;
            }
        }
        if (x == 0) {
            return;
        }
    }

    public ArrayList<Note> getNotelist() {
        return Notelist;
    }
}
