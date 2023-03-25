package com.gersonfaneto.techinfo.dao.billing.invoice;

import com.gersonfaneto.techinfo.models.billing.Invoice;

import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

public class InvoiceListCRUD implements InvoiceDAO {
    private List<Invoice> invoiceList;
    private int referenceID;

    public InvoiceListCRUD() {
        this.invoiceList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public Invoice createOne(Invoice targetObject) {
        targetObject.setInvoiceID(++referenceID);
        invoiceList.add(targetObject);
        return targetObject;
    }

    @Override
    public Invoice findByID(int targetID) {
        for (Invoice currentInvoice : invoiceList) {
            if (currentInvoice.getInvoiceID() == targetID) {
                return currentInvoice;
            }
        }
        return null;
    }

    @Override
    public List<Invoice> findMany() {
        return invoiceList;
    }

    @Override
    public boolean updateInformation(Invoice targetObject) {
        for (Invoice currentInvoice : invoiceList) {
            if (currentInvoice.getInvoiceID() == targetObject.getInvoiceID()) {
                int targetIndex = invoiceList.indexOf(currentInvoice);
                invoiceList.set(targetIndex, targetObject);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (Invoice currentInvoice : invoiceList) {
            if (currentInvoice.getInvoiceID() == targetID) {
                invoiceList.remove(currentInvoice);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!invoiceList.isEmpty()) {
            invoiceList.clear();
            return true;
        }

        return false;
    }

    @Override
    public Invoice findByOrderID(int targetID) {
        for (Invoice currentInvoice : invoiceList) {
            if (currentInvoice.getOrderID() == targetID) {
                return currentInvoice;
            }
        }
        return null;
    }
}
