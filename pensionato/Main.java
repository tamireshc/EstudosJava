import Entities.Rent;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Rent[] roons = new Rent[10];

    System.out.println("How many rooms will be rented?");
    int n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      System.out.println("Rent #" + (i + 1) + ":");
      sc.nextLine();
      System.out.println("Name");
      String name = sc.nextLine();

      System.out.println("Email");
      String email = sc.nextLine();

      System.out.println("Room");
      int room = sc.nextInt();

      roons[room] = new Rent(name, email, room);
    }

    System.out.println("Busy rooms:");
    for (int i=0; i<roons.length; i++) {
      if (roons[i] != null) {
        System.out.println(
          roons[i].getRoom()
            + ": "
            + roons[i].getName() + ", "
            + roons[i].getEmail());
      }
    }
    sc.close();
  }
}
