import entities.CompanyPayer;
import entities.IndividualPayer;
import entities.Payer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Locale.setDefault(Locale.US);
    List<Payer> payers = new ArrayList<>();

    System.out.print("Enter the number of tax payers: ");
    int n = sc.nextInt();

    for (int i = 1; i <= n; i++) {
      System.out.print("tax payer #" + i + " data: ");

      System.out.print("Individual or company (i/c)? ");
      char type = sc.next().charAt(0);

      System.out.print("Name: ");
      String name = sc.next();

      System.out.print("Anual Income: ");
      String incomeString = sc.next();
      double income = Double.parseDouble(incomeString);

      if (type == 'i') {
        System.out.print("Health expenditures: ");
        String helthString = sc.next();
        double health = Double.parseDouble(helthString);
        IndividualPayer individualPayer = new IndividualPayer(name, income, health);
        payers.add(individualPayer);
      } else if (type == 'c') {
        System.out.print("Number od employees: ");
        int employees = sc.nextInt();
        CompanyPayer companyPayer = new CompanyPayer(name, income, employees);
        payers.add(companyPayer);
      }
    }
    System.out.println();
    System.out.println("TAXES PAID:");

    double sum = 0;
    for (Payer payer : payers) {
      sum += payer.taxes();
      System.out.println(payer.getName() + " $ " + String.format("%.2f%n", payer.taxes()));
    }
    System.out.println("TOTAL TAXES: $ " + String.format("%.2f%n", sum));
    sc.close();
  }
}
