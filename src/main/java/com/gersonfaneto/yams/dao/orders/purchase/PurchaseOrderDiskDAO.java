package com.gersonfaneto.yams.dao.orders.purchase;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.ObjectIO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import com.gersonfaneto.yams.models.stock.ComponentType;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>PurchaseOrderCRUD</code> and <code>CRUD</code> operations. Uses a
 * <code> HashMap</code> as a cache to store all the <code>PurchaseOrder</code>s during the
 * execution of the program and loads or unloads the contents of it into a file using an
 * <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see PurchaseOrderCRUD
 * @see ObjectIO
 */
public class PurchaseOrderDiskDAO implements PurchaseOrderCRUD, Persist {

  private final Map<String, PurchaseOrder> storedPurchaseOrders;
  private final ObjectIO<PurchaseOrder> purchaseOrderObjectIO;

  /**
   * Constructs a new <code>{@link PurchaseOrderDiskDAO}</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public PurchaseOrderDiskDAO(String savePath) {
    this.storedPurchaseOrders = new HashMap<>();
    this.purchaseOrderObjectIO = new ObjectIO<>(savePath);
  }

  public boolean saveAll() {
    List<PurchaseOrder> toSave = storedPurchaseOrders.values()
        .stream()
        .toList();

    return purchaseOrderObjectIO.saveObjects(toSave);
  }

  public boolean loadAll() {
    List<PurchaseOrder> loadedPurchaseOrders = purchaseOrderObjectIO.loadObjects();

    if (loadedPurchaseOrders == null) {
      return false;
    }

    for (PurchaseOrder currentPurchaseOrder : loadedPurchaseOrders) {
      storedPurchaseOrders.put(currentPurchaseOrder.getPurchaseOrderID(), currentPurchaseOrder);
    }

    return true;
  }


  @Override
  public PurchaseOrder createOne(PurchaseOrder newPurchaseOrder) {
    String newID = Generators.randomID();

    newPurchaseOrder.setPurchaseOrderID(newID);

    storedPurchaseOrders.put(newID, newPurchaseOrder);

    return newPurchaseOrder;
  }

  @Override
  public PurchaseOrder findByID(String targetID) {
    return storedPurchaseOrders.get(targetID);
  }

  @Override
  public List<PurchaseOrder> findMany() {
    return storedPurchaseOrders.values()
        .stream()
        .toList();
  }

  @Override
  public boolean updateInformation(PurchaseOrder updatedPurchaseOrder) {
    String purchaseOrderID = updatedPurchaseOrder.getPurchaseOrderID();

    if (storedPurchaseOrders.containsKey(purchaseOrderID)) {
      storedPurchaseOrders.put(purchaseOrderID, updatedPurchaseOrder);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedPurchaseOrders.containsKey(targetID)) {
      storedPurchaseOrders.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedPurchaseOrders.isEmpty()) {
      storedPurchaseOrders.clear();
      return true;
    }

    return false;
  }

  @Override
  public List<PurchaseOrder> findByType(ComponentType componentType) {
    return storedPurchaseOrders.values()
        .stream()
        .filter(x -> x.getComponentType().equals(componentType))
        .toList();
  }
}
