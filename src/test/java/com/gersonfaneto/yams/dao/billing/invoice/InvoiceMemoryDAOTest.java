package com.gersonfaneto.yams.dao.billing.invoice;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InvoiceMemoryDAOTest {

  private final String randomWorkOrderID = UUID.randomUUID().toString();
  private Invoice randomInvoice;

  @BeforeEach
  void setUp() {
    randomInvoice = DAO.fromInvoices().createOne(new Invoice(randomWorkOrderID, 25.50));

    for (int i = 0; i < 10; i++) {
      DAO.fromInvoices().createOne(new Invoice(UUID.randomUUID().toString(), 25.50));
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromInvoices().deleteMany();
  }

  @Test
  void createOne() {
    Invoice newInvoice =
        DAO.fromInvoices().createOne(new Invoice(UUID.randomUUID().toString(), 25.50));

    Invoice foundInvoice = DAO.fromInvoices().findByID(newInvoice.getInvoiceID());

    Assertions.assertEquals(newInvoice, foundInvoice);
  }

  @Test
  void findByID() {
    Invoice foundInvoice = DAO.fromInvoices().findByID(randomInvoice.getInvoiceID());

    Assertions.assertEquals(randomInvoice, foundInvoice);
  }

  @Test
  void findMany() {
    List<Invoice> foundInvoices = DAO.fromInvoices().findMany();

    Assertions.assertEquals(11, foundInvoices.size());
  }

  @Test
  void updateInformation() {
    randomInvoice.setWorkOrderID("FizzBuzz");

    boolean hasFound = DAO.fromInvoices().updateInformation(randomInvoice);
    Invoice foundInvoice = DAO.fromInvoices().findByID(randomInvoice.getInvoiceID());

    Assertions.assertTrue(hasFound);
    Assertions.assertEquals("FizzBuzz", foundInvoice.getWorkOrderID());
  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromInvoices().deleteByID(randomInvoice.getInvoiceID());

    Invoice removedInvoice = DAO.fromInvoices().findByID(randomInvoice.getInvoiceID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(removedInvoice);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromInvoices().deleteMany();
    List<Invoice> foundInvoices = DAO.fromInvoices().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundInvoices.size());
  }

  @Test
  void findByWorkOrder() {
    Invoice foundInvoice = DAO.fromInvoices().findByWorkOrder(randomWorkOrderID);

    Assertions.assertEquals(randomInvoice, foundInvoice);
  }
}
