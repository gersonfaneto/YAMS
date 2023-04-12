package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.billing.InvoiceNotFoundException;
import com.gersonfaneto.yams.exceptions.billing.PaymentMethodNotFound;
import com.gersonfaneto.yams.exceptions.billing.ValueExceededException;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import java.util.List;

public abstract class BillingController {

  public static Payment receivePayment(
      String paymentMethod,
      String invoiceID,
      double paidValue
  ) throws PaymentMethodNotFound, ValueExceededException, InvoiceNotFoundException {
    if (PaymentMethod.findByType(paymentMethod) == null) {
      throw new PaymentMethodNotFound("Payment method not found!");
    }

    Invoice foundInvoice = DAO.fromInvoices().findByID(invoiceID);
    double invoicePaidValue = calculatePaidValue(invoiceID);

    if (invoicePaidValue + paidValue >= foundInvoice.getTotalValue()) {
      throw new ValueExceededException("Paid value exceeds the total!");
    }

    Payment newPayment = new Payment(invoiceID, PaymentMethod.findByType(paymentMethod), paidValue);

    return DAO.fromPayments().createOne(newPayment);
  }

  public static double calculatePaidValue(String invoiceID) throws InvoiceNotFoundException {
    Invoice foundInvoice = DAO.fromInvoices().findByID(invoiceID);

    if (foundInvoice == null) {
      throw new InvoiceNotFoundException("Invoice not found!");
    }

    double paidValue = DAO.fromPayments()
        .findByInvoice(invoiceID)
        .stream()
        .map(Payment::getPaidValue)
        .reduce(0.0, Double::sum);

    return paidValue;
  }

  public static List<Payment> invoicePayments(String invoiceID) throws InvoiceNotFoundException {
    Invoice foundInvoice = DAO.fromInvoices().findByID(invoiceID);

    if (foundInvoice == null) {
      throw new InvoiceNotFoundException("Invoice not found!");
    }

    return DAO.fromPayments().findByInvoice(invoiceID);
  }
}
