package com.gersonfaneto.yams.dao;

import com.gersonfaneto.yams.dao.billing.invoice.InvoiceCRUD;
import com.gersonfaneto.yams.dao.billing.invoice.InvoiceDiskDAO;
import com.gersonfaneto.yams.dao.billing.payment.PaymentCRUD;
import com.gersonfaneto.yams.dao.billing.payment.PaymentDiskDAO;
import com.gersonfaneto.yams.dao.entities.client.ClientCRUD;
import com.gersonfaneto.yams.dao.entities.client.ClientDiskDAO;
import com.gersonfaneto.yams.dao.entities.user.UserCRUD;
import com.gersonfaneto.yams.dao.entities.user.UserDiskDAO;
import com.gersonfaneto.yams.dao.services.order.WorkOrderCRUD;
import com.gersonfaneto.yams.dao.services.order.WorkOrderDiskDAO;
import com.gersonfaneto.yams.dao.services.service.ServiceCRUD;
import com.gersonfaneto.yams.dao.services.service.ServiceDiskDAO;
import com.gersonfaneto.yams.dao.stock.ComponentCRUD;
import com.gersonfaneto.yams.dao.stock.ComponentDiskDAO;

/**
 * Defines a access point for the <code>DAO</code>s of each model in the System using the
 * "Singleton" pattern. Each <code>DAO</code> is accessed through a <code>static</code> method.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see ClientCRUD
 * @see UserCRUD
 * @see PaymentCRUD
 * @see InvoiceCRUD
 * @see ComponentCRUD
 * @see ServiceCRUD
 * @see WorkOrderCRUD
 */
public abstract class DAO {

  private static ClientCRUD clientCRUD;
  private static UserCRUD userCRUD;
  private static PaymentCRUD paymentCRUD;
  private static InvoiceCRUD invoiceCRUD;
  private static ComponentCRUD componentCRUD;
  private static ServiceCRUD serviceCRUD;
  private static WorkOrderCRUD workOrderCRUD;

  private static final String SAVE_PATH = "data/";

  /**
   * Retrieves the <code>DAO</code> for the <code>Client</code> model, instantiating it if
   * necessary.
   *
   * @return The <code>DAO</code> for the <code>Client</code> model.
   */
  public static ClientCRUD fromClients() {
    if (clientCRUD == null) {
      clientCRUD = new ClientDiskDAO(SAVE_PATH + "clients.ser");
    }

    return clientCRUD;
  }

  /**
   * Retrieves the <code>DAO</code> for the <code>User</code> model, instantiating it if necessary.
   *
   * @return The <code>DAO</code> for the <code>User</code> model.
   */
  public static UserCRUD fromUsers() {
    if (userCRUD == null) {
      userCRUD = new UserDiskDAO(SAVE_PATH + "users.ser");
    }

    return userCRUD;
  }

  /**
   * Retrieves the <code>DAO</code> for the <code>Payment</code> model, instantiating it if
   * necessary.
   *
   * @return The <code>DAO</code> for the <code>Payment</code> model.
   */
  public static PaymentCRUD fromPayments() {
    if (paymentCRUD == null) {
      paymentCRUD = new PaymentDiskDAO(SAVE_PATH + "payments.ser");
    }

    return paymentCRUD;
  }

  /**
   * Retrieves the <code>DAO</code> for the <code>Invoice</code> model, instantiating it if
   * necessary.
   *
   * @return The <code>DAO</code> for the <code>Invoice</code> model.
   */
  public static InvoiceCRUD fromInvoices() {
    if (invoiceCRUD == null) {
      invoiceCRUD = new InvoiceDiskDAO(SAVE_PATH + "invoices.ser");
    }

    return invoiceCRUD;
  }

  /**
   * Retrieves the <code>DAO</code> for the <code>Component</code> model, instantiating it if
   * necessary.
   *
   * @return The <code>DAO</code> for the <code>Component</code> model.
   */
  public static ComponentCRUD fromComponents() {
    if (componentCRUD == null) {
      componentCRUD = new ComponentDiskDAO(SAVE_PATH + "components.ser");
    }

    return componentCRUD;
  }

  /**
   * Retrieves the <code>DAO</code> for the <code>Service</code> model, instantiating it if
   * necessary.
   *
   * @return The <code>DAO</code> for the <code>Service</code> model.
   */
  public static ServiceCRUD fromService() {
    if (serviceCRUD == null) {
      serviceCRUD = new ServiceDiskDAO(SAVE_PATH + "services.ser");
    }

    return serviceCRUD;
  }

  /**
   * Retrieves the <code>DAO</code> for the <code>WorkOrder</code> model, instantiating it if
   * necessary.
   *
   * @return The <code>DAO</code> for the <code>WorkOrder</code> model.
   */
  public static WorkOrderCRUD fromWorkOrders() {
    if (workOrderCRUD == null) {
      workOrderCRUD = new WorkOrderDiskDAO(SAVE_PATH + "work-orders.ser");
    }

    return workOrderCRUD;
  }
}
