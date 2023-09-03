package com.example.javafxjdbc;

import com.example.javafxjdbc.services.DepartmentService;
import com.example.javafxjdbc.services.SellerService;
import com.example.javafxjdbc.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import kotlin.jvm.Synchronized;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {
  @FXML
  private MenuItem menuItemSeller;
  @FXML
  private MenuItem menuItemDepartement;
  @FXML
  private MenuItem menuItemAbout;

  @FXML
  public void onMenuItemSellerAction() {
    loadView("SellerList.fxml",
      (SellerListController controller) -> {
        controller.setSellerService(new SellerService());
        controller.updateTableView();
      });
  }

  @FXML
  public void onMenuItemSDepartmentAction() {
    loadView("DepartmentList.fxml",
      (DepartmentListController controller) -> {
        controller.setDepartmentService(new DepartmentService());
        controller.updateTableView();
      });
  }

  @FXML
  public void onMenuItemAboutAction() {
    loadView("About.fxml", x -> {
    });
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  private <T> void loadView(String absoluteName, Consumer<T> initializing) {
    try {
      FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(absoluteName));
      VBox newVBox = loader.load();
      Scene mainScene = MainApplication.mainScene();
      VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
      Node mainMenu = mainVbox.getChildren().get(0);
      mainVbox.getChildren().clear();
      mainVbox.getChildren().add(mainMenu);
      mainVbox.getChildren().addAll(newVBox.getChildren());

      T controller = loader.getController();
      initializing.accept(controller);
    } catch (IOException e) {
      Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.WARNING);
    }
  }

}