package com.gersonfaneto.yams.models.entities.technician;

import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.states.Free;
import com.gersonfaneto.yams.models.entities.technician.states.State;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;

/**
 * Represents the Technicians of the assistance as Users of the System, using the "State" pattern to
 * encapsulate some of its mutable behaviour.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see com.gersonfaneto.yams.models.entities.technician.states.State
 * @see User
 * @see UserType
 */
public class Technician extends User {

  private State technicianState;

  /**
   * Constructs a new <code>Technician</code>. The initial <code>State</code> of <code>Technician
   * </code> is <code>Free</code>.
   *
   * @param userEmail      The <code>User</code> chosen email.
   * @param userPassword   The <code>User</code> chosen password.
   * @param userType       The type of the <code>User</code>.
   * @param technicianName The name of the <code>Technician</code>.
   * @see Free
   * @see User
   * @see UserType
   */
  public Technician(
      String userEmail,
      String userPassword,
      String technicianName
  ) {
    super(technicianName, userEmail, userPassword, UserType.Technician);
    this.technicianState = new Free(this);
  }

  /**
   * Opens a <code>WorkOrder</code> depending on the current <code>State</code>.
   *
   * @param workOrder The <code>WorkOrder</code> to be opened.
   * @return <code>true</code> if the operation succeeded, or <code>false</code>, if it didn't.
   * @see WorkOrder
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   */
  public boolean openOrder(WorkOrder workOrder) {
    return technicianState.openOrder(workOrder);
  }

  /**
   * Cancels the current <code>WorkOrder</code> depending on the current <code>State</code>.
   *
   * @return <code>true</code> if the operations succeeded, or <code>false</code>, if it didn't.
   * @see WorkOrder
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   */
  public boolean cancelOrder() {
    return technicianState.cancelOrder();
  }

  /**
   * Closes the current <code>WorkOrder</code> depending on the current <code>State</code>.
   *
   * @return <code>true</code> if the operation succeeded, or <code>false</code>, if it didn't.
   * @see WorkOrder
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   */
  public boolean closeOrder() {
    return technicianState.closeOrder();
  }

  /**
   * Generates the <code>Invoice</code> of a <code>WorkOrder</code> depending on the current <code>
   * State</code>.
   *
   * @return <code>true</code> if the operation succeeded, or <code>false</code>, if it didn't.
   * @see WorkOrder
   * @see Invoice
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   */
  public Invoice generateInvoice() {
    return technicianState.generateInvoice();
  }

  public State getTechnicianState() {
    return technicianState;
  }

  public void setTechnicianState(State technicianState) {
    this.technicianState = technicianState;
  }
}
