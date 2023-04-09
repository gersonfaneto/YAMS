package com.gersonfaneto.yams.models.reports.work;

import static com.gersonfaneto.yams.models.services.ServiceType.Assembly;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.utils.TimeConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkReport {

  private final String workOrderID;
  private final String technicianName;
  private final String clientName;
  private final String waitTime;
  private final double averageRating;
  private final Map<String, Double> usedComponents;

  public WorkReport(WorkOrder workOrder) {
    this.workOrderID = workOrder.getWorkOrderID();

    this.technicianName =
        DAO.fromTechnicians().findByID(workOrder.getTechnicianID()).getTechnicianName();

    this.clientName = DAO.fromClients().findByID(workOrder.getClientID()).getClientName();

    this.waitTime =
        TimeConverter.convertToDuration(
            workOrder.getCreatedAt().getTimeInMillis(), workOrder.getClosedAt().getTimeInMillis());

    this.averageRating =
        workOrder.getChosenServices().stream()
                .map(Service::getClientRating)
                .reduce(0.0, Double::sum)
            / workOrder.getChosenServices().size();

    this.usedComponents = new HashMap<>();

    List<Service> performedServices = workOrder.getChosenServices();
    for (Service currentService : performedServices) {
      if (currentService.getServiceType().equals(Assembly)) {
        for (Component currentComponent : currentService.getUsedComponents()) {
          usedComponents.put(
              currentComponent.getComponentDescription(), currentComponent.getComponentCost());
        }
      }
    }
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

  public Map<String, Double> getUsedComponents() {
    return usedComponents;
  }
}
