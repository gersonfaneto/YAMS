package com.gersonfaneto.yams.dao;

import com.gersonfaneto.yams.dao.client.ClientDAO;
import com.gersonfaneto.yams.dao.client.ClientListCRUD;
import com.gersonfaneto.yams.dao.component.ComponentDAO;
import com.gersonfaneto.yams.dao.component.ComponentListCRUD;
import com.gersonfaneto.yams.dao.invoice.InvoiceDAO;
import com.gersonfaneto.yams.dao.invoice.InvoiceListCRUD;
import com.gersonfaneto.yams.dao.payment.PaymentDAO;
import com.gersonfaneto.yams.dao.payment.PaymentListCRUD;
import com.gersonfaneto.yams.dao.purchaseorder.PurchaseOrderDAO;
import com.gersonfaneto.yams.dao.purchaseorder.PurchaseOrderListCRUD;
import com.gersonfaneto.yams.dao.service.ServiceDAO;
import com.gersonfaneto.yams.dao.service.ServiceListCRUD;
import com.gersonfaneto.yams.dao.serviceorder.ServiceOrderDAO;
import com.gersonfaneto.yams.dao.serviceorder.ServiceOrderListCRUD;
import com.gersonfaneto.yams.dao.technician.TechnicianDAO;
import com.gersonfaneto.yams.dao.technician.TechnicianListCRUD;

public abstract class DAO {
    private static ClientDAO clientDAO;
    private static TechnicianDAO technicianDAO;
    private static ServiceDAO serviceDAO;
    private static ServiceOrderDAO serviceOrderDAO;
    private static PurchaseOrderDAO purchaseOrderDAO;
    private static ComponentDAO componentDAO;
    private static InvoiceDAO invoiceDAO;
    private static PaymentDAO paymentDAO;

    public static ClientDAO fromClients() {
        if (clientDAO == null) {
            clientDAO = new ClientListCRUD();
        }
        return clientDAO;
    }

    public static TechnicianDAO fromTechnicians() {
        if (technicianDAO == null) {
            technicianDAO = new TechnicianListCRUD();
        }
        return technicianDAO;
    }

    public static ServiceDAO fromServices() {
        if (serviceDAO == null) {
            serviceDAO = new ServiceListCRUD();
        }
        return serviceDAO;
    }

    public static ServiceOrderDAO fromServiceOrders() {
        if (serviceOrderDAO == null) {
            serviceOrderDAO = new ServiceOrderListCRUD();
        }
        return serviceOrderDAO;
    }

    public static PurchaseOrderDAO fromPurchaseOrders() {
        if (purchaseOrderDAO == null) {
            purchaseOrderDAO = new PurchaseOrderListCRUD();
        }
        return purchaseOrderDAO;
    }

    public static ComponentDAO fromComponents() {
        if (componentDAO == null) {
            componentDAO = new ComponentListCRUD();
        }

        return componentDAO;
    }

    public static InvoiceDAO fromInvoices() {
        if (invoiceDAO == null) {
            invoiceDAO = new InvoiceListCRUD();
        }
        return invoiceDAO;
    }

    public static PaymentDAO fromPayments() {
        if (paymentDAO == null) {
            paymentDAO = new PaymentListCRUD();
        }
        return paymentDAO;
    }
}
