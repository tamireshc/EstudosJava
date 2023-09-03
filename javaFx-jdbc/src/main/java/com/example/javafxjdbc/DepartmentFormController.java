package com.example.javafxjdbc;

import com.example.javafxjdbc.db.DbException;
import com.example.javafxjdbc.exception.ValidationException;
import com.example.javafxjdbc.listeners.DataChangeListener;
import com.example.javafxjdbc.model.entities.Department;
import com.example.javafxjdbc.services.DepartmentService;
import com.example.javafxjdbc.util.Alerts;
import com.example.javafxjdbc.util.Constraints;
import com.example.javafxjdbc.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class DepartmentFormController implements Initializable {
  private DepartmentService service;
  private Department entity;
  private List<DataChangeListener> dataChangeListenerList = new ArrayList<>();
  @FXML
  private TextField txtId;
  @FXML
  private TextField txtName;
  @FXML
  private Label labelErrorName;
  @FXML
  private Button btSave;
  @FXML
  private Button btCancel;

  @FXML
  public void onBtSaveAction(ActionEvent event) {
    if (entity == null) {
      throw new IllegalStateException("Entity was null");
    }
    if (service == null) {
      throw new IllegalStateException("service was null");
    }
    try {
      entity = getFormData();
      service.saveOrUpdate(entity);
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

  private Department getFormData() {
    Department obj = new Department();
    ValidationException exception = new ValidationException("Validation Error");
    obj.setId(Utils.TryParseToInt(txtId.getText()));
    if (txtName.getText() == null || txtName.getText().trim().equals("")) {
      exception.addError("name", "Field can't bem empty");
    }
    obj.setName(txtName.getText());
    if (exception.getErrors().size() > 0) {
      throw exception;
    }
    return obj;
  }

  public void setDepartment(Department entity) {
    this.entity = entity;
  }

  public void setDepartmentService(DepartmentService service) {
    this.service = service;
  }

  private void setErrorMessages(Map<String, String> errors) {
    Set<String> fields = errors.keySet();
    if (fields.contains("name")) {
      labelErrorName.setText(errors.get("name"));
    }
  }

  private void initializeNodes() {
    Constraints.setTextFieldInteger(txtId);
    Constraints.setTextFieldMaxLength(txtName, 30);
  }

  public void updateFormData() {
    if (entity == null) {
      throw new IllegalStateException("Entity was null");
    }
    txtId.setText(String.valueOf(entity.getId()));
    txtName.setText(String.valueOf(entity.getName()));
  }

  public void subscribeDataChangeListener(DataChangeListener dataChangeListener) {
    dataChangeListenerList.add(dataChangeListener);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeNodes();
  }
}
