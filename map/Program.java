import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Map<String, Integer> votes = new HashMap<>();

    String path = "in.csv";
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line = br.readLine();
      while (line != null) {
        String[] lineVotes = line.split(",");
        if (votes.containsKey(lineVotes[0])) {
          Integer newvalue = votes.get(lineVotes[0]) + Integer.parseInt(lineVotes[1]);
          votes.put(lineVotes[0], newvalue);
        } else {
          votes.put(lineVotes[0], Integer.parseInt(lineVotes[1]));
        }
        line = br.readLine();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      sc.close();
    }
    for (String key : votes.keySet()) {
      System.out.println(key + ": " + votes.get(key));
    }
  }
}
