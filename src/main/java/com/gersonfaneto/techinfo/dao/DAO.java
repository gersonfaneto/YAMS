package com.gersonfaneto.techinfo.dao;

import com.gersonfaneto.techinfo.dao.billing.invoice.InvoiceDAO;
import com.gersonfaneto.techinfo.dao.billing.invoice.InvoiceListCRUD;
import com.gersonfaneto.techinfo.dao.billing.payment.PaymentDAO;
import com.gersonfaneto.techinfo.dao.billing.payment.PaymentListCRUD;
import com.gersonfaneto.techinfo.dao.stock.component.ComponentDAO;
import com.gersonfaneto.techinfo.dao.stock.component.ComponentListCRUD;
import com.gersonfaneto.techinfo.dao.stock.purchaseorder.PurchaseOrderDAO;
import com.gersonfaneto.techinfo.dao.stock.purchaseorder.PurchaseOrderListCRUD;
import com.gersonfaneto.techinfo.dao.client.ClientDAO;
import com.gersonfaneto.techinfo.dao.client.ClientListCRUD;
import com.gersonfaneto.techinfo.dao.order.OrderDAO;
import com.gersonfaneto.techinfo.dao.order.OrderListCRUD;
import com.gersonfaneto.techinfo.dao.service.ServiceDAO;
import com.gersonfaneto.techinfo.dao.service.ServiceListCRUD;
import com.gersonfaneto.techinfo.dao.technician.TechnicianDAO;
import com.gersonfaneto.techinfo.dao.technician.TechnicianListCRUD;

public abstract class DAO {
    private static ClientDAO registeredClients;
    private static TechnicianDAO registeredTechnicians;
    private static ServiceDAO registeredServices;
    private static OrderDAO registeredOrders;
    private static PurchaseOrderDAO registeredPurchaseOrders;
    private static ComponentDAO registeredComponents;
    private static InvoiceDAO registeredInvoices;
    private static PaymentDAO registeredPayments;

    public static ClientDAO getClients() {
        if (registeredClients == null) {
            registeredClients = new ClientListCRUD();
        }
        return registeredClients;
    }

    public static TechnicianDAO getTechnicians() {
        if (registeredTechnicians == null) {
            registeredTechnicians = new TechnicianListCRUD();
        }
        return registeredTechnicians;
    }

    public static ServiceDAO getServices() {
        if (registeredServices == null) {
            registeredServices = new ServiceListCRUD();
        }
        return registeredServices;
    }

    public static OrderDAO getOrders() {
        if (registeredOrders == null) {
            registeredOrders = new OrderListCRUD();
        }
        return registeredOrders;
    }

    public static PurchaseOrderDAO getPurchaseOrders() {
        if (registeredPurchaseOrders == null) {
            registeredPurchaseOrders = new PurchaseOrderListCRUD();
        }
        return registeredPurchaseOrders;
    }

    public static ComponentDAO getComponents() {
        if (registeredComponents == null) {
            registeredComponents = new ComponentListCRUD();
        }

        return registeredComponents;
    }

    public static InvoiceDAO getInvoices() {
        if (registeredInvoices == null) {
            registeredInvoices = new InvoiceListCRUD();
        }
        return registeredInvoices;
    }

    public static PaymentDAO getPayments() {
        if (registeredPayments == null) {
            registeredPayments = new PaymentListCRUD();
        }
        return registeredPayments;
    }
}
