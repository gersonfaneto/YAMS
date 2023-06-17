package com.gersonfaneto.yams.utils;

import com.gersonfaneto.yams.controllers.MainController;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.entities.receptionist.Receptionist;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.services.order.WorkOrder;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;

public abstract class Mock {
  public static void mockData() {
    DAO.fromUsers().createOne(
        new Receptionist(
          "jwatson@gmail.com", "221B", "John Watson"
        )
    );
    DAO.fromUsers().createOne(
        new Technician(
          "sholmes@gmail.com", "221B", "Shelock Holmes"
        )
    );
    DAO.fromUsers().createOne(
        new Technician(
          "mholmes@gmail.com", "eurus", "Mycroft Holmes"
        )
    );

    DAO.fromClients().createOne(
        new Client(
          "Emily Johnson",
          "123 Main Street, Anytown, USA",
          "(11) 91234-5678"
        )
    );
    DAO.fromClients().createOne(
        new Client(
          "Benjamin Lee",
          "456 Elm Avenue, Cityville, USA",
          "(21) 99876-5432"
        )
    );
    DAO.fromClients().createOne(
        new Client(
          "Sophia Rodriguez",
          "789 Oak Lane, Townsville, USA",
          "(31) 91111-2222"
        )
    );
    DAO.fromClients().createOne(
        new Client(
          "Oliver Smith",
          "987 Pine Road, Villagetown, USA",
          "(41) 94444-3333"
        )
    );
    DAO.fromClients().createOne(
        new Client(
          "Ava Williams",
          "321 Cedar Court, Boroughville, USA",
          "(51) 96666-7777"
        )
    );

    DAO.fromComponents().createOne(
        new Component(
          ComponentType.Motherboard,
          "ASUS Prime - A520M-E",
          20,
          100,
          240.40
        )
    );

    DAO.fromComponents().createOne(
        new Component(
          ComponentType.GraphicsCard,
          "NVIDIA RTX 4090 TI",
          30,
          100,
          492.90
        )
    );

    DAO.fromComponents().createOne(
        new Component(
          ComponentType.RAM,
          "Corsair Vengeance LPX 16GB DDR 3000",
          40,
          20,
          12.40
        )
    );

  
    WorkOrder newOrder = DAO.fromWorkOrders().createOne(
        new WorkOrder(
          DAO.fromClients()
            .findByName("Oliver Smith")
            .get(0)
            .getClientID()
        )
    );

    Service firstService = new Service(
        ServiceType.Formatting,
        "Instalar o Windows 11",
        null,
        0
    );
    Service secondService = new Service(
        ServiceType.ProgramInstallation,
        "Baixar o Google Chrome",
        null,
        0
    );

    firstService.setWorkOrderID(newOrder.getWorkOrderID());
    secondService.setWorkOrderID(newOrder.getWorkOrderID());

    DAO.fromService().createOne(firstService);
    DAO.fromService().createOne(secondService);

    MainController.saveData();
  }

  public static void cleanData() {
    DAO.fromUsers().deleteMany();
    DAO.fromClients().deleteMany();
    DAO.fromPayments().deleteMany();
    DAO.fromComponents().deleteMany();
    DAO.fromService().deleteMany();
    DAO.fromWorkOrders().deleteMany();

    MainController.saveData();
  }
}
