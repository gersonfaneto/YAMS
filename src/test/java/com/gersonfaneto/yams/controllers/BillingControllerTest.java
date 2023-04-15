package com.gersonfaneto.yams.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.billing.InvoiceNotFoundException;
import com.gersonfaneto.yams.exceptions.billing.PaymentMethodNotFound;
import com.gersonfaneto.yams.exceptions.billing.ValueExceededException;
import com.gersonfaneto.yams.exceptions.users.InvalidPasswordException;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BillingControllerTest {

  private final Invoice randomInvoice = DAO.fromInvoices().createOne(new Invoice(
      UUID.randomUUID().toString(),
      125.50
  ));

  @BeforeEach
  void setUp() {
    DAO.fromPayments().createOne(new Payment(
        randomInvoice.getInvoiceID(),
        PaymentMethod.Cash,
        10.00
    ));
  }

  @Test
  void receivePayment() {
    Assertions.assertThrows(ValueExceededException.class, () -> {
      BillingController.receivePayment(
          "Cash",
          randomInvoice.getInvoiceID(),
          200.00
      );
    });

    Assertions.assertThrows(PaymentMethodNotFound.class, () -> {
      BillingController.receivePayment(
          "Banana",
          randomInvoice.getInvoiceID(),
          60.00
      );
    });

    Assertions.assertThrows(InvoiceNotFoundException.class, () -> {
      BillingController.receivePayment(
          "Cash",
          UUID.randomUUID().toString(),
          25.50
      );
    });

    Payment performedPayment;

    try {
      performedPayment = BillingController.receivePayment(
          "Cash",
          randomInvoice.getInvoiceID(),
          25.50
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<Payment> receivedPayments = DAO.fromPayments().findByInvoice(randomInvoice.getInvoiceID());
    Assertions.assertEquals(2, receivedPayments.size());

    Payment foundPayment = DAO.fromPayments().findByID(performedPayment.getPaymentID());
    Assertions.assertEquals(performedPayment, foundPayment);
    Assertions.assertEquals(25.50, foundPayment.getPaidValue());
    Assertions.assertEquals("Cash", foundPayment.getPaymentMethod().getTypeName());
  }

  @Test
  void calculatePaidValue() {
    Assertions.assertThrows(InvoiceNotFoundException.class, () -> {
      BillingController.calculatePaidValue(
          UUID.randomUUID().toString()
      );
    });

    double paidValue;
    try {
      paidValue = BillingController.calculatePaidValue(
          randomInvoice.getInvoiceID()
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Assertions.assertEquals(10.00, paidValue);
  }

  @Test
  void invoicePayments() {
    Assertions.assertThrows(InvoiceNotFoundException.class, () -> {
      BillingController.invoicePayments(
          UUID.randomUUID().toString()
      );
    });

    List<Payment> receivedPayments;

    try {
      receivedPayments = BillingController.invoicePayments(
          randomInvoice.getInvoiceID()
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Assertions.assertEquals(1, receivedPayments.size());
  }
}