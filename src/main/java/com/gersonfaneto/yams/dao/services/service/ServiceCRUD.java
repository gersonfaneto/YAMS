package com.gersonfaneto.yams.dao.services.service;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import java.util.List;

/**
 * Extends the <code>CRUD</code> interface by adding opeations specific to the <code>Service</code>
 * models.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see Service
 */
public interface ServiceCRUD extends CRUD<Service>, Persist {

  /**
   * Searches for all the <code>Service</code>s of a given type.
   *
   * @param serviceType The targeted <code>Service</code> type.
   * @return The list of all the found <code>Service</code>s.
   */
  List<Service> findByType(ServiceType serviceType);

  /**
   * Searches for all the <code>Service</code>s related to a given <code>WorkOrder</code>.
   *
   * @param workOrderID The ID of the targeted <code>WorkOrder</code>.
   * @return The list of all the found <code>Service</code>s.
   */
  List<Service> findByWorkOrder(String workOrderID);
}
