package com.gersonfaneto.yams.factories.types;

import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.types.Assembly;
import com.gersonfaneto.yams.models.services.types.Cleaning;
import com.gersonfaneto.yams.models.services.types.Formatting;
import com.gersonfaneto.yams.models.services.types.ProgramsInstallation;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

  private final Map<String, Service> servicePrototypes;

  public ServiceFactory() {
    this.servicePrototypes = new HashMap<>();

    servicePrototypes.put("Assembly", new Assembly());
    servicePrototypes.put("Cleaning", new Cleaning());
    servicePrototypes.put("Formatting", new Formatting());
    servicePrototypes.put("Programs Installation", new ProgramsInstallation());
  }

  public Service generateService(String serviceType, String workOrderID, String serviceDescription) {
    if (!servicePrototypes.containsKey(serviceType)) {
      throw new InvalidParameterException("Service type not found!");
    }

    return servicePrototypes.get(serviceType).clone(workOrderID, serviceDescription);
  }
}
