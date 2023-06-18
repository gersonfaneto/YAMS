package com.gersonfaneto.yams.views.components;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.controllers.MainController;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import com.gersonfaneto.yams.utils.TypeParser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ServicesListComponent extends AnchorPane {
  public ServicesListComponent(
      Service targetService,
      ObservableList<Service> servicesList,
      ComponentSize componentSize,
      boolean enableToggles
  ) {
    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStylesheets().clear();
    super.getStyleClass().add("component-item");

    switch (componentSize) {
      case Small:
        super.setPrefSize(650, 100);
        break;
      case Medium:
        super.setPrefSize(650, 125);
        break;
      case Large:
        break;
      default:
        break;
    }

    Label priceFieldIndicator = new Label("Valor");
    Label typeFieldIndicator = new Label("Categoria");
    Label statusFieldIndicator = new Label("Status");
    Label ratingFieldIndicator = new Label("Avaliação");

    priceFieldIndicator.setLayoutX(80);
    priceFieldIndicator.setLayoutY(40);
    priceFieldIndicator.setPrefSize(40, 20);

    typeFieldIndicator.setLayoutX(80);
    typeFieldIndicator.setLayoutY(65);
    typeFieldIndicator.setPrefSize(70, 20);

    statusFieldIndicator.setLayoutX(250);
    statusFieldIndicator.setLayoutY(40);
    statusFieldIndicator.setPrefSize(40, 20);

    ratingFieldIndicator.setLayoutX(250);
    ratingFieldIndicator.setLayoutY(65);
    ratingFieldIndicator.setPrefSize(60, 20);

    Label descriptionField = new Label();
    Label priceField = new Label();
    Label typeField = new Label();
    Label statusField = new Label();
    Label ratingField = new Label();

    descriptionField.setLayoutX(80);
    descriptionField.setLayoutY(15);
    descriptionField.setPrefSize(450, 20);
    descriptionField.setText(targetService.getServiceDescription());

    priceField.setLayoutX(160);
    priceField.setLayoutY(40);
    priceField.setPrefSize(80, 20);
    priceField.setText(formatMoney(targetService.getServicePrice()));

    typeField.setLayoutX(160);
    typeField.setLayoutY(65);
    typeField.setPrefSize(80, 20);
    typeField.setText(TypeParser.parseServiceType(targetService.getServiceType()));

    statusField.setLayoutX(350);
    statusField.setLayoutY(40);
    statusField.setPrefSize(80, 20);
    statusField.setText((targetService.isComplete()) ? "Concluído" : "Pendente");

    ratingField.setLayoutX(350);
    ratingField.setLayoutY(65);
    ratingField.setPrefSize(80, 20);
    ratingField.setText(
        (targetService.getClientRating() == null)
          ? "Pendente"
          : targetService.getClientRating()
    );

    if (targetService.getServiceType() == ServiceType.Assembly) {
      Label componentFieldIndicator = new Label("Peça");
      Label componentField = new Label();

      componentFieldIndicator.setLayoutX(80);
      componentFieldIndicator.setLayoutY(90);
      componentFieldIndicator.setPrefSize(35, 20);

      componentField.setLayoutX(160);
      componentField.setLayoutY(90);
      componentField.setPrefSize(210, 20);
      componentField.setText(
          targetService
          .getUsedComponent()
          .getComponentDescription()
      );

      Label componentAmountIndicator = new Label("Qnt.");
      Label componentAmount = new Label();

      componentAmountIndicator.setLayoutX(380);
      componentAmountIndicator.setLayoutY(90);
      componentAmountIndicator.setPrefSize(35, 20);

      componentAmount.setLayoutX(430);
      componentAmount.setLayoutY(90);
      componentAmount.setPrefSize(35, 20);
      componentAmount.setText(
          Integer.toString(targetService.getAmountUsed())
      );

      super.getChildren().addAll(
          componentFieldIndicator,
          componentField,
          componentAmountIndicator,
          componentAmount
      );
    }

    if (enableToggles) {
      FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

      switch (componentSize) {
        case Small:
          updateIcon.setLayoutX(620);
          updateIcon.setLayoutY(35);
          break;
        case Medium:
          updateIcon.setLayoutX(620);
          updateIcon.setLayoutY(35);
          break;
        case Large:
          break;
        default:
          break;
      }

      updateIcon.setSize("20");
      updateIcon.getStyleClass().add("delete-icon");

      updateIcon.setOnMouseClicked(event -> {
        if (targetService.isComplete()) {
          return;
        }

        String confirmationMessage = "Deseja mesmo remover o serviço?";

        MainController.openModal(confirmationMessage, true);

        if (MainController.isConfirmed) {
          DAO.fromService().deleteByID(targetService.getServiceID());

          servicesList.remove(targetService);

          MainController.modalStage.close();
        }
      });

      ratingField.setVisible(false);

      ComboBox<String> ratingSelector = new ComboBox<>();

      ratingSelector.setLayoutX(350);
      ratingSelector.setLayoutY(65);
      ratingSelector.setPrefSize(120, 25);
      ratingSelector.setValue(
        (targetService.getClientRating() == null) 
          ? "Selecione"
          : targetService.getClientRating()
      );

      ratingSelector.getStyleClass().add("combo-box");

      ratingSelector.getItems().addAll(
        "Ótimo",
        "Bom",
        "Suficiente",
        "Ruim",
        "Péssimo"
      );

      ratingSelector.setOnAction(event -> {
        targetService.setClientRating(ratingSelector.getValue());

        DAO.fromService().updateInformation(targetService);

        servicesList.set(servicesList.indexOf(targetService), targetService);
      });

      CheckBox toggleStatus = new CheckBox();

      switch (componentSize) {
        case Small:
          toggleStatus.setLayoutX(620);
          toggleStatus.setLayoutY(65);
          break;
        case Medium:
          toggleStatus.setLayoutX(620);
          toggleStatus.setLayoutY(80);
          break;
        case Large:
          break;
        default:
          break;
      }

      if (targetService.isComplete()) {
        toggleStatus.setSelected(true);
      }
      else {
        toggleStatus.setSelected(false);
      }

      toggleStatus.setOnMouseClicked(event -> {
        targetService.setComplete(!targetService.isComplete());

        DAO.fromService().updateInformation(targetService);

        servicesList.set(servicesList.indexOf(targetService), targetService);
      });

      ratingSelector.visibleProperty().bind(toggleStatus.selectedProperty());

      super.getChildren().addAll(toggleStatus, updateIcon, ratingSelector);
    }

    ImageView typeIcon = new ImageView();

    switch (componentSize) {
      case Small:
        typeIcon.setLayoutX(15);
        typeIcon.setLayoutY(25);
        break;
      case Medium:
        typeIcon.setLayoutX(15);
        typeIcon.setLayoutY(40);
        break;
      case Large:
        break;
      default:
        break;
    }

    typeIcon.setFitWidth(50);
    typeIcon.setFitHeight(50);

    String typeIconPath = "assets/services/%s.png".formatted(
        targetService.getServiceType().getTypeName().replace(" ", "")
    );

    Image typeImage = new Image(App.class.getResourceAsStream(typeIconPath));

    typeIcon.setImage(typeImage);

    super.getChildren().addAll(
        typeIcon
    );

    super.getChildren().addAll(
        priceFieldIndicator,
        typeFieldIndicator,
        statusFieldIndicator,
        ratingFieldIndicator
    );

    super.getChildren().addAll(
        descriptionField,
        priceField,
        typeField,
        statusField,
        ratingField
    );
  }

  private String formatMoney(double moneyInput) {
    return String.format("R$ %.2f", moneyInput).replace(".", ",");
  }
}
