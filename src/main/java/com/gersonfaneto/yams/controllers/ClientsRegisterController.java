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

public class ClientsRegisterController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private FontAwesomeIconView backButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField nameField;

  @FXML
  private TextField addressField;

  @FXML
  private TextField phoneField;

  @FXML
  private Button cancelButton;

  @FXML
  private Button confirmButton;

  private final String PHONE_REGEX = "^\\(?(\\d{2})\\)?[-.\\s]?(\\d{4,5})[-.\\s]?(\\d{4})$";
  private final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
  private final String MASK = "($1) $2-$3";

  @FXML
  public void initialize() {

  }

  @FXML
  public void cancelRegister() throws IOException {
    Parent clientsView = FXMLLoader.load(App.class.getResource("views/clients/Main.fxml"));

    MainController.mainWindow.setRight(clientsView);
  }

  @FXML
  public void confirmRegister() {
    String clientName = nameField.getText();
    String homeAddress = addressField.getText();
    String phoneNumber = phoneField.getText();

    if (clientName.isBlank() || homeAddress.isBlank() || phoneNumber.isBlank()) {
      visualFeedback.setText("Favor, preencher todos os campos!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (!isValidPhoneNumber(phoneNumber)) {
      visualFeedback.setText("Número de telefone inválido!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    DAO.fromClients().createOne(
        new Client(
            clientName,
            homeAddress,
            applyMaskToPhoneNumber(phoneNumber)
        )
    );

    visualFeedback.setText("Registrado com sucesso!");
    visualFeedback.setTextFill(Color.GREEN);

    nameField.clear();
    phoneField.clear();
    addressField.clear();
  }

  @FXML
  public void closeWindow() throws IOException {
    MainController.saveData();
    System.exit(0);
  }

  public boolean isValidPhoneNumber(String phoneNumber) {
    return PHONE_PATTERN.matcher(phoneNumber).matches();
  }

  public String applyMaskToPhoneNumber(String phoneNumber) {
    return phoneNumber.replaceAll(PHONE_REGEX, MASK);
  }
}
