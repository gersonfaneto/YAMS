package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.billing.InvoiceNotFoundException;
import com.gersonfaneto.yams.exceptions.billing.PaymentMethodNotFoundException;
import com.gersonfaneto.yams.exceptions.billing.ValueExceededException;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BillingControllerTest {

  private Invoice randomInvoice;

  @BeforeEach
  void setUp() {
    randomInvoice = DAO.fromInvoices().createOne(new Invoice(UUID.randomUUID().toString(), 125.50));

    DAO.fromPayments()
        .createOne(new Payment(randomInvoice.getInvoiceID(), PaymentMethod.Cash, 10.00));
  }

  @Test
  void receivePayment() {
    Assertions.assertThrows(
        ValueExceededException.class,
        () -> {
          BillingController.receivePayment("Cash", randomInvoice.getInvoiceID(), 200.00);
        },
        "receivePayment(): Expected ValueExceededException not thrown!");

    Assertions.assertThrows(
        PaymentMethodNotFoundException.class,
        () -> {
          BillingController.receivePayment("Banana", randomInvoice.getInvoiceID(), 60.00);
        },
        "receivePayment(): Expected PaymentMethodNotFoundException not thrown!");

    Assertions.assertThrows(
        InvoiceNotFoundException.class,
        () -> {
          BillingController.receivePayment("Cash", UUID.randomUUID().toString(), 25.50);
        },
        "receivePayment(): Expected InvoiceNotFoundException not thrown!");

    Payment performedPayment = null;

    try {
      performedPayment =
          BillingController.receivePayment("Cash", randomInvoice.getInvoiceID(), 25.50);
    } catch (Exception e) {
      Assertions.fail("receivePayment(): Unexpected Exception was thrown!");
    }

    Payment foundPayment = DAO.fromPayments().findByID(performedPayment.getPaymentID());

    Assertions.assertEquals(
        performedPayment, foundPayment, "receivePayment(): Failed to store Payment!");
  }

  @Test
  void calculatePaidValue() {
    Assertions.assertThrows(
        InvoiceNotFoundException.class,
        () -> {
          BillingController.calculatePaidValue(UUID.randomUUID().toString());
        },
        "calculatePaidValue(): Expected InvoiceNotFoundException not thrown!");

    double paidValue = 0.0;

    try {
      paidValue = BillingController.calculatePaidValue(randomInvoice.getInvoiceID());
    } catch (Exception e) {
      Assertions.fail("calculatePaidValue(): Unexpected Exception was thrown!");
    }

    Assertions.assertEquals(
        10.00, paidValue, "calculatePaidValue(): Something went wrong with the calculation!");
  }

  @Test
  void invoicePayments() {
    Assertions.assertThrows(
        InvoiceNotFoundException.class,
        () -> {
          BillingController.invoicePayments(UUID.randomUUID().toString());
        },
        "invoicePayments(): Expected InvoiceNotFoundException not thrown!");

    List<Payment> receivedPayments = null;

    try {
      receivedPayments = BillingController.invoicePayments(randomInvoice.getInvoiceID());
    } catch (Exception e) {
      Assertions.fail("invoicePayments(): Unexpected Exception was thrown!");
    }

    Assertions.assertEquals(
        1,
        receivedPayments.size(),
        "invoicePayments(): Amount of Payments found didn't match expected!");
  }
}
