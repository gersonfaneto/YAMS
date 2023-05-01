package com.gersonfaneto.yams.dao.billing.invoice;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.ObjectIO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Implementations for the <code>InvoiceCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> as a cache to store all the <code>Invoice</code>s during the execution of the
 * program and loads or unloads the contents of it into a file using an <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see InvoiceCRUD
 * @see ObjectIO
 */
public class InvoiceDiskDAO implements InvoiceCRUD, Persist {

  private final Map<String, Invoice> storedInvoices;
  private final ObjectIO<Invoice> invoiceObjectIO;

  /**
   * Constructs a new <code>InvoiceDiskDAO</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public InvoiceDiskDAO(String savePath) {
    this.invoiceObjectIO = new ObjectIO<>(savePath);
    this.storedInvoices = new HashMap<>();
  }

  public boolean saveAll() {
    List<Invoice> toSave = storedInvoices.values()
        .stream()
        .toList();

    return invoiceObjectIO.saveObjects(toSave);
  }

  public boolean loadAll() {
    List<Invoice> loadedInvoices = invoiceObjectIO.loadObjects();

    if (loadedInvoices == null) {
      return false;
    }

    for (Invoice currentInvoice : loadedInvoices) {
      storedInvoices.put(currentInvoice.getInvoiceID(), currentInvoice);
    }

    return true;
  }

  @Override
  public Invoice createOne(Invoice newInvoice) {
    String newID = UUID.randomUUID().toString();

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
    return storedInvoices.values()
        .stream()
        .toList();
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
