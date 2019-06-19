package com.denys.classes;

import java.net.URL;
import java.time.LocalDate;

public class Presse extends News {

  //region Fields

  private String descriptionLong;

  private URL longVersionUrl;

  private boolean onlyOnline;

  //endregion

  /**
   * Constructor, that helps us to presse new instance of news quickly
   *
   * @param title  Title of news
   * @param date   Creation date of news
   * @param author Author of news
   * @param source Source url of news
   */
  public Presse(String title, LocalDate date, String author, URL source, String descriptionLong, URL longVersionUrl, boolean onlyOnline) {
    super(title, date, author, source);
    this.descriptionLong = descriptionLong;
    this.longVersionUrl = longVersionUrl;
    this.onlyOnline = onlyOnline;
  }

  //region Get-Set


  public String getDescriptionLong() {
    return descriptionLong;
  }

  public void setDescriptionLong(String descriptionLong) {
    this.descriptionLong = descriptionLong;
  }

  public URL getLongVersionUrl() {
    return longVersionUrl;
  }

  public void setLongVersionUrl(URL longVersionUrl) {
    this.longVersionUrl = longVersionUrl;
  }

  public boolean isOnlyOnline() {
    return onlyOnline;
  }

  public void setOnlyOnline(boolean onlyOnline) {
    this.onlyOnline = onlyOnline;
  }

  //endregion


  @Override
  public String toString() {
    String presseString = super.toString();
    presseString += "Description long: " + this.descriptionLong + "\n";
    presseString += "Long version url: " + this.longVersionUrl + "\n";
    presseString += "Is online only: " + this.onlyOnline + "\n";
    return presseString;
  }
}
