package com.gersonfaneto.yams.dao.billing.paymet;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>PaymentCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> to store all the <code>Payment</code>s.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see PaymentCRUD
 */
public class PaymentMemoryDAO implements PaymentCRUD {

  private final Map<String, Payment> storedPayments;

  /**
   * Constructs a new <code>{@link PaymentMemoryDAO}</code>
   */
  public PaymentMemoryDAO() {
    this.storedPayments = new HashMap<>();
  }

  @Override
  public Payment createOne(Payment newPayment) {
    String newID = Generators.randomID();

    newPayment.setPaymentID(newID);

    storedPayments.put(newID, newPayment);

    return newPayment;
  }

  @Override
  public Payment findByID(String targetID) {
    return storedPayments.get(targetID);
  }

  @Override
  public List<Payment> findMany() {
    return storedPayments.values().stream().toList();
  }

  @Override
  public boolean updateInformation(Payment updatedPayment) {
    String paymentID = updatedPayment.getPaymentID();

    if (storedPayments.containsKey(paymentID)) {
      storedPayments.put(paymentID, updatedPayment);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedPayments.containsKey(targetID)) {
      storedPayments.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedPayments.isEmpty()) {
      storedPayments.clear();
      return true;
    }

    return false;
  }

  @Override
  public List<Payment> findByInvoice(String invoiceID) {
    return storedPayments.values()
        .stream()
        .filter(x -> x.getInvoiceID().equals(invoiceID))
        .toList();
  }
}
