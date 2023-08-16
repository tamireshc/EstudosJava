package service;

public class PaypalService implements OnlinePaymentService {

  private double simpleInterest = 0.01;
  private double paymentTax = 0.02;

  @Override
  public double interest(double amount, int months) {
    return amount * simpleInterest * months;
  }

  @Override
  public double paymentFee(double amount) {
    return amount * paymentTax;
  }

}
