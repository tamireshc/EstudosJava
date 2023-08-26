package com.mongo.mongoprojectspring.dto;

import com.mongo.mongoprojectspring.entities.User;

import java.util.Objects;

public class AuthorDTO {
  private String id;
  private String name;

  public AuthorDTO(User user) {
    this.id = user.getId();
    this.name = user.getName();
  }

  public AuthorDTO() {
  }

  ;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthorDTO authorDTO = (AuthorDTO) o;
    return Objects.equals(id, authorDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
