package com.gersonfaneto.techinfo.dao.billing.invoice;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.billing.Invoice;

public interface InvoiceDAO extends CRUD<Invoice> {
    public Invoice findByOrderID(int targetID);
}
