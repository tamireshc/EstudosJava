import entities.Contract;
import service.ContractService;
import service.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    System.out.println("Entre os dados do contrato: ");

    System.out.print("Numero: ");
    int numero = sc.nextInt();

    System.out.print("Data (dd/MM/yyyy): ");
    String dataString = sc.next();
    LocalDate data = LocalDate.parse(dataString, fmt);

    System.out.print("Valor do contrato: ");
    String valorString = sc.next();
    double valor = Double.parseDouble(valorString);

    System.out.print("Entre com o numero de parcelas: ");
    int parcelas = sc.nextInt();

    Contract contract = new Contract(numero, data, valor);
    ContractService contractService = new ContractService(new PaypalService());
    contractService.processContract(contract, parcelas);
    System.out.println();
    System.out.println("Parcelas:");
    contract.parcels();
  }
}
