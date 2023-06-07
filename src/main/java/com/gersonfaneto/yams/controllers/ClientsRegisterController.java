package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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

  @FXML
  public void initialize() {

  }

  @FXML
  public void cancelRegister() throws IOException {
    Parent clientsView = FXMLLoader.load(App.class.getResource("views/clients.fxml"));

    MainController.mainWindow.setRight(clientsView);
  }

  @FXML
  public void confirmRegister() {
    String clientName = nameField.getText();
    String homeAddress = addressField.getText();
    String phoneNumber = phoneField.getText();

    if (clientName.length() == 0 || homeAddress.length() == 0 || phoneNumber.length() == 0) {
      visualFeedback.setText("Favor, preencher todos os campos!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    DAO.fromClients().createOne(new Client(clientName, homeAddress, phoneNumber));

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
}
