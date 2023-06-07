package com.gersonfaneto.yams.controllers;

import java.io.IOException;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.utils.TypeParser;

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

public class EmployeesUpdateController {

  @FXML
  private FontAwesomeIconView closeButton;

  @FXML
  private Label visualFeedback;

  @FXML
  private TextField nameField;

  @FXML
  private TextField emailField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private TextField passwordText;

  @FXML
  private PasswordField confirmPasswordField;

  @FXML
  private TextField confirmPasswordText;

  @FXML
  private ComboBox<String> roleSelector;

  @FXML
  private CheckBox showPassword;

  @FXML
  private Button confirmUpdate;

  @FXML
  private Button cancelButton;

  private String employeeID;

  @FXML
  public void initialize() {
    revealSecrets();

    for (UserType userType : UserType.values()) {
      if (userType != UserType.Administrator) {
        roleSelector.getItems().add(TypeParser.parseUserType(userType));
      }
    } 
  }

  @FXML
  public void confirmUpdate() {
    String userName = nameField.getText();
    String userEmail = emailField.getText();
    String userPassword = passwordValue();
    String userPasswordConfirm = confirmPasswordValue();
    String userType = roleSelector.getValue();

    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (userName.length() == 0 || userEmail.length() == 0 || userPassword.length() == 0) {
      visualFeedback.setText("Insira o seus dados!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (foundUser != null && foundUser.getUserID() != employeeID) {
      visualFeedback.setText("Usuário já cadastrado!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (!userPassword.equals(userPasswordConfirm)) {
      visualFeedback.setText("Senhas não conferem!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (userPassword.length() < 4) {
      visualFeedback.setText("Senha muito curta!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (userType == null) {
      visualFeedback.setText("Selecione o seu cargo!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    foundUser.setUserName(userName);
    foundUser.setUserEmail(userEmail);
    foundUser.setUserType(TypeParser.parseUserType(userType));
    foundUser.setUserPassword(userPassword);

    DAO.fromUsers().updateInformation(foundUser);

    nameField.clear();
    emailField.clear();
    passwordField.clear();
    passwordText.clear();
    confirmPasswordField.clear();
    confirmPasswordText.clear();

    visualFeedback.setText("Cadastro atualizado com sucesso!");
    visualFeedback.setTextFill(Color.GREEN);
  }

  @FXML
  public void cancelUpdate() throws IOException {
    Parent employeesView = FXMLLoader.load(App.class.getResource("views/employees.fxml"));

    MainController.mainWindow.setRight(employeesView);

    MainController.modalWindow.close();
  }

  @FXML
  public void revealSecrets() {
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

  public void injectFields(
      String userID,
      String userName,
      String userEmail,
      String userPassword,
      UserType userType
  ) {
    this.employeeID = userID;
    nameField.setText(userName);
    emailField.setText(userEmail);
    passwordField.setText(userPassword);
    confirmPasswordField.setText(userPassword);
    roleSelector.setValue(TypeParser.parseUserType(userType));
  }

  private String passwordValue() {
    return showPassword.isSelected() ? passwordText.getText() : passwordField.getText();
  }

  private String confirmPasswordValue() {
    return showPassword.isSelected() ? confirmPasswordText.getText() : confirmPasswordField.getText();
  }
}
