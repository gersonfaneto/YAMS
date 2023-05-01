package com.gersonfaneto.yams.dao.billing.invoice;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>InvoiceCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> to store all the <code>Invoice</code>s.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see InvoiceCRUD
 */
public class InvoiceMemoryDAO implements InvoiceCRUD {

  private final Map<String, Invoice> storedInvoices;

  /**
   * Constructs a new <code>{@link InvoiceMemoryDAO}</code>
   */
  public InvoiceMemoryDAO() {
    this.storedInvoices = new HashMap<>();
  }

  @Override
  public Invoice createOne(Invoice newInvoice) {
    String newID = Generators.randomID();

    newInvoice.setInvoiceID(newID);

    storedInvoices.put(newID, newInvoice);

    return newInvoice;
  }

  @Override
  public Invoice findByID(String targetID) {
    return storedInvoices.get(targetID);
  }

  @Override
  public List<Invoice> findMany() {
    return storedInvoices.values().stream().toList();
  }

  @Override
  public boolean updateInformation(Invoice updatedInvoice) {
    String invoiceID = updatedInvoice.getInvoiceID();

    if (storedInvoices.containsKey(invoiceID)) {
      storedInvoices.put(invoiceID, updatedInvoice);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedInvoices.containsKey(targetID)) {
      storedInvoices.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedInvoices.isEmpty()) {
      storedInvoices.clear();
      return true;
    }

    return false;
  }

  @Override
  public Invoice findByWorkOrder(String workOrderID) {
    return storedInvoices.values()
        .stream()
        .filter(x -> x.getWorkOrderID().equals(workOrderID))
        .findFirst()
        .orElse(null);
  }
}
