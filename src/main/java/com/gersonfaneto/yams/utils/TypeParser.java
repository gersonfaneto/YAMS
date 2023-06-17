package com.gersonfaneto.yams.utils;

import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.models.orders.work.WorkOrderState;
import com.gersonfaneto.yams.models.services.ServiceType;
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
      case "Têcnico":
        userType = UserType.Technician;
        break;
      case "Administrador":
        userType = UserType.Administrator;
        break;
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
        typeName = "Têcnico";
        break;
      case Administrator:
        typeName = "Administrador";
        break;
      default:
        typeName = "Todos";
        break;
    }

    return typeName;
  }

  public static WorkOrderState parseWorkOrderStateType(String workOrderState) {
    WorkOrderState typeName = null;

    switch (workOrderState) {
      case "Cancelada":
        typeName = WorkOrderState.Canceled;
        break;
      case "Em Espera":
        typeName = WorkOrderState.Created;
        break;
      case "Concluída":
        typeName = WorkOrderState.Finished;
        break;
      case "Aberta":
        typeName = WorkOrderState.Open;
        break;
      case "Paga":
        typeName = WorkOrderState.Payed;
        break;
      default:
        break;
    }

    return typeName;
  }

  public static String parseWorkOrderStateType(WorkOrderState workOrderState) {
    String typeName = null;

    switch (workOrderState) {
      case Canceled:
        typeName = "Cancelada";
        break;
      case Created:
        typeName = "Em Espera";
        break;
      case Finished:
        typeName = "Concluída";
        break;
      case Open:
        typeName = "Aberta";
        break;
      case Payed:
        typeName = "Paga";
        break;
      default:
        break;
    }

    return typeName;
  }

  public static ServiceType parseServiceType(String typeName) {
    ServiceType serviceType = null;

    switch (typeName) {
      case "Montagem":
        serviceType = ServiceType.Assembly;
        break;
      case "Limpeza":
        serviceType = ServiceType.Cleaning;
        break;
      case "Formatação":
        serviceType = ServiceType.Formatting;
        break;
      case "Programas":
        serviceType = ServiceType.ProgramInstallation;
        break;
      default:
        break;
    }

    return serviceType;
  }

  public static String parseServiceType(ServiceType serviceType) {
    String typeName = null;

    switch (serviceType) {
      case Assembly:
        typeName = "Montagem";
        break;
      case Cleaning:
        typeName = "Limpeza";
        break;
      case Formatting:
        typeName = "Formatação";
        break;
      case ProgramInstallation:
        typeName = "Programas";
        break;
      default:
        break;
    }

    return typeName;
  }

  public static PaymentMethod parsePaymentMethod(String typeName) {
    PaymentMethod paymentMethod = null;

    switch (typeName) {
      case "Dinheiro":
        paymentMethod = PaymentMethod.Cash;
        break;
      case "Cartão de Credito":
        paymentMethod = PaymentMethod.CreditCard;
        break;
      case "Cartão de Débito":
        paymentMethod = PaymentMethod.DebitCard;
        break;
      case "Transferência":
        paymentMethod = PaymentMethod.Transference;
        break;
      default:
        break;
    }

    return paymentMethod;
  }

  public static String parsePaymentMethod(PaymentMethod paymentMethod) {
    String methodName = null;

    switch (paymentMethod) {
      case Cash:
        methodName = "Dinheiro";
        break;
      case CreditCard:
        methodName = "Cartão de Credito";
        break;
      case DebitCard:
        methodName = "Cartão de Débito";
        break;
      case Transference:
        methodName = "Transferência";
        break;
      default:
        break;
    }

    return methodName;
  }
}
