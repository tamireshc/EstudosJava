package service;

import entities.Contract;
import entities.Installment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContractService {

  private OnlinePaymentService onlinePaymentService;

  public ContractService(OnlinePaymentService onlinePaymentService) {
    this.onlinePaymentService = onlinePaymentService;
  }

  public void processContract(Contract contract, int months){
    for(int i =1; i<= months; i++){
      LocalDate date =  contract.getDate().plus(i, ChronoUnit.MONTHS);
      double baseParcel = contract.getTotalValue()/months;
      double interest = onlinePaymentService.interest(baseParcel, i);
      double paymentFee = onlinePaymentService.paymentFee(baseParcel+interest);

      Installment installment = new Installment(date,(baseParcel+interest+paymentFee));
      contract.addInstallment(installment);
    }

  }
}
