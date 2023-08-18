import entities.Employees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Locale.setDefault(Locale.US);
    String path = "in.csv";
    List<Employees> employees = new ArrayList<>();

    try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
      String line = bf.readLine();
      while (line != null) {
        String[] itens = line.split(",");
        Employees employee = new Employees(itens[0], itens[1], Double.parseDouble(itens[2]));
        employees.add(employee);
        line = bf.readLine();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    System.out.print("Enter salary: ");
    String salaryString = sc.next();
    double salary = Double.parseDouble(salaryString);
    System.out.println("Email of people whose salary is more than " +  String.format("%.2f",salary) + " :");

    List<String> emails = employees.stream()
      .filter(e -> e.getSalary() > salary)
      .map(e -> e.getEmail())
      .sorted()
      .toList();

    for (String email : emails) {
      System.out.println(email);
    }

    double sum = employees.stream().filter(e -> e.getName().charAt(0) == 'M')
      .map(e -> e.getSalary())
      .reduce(0.0, (x, y) -> x + y);

    System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f%n%n",sum));

    sc.close();
  }
}
