package com.gersonfaneto.techinfo.dao;

import com.gersonfaneto.techinfo.dao.billing.payment.PaymentDAO;
import com.gersonfaneto.techinfo.dao.billing.payment.PaymentListCRUD;
import com.gersonfaneto.techinfo.dao.billing.purchaseorder.PurchaseOrderDAO;
import com.gersonfaneto.techinfo.dao.billing.purchaseorder.PurchaseOrderListCRUD;
import com.gersonfaneto.techinfo.dao.client.ClientDAO;
import com.gersonfaneto.techinfo.dao.client.ClientListCRUD;
import com.gersonfaneto.techinfo.dao.order.OrderDAO;
import com.gersonfaneto.techinfo.dao.order.OrderListCRUD;
import com.gersonfaneto.techinfo.dao.technician.TechnicianDAO;
import com.gersonfaneto.techinfo.dao.technician.TechnicianListCRUD;

public abstract class DAO {
    private static ClientDAO registeredClients;
    private static OrderDAO registeredOrders;
    private static TechnicianDAO registeredTechnicians;
    private static PurchaseOrderDAO registeredPurchaseOrders;
    private static PaymentDAO registeredPayments;

    public static ClientDAO getClients() {
        if (registeredClients == null) {
            registeredClients = new ClientListCRUD();
        }
        return registeredClients;
    }

    public static OrderDAO getOrders() {
        if (registeredOrders == null) {
            registeredOrders = new OrderListCRUD();
        }
        return registeredOrders;
    }

    public static TechnicianDAO getTechnicians() {
        if (registeredTechnicians == null) {
            registeredTechnicians = new TechnicianListCRUD();
        }
        return registeredTechnicians;
    }

    public static PurchaseOrderDAO getPurchaseOrders() {
        if (registeredPurchaseOrders == null) {
            registeredPurchaseOrders = new PurchaseOrderListCRUD();
        }
        return registeredPurchaseOrders;
    }

    public static PaymentDAO getPayments() {
        if (registeredPayments == null) {
            registeredPayments = new PaymentListCRUD();
        }
        return registeredPayments;
    }
}
