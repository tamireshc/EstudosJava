package com.mongo.mongoprojectspring.dto;

import java.time.LocalDate;

public class CommentDTO {
  private String text;
  private LocalDate date;
  private AuthorDTO author;

  public CommentDTO(String text, LocalDate date, AuthorDTO author) {
    this.text = text;
    this.date = date;
    this.author = author;
  }

  public CommentDTO() {
  }

  ;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public AuthorDTO getAuthor() {
    return author;
  }

  public void setAuthor(AuthorDTO author) {
    this.author = author;
  }
}
