package com.gersonfaneto.yams.models.entities;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;

public class Receptionist extends User {

  private String receptionistName;


  public Receptionist(String userEmail, String userPassword, String receptionistName) {
    super(userEmail, userPassword);
    this.receptionistName = receptionistName;
  }

  public Client registerClient(String clientName, String homeAddress, String phoneNumber) {
    return DAO.fromClients().createOne(new Client(clientName, homeAddress, phoneNumber));
  }

  public String getReceptionistName() {
    return receptionistName;
  }

  public void setReceptionistName(String receptionistName) {
    this.receptionistName = receptionistName;
  }
}
