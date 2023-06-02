package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.admnistrator.Administrator;
import com.gersonfaneto.yams.models.entities.user.User;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class LoginController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private TextField emailField;

  @FXML
  private Button loginButton;

  @FXML
  private PasswordField passwordField;

  @FXML
  private TextField passwordText;

  @FXML
  private CheckBox showPassword;

  @FXML
  private Label visualFeedback;

  @FXML
  private AnchorPane loginArea;

  @FXML
  private BorderPane mainWindow;

  @FXML
  public void initialize() {
    MainController.loadData();

    if (DAO.fromUsers().findByEmail("admin") == null) {
      User sysAdm = Administrator.retrieveInstance("admin", "admin", "John Smith");
      DAO.fromUsers().createOne(sysAdm);
    }

    if (MainController.mainWindow == null) {
      MainController.mainWindow = mainWindow;
    }

    revealSecrets();
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  public void revealSecrets() {
    if (showPassword.isSelected()) {
      passwordText.setText(passwordField.getText());
      passwordText.setVisible(true);
      passwordField.setVisible(false);
      return;
    }
    passwordField.setText(passwordText.getText());
    passwordField.setVisible(true);
    passwordText.setVisible(false);
  }

  @FXML
  public void validateEntries() throws IOException {
    String passwordText = passwordValue();
    String emailText = emailField.getText();
    User foundUser = DAO.fromUsers().findByEmail(emailText);

    if (passwordText.length() == 0 || emailText.length() == 0) {
      visualFeedback.setText("Insira o seus dados!");
      visualFeedback.setTextFill(Color.RED);
      return;
    } else if (foundUser == null) {
      visualFeedback.setText("Usuário não encontrado!");
      visualFeedback.setTextFill(Color.RED);
      return;
    } else if (!foundUser.getUserPassword().equals(passwordText)) {
      visualFeedback.setText("Senha incorreta!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    MainController.loggedUser = foundUser;

    Parent menuElements = FXMLLoader.load(App.class.getResource("views/menu.fxml"));
    Parent homeElements = FXMLLoader.load(App.class.getResource("views/home.fxml"));
    
    mainWindow.setLeft(menuElements);
    mainWindow.setRight(homeElements);
  }

  private String passwordValue() {
    return showPassword.isSelected() ? passwordText.getText() : passwordField.getText();
  }


  @FXML
  public void openRegistration() throws IOException {
    Parent registerElements = FXMLLoader.load(App.class.getResource("views/register.fxml"));

    mainWindow.setRight(registerElements);
  }
}
