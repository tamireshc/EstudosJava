package com.example.demo.controllers;

import com.example.demo.util.Alerts;
import com.example.demo.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SomaController implements Initializable {
  @FXML
  public TextField txtNumber1;
  @FXML
  public TextField txtNumber2;
  @FXML
  public Label labelResult;
  @FXML
  public Button btSum;

  @FXML
  public void onBtTSumAction() {
    Locale.setDefault(Locale.US);
    try {
      double number1 = Double.parseDouble(txtNumber1.getText());
      double number2 = Double.parseDouble(txtNumber2.getText());
      double sum = number1 + number2;
      labelResult.setText(String.format("%.2f", sum));
    } catch (NumberFormatException e) {
      Alerts.showAlert(null, null, "Invalid Format", Alert.AlertType.ERROR);
    }

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Constraints.setTextFieldDouble(txtNumber1);
    Constraints.setTextFieldDouble(txtNumber2);
    Constraints.setTextFieldMaxLength(txtNumber1, 12);
    Constraints.setTextFieldMaxLength(txtNumber2, 12);
  }
}