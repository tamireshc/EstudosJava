package com.example.javafxjdbc;

import com.example.javafxjdbc.db.DbIntegrityException;
import com.example.javafxjdbc.listeners.DataChangeListener;
import com.example.javafxjdbc.model.entities.Department;
import com.example.javafxjdbc.services.DepartmentService;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DepartmentListController implements Initializable, DataChangeListener {

  @FXML
  private TableView<Department> tableViewDepartment;
  @FXML
  private TableColumn<Department, Integer> tableColummId;
  @FXML
  private TableColumn<Department, String> tableCollumnName;
  @FXML
  private TableColumn<Department, Department> tableColumnEDIT;
  @FXML
  TableColumn<Department, Department> tableColumnREMOVE;
  @FXML
  private Button btNew;
  private ObservableList<Department> obsList;
  private DepartmentService departmentService;

  @FXML
  public void onBtNewAction(ActionEvent event) {
    Stage parentStage = Utils.currentStage(event);
    Department obj = new Department();
    createDialogForm(obj, "DepartmentForm.fxml", parentStage);
  }

  public void setDepartmentService(DepartmentService service) {
    this.departmentService = service;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeNodes();
  }

  private void initializeNodes() {
    tableColummId.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableCollumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    Stage stage = (Stage) MainApplication.mainScene().getWindow();
    tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
  }

  public void updateTableView() {
    if (departmentService == null) {
      throw new IllegalStateException("Service was null");
    }
    List<Department> list = departmentService.findAll();
    obsList = FXCollections.observableArrayList(list);
    tableViewDepartment.setItems(obsList);
    initEditButtons();
    initRemoveButtons();
  }

  public void createDialogForm(Department obj, String absoluteName, Stage parentStage) {
    try {
      FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(absoluteName));
      Pane pane = loader.load();

      DepartmentFormController controller = loader.getController();
      controller.setDepartment(obj);
      controller.setDepartmentService(new DepartmentService());
      controller.subscribeDataChangeListener(this);
      controller.updateFormData();

      Stage dialogStage = new Stage();
      dialogStage.setTitle("Enter department data");
      dialogStage.setScene(new Scene(pane));
      dialogStage.setResizable(false);
      dialogStage.initOwner(parentStage);
      dialogStage.initModality(Modality.WINDOW_MODAL);
      dialogStage.showAndWait();

    } catch (IOException e) {
      Alerts.showAlert("IO Exeption", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
    }
  }

  private void initEditButtons() {
    tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    tableColumnEDIT.setCellFactory(param -> new TableCell<Department, Department>() {
      private final Button button = new Button("edit");

      @Override
      protected void updateItem(Department obj, boolean empty) {
        super.updateItem(obj, empty);
        if (obj == null) {
          setGraphic(null);
          return;
        }
        setGraphic(button);
        button.setOnAction(
          event -> createDialogForm(
            obj, "DepartmentForm.fxml", Utils.currentStage(event)));
      }
    });
  }

  private void initRemoveButtons() {
    tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    tableColumnREMOVE.setCellFactory(param -> new TableCell<Department, Department>() {
      private final Button button = new Button("remove");

      @Override
      protected void updateItem(Department obj, boolean empty) {
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

  private void removeEntity(Department obj) {
    Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you shure to delete?");
    if (result.get() == ButtonType.OK) {
      if (departmentService == null) {
        throw new IllegalStateException("Service was null");
      }
      try {
        departmentService.remove(obj);
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
