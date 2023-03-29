package com.gersonfaneto.techinfo.dao.payment;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.billing.payment.Payment;

import java.util.List;

public interface PaymentDAO extends CRUD<Payment> {
    List<Payment> findByInvoiceID(String invoiceID);
}
