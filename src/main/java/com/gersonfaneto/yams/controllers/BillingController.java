package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.builders.payment.PaymentBuilder;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.InvoiceNotFoundException;
import com.gersonfaneto.yams.exceptions.PaymentMethodNotFound;
import com.gersonfaneto.yams.exceptions.ValueExceededException;
import com.gersonfaneto.yams.models.billing.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import java.util.List;

public abstract class BillingController {

  public static Payment receivePayment(String paymentMethod, String invoiceID, double paidValue)
      throws PaymentMethodNotFound, ValueExceededException {
    if (PaymentMethod.findByType(paymentMethod) == null) {
      throw new PaymentMethodNotFound("Payment method '" + paymentMethod + "' not found!");
    }

    Invoice foundInvoice = DAO.fromInvoices().findByID(invoiceID);
    double invoicePaidValue = foundInvoice.calculatePaidValue();

    if (invoicePaidValue + paidValue >= foundInvoice.getTotalValue()) {
      throw new ValueExceededException(
          "Payment of value R$ " + paidValue + "' exceeds total value from Invoice of ID '" + invoiceID
              + "' total value");
    }

    Payment newPayment =
        new PaymentBuilder(PaymentMethod.findByType(paymentMethod))
            .fromInvoice(invoiceID)
            .ofValue(paidValue)
            .Build();

    return DAO.fromPayments().createOne(newPayment);
  }

  public static double invoicePaidValue(String invoiceID) throws InvoiceNotFoundException {
    if (DAO.fromInvoices().findByID(invoiceID) == null) {
      throw new InvoiceNotFoundException("Invoice of ID '" + invoiceID + "' not found!");
    }

    return DAO.fromInvoices().findByID(invoiceID).calculatePaidValue();
  }

  public static List<Payment> invoicePayments(String invoiceID) throws InvoiceNotFoundException {
    if (DAO.fromInvoices().findByID(invoiceID) == null) {
      throw new InvoiceNotFoundException("Invoice of ID '" + invoiceID + "' not found!");
    }

    return DAO.fromInvoices().findByID(invoiceID).retrievePayments();
  }
}
