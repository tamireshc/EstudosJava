package entities;

import java.util.Objects;

public class Student {
  private int code;

  public Student(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return code == student.code;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }
}
