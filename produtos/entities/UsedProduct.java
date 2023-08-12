package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsedProduct extends Product {
  private LocalDate manufatureDate;

  public UsedProduct(String name, Double price, LocalDate manufatureDate) {
    super(name, price);
    this.manufatureDate = manufatureDate;
  }

  @Override
  public String priceTag() {
    return getName()
      + " (used) "
      + " $ "
      + String.format("%.2f",getPrice())
      + " (Manufacture date: " + manufatureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
  }

}
