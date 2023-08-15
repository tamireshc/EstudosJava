package entities;

public class IndividualPayer extends Payer {
  private double healthExpenditures;

  public IndividualPayer(String name, double income, double healthExpenditures) {
    super(name, income);
    this.healthExpenditures = healthExpenditures;
  }

  public double getHealthExpenditures() {
    return healthExpenditures;
  }

  public void setHealthExpenditures(double healthExpenditures) {
    this.healthExpenditures = healthExpenditures;
  }

  @Override
  public double taxes() {
    if (getIncome() > 20000 && healthExpenditures > 0) {
      return (getIncome() * 0.25) - (healthExpenditures * 0.5);
    } else if (getIncome() > 20000) {
      return getIncome() * 0.25;
    } else if (healthExpenditures > 0) {
      return (getIncome() * 0.15) - (healthExpenditures * 0.5);
    } else {
      return getIncome() * 0.15;
    }

  }
}
