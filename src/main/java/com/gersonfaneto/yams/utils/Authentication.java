package com.gersonfaneto.yams.utils;

import com.gersonfaneto.yams.models.entities.user.User;

public abstract class Authentication {
  public static boolean validateValue(String receivedValue, String expectedValue) {
    return expectedValue.equals(receivedValue);
  }
}
