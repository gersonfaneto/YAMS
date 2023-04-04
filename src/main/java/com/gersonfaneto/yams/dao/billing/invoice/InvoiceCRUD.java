package com.gersonfaneto.yams.dao.billing.invoice;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.Invoice;

public interface InvoiceCRUD extends CRUD<Invoice> {
    public Invoice findByWorkOrder(String workOrderID);
}
