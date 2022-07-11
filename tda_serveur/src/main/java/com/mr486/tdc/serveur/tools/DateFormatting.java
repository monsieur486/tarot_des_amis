package com.mr486.tdc.serveur.tools;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@Component
public class DateFormatting {

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  private final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public java.sql.Date parseDate(String date) {
    try {
      return new Date(dateFormat.parse(date).getTime());
    } catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public java.sql.Timestamp parseTimestamp(String timestamp) {
    try {
      return new Timestamp(dateTimeFormat.parse(timestamp).getTime());
    } catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
