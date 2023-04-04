package com.gersonfaneto.yams.dao;

import com.gersonfaneto.yams.dao.billing.invoice.InvoiceCRUD;
import com.gersonfaneto.yams.dao.billing.invoice.InvoiceMemoryDAO;
import com.gersonfaneto.yams.dao.billing.paymet.PaymentCRUD;
import com.gersonfaneto.yams.dao.billing.paymet.PaymentMemoryDAO;
import com.gersonfaneto.yams.dao.components.ComponentCRUD;
import com.gersonfaneto.yams.dao.components.ComponentMemoryDAO;
import com.gersonfaneto.yams.dao.entities.client.ClientCRUD;
import com.gersonfaneto.yams.dao.entities.client.ClientMemoryDAO;
import com.gersonfaneto.yams.dao.entities.receptionist.ReceptionistCRUD;
import com.gersonfaneto.yams.dao.entities.receptionist.ReceptionistMemoryDAO;
import com.gersonfaneto.yams.dao.entities.technician.TechnicianCRUD;
import com.gersonfaneto.yams.dao.entities.technician.TechnicianMemoryDAO;
import com.gersonfaneto.yams.dao.services.ServiceCRUD;
import com.gersonfaneto.yams.dao.services.ServiceMemoryDAO;

public abstract class DAO {
    private static ClientCRUD clientCRUD;
    private static ReceptionistCRUD receptionistCRUD;
    private static TechnicianCRUD technicianCRUD;
    private static PaymentCRUD paymentCRUD;
    private static InvoiceCRUD invoiceCRUD;
    private static ComponentCRUD componentCRUD;
    private static ServiceCRUD serviceCRUD;

    public static ClientCRUD fromClients() {
        if (clientCRUD == null) {
            clientCRUD = new ClientMemoryDAO();
        }

        return clientCRUD;
    }

    public static ReceptionistCRUD fromReceptionists() {
        if (receptionistCRUD == null) {
            receptionistCRUD = new ReceptionistMemoryDAO();
        }

        return receptionistCRUD;
    }

    public static TechnicianCRUD fromTechnicians() {
        if (technicianCRUD == null) {
            technicianCRUD = new TechnicianMemoryDAO();
        }

        return technicianCRUD;
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
}
