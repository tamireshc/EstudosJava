import Entities.Person;

import java.util.Locale;
import java.util.Scanner;

public class main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Locale.setDefault(Locale.US);


    System.out.println("Quantas pessoas serao digitadas?");

    int n = sc.nextInt();
    Person[] persons = new Person[n];

    for (int i = 0; i < n; i++) {
      System.out.println("Dados da " + (i + 1) + "a pessoa:");

      String nome = sc.next();
      int idade = sc.nextInt();
      String alturaString = sc.next();
      Double altura = Double.parseDouble(alturaString.replace(",", "."));

      persons[i] = new Person(nome, altura, idade);
    }

    double alturaTotal = 0;
    double totalpessoasMenos16Anos = 0;

    for (Person person : persons) {
      alturaTotal += person.getAltura();
      if (person.getIdade() < 16) {
        totalpessoasMenos16Anos += 1;
      }
    }
    System.out.printf("Altura média: %.2f%n", (alturaTotal / n));
    System.out.printf("Pessoas com menos de 16 anos: %.1f%%%n", (totalpessoasMenos16Anos / n * 100));

    for (Person person : persons) {
      if (person.getIdade() < 16) {
        System.out.println(person.getNome());
      }
    }
    sc.close();
  }
}
