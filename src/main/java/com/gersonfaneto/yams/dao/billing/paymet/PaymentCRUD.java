package com.gersonfaneto.yams.dao.billing.paymet;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.payments.Payment;

import java.util.List;

public interface PaymentCRUD extends CRUD<Payment> {
    public List<Payment> findByInvoice(String invoiceID);
}
