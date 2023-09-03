package com.example.javafxjdbc;

import com.example.javafxjdbc.db.DbIntegrityException;
import com.example.javafxjdbc.listeners.DataChangeListener;
import com.example.javafxjdbc.model.entities.Seller;
import com.example.javafxjdbc.services.DepartmentService;
import com.example.javafxjdbc.services.SellerService;
import com.example.javafxjdbc.util.Alerts;
import com.example.javafxjdbc.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SellerListController implements Initializable, DataChangeListener {

  @FXML
  private TableView<Seller> tableViewSeller;
  @FXML
  private TableColumn<Seller, Integer> tableColummId;
  @FXML
  private TableColumn<Seller, String> tableCollumnName;
  @FXML
  private TableColumn<Seller, String> tableCollumnEmail;
  @FXML
  private TableColumn<Seller, Date> tableCollumnBirthDate;
  @FXML
  private TableColumn<Seller, Double> tableCollumnBaseSalary;
  @FXML
  private TableColumn<Seller, Seller> tableColumnEDIT;
  @FXML
  private TableColumn<Seller, Seller>tableColumnREMOVE;

  @FXML
  private Button btNew;
  private ObservableList<Seller> obsList;
  private SellerService sellerService;

  @FXML
  public void onBtNewAction(ActionEvent event) {
    Stage parentStage = Utils.currentStage(event);
    Seller obj = new Seller();
    createDialogForm(obj, "SellerForm.fxml", parentStage);
  }

  public void setSellerService(SellerService service) {
    this.sellerService = service;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeNodes();
  }

  private void initializeNodes() {
    tableColummId.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableCollumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableCollumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    tableCollumnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    Utils.formatTableColumnDate(tableCollumnBirthDate,"dd/MM/yyyy");
    Utils.formatTableColumnDouble(tableCollumnBaseSalary, 2);
    tableCollumnBaseSalary.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));

    Stage stage = (Stage) MainApplication.mainScene().getWindow();
    tableViewSeller.prefHeightProperty().bind(stage.heightProperty());
  }

  public void updateTableView() {
    if (sellerService == null) {
      throw new IllegalStateException("Service was null");
    }
    List<Seller> list = sellerService.findAll();
    obsList = FXCollections.observableArrayList(list);
    tableViewSeller.setItems(obsList);
    initEditButtons();
    initRemoveButtons();
  }

  public void createDialogForm(Seller obj, String absoluteName, Stage parentStage) {
    try {
      FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(absoluteName));
      Pane pane = loader.load();

      SellerFormController controller = loader.getController();
      controller.setSeller(obj);
      controller.setServices(new SellerService(), new DepartmentService());
      controller.loadAssociatedObjects();
      controller.subscribeDataChangeListener(this);
      controller.updateFormData();

      Stage dialogStage = new Stage();
      dialogStage.setTitle("Enter Seller data");
      dialogStage.setScene(new Scene(pane));
      dialogStage.setResizable(false);
      dialogStage.initOwner(parentStage);
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.showAndWait();

    } catch (IOException e) {
      e.printStackTrace();
      Alerts.showAlert("IO Exeption", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
    }
  }

  private void initEditButtons() {
    tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    tableColumnEDIT.setCellFactory(param -> new TableCell<Seller, Seller>() {
      private final Button button = new Button("edit");

      @Override
      protected void updateItem(Seller obj, boolean empty) {
        super.updateItem(obj, empty);
        if (obj == null) {
          setGraphic(null);
          return;
        }
        setGraphic(button);
        button.setOnAction(
          event -> createDialogForm(
            obj, "SellerForm.fxml", Utils.currentStage(event)));
      }
    });
  }

  private void initRemoveButtons() {
    tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    tableColumnREMOVE.setCellFactory(param -> new TableCell<Seller, Seller>() {
      private final Button button = new Button("remove");

      @Override
      protected void updateItem(Seller obj, boolean empty) {
        super.updateItem(obj, empty);
        if (obj == null) {
          setGraphic(null);
          return;
        }
        setGraphic(button);
        button.setOnAction(event -> removeEntity(obj));
      }
    });
  }

  private void removeEntity(Seller obj) {
    Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you shure to delete?");
    if (result.get() == ButtonType.OK) {
      if (sellerService == null) {
        throw new IllegalStateException("Service was null");
      }
      try {
        sellerService.remove(obj);
        updateTableView();
      } catch (DbIntegrityException e) {
        Alerts.showAlert("Error removing object", null, e.getMessage(), Alert.AlertType.ERROR);
      }
    }
  }

  @Override
  public void onDataChanged() {
    updateTableView();
  }
}
