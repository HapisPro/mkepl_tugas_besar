
package com.readlab.models;

public class BukuPremium extends Book {
    private int bukuPremium_harga;
    private boolean bukuPremium_purchasedStatus;

    public BukuPremium(String book_id, String book_title, String book_author, String book_genre, int bukuPremium_Harga) {
        super(book_id, book_title, book_author, book_genre);
        this.bukuPremium_harga = bukuPremium_Harga;
        this.bukuPremium_purchasedStatus = false; // default status belum dibeli
    }
    
     @Override
    public void getBookDetails() {
        System.out.println("Buku Premium");
        System.out.println("Harga: " + bukuPremium_harga);
         if (bukuPremium_purchasedStatus) {
             System.out.println("Dimiliki");
         } else {
             System.out.println("Belum Dimiliki");
         }
 
        super.getBookDetails();
    }

    // Metode untuk membeli buku
    public void purchaseBook() {
        try {
            if (!bukuPremium_purchasedStatus) {
                bukuPremium_purchasedStatus = true;
                System.out.println("Anda telah berhasil membeli buku: " + getBook_title());
            } else {
                throw new IllegalStateException("Buku ini telah dibeli.");
            }
        } catch (IllegalStateException e) {
            System.err.println( e.getMessage());
        }
    }

    @Override
    public void readBook() {
        try {
            if (bukuPremium_purchasedStatus) {
                System.out.println("Membaca buku: " + getBook_title());
            } else {
                throw new IllegalStateException("Anda harus membeli buku ini.");
            }
        } catch (Exception e) {
            System.err.println( e.getMessage());
        }
    }

    @Override
    public void downloadBook() {
         try {
            if (bukuPremium_purchasedStatus) {
                System.out.println("Mengunduh Buku: " + getBook_title());
            } else {
                throw new IllegalStateException("Anda harus membeli buku ini.");
            }
        } catch (Exception e) {
            System.err.println( e.getMessage());
        }
    }
    
    // Getter dan setter
    public int getBukuPremium_harga() {
        return bukuPremium_harga;
    }

    public void setBukuPremium_harga(int bukuPremium_harga) {
        this.bukuPremium_harga = bukuPremium_harga;
    }

    public boolean isBukuPremium_purchasedStatus() {
        return bukuPremium_purchasedStatus;
    }

    public void setBukuPremium_purchasedStatus(boolean bukuPremium_purchasedStatus) {
        this.bukuPremium_purchasedStatus = bukuPremium_purchasedStatus;
    }
}
