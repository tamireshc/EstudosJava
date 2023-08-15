import entities.Account;
import exceptions.ExceedsWithdrawException;
import exceptions.NotEnougthException;

import java.util.Locale;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);
      Locale.setDefault(Locale.US);

      System.out.println("Enter account data:");

      System.out.print("Number: ");
      int number = sc.nextInt();

      System.out.print("Holder: ");
      sc.next();
      String holder = sc.nextLine();

      System.out.print("Initial balance: ");
      String balanceString = sc.next();
      double balance = Double.parseDouble(balanceString);

      System.out.print("Withdraw limit: ");
      String withdrawLimitString = sc.next();
      double withdrawLimit = Double.parseDouble(withdrawLimitString);
      System.out.println();

      Account account = new Account(number, holder, balance, withdrawLimit);

      System.out.print("Enter amount for withdraw: ");
      String withdrawString = sc.next();
      double amount = Double.parseDouble(withdrawString);
      account.withdraw(amount);

      System.out.print("New balance " + String.format("%.2f%n%n", account.getBalance()));

      sc.close();
    } catch (ExceedsWithdrawException | NotEnougthException e) {
      System.out.println("Withdraw error: " + e.getMessage());
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}
