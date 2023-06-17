package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ClientsUpdateController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField addressField;

  @FXML
  private TextField nameField;

  @FXML
  private TextField phoneField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;

  private String clientID;

  private final String PHONE_REGEX = "^\\(?(\\d{2})\\)?[-.\\s]?(\\d{4,5})[-.\\s]?(\\d{4})$";
  private final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
  private final String MASK = "($1) $2-$3";

  @FXML
  public void confirmUpdate() {
    String clientName = nameField.getText();
    String homeAddress = addressField.getText();
    String phoneNumber = phoneField.getText();

    if (clientName.length() == 0 || homeAddress.length() == 0 || phoneNumber.length() == 0) {
      visualFeedback.setText("Insira o seus dados!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (!isValidPhoneNumber(phoneNumber)) {
      visualFeedback.setText("Número de telefone inválido!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    Client targetClient = DAO.fromClients().findByID(clientID);

    targetClient.setClientName(clientName);
    targetClient.setHomeAddress(homeAddress);
    targetClient.setPhoneNumber(applyMaskToPhoneNumber(phoneNumber));

    DAO.fromClients().updateInformation(targetClient);

    visualFeedback.setText("Cadastro atualizado com sucesso!");
    visualFeedback.setTextFill(Color.GREEN);
  }

  @FXML
  public void closeWindow() throws IOException {
    Parent clientsView = FXMLLoader.load(App.class.getResource("views/clients/Main.fxml"));

    MainController.mainWindow.setRight(clientsView);

    MainController.modalStage.close();
  }

  public void injectFields(
      String clientID,
      String clientName,
      String homeAddress,
      String phoneNumber
  ) {
    this.clientID = clientID;
    nameField.setText(clientName);
    addressField.setText(homeAddress);
    phoneField.setText(phoneNumber);
  }

  public boolean isValidPhoneNumber(String phoneNumber) {
    return PHONE_PATTERN.matcher(phoneNumber).matches();
  }

  public String applyMaskToPhoneNumber(String phoneNumber) {
    return phoneNumber.replaceAll(PHONE_REGEX, MASK);
  }
}
