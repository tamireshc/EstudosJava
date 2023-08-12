import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Locale.setDefault(Locale.ENGLISH);
    List<Product> products = new ArrayList<>();

    System.out.print("Enter the number of products: ");
    int n = sc.nextInt();

    for (int i = 1; i <= n; i++) {
      System.out.println("Product #" + i + " data:");
      System.out.print("Common, used or imported (c/u/i)? ");

      char stateProduct = sc.next().charAt(0);
      sc.nextLine();

      System.out.print("Name: ");
      String name = sc.nextLine();

      System.out.print("Price: ");
      String price = sc.next();

      if (stateProduct == 'i') {
        System.out.print("Customs fee: ");
        String fee = sc.next();
        ImportedProduct importedProduct = new ImportedProduct(name, Double.parseDouble(price), Double.parseDouble(fee));
        products.add(importedProduct);
      } else if (stateProduct == 'u') {
        System.out.print("Manufacture date (DD/MM/YYYY): ");
        String date = sc.next();
        LocalDate dateFormact = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        UsedProduct usedProduct = new UsedProduct(name, Double.parseDouble(price), dateFormact);
        products.add(usedProduct);
      } else {
        Product product = new Product(name, Double.parseDouble(price));
        products.add(product);
      }
    }
    System.out.println();
    System.out.println("PRICE TAGS ");
    for (Product product : products) {
      System.out.println(product.priceTag());
    }
    sc.close();
  }
}
