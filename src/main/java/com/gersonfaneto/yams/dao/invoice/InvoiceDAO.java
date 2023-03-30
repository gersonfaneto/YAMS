package com.gersonfaneto.yams.dao.invoice;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.Invoice;

public interface InvoiceDAO extends CRUD<Invoice> {
    Invoice findByOrderID(String targetID);
}
