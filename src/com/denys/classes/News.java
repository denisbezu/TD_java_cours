package com.denys.classes;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;

public class News implements Comparable {

  //region Fields
  /**
   * The title of news
   */
  private String title;

  /**
   * The creation date of news
   */
  private LocalDate date;

  /**
   * The name of author for given news
   */
  private String author;

  /**
   * The source url of news
   */
  private URL source;

  //endregion

  //region Get-Set

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public URL getSource() {
    return source;
  }

  public void setSource(URL source) {
    this.source = source;
  }

  //endregion

  /**
   * Constructor, that helps us to crete new instance of news quickly
   *
   * @param title  Title of news
   * @param date   Creation date of news
   * @param author Author of news
   * @param source Source url of news
   */
  public News(String title, LocalDate date, String author, URL source) {
    this.title = title;
    this.date = date;
    this.author = author;
    this.source = source;
  }


//  public void show() {
//    System.out.println("Titre: " + this.title);
//    System.out.println("Date: " + this.date);
//    System.out.println("Author: " + this.author);
//    System.out.println("Source: " + this.source);
//  }

//  public void enter() throws MalformedURLException {
//    Scanner scanner = new Scanner(System.in);
//
//    System.out.println("Enter a title: ");
//    this.title = scanner.nextLine();
//
//    System.out.println("Enter the date: ");
//    String date = scanner.nextLine();
//    this.date = LocalDate.parse(date);
//
//    System.out.println("Enter author: ");
//    this.author = scanner.nextLine();
//
//    System.out.println("Enter URL: ");
//    String url = scanner.nextLine();
//    this.source = new URL(url);
//  }

  /**
   * Information about current news
   *
   * @return
   */
  @Override
  public String toString() {
    String newsString = "Titre: " + this.title + "\n" +
        "Date: " + this.date + "\n" +
        "Author: " + this.author + "\n" +
        "Source: " + this.source + "\n";
    return newsString;
  }

  /**
   * Helps us to specify rules of comparison for news sorting
   *
   * @param o
   * @return
   */
  @Override
  public int compareTo(Object o) {
    News entry = (News) o;
    return this.title.compareTo(entry.getTitle());
  }
}
