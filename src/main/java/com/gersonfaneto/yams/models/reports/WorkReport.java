package com.gersonfaneto.yams.models.reports;

import static com.gersonfaneto.yams.models.services.ServiceType.Assembly;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.utils.Time;
import java.util.List;

public class WorkReport {

  private final String workOrderID;
  private final String technicianName;
  private final String clientName;
  private final String waitTime;
  private final double averageRating;
  private final String usedComponents;

  public WorkReport(WorkOrder workOrder) {
    this.workOrderID = workOrder.getWorkOrderID();

    this.technicianName = ((Technician) DAO.fromUsers()
        .findByID(workOrder.getTechnicianID()))
        .getTechnicianName();

    this.clientName = DAO.fromClients()
        .findByID(workOrder.getClientID())
        .getClientName();

    this.waitTime = Time.durationToString(
        workOrder.getCreatedAt().getTimeInMillis(),
        workOrder.getClosedAt().getTimeInMillis()
    );

    this.averageRating = DAO.fromService()
        .findByWorkOrder(workOrderID)
        .stream()
        .mapToDouble(Service::getClientRating)
        .average()
        .orElse(0.0);

    StringBuilder stringBuilder = new StringBuilder();

    List<Service> performedServices = DAO.fromService().findByWorkOrder(workOrderID);

    for (Service currentService : performedServices) {
      if (currentService.getServiceType().equals(Assembly)) {
        for (Component currentComponent : currentService.getUsedComponents()) {
          stringBuilder.append(
              String.format(
                  "%s - R$ %.2f\n",
                  currentComponent.getComponentDescription(),
                  currentComponent.getComponentCost()
              )
          );
        }
      }
    }

    this.usedComponents = stringBuilder.toString();
  }

  public String getWorkOrderID() {
    return workOrderID;
  }

  public String getTechnicianName() {
    return technicianName;
  }

  public String getClientName() {
    return clientName;
  }

  public String getWaitTime() {
    return waitTime;
  }

  public double getAverageRating() {
    return averageRating;
  }

  public String getUsedComponents() {
    return usedComponents;
  }
}