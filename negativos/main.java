import java.util.Scanner;

public class main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Quantos numeros voce vai digitar?");

    int totalValores = sc.nextInt();
    int[] vect = new int[totalValores];

    for (int i = 0; i < totalValores; i++) {
      System.out.println("Digite um numero:");
      int n = sc.nextInt();
      vect[i] = n;
    }

    System.out.println("NUMEROS NEGATIVOS:");

    for (int number : vect) {
      if (number < 0) {
        System.out.println(number);
      }
    }
    sc.close();
  }
}
