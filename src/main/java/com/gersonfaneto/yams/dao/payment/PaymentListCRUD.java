package com.gersonfaneto.techinfo.dao.payment;

import com.gersonfaneto.techinfo.models.billing.payment.Payment;

import java.util.*;

public class PaymentListCRUD implements PaymentDAO {
    private final Map<String, Payment> registeredPayments;

    public PaymentListCRUD() {
        this.registeredPayments = new HashMap<>();
    }

    @Override
    public Payment createOne(Payment newPayment) {
        String newID = UUID.randomUUID().toString();

        newPayment.setPaymentID(newID);

        registeredPayments.put(newID, newPayment);

        return newPayment;
    }

    @Override
    public Payment findByID(String targetID) {
        return registeredPayments.get(targetID);
    }

    @Override
    public List<Payment> findMany() {
        List<Payment> foundPayments = new LinkedList<>();

        for (Payment currentPayment : registeredPayments.values()) {
            foundPayments.add(currentPayment);
        }

        return foundPayments;
    }

    @Override
    public boolean updateInformation(Payment targetPayment) {
        String paymentID = targetPayment.getPaymentID();

        if (registeredPayments.containsKey(paymentID)) {
            registeredPayments.put(paymentID, targetPayment);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredPayments.containsKey(targetID)) {
            registeredPayments.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredPayments.isEmpty()) {
            registeredPayments.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<Payment> findByInvoiceID(String targetID) {
        List<Payment> foundPayments = new LinkedList<>();

        for (Payment currentPayment : registeredPayments.values()) {
            if (currentPayment.getInvoiceID().equals(targetID)) {
                foundPayments.add(currentPayment);
            }
        }

        return foundPayments;
    }
}
