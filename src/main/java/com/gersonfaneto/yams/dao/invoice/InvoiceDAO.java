package com.gersonfaneto.techinfo.dao.invoice;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.billing.Invoice;

public interface InvoiceDAO extends CRUD<Invoice> {
    Invoice findByOrderID(String targetID);
}
