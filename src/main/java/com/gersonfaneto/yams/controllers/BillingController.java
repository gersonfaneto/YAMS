package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.builders.payment.PaymentBuilder;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.InvoiceNotFoundException;
import com.gersonfaneto.yams.exceptions.PaymentMethodNotFound;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import java.util.List;

public abstract class BillingController {

  public static Payment receivePayment(String paymentMethod, String invoiceID, double paidValue)
      throws PaymentMethodNotFound {
    if (PaymentMethod.findByType(paymentMethod) == null) {
      throw new PaymentMethodNotFound("Payment method '" + paymentMethod + "' not found!");
    }

    Payment newPayment =
        new PaymentBuilder(PaymentMethod.findByType(paymentMethod))
            .fromInvoice(invoiceID)
            .ofValue(paidValue)
            .Build();

    return DAO.fromPayments().createOne(newPayment);
  }

  public static double calculatePaidValue(String invoiceID) throws InvoiceNotFoundException {
    if (DAO.fromInvoices().findByID(invoiceID) == null) {
      throw new InvoiceNotFoundException("Invoice of ID '" + invoiceID + "' not found!");
    }

    return DAO.fromPayments().findByInvoice(invoiceID).stream()
        .map(Payment::getPaidValue)
        .reduce(0.0, Double::sum);
  }

  public static List<Payment> listPayments(String invoiceID) throws InvoiceNotFoundException {
    if (DAO.fromInvoices().findByID(invoiceID) == null) {
      throw new InvoiceNotFoundException("Invoice of ID '" + invoiceID + "' not found!");
    }

    return DAO.fromPayments().findByInvoice(invoiceID);
  }
}
