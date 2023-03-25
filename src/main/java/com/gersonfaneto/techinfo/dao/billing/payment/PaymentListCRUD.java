package com.gersonfaneto.techinfo.dao.billing.payment;

import com.gersonfaneto.techinfo.models.billing.Payment;

import java.util.LinkedList;
import java.util.List;

public class PaymentListCRUD implements PaymentDAO {
    private List<Payment> paymentList;
    private int referenceID;

    public PaymentListCRUD() {
        this.paymentList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public Payment createOne(Payment targetObject) {
        targetObject.setPaymentID(++referenceID);
        paymentList.add(targetObject);
        return targetObject;
    }

    @Override
    public Payment findByID(int targetID) {
        for (Payment currentPayment : paymentList) {
            if (currentPayment.getPaymentID() == targetID) {
                return currentPayment;
            }
        }
        return null;
    }

    @Override
    public List<Payment> findMany() {
        return paymentList;
    }

    @Override
    public boolean updateInformation(Payment targetObject) {
        for (Payment currentPayment : paymentList) {
            if (currentPayment.getPaymentID() == targetObject.getPaymentID()) {
                int targetIndex = paymentList.indexOf(targetObject);
                paymentList.set(targetIndex, targetObject);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (Payment currentPayment : paymentList) {
            if (currentPayment.getPaymentID() == targetID) {
                paymentList.remove(currentPayment);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!paymentList.isEmpty()) {
            paymentList.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<Payment> findByInvoiceID(int targetID) {
        List<Payment> foundPayments = new LinkedList<>();

        for (Payment currentPayment : paymentList) {
            if (currentPayment.getInvoiceID() == targetID) {
                foundPayments.add(currentPayment);
            }
        }

        return foundPayments;
    }
}
