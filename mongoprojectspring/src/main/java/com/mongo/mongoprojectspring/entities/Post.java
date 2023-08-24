package com.mongo.mongoprojectspring.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Post {
  private String id;
  private LocalDate date;
  private String title;
  private String body;

  public Post(String id, LocalDate date, String title, String body) {
    this.id = id;
    this.date = date;
    this.title = title;
    this.body = body;
  }

  Post() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Post post = (Post) o;
    return Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
