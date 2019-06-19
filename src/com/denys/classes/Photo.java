package com.denys.classes;

import java.net.URL;
import java.time.LocalDate;

public class Photo extends News {

  //region Fields

  private String photoName;

  private String extension;

  private int resolutionX;

  private int resolutionY;

  private boolean isColored;

  //endregion

  /**
   * Constructor, that helps us to crete new instance of photo quickly
   *
   * @param title  Title of news
   * @param date   Creation date of news
   * @param author Author of news
   * @param source Source url of news
   */
  public Photo(String title, LocalDate date, String author, URL source,
               String photoName, String extension, int resolutionX, int resolutionY, boolean isColored) {
    super(title, date, author, source);
    this.photoName = photoName;
    this.extension = extension;
    this.resolutionX = resolutionX;
    this.resolutionY = resolutionY;
    this.isColored = isColored;
  }

  //region Get-Set

  public String getPhotoName() {
    return photoName;
  }

  public void setPhotoName(String photoName) {
    this.photoName = photoName;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public int getResolutionX() {
    return resolutionX;
  }

  public void setResolutionX(int resolutionX) {
    this.resolutionX = resolutionX;
  }

  public int getResolutionY() {
    return resolutionY;
  }

  public void setResolutionY(int resolutionY) {
    this.resolutionY = resolutionY;
  }

  public boolean isColored() {
    return isColored;
  }

  public void setColored(boolean colored) {
    isColored = colored;
  }


  //endregion


  @Override
  public String toString() {
    String photoString = super.toString();
    photoString += "Name of photo: " + this.photoName + "\n";
    photoString += "Extension: " + this.extension + "\n";
    photoString += "Resolution: (" + this.resolutionX + ", " + this.resolutionY + ")\n";
    photoString += "Image type: " + (isColored ? "colored" : "black and white") + ")\n";
    return photoString;
  }
}
