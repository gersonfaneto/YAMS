package com.gersonfaneto.techinfo.dao.billing.payment;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.billing.Payment;

import java.util.List;

public interface PaymentDAO extends CRUD<Payment> {
    public List<Payment> findByInvoiceID(int invoiceID);
}
