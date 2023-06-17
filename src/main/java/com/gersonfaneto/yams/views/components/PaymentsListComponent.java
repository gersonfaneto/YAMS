package com.gersonfaneto.yams.views.components;

import com.gersonfaneto.yams.App;
import com.gersonfaneto.yams.models.billing.payment.Payment;
import com.gersonfaneto.yams.utils.TypeParser;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PaymentsListComponent extends AnchorPane {
  public PaymentsListComponent(Payment targetPayment) {
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
    paymentMethodField.setText(
        TypeParser.parsePaymentMethod(targetPayment.getPaymentMethod())
    );

    paidValueField.setLayoutX(160);
    paidValueField.setLayoutY(55);
    paidValueField.setPrefSize(100, 20);
    paidValueField.setText(
        formatMoney(targetPayment.getPaidValue())
    );

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

    String typeIconPath = "assets/payments/%s.png".formatted(
        targetPayment.getPaymentMethod().getTypeName().replace(" ", "")
    );

    Image typeImage = new Image(App.class.getResourceAsStream(typeIconPath));

    typeIcon.setImage(typeImage);

    super.getChildren().add(typeIcon);
  } 

  private String formatMoney(double moneyInput) {
    return String.format("%.2f", moneyInput).replace(".", ",");
  }
}
