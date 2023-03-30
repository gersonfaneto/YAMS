package com.gersonfaneto.yams.dao.payment;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.payment.Payment;

import java.util.List;

public interface PaymentDAO extends CRUD<Payment> {
    List<Payment> findByInvoiceID(String invoiceID);
}
