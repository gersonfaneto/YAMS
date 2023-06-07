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

  public static String parseComponentType(ComponentType componentType) {
    String typeName = null;

    switch (componentType) {
      case RAM:
        typeName = "RAM";
        break;
      case Motherboard:
        typeName = "Placa Mãe";
        break;
      case PowerSupply:
        typeName = "Fonte";
        break;
      case GraphicsCard:
        typeName = "Placa de Vídeo";
        break;
      case HD:
        typeName = "HD";
        break;
      case SSD:
        typeName = "SSD";
        break;
      default:
        typeName = "Outros";
        break;
    }

    return typeName;
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

  public static String parseUserType(UserType userType) {
    String typeName = null;

    switch (userType) {
      case Receptionist:
        typeName = "Recepcionista";
        break;
      case Technician:
        typeName = "Têcnico(a)";
        break;
      case Administrator:
        typeName = "Administrador(a)";
      default:
        break;
    }

    return typeName;
  }
}
