package com.example.javafxjdbc;

import com.example.javafxjdbc.db.DbException;
import com.example.javafxjdbc.exception.ValidationException;
import com.example.javafxjdbc.listeners.DataChangeListener;
import com.example.javafxjdbc.model.entities.Department;
import com.example.javafxjdbc.model.entities.Seller;
import com.example.javafxjdbc.services.DepartmentService;
import com.example.javafxjdbc.services.SellerService;
import com.example.javafxjdbc.util.Alerts;
import com.example.javafxjdbc.util.Constraints;
import com.example.javafxjdbc.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class SellerFormController implements Initializable {
  private SellerService sellerService;
  private DepartmentService departmentService;
  private Seller entity;
  private List<DataChangeListener> dataChangeListenerList = new ArrayList<>();
  @FXML
  private TextField txtId;
  @FXML
  private TextField txtName;
  @FXML
  private TextField txtEmail;
  @FXML
  private DatePicker dpBirthdate;
  @FXML
  private TextField txtBaseSalary;
  @FXML
  ComboBox<Department> comboBoxDepartment;
  @FXML
  private Label labelErrorName;
  @FXML
  private Label labelErrorEmail;
  @FXML
  private Label labelErrorBirthdate;
  @FXML
  private Label labelErrorsalary;
  @FXML
  private Button btSave;
  @FXML
  private Button btCancel;
  private ObservableList<Department> obsList;

  @FXML
  public void onBtSaveAction(ActionEvent event) {
    if (entity == null) {
      throw new IllegalStateException("Entity was null");
    }
    if (sellerService == null) {
      throw new IllegalStateException("service was null");
    }
    try {
      entity = getFormData();
      sellerService.saveOrUpdate(entity);
      notifyDataChangeListenrs();
      Utils.currentStage(event).close();
    } catch (ValidationException e) {
      setErrorMessages(e.getErrors());
    } catch (DbException e) {
      Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
    }
  }

  private void notifyDataChangeListenrs() {
    for (DataChangeListener listener : dataChangeListenerList) {
      listener.onDataChanged();
    }
  }

  @FXML
  public void onBtSCancelAction(ActionEvent event) {
    Utils.currentStage(event).close();
  }

  private Seller getFormData() {
    Seller obj = new Seller();
    ValidationException exception = new ValidationException("Validation Error");
    obj.setId(Utils.TryParseToInt(txtId.getText()));

    if (txtName.getText() == null || txtName.getText().trim().equals("")) {
      exception.addError("name", "Field can't bem empty");
    }

    obj.setName(txtName.getText());
    if (txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
      exception.addError("name", "Field can't bem empty");
    }

    obj.setEmail(txtEmail.getText());
    if (dpBirthdate.getValue() == null) {
      exception.addError("email", "Field can't bem empty");
    }

    if (dpBirthdate.getValue() == null) {
      exception.addError("birthDate", "Field can't bem empty");
    } else {
      Instant instant = Instant.from(dpBirthdate.getValue().atStartOfDay(ZoneId.systemDefault()));
      obj.setBirthDate(Date.from(instant));
    }

    if (txtBaseSalary.getText() == null || txtBaseSalary.getText().trim().equals("")) {
      exception.addError("baseSalary", "Field can't bem empty");
    }
    obj.setBaseSalary(Utils.TryParseToDouble(txtBaseSalary.getText()));

    obj.setDepartment(comboBoxDepartment.getValue());

    if (exception.getErrors().size() > 0) {
      throw exception;
    }
    return obj;
  }

  public void setSeller(Seller entity) {
    this.entity = entity;
  }

  public void setServices(SellerService sellerService, DepartmentService departmentService) {
    this.sellerService = sellerService;
    this.departmentService = departmentService;
  }

  private void setErrorMessages(Map<String, String> errors) {
    Set<String> fields = errors.keySet();
    if (fields.contains("name")) {
      labelErrorName.setText(errors.get("name"));
    } else {
      labelErrorName.setText("");
    }
    if (fields.contains("email")) {
      labelErrorEmail.setText(errors.get("email"));
    } else {
      labelErrorEmail.setText("");
    }
    if (fields.contains("baseSalary")) {
      labelErrorsalary.setText(errors.get("baseSalary"));
    } else {
      labelErrorsalary.setText("");
    }
    if (fields.contains("birthDate")) {
      labelErrorBirthdate.setText(errors.get("birthDate"));
    } else {
      labelErrorBirthdate.setText("");
    }
  }

  private void initializeNodes() {
    Constraints.setTextFieldInteger(txtId);
    Constraints.setTextFieldMaxLength(txtName, 30);
    Constraints.setTextFieldDouble(txtBaseSalary);
    Constraints.setTextFieldMaxLength(txtEmail, 60);
    Utils.formatDatePicker(dpBirthdate, "dd/MM/yyyy");

    initializeComboBoxDepartment();
  }

  public void updateFormData() {
    if (entity == null) {
      throw new IllegalStateException("Entity was null");
    }
    txtId.setText(String.valueOf(entity.getId()));
    txtName.setText(entity.getName());
    txtEmail.setText(entity.getEmail());
    Locale.setDefault(Locale.US);
    txtBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));
    if (entity.getBirthDate() != null) {
      dpBirthdate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));
    }
    if (entity.getDepartment() == null) {
      comboBoxDepartment.getSelectionModel().selectFirst();
    } else {
      comboBoxDepartment.setValue(entity.getDepartment());
    }
    comboBoxDepartment.setValue(entity.getDepartment());
  }


  public void subscribeDataChangeListener(DataChangeListener dataChangeListener) {
    dataChangeListenerList.add(dataChangeListener);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeNodes();
  }

  public void loadAssociatedObjects() {
    if (departmentService == null) {
      throw new IllegalStateException("DepartmentService was null");
    }
    List<Department> list = departmentService.findAll();
    obsList = FXCollections.observableArrayList(list);
    comboBoxDepartment.setItems(obsList);
  }

  private void initializeComboBoxDepartment() {
    Callback<ListView<Department>, ListCell<Department>> factory = lv -> new ListCell<Department>() {
      @Override
      protected void updateItem(Department item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getName());
      }
    };
    comboBoxDepartment.setCellFactory(factory);
    comboBoxDepartment.setButtonCell(factory.call(null));
  }
}
