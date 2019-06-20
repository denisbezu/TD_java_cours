package com.denys.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the storage for news
 */
public class BaseDeNews {
  private TreeSet<News> news;

  /**
   * Creates the new instance of TreeSet, our storage
   */
  public void initialize() {
    this.news = new TreeSet<>();
  }

  /**
   * Add a new news in the storage
   *
   * @param currentNews News instance
   */
  public void add(News currentNews) {
    if (this.news == null) {
      this.initialize();
    }

    this.news.add(currentNews);
  }

  /**
   * Shows all news from the storage
   */
  public void show() {
    Iterator iterator = this.news.iterator();
    int counter = 1;
    while (iterator.hasNext()) {
      System.out.println(counter + ". -------------");
      System.out.println(iterator.next());
      counter++;
    }
  }

  /**
   * Deletes a news by index from storage
   *
   * @param index
   * @return
   */
  public boolean deleteByIndex(int index) {
    Iterator iterator = this.news.iterator();
    int counter = 0;
    while (iterator.hasNext()) {
      if (counter == (index - 1)) {
        this.news.remove(iterator.next());
        return true;
      }
    }

    return false;
  }

  public void saveToFile(String fileName) throws IOException {
    FileOutputStream f = new FileOutputStream(new File(fileName));
    ObjectOutputStream o = new ObjectOutputStream(f);

    o.writeObject(this.news);
  }

  public void readFromFile(String fileName) throws IOException, ClassNotFoundException {
    FileInputStream fi = new FileInputStream(new File(fileName));
    ObjectInputStream oi = new ObjectInputStream(fi);

    this.news = (TreeSet<News>) oi.readObject();
  }

  public List<News> search(String searchString) {
    List<News> findNews = new ArrayList<>();
    Pattern pattern = Pattern.compile(searchString);

    Iterator iterator = this.news.iterator();
    while (iterator.hasNext()) {
      News news = (News) iterator.next();
      Matcher titleMatcher = pattern.matcher(news.getTitle());
      if (titleMatcher.find()) {
        findNews.add(news);
        continue;
      }

      Matcher authorMatcher = pattern.matcher(news.getAuthor());
      if (authorMatcher.find()) {
        findNews.add(news);
      }
    }

    return findNews;
  }

}
