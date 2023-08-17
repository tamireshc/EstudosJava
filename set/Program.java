import entities.Student;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Set<Student> studentSet = new HashSet<>();

    System.out.print("How many students for course A? ");
    int na = sc.nextInt();
    for (int i = 0; i < na; i++) {
      int code = sc.nextInt();
      Student student = new Student(code);
      studentSet.add(student);
    }

    System.out.print("How many students for course B? ");
    int nb = sc.nextInt();
    for (int i = 0; i < nb; i++) {
      int code = sc.nextInt();
      Student student = new Student(code);
      studentSet.add(student);
    }

    System.out.print("How many students for course C? ");
    int nc = sc.nextInt();
    for (int i = 0; i < nc; i++) {
      int code = sc.nextInt();
      Student student = new Student(code);
      studentSet.add(student);
    }
    System.out.println("Total students: " + studentSet.size());
  }
}
