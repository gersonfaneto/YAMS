package com.gersonfaneto.techinfo.dao;

import com.gersonfaneto.techinfo.dao.client.ClientDAO;
import com.gersonfaneto.techinfo.dao.client.ClientListCRUD;
import com.gersonfaneto.techinfo.dao.component.ComponentDAO;
import com.gersonfaneto.techinfo.dao.component.ComponentListCRUD;
import com.gersonfaneto.techinfo.dao.invoice.InvoiceDAO;
import com.gersonfaneto.techinfo.dao.invoice.InvoiceListCRUD;
import com.gersonfaneto.techinfo.dao.payment.PaymentDAO;
import com.gersonfaneto.techinfo.dao.payment.PaymentListCRUD;
import com.gersonfaneto.techinfo.dao.purchaseorder.PurchaseOrderDAO;
import com.gersonfaneto.techinfo.dao.purchaseorder.PurchaseOrderListCRUD;
import com.gersonfaneto.techinfo.dao.service.ServiceDAO;
import com.gersonfaneto.techinfo.dao.service.ServiceListCRUD;
import com.gersonfaneto.techinfo.dao.serviceorder.ServiceOrderDAO;
import com.gersonfaneto.techinfo.dao.serviceorder.ServiceOrderListCRUD;
import com.gersonfaneto.techinfo.dao.technician.TechnicianDAO;
import com.gersonfaneto.techinfo.dao.technician.TechnicianListCRUD;

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
