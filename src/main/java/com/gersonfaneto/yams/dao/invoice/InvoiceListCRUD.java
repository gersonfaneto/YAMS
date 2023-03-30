package com.gersonfaneto.techinfo.dao.invoice;

import com.gersonfaneto.techinfo.models.billing.Invoice;

import java.util.*;

public class InvoiceListCRUD implements InvoiceDAO {
    private final Map<String, Invoice> registeredInvoices;

    public InvoiceListCRUD() {
        this.registeredInvoices = new HashMap<>();
    }

    @Override
    public Invoice createOne(Invoice newInvoice) {
        String newID = UUID.randomUUID().toString();

        newInvoice.setInvoiceID(newID);

        registeredInvoices.put(newID, newInvoice);

        return newInvoice;
    }

    @Override
    public Invoice findByID(String targetID) {
        return registeredInvoices.get(targetID);
    }

    @Override
    public List<Invoice> findMany() {
        List<Invoice> foundInvoices = new LinkedList<>();

        for (Invoice currentInvoice : registeredInvoices.values()) {
            foundInvoices.add(currentInvoice);
        }

        return foundInvoices;
    }

    @Override
    public boolean updateInformation(Invoice targetInvoice) {
        String invoiceID = targetInvoice.getInvoiceID();

        if (registeredInvoices.containsKey(invoiceID)) {
            registeredInvoices.put(invoiceID, targetInvoice);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredInvoices.containsKey(targetID)) {
            registeredInvoices.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredInvoices.isEmpty()) {
            registeredInvoices.clear();
            return true;
        }

        return false;
    }

    @Override
    public Invoice findByOrderID(String targetID) {
        for (Invoice currentInvoice : registeredInvoices.values()) {
            if (currentInvoice.getServiceOrderID().equals(targetID)) {
                return currentInvoice;
            }
        }
        return null;
    }
}
