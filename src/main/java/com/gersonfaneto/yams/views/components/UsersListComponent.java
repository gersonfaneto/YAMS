package com.gersonfaneto.yams.views.components;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.controllers.EmployeesUpdateController;
import com.gersonfaneto.yams.controllers.MainController;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.utils.TypeParser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UsersListComponent extends AnchorPane {

  private User targetUser;
  private ObservableList<User> usersList;

  public UsersListComponent(User targetUser, ObservableList<User> usersList) {
    this.targetUser = targetUser;
    this.usersList = usersList;

    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStylesheets().clear();
    super.getStyleClass().add("client-item");

    super.setMinSize(650, 100);
    super.setPrefSize(650, 100);
    super.setMaxSize(650, 100);

    FontAwesomeIconView userIcon = new FontAwesomeIconView(FontAwesomeIcon.USER);
    FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
    FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

    userIcon.setLayoutX(27);
    userIcon.setLayoutY(67);
    userIcon.setSize("50");

    Button editButton = new Button();

    editButton.setLayoutX(610);
    editButton.setLayoutY(10);
    editButton.getStyleClass().add("transparent-button");

    editIcon.setSize("20");
    editIcon.getStyleClass().add("edit-icon");

    editButton.setGraphic(editIcon);

    Button deleteButton = new Button();

    deleteButton.setLayoutX(610);
    deleteButton.setLayoutY(50);
    deleteButton.getStyleClass().add("transparent-button");

    deleteIcon.setSize("20");
    deleteIcon.getStyleClass().add("delete-icon");

    deleteButton.setGraphic(deleteIcon);

    deleteButton.setOnMouseClicked((MouseEvent event) -> {
      deleteUser();
    });

    editButton.setOnMouseClicked((MouseEvent event) -> {
      updateUser();
    });

    Label nameField = new Label(targetUser.getUserName());
    Label emailField = new Label(targetUser.getUserEmail());
    Label typeField = new Label(TypeParser.parseUserType(targetUser.getUserType()));

    nameField.setLayoutX(100);
    nameField.setLayoutY(13);
    nameField.setMinSize(450, 20);
    nameField.setPrefSize(450, 20);
    nameField.setMaxSize(450, 20);

    emailField.setLayoutX(100);
    emailField.setLayoutY(38);
    emailField.setMinSize(450, 20);
    emailField.setPrefSize(450, 20);
    emailField.setMaxSize(450, 20);

    typeField.setLayoutX(100);
    typeField.setLayoutY(64);
    typeField.setMinSize(450, 20);
    typeField.setPrefSize(450, 20);
    typeField.setMaxSize(450, 20);

    super.getChildren().addAll(userIcon, editButton, deleteButton);
    super.getChildren().addAll(nameField, emailField, typeField);
  }

  private void deleteUser() {
    String confirmationMessage = "Deseja excluir o cadastro de %s?".formatted(
        targetUser.getUserName()
    );

    ActionConfirmationDialog confirmDialog = new ActionConfirmationDialog(confirmationMessage);

    Stage modalStage = new Stage();

    MainController.modalStage = modalStage;

    modalStage.setScene(new Scene(confirmDialog));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);
    modalStage.showAndWait();

    if (MainController.isConfirmed) {
      DAO.fromUsers().deleteByID(targetUser.getUserID());
      usersList.remove(targetUser);
    }
  }

  private void updateUser() {
    FXMLLoader loaderFXML = new FXMLLoader();
    loaderFXML.setLocation(App.class.getResource("views/employees_update.fxml"));

    try {
      loaderFXML.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    EmployeesUpdateController updateController = loaderFXML.getController();

    updateController.injectFields(
        targetUser.getUserID(),
        targetUser.getUserName(),
        targetUser.getUserEmail(),
        targetUser.getUserPassword(),
        targetUser.getUserType()
    );

    Parent updateView = loaderFXML.getRoot();
    Stage modalStage = new Stage();

    modalStage.setScene(new Scene(updateView));
    modalStage.initStyle(StageStyle.UNDECORATED);
    modalStage.initModality(Modality.APPLICATION_MODAL);
    modalStage.initOwner(MainController.primaryStage);
    modalStage.show();

    MainController.modalStage = modalStage;
  }
}
