package Entities;

public class Rent {
  private String Name;
  private String Email;
  private int room;

  public Rent(String name, String email, int room) {
    Name = name;
    Email = email;
    this.room = room;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public int getRoom() {
    return room;
  }

  public void setRoom(int room) {
    this.room = room;
  }
}
