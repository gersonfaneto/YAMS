package com.gersonfaneto.yams.exceptions.services;

import com.gersonfaneto.yams.dao.services.ServiceCRUD;
import com.gersonfaneto.yams.dao.services.ServiceMemoryDAO;
import com.gersonfaneto.yams.models.services.Service;

/**
 * Thrown when a <code>Service</code> isn't found by the DAO.
 *
 * @see ServiceMemoryDAO
 * @see ServiceCRUD
 * @see Service
 */
public class ServiceNotFoundException extends Exception {

  /**
   * Constructs a new <code>ServiceNotFoundException</code> with a custom error message.
   *
   * @param errorMessage
   */
  public ServiceNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
