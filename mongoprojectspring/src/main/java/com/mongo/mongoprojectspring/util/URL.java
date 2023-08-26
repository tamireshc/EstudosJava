package com.mongo.mongoprojectspring.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class URL {
  public static String decodeParam(String text) {
    return URLDecoder.decode(text, StandardCharsets.UTF_8);
  }

  public static LocalDate convertDate(String textDate, LocalDate defaultDate)  {
    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try {
      return LocalDate.parse(textDate, fmt1);
    } catch (Exception e) {
      return defaultDate;
    }

  }
}
