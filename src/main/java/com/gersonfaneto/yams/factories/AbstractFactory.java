package com.gersonfaneto.yams.factories;

import com.gersonfaneto.yams.factories.types.ComponentFactory;
import com.gersonfaneto.yams.factories.types.PaymentFactory;
import com.gersonfaneto.yams.factories.types.ServiceFactory;

public abstract class AbstractFactory {
  private static PaymentFactory paymentFactory;
  private static ServiceFactory serviceFactory;
  private static ComponentFactory componentFactory;

  public static PaymentFactory fromPayments() {
    if (paymentFactory == null) {
      paymentFactory = new PaymentFactory();
    }

    return paymentFactory;
  }

  public static ServiceFactory fromServices() {
    if (serviceFactory == null) {
      serviceFactory = new ServiceFactory();
    }

    return serviceFactory;
  }

  public static ComponentFactory fromComponents() {
    if (componentFactory == null) {
      componentFactory = new ComponentFactory();
    }

    return componentFactory;
  }
}
