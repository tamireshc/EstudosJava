package entities;

public abstract class Payer {
  private String name;
  private double income;

  public abstract double taxes();

  public Payer(String name, double income) {
    this.name = name;
    this.income = income;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getIncome() {
    return income;
  }

  public void setIncome(double income) {
    this.income = income;
  }
}
