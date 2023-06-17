package com.gersonfaneto.yams.dao.billing.paymet;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import com.gersonfaneto.yams.utils.Generators;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentDAOTest {

  private final String randomInvoiceID = Generators.randomID();
  private Payment randomPayment;

  @BeforeEach
  void setUp() {
    randomPayment = DAO.fromPayments().createOne(
        new Payment(
            randomInvoiceID,
            PaymentMethod.Cash,
            25.50
        )
    );

    for (int i = 0; i < 10; i++) {
      DAO.fromPayments().createOne(
          new Payment(
              Generators.randomID(),
              PaymentMethod.Cash,
              42.00
          )
      );
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromPayments().deleteMany();
  }

  // HACK: Find a better way of cleaning up these!
  @AfterAll
  static void cleanUp() {
    File dataFile = new File("data/payments.ser");

    dataFile.delete();
  }

  @Test
  void dataPersistence() {
    boolean hasSaved = ((Persist) DAO.fromPayments()).saveAll();

    Assertions.assertTrue(hasSaved, "dataPersistence(): Failed to save data!");

    List<Payment> beforeDeletion = DAO.fromPayments().findMany();

    DAO.fromPayments().deleteMany();

    boolean hasLoaded = ((Persist) DAO.fromPayments()).loadAll();

    Assertions.assertTrue(hasLoaded, "dataPersistence(): Failed to load data!");

    List<Payment> loadedPayments = DAO.fromPayments().findMany();

    Assertions.assertEquals(11, loadedPayments.size());
    Assertions.assertEquals(beforeDeletion, loadedPayments);
  }

  @Test
  void createOne() {
    Payment newPayment = DAO.fromPayments().createOne(
        new Payment(
            randomInvoiceID,
            PaymentMethod.CreditCard,
            10.50
        )
    );

    Payment foundPayment = DAO.fromPayments().findByID(newPayment.getPaymentID());

    Assertions.assertEquals(newPayment, foundPayment);
  }

  @Test
  void findByID() {
    Payment foundPayment = DAO.fromPayments().findByID(randomPayment.getPaymentID());

    Assertions.assertEquals(randomPayment, foundPayment);
  }

  @Test
  void findMany() {
    List<Payment> foundPayments = DAO.fromPayments().findMany();

    Assertions.assertEquals(11, foundPayments.size());
  }

  @Test
  void updateInformation() {
    randomPayment.setPaidValue(21.12);

    boolean hasFound = DAO.fromPayments().updateInformation(randomPayment);
    Payment foundPayment = DAO.fromPayments().findByID(randomPayment.getPaymentID());

    Assertions.assertTrue(hasFound);
    Assertions.assertEquals(21.12, foundPayment.getPaidValue());
  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromPayments().deleteByID(randomPayment.getPaymentID());
    Payment removedPayment = DAO.fromPayments().findByID(randomPayment.getInvoiceID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(removedPayment);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromPayments().deleteMany();
    List<Payment> foundPayments = DAO.fromPayments().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundPayments.size());
  }

  @Test
  void findByInvoice() {
    List<Payment> foundPayments = DAO.fromPayments().findByInvoice(randomInvoiceID);

    Assertions.assertEquals(1, foundPayments.size());

    Payment foundPayment = foundPayments.get(0);

    Assertions.assertEquals(randomPayment, foundPayment);
  }
}
