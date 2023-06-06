package com.gersonfaneto.yams.utils;

import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.models.stock.ComponentType;

public abstract class TypeParser {
  public static ComponentType parseComponentType(String typeName) {
    ComponentType componentType = null;

    switch (typeName) {
      case "RAM":
        componentType = ComponentType.RAM;
        break;
      case "Placa Mãe":
        componentType = ComponentType.Motherboard;
        break;
      case "Fonte":
        componentType = ComponentType.PowerSupply;
        break;
      case "Placa de Vídeo":
        componentType = ComponentType.GraphicsCard;
        break;
      case "HD":
        componentType = ComponentType.HD;
        break;
      case "SSD":
        componentType = ComponentType.SSD;
        break;
      default:
        componentType = ComponentType.Others;
        break;
    }

    return componentType;
  }

  public static UserType parseUserType(String typeName) {
    UserType userType = null;

    switch (typeName) {
      case "Recepcionista":
        userType = UserType.Receptionist;
        break;
      case "Têcnico(a)":
        userType = UserType.Technician;
        break;
      case "Administrador":
        userType = UserType.Administrator;
      default:
        break;
    }

    return userType;
  }
}
