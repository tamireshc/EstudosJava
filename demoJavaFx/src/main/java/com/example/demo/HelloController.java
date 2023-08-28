package com.example.demo;

import com.example.demo.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
  @FXML
  private Button btTest;

  @FXML
  public void onBtTestAction() {
    Alerts.showAlert("Alert Title", "Alert Header", "Hello", Alert.AlertType.ERROR);
  }
}