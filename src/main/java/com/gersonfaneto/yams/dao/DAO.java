package com.gersonfaneto.yams.dao;

import com.gersonfaneto.yams.dao.billing.invoice.InvoiceCRUD;
import com.gersonfaneto.yams.dao.billing.invoice.InvoiceMemoryDAO;
import com.gersonfaneto.yams.dao.billing.paymet.PaymentCRUD;
import com.gersonfaneto.yams.dao.billing.paymet.PaymentMemoryDAO;
import com.gersonfaneto.yams.dao.stock.ComponentCRUD;
import com.gersonfaneto.yams.dao.stock.ComponentMemoryDAO;
import com.gersonfaneto.yams.dao.entities.client.ClientCRUD;
import com.gersonfaneto.yams.dao.entities.client.ClientMemoryDAO;
import com.gersonfaneto.yams.dao.entities.user.UserCRUD;
import com.gersonfaneto.yams.dao.entities.user.UserMemoryDAO;
import com.gersonfaneto.yams.dao.orders.purchase.PurchaseOrderCRUD;
import com.gersonfaneto.yams.dao.orders.purchase.PurchaseOrderMemoryDAO;
import com.gersonfaneto.yams.dao.orders.work.WorkOrderCRUD;
import com.gersonfaneto.yams.dao.orders.work.WorkOrderMemoryDAO;
import com.gersonfaneto.yams.dao.services.ServiceCRUD;
import com.gersonfaneto.yams.dao.services.ServiceMemoryDAO;

public abstract class DAO {

  private static ClientCRUD clientCRUD;
  private static UserCRUD userCRUD;
  private static PaymentCRUD paymentCRUD;
  private static InvoiceCRUD invoiceCRUD;
  private static ComponentCRUD componentCRUD;
  private static ServiceCRUD serviceCRUD;
  private static PurchaseOrderCRUD purchaseOrderCRUD;
  private static WorkOrderCRUD workOrderCRUD;

  public static ClientCRUD fromClients() {
    if (clientCRUD == null) {
      clientCRUD = new ClientMemoryDAO();
    }

    return clientCRUD;
  }

  public static UserCRUD fromUsers() {
    if (userCRUD == null) {
      userCRUD = new UserMemoryDAO();
    }

    return userCRUD;
  }

  public static PaymentCRUD fromPayments() {
    if (paymentCRUD == null) {
      paymentCRUD = new PaymentMemoryDAO();
    }

    return paymentCRUD;
  }

  public static InvoiceCRUD fromInvoices() {
    if (invoiceCRUD == null) {
      invoiceCRUD = new InvoiceMemoryDAO();
    }

    return invoiceCRUD;
  }

  public static ComponentCRUD fromComponents() {
    if (componentCRUD == null) {
      componentCRUD = new ComponentMemoryDAO();
    }

    return componentCRUD;
  }

  public static ServiceCRUD fromService() {
    if (serviceCRUD == null) {
      serviceCRUD = new ServiceMemoryDAO();
    }

    return serviceCRUD;
  }

  public static PurchaseOrderCRUD fromPurchaseOrders() {
    if (purchaseOrderCRUD == null) {
      purchaseOrderCRUD = new PurchaseOrderMemoryDAO();
    }

    return purchaseOrderCRUD;
  }

  public static WorkOrderCRUD fromWorkOrders() {
    if (workOrderCRUD == null) {
      workOrderCRUD = new WorkOrderMemoryDAO();
    }

    return workOrderCRUD;
  }
}
