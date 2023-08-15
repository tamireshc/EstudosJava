package entities;

public class CompanyPayer extends Payer {
  private Integer numberOfEmployees;

  public CompanyPayer(String name, Double income, Integer numberOfEmployees) {
    super(name, income);
    this.numberOfEmployees = numberOfEmployees;
  }

  public Integer getNumberOfEmployees() {
    return numberOfEmployees;
  }

  public void setNumberOfEmployees(Integer numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
  }

  @Override
  public double taxes() {
    if (numberOfEmployees > 10) {
      return getIncome() * 0.14;
    } else {
      return getIncome() * 0.16;
    }
  }
}
