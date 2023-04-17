package com.gersonfaneto.yams.exceptions.services;

import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;

/**
 * Thrown when a <code>ServiceType</code> doesn't match any of the ones declared under the
 * <code>ServiceType</code> enumeration.
 *
 * @see Service
 * @see ServiceType
 */
public class ServiceTypeNotFoundException extends Exception {

  /**
   * Constructs a new <code>ServiceTypeNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public ServiceTypeNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
