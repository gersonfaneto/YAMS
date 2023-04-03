# System Models

## Entities

- Client
  - Attributes
    - ID: String (UUID)
    - Name: String
    - HomeAddress: String
    - PhoneNumber: String
    - WorkOrderHistory: List\<WorkOrder\>

- _User_
  - Attributes
    - ID: String (UUID)
    - Email: String
    - Password: String

- Receptionist : _User_
  - Attributes
    - Name: String
  - Methods:
    - RegisterClient(): Client

- Technician : _User_
  - Attributes
    - Name: String
    - WorkingOn: WorkOrder
    - State: TechnicianState
    - WorkOrderHistory: List<WorkOrder>
  - Methods:
    - CreateWorkOrder(): WorkOrder
    - AddService(): Boolean
    - RemoveService(): Boolean
    - OpenOrder(): Boolean
    - CancelOrder(): Boolean
    - CloseOrder(): Boolean
    - GenerateInvoice(): Boolean
    - BuyComponent(): Boolean
    - GenerateReport(): Boolean

## Services

- _Service_
  - Attributes
    - ID: String (UUID)
    - Type: String
    - Description: String
    - Price: Double

- Assembly : _Service_
  - Attributes
    - UsedComponents: List<Component>
  - Methods
    - AddComponent(): Boolean
    - RemoveComponent(): Boolean

- Cleaning : _Service_

- Formatting : _Service_

- ProgramsInstallation : _Service_
  - Attributes
    - ChosenPrograms: List<String>
  - Methods
    - AddProgram(): Boolean
    - RemoveProgram(): Boolean

## Stock

- _Component_
  - Attributes
    - ID: String (UUID)
    - Type: String
    - Description: String
    - Cost: Double
    - Price: Double

- RAM : _Component_
- Motherboard : _Component_
- PowerSupply : _Component_
- GraphicsCard : _Component_
- HD : _Component_
- SSD : _Component_
- Others : _Component_

## Orders

- WorkOrder
  - Attributes
    - ID: String (UUID)
    - Client: String (UUID)
    - Technician: String (UUID)
    - Invoice: String (UUID)
    - Services: List<Service>
    - State: WorkOrderState
  - Methods
    - AddService: Boolean
    - RemoveService: Boolean

- PurchaseOrder
  - Attributes
    - ID: String (UUID)
    - ComponentType: String
    - ComponentDescription: String
    - Amount: Integer
    - UniCost: Double
    - UniPrice: Double

## Billing

- _Payment_
  - Attributes
    - ID: String (UUID)
    - Invoice: String (UUID)
    - Value: Double

- Credit : _Payment_
- Cash : _Payment_
- Transfer : _Payment_

- Invoice
  - Attributes
    - ID: String (UUID)
    - WorkOrder: String (UUID)
    - TotalValue: Double
    - PaidValue: Double
    - Payments: List<Payment>
  - Methods
    - NewPayment(): Boolean

## Design Patterns

- _TechnicianState_
    - Technician
  - Methods:
    - _AddService_(): Boolean
    - _RemoveService_(): Boolean
    - _OpenOrder_(): Boolean
    - _CancelOrder_(): Boolean
    - _CloseOrder_(): Boolean
    - _GenerateInvoice_(): Boolean

- Free : _TechnicianState_

- Occupied : _TechnicianState_

- _WorkOrderState_
  - Attributes
    - WorkOrder
  - Methods
    - _AddService_: Boolean
    - _RemoveService_: Boolean

- Created : _WorkOrderState_

- Open : _WorkOrderState_

- Canceled : _WorkOrderState_

- Finished : _WorkOrderState_

- Payed : _WorkOrderState_

- _ComponentFactory_
  - Attributes:
    - ComponentPrototypes: Map<String, Component>
  - Methods:
    - CreateComponent(): Component

- _PaymentFactory_
  - Attributes
    - PaymentPrototypes: Map<String, Component>
  - Methods:
    - CreatePayment(): Payment

- _ServiceFactory_
  - Attributes
    - ServicePrototypes: Map<String, Component>
  - Methods:
    - CreateService(): Payment
