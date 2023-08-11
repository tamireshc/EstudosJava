import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enuns.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter cliente data:");

    System.out.print("Name:");
    String name = sc.nextLine();

    System.out.print("Email:");
    String email = sc.nextLine();

    System.out.print("Birth date (DD/MM/YYYY):");
    String birthDate = sc.nextLine();

    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    Client client = new Client(name, email, LocalDate.parse(birthDate, fmt1));
    Order order = new Order();

    System.out.println("Enter order data:");
    System.out.print("Status:");
    String status = sc.nextLine();

    System.out.print("How many itens to this order?");
    int n = sc.nextInt();

    order.setCliente(client);
    order.setMoment(LocalDateTime.now());
    order.setStatus(OrderStatus.valueOf(status));

    for (int i = 0; i < n; i++) {
      System.out.println("Enter #" + (i + 1) + " item data:");

      System.out.print("Product name:");
      sc.nextLine();
      String productName = sc.nextLine();

      System.out.print("Product price:");
      String productPriceString = sc.nextLine();
      double productPrice = Double.parseDouble(productPriceString);

      System.out.print("Quantity:");
      int quantity = sc.nextInt();

      Product product = new Product(productName, productPrice);
      OrderItem orderItem = new OrderItem(quantity, product);

      order.addItem(orderItem);
    }
    System.out.println();
    System.out.println("ORDER SUMMARY");
    System.out.println("Order moment: " + order.getMoment().format(fmt2));
    System.out.println("Order status: " + order.getStatus());
    System.out.println("Client: "
      + order.getCliente().getName() + " "
      + "(" + order.getCliente().getBirthDate().format(fmt1) + ") - "
      + order.getCliente().getEmail()
    );
    System.out.println("Order items");
    for (OrderItem item : order.getItens()) {
      System.out.println(
        item.getProduct().getName() + ", "
          + "$" + String.format("%.2f", item.getProduct().getPrice()) + ", "
          + "Quantity: " + item.getQuantity() + ", "
          + "Subtotal: $" + String.format("%.2f", item.subTotal())
      );
    }
    System.out.println("Total Price: $" + String.format("%.2f", order.total()));

  }
}
