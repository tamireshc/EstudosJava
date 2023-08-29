package com.example.demo.controllers;

import com.example.demo.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.util.*;

public class SomaController implements Initializable {
  @FXML
  private ComboBox<Person> comboboxPerson;
  @FXML
  private Button btAll;
  private ObservableList<Person> obsList;

  @FXML
  public void onBtAllAction() {
    for (Person person : comboboxPerson.getItems()) {
      System.out.println(person);
    }
  }

  @FXML
  public void onComboxPersonAction() {
    Person person = comboboxPerson.getSelectionModel().getSelectedItem();
    System.out.println(person);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    List<Person> list = new ArrayList<>();
    list.add(new Person(1, "Maria", "maria@gmail.com"));
    list.add(new Person(2, "Alex", "alex@gmail.com"));
    list.add(new Person(3, "Bob", "bob@gmail.com"));
    obsList = FXCollections.observableArrayList(list);
    comboboxPerson.setItems(obsList);

    Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
      @Override
      protected void updateItem(Person item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? "" : item.getName());
      }
    };
    comboboxPerson.setCellFactory(factory);
    comboboxPerson.setButtonCell(factory.call(null));
  }
}