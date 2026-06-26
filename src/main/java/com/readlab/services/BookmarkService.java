package com.readlab.services;

import com.readlab.models.*;
import java.util.ArrayList;

public class BookmarkService {
    private ArrayList<Bookmark> Bookmarklist = new ArrayList<Bookmark>();

    public void TambahBookmark(Book book, Reader reader) {
        if (Bookmarklist.isEmpty()) {
            Bookmark x = new Bookmark(book, reader, 1);
            this.Bookmarklist.add(x);
        } else {
            for (Bookmark b : Bookmarklist) {
                if (b.getBook().equals(book) && b.getReader().equals(reader)) {
                    return;
                }
            }
            Bookmark x = new Bookmark(book, reader, Bookmarklist.get(Bookmarklist.size() - 1).getBookmark_id() + 1);
            this.Bookmarklist.add(x);
        }
    }

    public void DeleteBookmark(int idBookmark) {
        if (Bookmarklist.isEmpty()) {
            return;
        }
        for (int i = 0; i < Bookmarklist.size(); i++) {
            if (Bookmarklist.get(i).getBookmark_id() == idBookmark) {
                Bookmarklist.remove(i);
                return;
            }
        }
    }

    public void printallBookmark(Reader reader) {
        int x = 0;
        if (Bookmarklist.isEmpty()) {
            return;
        }
        for (int i = 0; i < Bookmarklist.size(); i++) {
            if (Bookmarklist.get(i).getReader().equals(reader)) {
                Bookmarklist.get(i).PrintBookmark();
                x++;
            }
        }
        if (x == 0) {
            return;
        }
    }

    public ArrayList<Bookmark> getBookmarklist() {
        return Bookmarklist;
    }
}
