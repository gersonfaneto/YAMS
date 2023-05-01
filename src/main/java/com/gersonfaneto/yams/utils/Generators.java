package com.gersonfaneto.yams.utils;

import java.util.UUID;

/**
 * <code>Generators</code> are used for quickly generating "random" IDs using the {@link UUID} from
 * {@link java.util} API.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public abstract class Generators {

  /**
   * Generates a new "random" ID using the {@link UUID} Java utility.
   *
   * @return A "random" ID of the <code>String</code> type.
   */
  public static String randomID() {
    return UUID.randomUUID().toString();
  }
}
