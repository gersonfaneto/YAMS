package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.receptionist.Receptionist;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.utils.TypeParser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.regex.Pattern;
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

  private final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
  private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

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
  void validateEntries() {
    String userName = nameField.getText();
    String userEmail = emailField.getText();
    String userPassword = passwordValue();
    String userConfirmPassword = confirmPasswordValue();
    String userType = roleSelector.getValue();

    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (userName.isBlank() || userEmail.isBlank() || userPassword.isBlank()) {
      visualFeedback.setText("Insira o seus dados!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (foundUser != null) {
      visualFeedback.setText("Usuário já cadastrado!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (!isValidEmail(userEmail)) {
      visualFeedback.setText("Email inváido!");
      visualFeedback.setTextFill(Color.RED);
      return;
    }

    if (!userPassword.equals(userConfirmPassword)) {
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

    if (userType.equals("Têcnico")) {
      DAO.fromUsers().createOne(new Technician(userEmail, userPassword, userName));
    } else {
      DAO.fromUsers().createOne(new Receptionist(userEmail, userPassword, userName));
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
  public void openLogin() throws IOException {
    Parent loginView = FXMLLoader.load(App.class.getResource("views/Login.fxml"));

    MainController.mainWindow.getChildren().setAll(loginView);
  }

  @FXML
  public void closeWindow() {
    MainController.saveData();
    System.exit(0);
  }

  private String passwordValue() {
    return showPassword.isSelected() ? passwordText.getText() : passwordField.getText();
  }

  private String confirmPasswordValue() {
    return showPassword.isSelected()
        ? confirmPasswordText.getText()
        : confirmPasswordField.getText();
  }

  public boolean isValidEmail(String userEmail) {
    return EMAIL_PATTERN.matcher(userEmail).matches();
  }
}
