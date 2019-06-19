package com.denys.classes;

import java.util.Iterator;
import java.util.TreeSet;

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

}
