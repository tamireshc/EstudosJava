package entities;

import entities.enuns.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
  private LocalDateTime moment;
  private OrderStatus status;
  private Client cliente;
  private List<OrderItem> itens = new ArrayList<>();

  public Order(LocalDateTime moment, OrderStatus status, Client cliente) {
    this.moment = moment;
    this.status = status;
    this.cliente = cliente;
  }

  public Order() {

  }

  public LocalDateTime getMoment() {
    return moment;
  }

  public void setMoment(LocalDateTime moment) {
    this.moment = moment;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Client getCliente() {
    return cliente;
  }

  public void setCliente(Client cliente) {
    this.cliente = cliente;
  }

  public List<OrderItem> getItens() {
    return itens;
  }

  public void addItem(OrderItem item) {
    itens.add(item);
  }

  public void removeItem(OrderItem item) {
    itens.remove(item);
  }

  public double total() {
    double total = 0;
    for (OrderItem item : itens) {
      total += item.subTotal();
    }
    return  total;
  }

}
