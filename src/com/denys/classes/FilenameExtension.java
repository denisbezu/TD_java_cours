package com.denys.classes;

public class FilenameExtension {

  public String getExtension(String fileName) throws Exception {
    String extension = "";

    int i = fileName.lastIndexOf('.');
    if (i > 0) {
      extension = fileName.substring(i + 1);
    } else {
      throw new Exception("Extension not found!");
    }

    return extension;
  }

  public String getFileName(String fileName) throws Exception {
    String extension = "";

    int i = fileName.lastIndexOf('.');
    if (i > 0) {
      extension = fileName.substring(0, i);
    } else {
      throw new Exception("Filename not found!");
    }

    return extension;
  }
}
