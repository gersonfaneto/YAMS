package com.gersonfaneto.yams.views.components;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.billing.payments.Payment;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PaymentsListComponent extends AnchorPane {
  private Payment targetPayment;

  public PaymentsListComponent(Payment targetPayment) {
    this.targetPayment = targetPayment;

    super.getStylesheets().add(App.class.getResource("stylesheets/global.css").toExternalForm());
    super.getStylesheets().clear();
    super.getStyleClass().add("component-item");
    super.setPrefSize(650, 100);

    Label paymentMethodFieldIndicator = new Label("Método");
    Label paidValueFieldIndicator = new Label("Valor");

    paymentMethodFieldIndicator.setLayoutX(90);
    paymentMethodFieldIndicator.setLayoutY(25);
    paymentMethodFieldIndicator.setPrefSize(50, 20);

    paidValueFieldIndicator.setLayoutX(90);
    paidValueFieldIndicator.setLayoutY(55);
    paidValueFieldIndicator.setPrefSize(40, 20);

    Label paymentMethodField = new Label();
    Label paidValueField = new Label();

    paymentMethodField.setLayoutX(160);
    paymentMethodField.setLayoutY(25);
    paymentMethodField.setPrefSize(150, 20);

    paidValueField.setLayoutX(160);
    paidValueField.setLayoutY(55);
    paidValueField.setPrefSize(100, 20);

    super.getChildren().addAll(
        paymentMethodFieldIndicator,
        paidValueFieldIndicator
    );
    super.getChildren().addAll(
        paymentMethodField,
        paidValueField
    );

    ImageView typeIcon = new ImageView();

    typeIcon.setFitWidth(50);
    typeIcon.setFitHeight(50);
    typeIcon.setLayoutX(20);
    typeIcon.setLayoutY(25);

    String typeIconPath = "assets/%s.png".formatted(
        targetPayment.getPaymentMethod().getTypeName().replace(" ", "")
    );

    Image typeImage = new Image(App.class.getResourceAsStream(typeIconPath));

    typeIcon.setImage(typeImage);

    super.getChildren().add(typeIcon);
  } 
}