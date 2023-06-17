package com.gersonfaneto.yams.dao.billing.payment;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.payment.Payment;
import com.gersonfaneto.yams.utils.Generators;
import com.gersonfaneto.yams.utils.ObjectIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>PaymentCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> as a cache to store all the <code>Payment</code>s during the execution of the
 * program and loads or unloads the contents of it into a file using an <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see PaymentCRUD
 * @see ObjectIO
 */
public class PaymentDiskDAO implements PaymentCRUD {

  private final Map<String, Payment> storedPayments;
  private final ObjectIO<Payment> paymentObjectIO;

  /**
   * Constructs a new <code>{@link PaymentDiskDAO}</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public PaymentDiskDAO(String savePath) {
    this.storedPayments = new HashMap<>();
    this.paymentObjectIO = new ObjectIO<>(savePath);
  }

  public boolean saveAll() {
    List<Payment> toSave = storedPayments.values()
        .stream()
        .toList();

    return paymentObjectIO.saveObjects(toSave);
  }

  public boolean loadAll() {
    List<Payment> loadedPayments = paymentObjectIO.loadObjects();

    if (loadedPayments == null) {
      return false;
    }

    for (Payment currentPayment : loadedPayments) {
      storedPayments.put(currentPayment.getPaymentID(), currentPayment);
    }

    return true;
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
    return storedPayments.values()
        .stream()
        .toList();
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
