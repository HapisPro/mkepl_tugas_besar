
package com.readlab.models;


public class Payment {
    private String payment_id;
    private int payment_amount;
    private String payment_status;
    private String payment_method;

    public Payment(String payment_id, String payment_status, String payment_method) {
        this.payment_id = payment_id;
        this.payment_status = payment_status;
        this.payment_method = payment_method;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public int getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(int payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
    
    public void prosesPembayaran(){
        if ("QRIS".equals(this.payment_method)){
            this.payment_amount = this.payment_amount+ 1000; 
            verifikasiPembayaran();
        }else{
            this.payment_amount = this.payment_amount+ 2000; 
            verifikasiPembayaran();
        }
    }
    
    public void verifikasiPembayaran(){
        this.payment_status = "done";
        
    }
    public void printPayment(){
        System.out.println("Payment ID: " + this.payment_id );
        System.out.println("Payment amount: " + this.payment_amount );
        System.out.println("Payment method: " + this.payment_method );
        System.out.println("Payment status: " + this.payment_status );
    }
}
