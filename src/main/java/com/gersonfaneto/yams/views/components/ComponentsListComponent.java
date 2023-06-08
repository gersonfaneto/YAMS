package com.gersonfaneto.yams.views.components;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.utils.TypeParser;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ComponentsListComponent extends AnchorPane {
  private Component targetComponent;
  private ObservableList<Component> componentsList;

  public ComponentsListComponent(
    Component targetComponent,
    ObservableList<Component> componentsList
  ) {
    this.targetComponent = targetComponent;
    this.componentsList = componentsList;

    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStylesheets().clear();
    super.getStyleClass().add("component-item");

    super.setMinSize(650, 100);
    super.setPrefSize(650, 100);
    super.setMaxSize(650, 100);

    FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.WRENCH);

    Button updateButton = new Button();

    updateButton.setLayoutX(620);
    updateButton.setLayoutY(10);
    updateButton.getStyleClass().add("transparent-button");

    updateIcon.setSize("20");
    updateIcon.getStyleClass().add("update-icon");

    updateButton.setGraphic(updateIcon);

    updateButton.setOnMouseClicked((MouseEvent event) -> {
      updateComponent();
    });

    Label priceFieldIndicator = new Label("Preço R$");
    Label costFieldIndicator = new Label("Custo R$");
    Label amountFieldIndicator = new Label("Quantidade");
    Label typeFieldIndicator = new Label("Categoria");

    priceFieldIndicator.setLayoutX(90);
    priceFieldIndicator.setLayoutY(40);
    priceFieldIndicator.setPrefSize(60, 20);

    costFieldIndicator.setLayoutX(90);
    costFieldIndicator.setLayoutY(65);
    costFieldIndicator.setPrefSize(60, 20);

    amountFieldIndicator.setLayoutX(225);
    amountFieldIndicator.setLayoutY(40);
    amountFieldIndicator.setPrefSize(80, 20);

    typeFieldIndicator.setLayoutX(225);
    typeFieldIndicator.setLayoutY(65);
    typeFieldIndicator.setPrefSize(80, 20);

    Label descriptionField = new Label();
    Label priceField = new Label();
    Label costField = new Label();
    Label amountField = new Label();
    Label typeField = new Label();

    descriptionField.setLayoutX(90);
    descriptionField.setLayoutY(15);
    descriptionField.setPrefSize(450, 20);
    descriptionField.setText(targetComponent.getComponentDescription());

    priceField.setLayoutX(160);
    priceField.setLayoutY(40);
    priceField.setPrefSize(50, 20);
    priceField.setText(formatMoney(targetComponent.getComponentPrice()));

    costField.setLayoutX(160);
    costField.setLayoutY(65);
    costField.setPrefSize(50, 20);
    costField.setText(formatMoney(targetComponent.getComponentCost()));

    amountField.setLayoutX(325);
    amountField.setLayoutY(40);
    amountField.setPrefSize(80, 20);
    amountField.setText(Integer.toString(targetComponent.getAmountInStock()));

    typeField.setLayoutX(325);
    typeField.setLayoutY(65);
    typeField.setPrefSize(150, 20);
    typeField.setText(TypeParser.parseComponentType(targetComponent.getComponentType()));

    ImageView typeIcon = new ImageView();

    typeIcon.setFitWidth(50);
    typeIcon.setFitHeight(50);
    typeIcon.setLayoutX(15);
    typeIcon.setLayoutY(25);
  
    String typeIconPath = "assets/%s.png".formatted(targetComponent.getComponentType().getTypeName().replace(" ", ""));

    Image typeImage = new Image(App.class.getResourceAsStream(typeIconPath));

    typeIcon.setImage(typeImage);

    super.getChildren().add(typeIcon);
    super.getChildren().addAll(updateButton);
    super.getChildren().addAll(descriptionField, priceField, costField, amountField, typeField);
    super.getChildren().addAll(priceFieldIndicator, costFieldIndicator, amountFieldIndicator, typeFieldIndicator);
  }

  public void updateComponent() {

  }

  public String formatMoney(double priceInput) {
    return String.format("%.2f", priceInput);
  } 
}
