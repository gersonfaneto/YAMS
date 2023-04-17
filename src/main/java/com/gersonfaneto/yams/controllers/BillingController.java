package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.billing.invoice.InvoiceCRUD;
import com.gersonfaneto.yams.dao.billing.invoice.InvoiceMemoryDAO;
import com.gersonfaneto.yams.dao.billing.paymet.PaymentCRUD;
import com.gersonfaneto.yams.dao.billing.paymet.PaymentMemoryDAO;
import com.gersonfaneto.yams.exceptions.billing.InvoiceNotFoundException;
import com.gersonfaneto.yams.exceptions.billing.PaymentMethodNotFoundException;
import com.gersonfaneto.yams.exceptions.billing.ValueExceededException;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import java.util.List;

/**
 * Controller containing all the operations related with the <code>Payment</code> and <code>Invoice
 * </code> models on the System, such as creating, updating and retrieving information about them.
 * Most of the operations relly on the CRUD operations accessed through the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see PaymentMemoryDAO
 * @see PaymentCRUD
 * @see Payment
 * @see InvoiceMemoryDAO
 * @see InvoiceCRUD
 * @see Invoice
 */
public abstract class BillingController {

  /**
   * Attempts to generate a new <code>Payment</code> for a given <code>Invoice</code>.
   *
   * @param paymentMethod The method of the <code>Payment</code>.
   * @param invoiceID The ID of the referred <code>Invoice</code>.
   * @param paidValue The paid value.
   * @return The generated <code>Payment</code>.
   * @throws PaymentMethodNotFoundException If the <code>paymentMethod</code> didn't match any of
   *     the ones declared under the <code>PaymentMethod</code> enumeration.
   * @throws ValueExceededException If the <code>paidValue</code> exceeds the total value of the
   *     referred <code>Invoice</code>.
   * @throws InvoiceNotFoundException If the <code>invoiceID</code> didn't match any of the ones
   *     from the registered <code>Invoice</code>s.
   * @see Payment
   * @see PaymentMethod
   * @see Invoice
   */
  public static Payment receivePayment(String paymentMethod, String invoiceID, double paidValue)
      throws PaymentMethodNotFoundException, ValueExceededException, InvoiceNotFoundException {
    if (PaymentMethod.findByType(paymentMethod) == null) {
      throw new PaymentMethodNotFoundException("Payment method not found!");
    }

    Invoice foundInvoice = DAO.fromInvoices().findByID(invoiceID);
    double invoicePaidValue = calculatePaidValue(invoiceID);

    if (invoicePaidValue + paidValue >= foundInvoice.getTotalValue()) {
      throw new ValueExceededException("Paid value exceeds the total!");
    }

    Payment newPayment = new Payment(invoiceID, PaymentMethod.findByType(paymentMethod), paidValue);

    return DAO.fromPayments().createOne(newPayment);
  }

  /**
   * Calculates the currently total paid value of a given <code>Invoice</code>.
   *
   * @param invoiceID The ID of the targeted <code>Invoice</code>.
   * @return The total paid value.
   * @throws InvoiceNotFoundException If the <code>invoiceID</code> didn't match any of the ones
   *     from the registered <code>Invoice</code>s.
   * @see Payment
   * @see Invoice
   */
  public static double calculatePaidValue(String invoiceID) throws InvoiceNotFoundException {
    Invoice foundInvoice = DAO.fromInvoices().findByID(invoiceID);

    if (foundInvoice == null) {
      throw new InvoiceNotFoundException("Invoice not found!");
    }

    return DAO.fromPayments().findByInvoice(invoiceID).stream()
        .map(Payment::getPaidValue)
        .reduce(0.0, Double::sum);
  }

  /**
   * Searches for all the <code>Payment</code>s related to a given <code>Invoice</code>.
   *
   * @param invoiceID The ID of the targeted <code>Invoice</code>.
   * @return A list of all the found <code>Payment</code>s.
   * @throws InvoiceNotFoundException If the <code>invoiceID</code> didn't match any of the one from
   *     the registered <code>Invoice</code>s.
   * @see Invoice
   * @see Payment
   */
  public static List<Payment> invoicePayments(String invoiceID) throws InvoiceNotFoundException {
    Invoice foundInvoice = DAO.fromInvoices().findByID(invoiceID);

    if (foundInvoice == null) {
      throw new InvoiceNotFoundException("Invoice not found!");
    }

    return DAO.fromPayments().findByInvoice(invoiceID);
  }
}
