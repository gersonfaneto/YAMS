package com.gersonfaneto.yams.factories.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.types.Cash;
import com.gersonfaneto.yams.models.billing.payments.types.CreditCard;
import com.gersonfaneto.yams.models.billing.payments.types.DebitCard;
import com.gersonfaneto.yams.models.billing.payments.types.Transference;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {
    private final Map<String, Payment> paymentPrototypes;

    public PaymentFactory() {
      this.paymentPrototypes = new HashMap<>();

      paymentPrototypes.put("Cash", new Cash());
      paymentPrototypes.put("Transference", new Transference());
      paymentPrototypes.put("Credit Card", new CreditCard());
      paymentPrototypes.put("Debit Card", new DebitCard());
    }

    public Payment generatePayment(String paymentMethod, String invoiceID, double paidValue) {
      if (!paymentPrototypes.containsKey(paymentMethod)) {
        throw new InvalidParameterException("Payment method not found!");
      }

      return paymentPrototypes.get(paymentMethod).clone(invoiceID, paidValue);
    }
}
