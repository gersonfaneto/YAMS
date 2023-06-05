package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.receptionist.Receptionist;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class RegisterController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private FontAwesomeIconView backButton;

  @FXML
  private PasswordField confirmPasswordField;

  @FXML
  private TextField confirmPasswordText;

  @FXML
  private TextField emailField;

  @FXML
  private TextField nameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private TextField passwordText;

  @FXML
  private Button registerButton;

  @FXML
  private CheckBox showPassword;

  @FXML
  private Label visualFeedback;

  @FXML
  private ComboBox<String> roleSelector;

  @FXML
  public void initialize() {
    revealSecrets();

    roleSelector.getItems().add("Têcnico(a)");
    roleSelector.getItems().add("Recepcionista");
  }

  @FXML
  void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  @FXML
  void revealSecrets() {
    if (showPassword.isSelected()) {
      passwordText.setText(passwordField.getText());
      confirmPasswordText.setText(confirmPasswordField.getText());
      passwordText.setVisible(true);
      confirmPasswordText.setVisible(true);
      passwordField.setVisible(false);
      confirmPasswordField.setVisible(false);
      return;
    }
    passwordField.setText(passwordText.getText());
    confirmPasswordField.setText(confirmPasswordText.getText());
    passwordField.setVisible(true);
    confirmPasswordField.setVisible(true);
    passwordText.setVisible(false);
    confirmPasswordText.setVisible(false);
  }

  @FXML
  void validateEntries() {
    String nameValue = nameField.getText();
    String emailValue = emailField.getText();
    String passwordValue = passwordValue();
    String confirmPasswordValue = confirmPasswordValue();
    String roleValue = roleSelector.getValue();

    User foundUser = DAO.fromUsers().findByEmail(emailValue);

    if (nameValue.length() == 0 || emailValue.length() == 0 || passwordValue.length() == 0) {
      visualFeedback.setText("Insira o seus dados!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (foundUser != null) {
      visualFeedback.setText("Email já cadastrado!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (!passwordValue.equals(confirmPasswordValue)) {
      visualFeedback.setText("Senhas não conferem!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }
    if (roleValue == null) {
      visualFeedback.setText("Selecione o seu cargo!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (roleValue.equals("Têcnico(a)")) {
      DAO.fromUsers().createOne(new Technician(emailValue, passwordValue, nameValue));
    }
    else {
      DAO.fromUsers().createOne(new Receptionist(emailValue, passwordValue, nameValue));
    }

    nameField.clear();
    emailField.clear();
    passwordField.clear();
    passwordText.clear();
    confirmPasswordField.clear();
    confirmPasswordText.clear();

    visualFeedback.setText("Registrado com sucesso!");
    visualFeedback.setTextFill(Color.GREEN);
  }

  private String passwordValue() {
    return showPassword.isSelected() ? passwordText.getText() : passwordField.getText();
  }

  private String confirmPasswordValue() {
    return showPassword.isSelected() ? confirmPasswordText.getText() : confirmPasswordField.getText();
  }

  @FXML
  public void openLogin() throws IOException {
    Parent loginElements = FXMLLoader.load(App.class.getResource("views/login.fxml"));

    MainController.mainWindow.getChildren().setAll(loginElements);
  }
}