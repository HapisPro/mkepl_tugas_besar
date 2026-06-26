package com.readlab.models;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void constructor_shouldSetFields() {
        Payment payment = new Payment("PAY001", "pending", "QRIS");
        assertEquals("PAY001", payment.getPayment_id());
        assertEquals("pending", payment.getPayment_status());
        assertEquals("QRIS", payment.getPayment_method());
    }

    @Test
    void setPayment_amount_shouldUpdateAmount() {
        Payment payment = new Payment("PAY001", "pending", "QRIS");
        payment.setPayment_amount(5000);
        assertEquals(5000, payment.getPayment_amount());
    }

    @Test
    void prosesPembayaran_shouldAddQrisFee() {
        Payment payment = new Payment("PAY001", "pending", "QRIS");
        payment.setPayment_amount(5000);
        payment.prosesPembayaran();
        assertEquals(6000, payment.getPayment_amount());
    }

    @Test
    void prosesPembayaran_shouldAddOthersFee() {
        Payment payment = new Payment("PAY002", "pending", "Others");
        payment.setPayment_amount(5000);
        payment.prosesPembayaran();
        assertEquals(7000, payment.getPayment_amount());
    }

    @Test
    void prosesPembayaran_shouldDefaultToOthersFeeForUnknownMethod() {
        Payment payment = new Payment("PAY003", "pending", "Unknown");
        payment.setPayment_amount(5000);
        payment.prosesPembayaran();
        assertEquals(7000, payment.getPayment_amount());
    }

    @Test
    void verifikasiPembayaran_shouldSetStatusToDone() {
        Payment payment = new Payment("PAY001", "pending", "QRIS");
        payment.verifikasiPembayaran();
        assertEquals("done", payment.getPayment_status());
    }

    @Test
    void prosesPembayaran_shouldSetStatusToDoneAfterVerification() {
        Payment payment = new Payment("PAY001", "pending", "QRIS");
        payment.setPayment_amount(3000);
        payment.prosesPembayaran();
        assertEquals("done", payment.getPayment_status());
    }

    @Test
    void prosesPembayaran_shouldHandleZeroAmountWithFee() {
        Payment payment = new Payment("PAY001", "pending", "QRIS");
        payment.setPayment_amount(0);
        payment.prosesPembayaran();
        assertEquals(1000, payment.getPayment_amount());
    }

    @Test
    void printPayment_shouldNotThrow() {
        Payment payment = new Payment("PAY001", "done", "QRIS");
        payment.setPayment_amount(6000);
        assertDoesNotThrow(() -> payment.printPayment());
    }

    @Test
    void setPayment_method_shouldUpdateMethod() {
        Payment payment = new Payment("PAY001", "pending", "QRIS");
        payment.setPayment_method("Others");
        assertEquals("Others", payment.getPayment_method());
    }

    // Helper: accessing private fields via public getters for verification
    // Payment class doesn't expose getPayment_id/getPayment_status/getPayment_method directly
    // Let's use the printPayment output for verification

    @Test
    void printPayment_shouldContainCorrectInfo() {
        Payment payment = new Payment("PAY001", "done", "QRIS");
        payment.setPayment_amount(6000);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            payment.printPayment();
            String output = outContent.toString();
            assertTrue(output.contains("PAY001"));
            assertTrue(output.contains("6000"));
            assertTrue(output.contains("QRIS"));
            assertTrue(output.contains("done"));
        } finally {
            System.setOut(originalOut);
        }
    }
}
