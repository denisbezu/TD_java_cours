package com.denys;

import com.denys.classes.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The main class of program
 */
public class MesNews {

  /**
   * Our stockage
   */
  private static BaseDeNews baseDeNews;

  private final static String baseDeNewsFileName = "news.txt";

  /**
   * The entry point of program
   *
   * @param args
   * @throws MalformedURLException
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException {

    baseDeNews = new BaseDeNews();
    baseDeNews.initialize(); // to remove latter

    doActions();
  }

  /**
   * Shows themenu items
   */
  public static void showMenu() {
    System.out.println("--------------------------");
    System.out.println("Entrer le numero du menu: ");
    System.out.println("1. Creer");
    System.out.println("2. Ouvrir");
    System.out.println("3. Sauvegarder");
    System.out.println("4. Inserer");
    System.out.println("5. Supprimer");
    System.out.println("6. Afficher");
    System.out.println("7. Rechercher");
    System.out.println("8. Quiter");
    System.out.println("--------------------------");
  }

  /**
   * DO different actions
   */
  public static void doActions() {
    Scanner scanner = new Scanner(System.in);
    int number;
    do {
      showMenu();
      number = scanner.nextInt();
      switch (number) {
        case 1:
          create();
          break;
        case 2:
          open();
          break;
        case 3:
          save();
          break;
        case 4:
          insert();
          break;
        case 5:
          delete();
          break;
        case 6:
          show();
          break;
        case 7:
          search();
          break;
        case 8:
          exit();
          break;
        default:
          System.out.println("Not recognized");
          break;
      }
    } while (number != 8);
  }

  /**
   * Create the instance of our storage Tree
   */
  public static void create() {
    System.out.println("cree une nouvelle base d’actualit´e, c’est-`a-dire un ensemble de news dans la collection ;\n" +
        "attention, cette action est `a r´ealiser une seule fois au d´ebut, quand la base n’existe pas encore");

    baseDeNews = new BaseDeNews();
    baseDeNews.initialize();
  }

  /**
   * Open the file with news
   */
  public static void open() {
    System.out.println("charge une base d’actualit´e existante qui a " +
        "´et´e enregistr´ee pr´ealablement sur le disque\n" +
        "dur de l’ordinateur");
    try {
      baseDeNews.readFromFile(baseDeNewsFileName);
    } catch (IOException e) {
      System.out.println("Error while reading data from file");
    } catch (ClassNotFoundException e) {
      System.out.println("Casting class not found");
    }
  }

  /**
   * Save our news to file
   */
  public static void save() {
    System.out.println("sauvegarde la base courante sur le disque dur de l’ordinateur");
    try {
      baseDeNews.saveToFile(baseDeNewsFileName);
    } catch (IOException e) {
      System.out.println("Error while saving file!");
    }
  }

  /**
   * Insert the ne item in the news storage
   */
  public static void insert() {
    System.out.println("ins`ere une nouvelle actualit´e dans la base (statut d’un ami, article de presse, photo. . . ).");

    try {
      News news = null;

      System.out.println("Enter the desired type (1 - presse; 2 - photo): ");

      try {
        Scanner scanner = new Scanner(System.in);
        int newsType = scanner.nextInt();
        if (newsType != 1 && newsType != 2) {
          throw new Exception("Invalid type");
        }

        String title = requestTitle();
        LocalDate localDate = requestDate();
        String author = requestAuthor();
        URL source = requestURL();

        switch (newsType) {
          case 1:
            String longDescription = requestLongDescription();
            URL longVersionSource = requestLongURL();
            boolean isOnlineOnly = requestIsOnline();
            news = new Presse(title, localDate, author, source, longDescription, longVersionSource, isOnlineOnly);
            break;
          case 2:
            String photo = requestPhotoFileName();
            FilenameExtension filenameExtension = new FilenameExtension();
            String extension = filenameExtension.getExtension(photo);
            String fileName = filenameExtension.getFileName(photo);
            int resolutionX = requestResolutionX();
            int resolutionY = requestResolutionY();
            boolean isColored = requestIsColored();

            news = new Photo(title, localDate, author, source, fileName, extension, resolutionX, resolutionY, isColored);
            break;
        }

      } catch (Exception e) {
        System.out.println("Invalid type");
        insert();
        return;
      }


      System.out.println(news.toString());
      if (baseDeNews != null) {
        baseDeNews.add(news);
      } else {
        System.out.println("Initiate base first!");
      }
    } catch (Exception e) {
      System.out.println("Invalid input!");
      insert();
    }
  }

  public static String requestTitle() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a title: ");
    String title = scanner.nextLine();
    return title;
  }

  public static LocalDate requestDate() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the date: ");
    String date = scanner.nextLine();
    return LocalDate.parse(date);
  }

  public static String requestAuthor() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter author: ");
    return scanner.nextLine();
  }

  public static URL requestURL() throws MalformedURLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter URL: ");
    String url = scanner.nextLine();
    return new URL(url);
  }

  public static String requestLongDescription() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a long description: ");
    return scanner.nextLine();
  }

  public static String requestPhotoFileName() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter name of file (photo): ");
    return scanner.nextLine();
  }

  public static URL requestLongURL() throws MalformedURLException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a long version of URL: ");
    String longVersionUrl = scanner.nextLine();
    return new URL(longVersionUrl);
  }

  public static int requestResolutionX() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter resolution X of photo: ");
    return scanner.nextInt();
  }

  public static int requestResolutionY() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter resolution Y of photo: ");
    return scanner.nextInt();
  }

  public static boolean requestIsOnline() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Is presse is online only? (1 -  yes, 2 - non)");
    int result = scanner.nextInt();
    if (result != 1 && result != 2) {
      throw new InputMismatchException("Invalid argument");
    }

    return result == 1;
  }

  public static boolean requestIsColored() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Is image colored? (1 - oui, 2 - black and white)");
    int result = scanner.nextInt();
    if (result != 1 && result != 2) {
      throw new InputMismatchException("Invalid argument");
    }

    return result == 1;
  }

  /**
   * Removes the item by index
   */
  public static void delete() {
    System.out.println("supprime une actualit´e de la base");
    if (baseDeNews != null) {
      baseDeNews.show();
      System.out.println("Enter index to delete: ");
      Scanner scanner = new Scanner(System.in);
      try {
        int indexToDelete = scanner.nextInt();
        boolean deleteResult = baseDeNews.deleteByIndex(indexToDelete);
        if (!deleteResult) {
          System.out.println("Index not found!");
        }
      } catch (Exception e) {
        System.out.println("Invalid input!");
      }

    } else {
      System.out.println("Base not created");
    }

  }

  /**
   * Show the current state of our storage
   */
  public static void show() {
    System.out.println("affiche le contenu total de la base");
    if (baseDeNews != null) {
      baseDeNews.show();
    }
  }

  /**
   * Find an item in storage
   */
  public static void search() {
    System.out.println("permet de recherche si une actualit´e existe en fonction du titre, ou mˆeme de\n" +
        "mots-clefs, entr´es par l’utilisateur");
    Scanner scanner = new Scanner(System.in);
    String searchString = scanner.nextLine();
    List<News> searchResult = baseDeNews.search(searchString);
    for (News news : searchResult) {
      System.out.println(news.toString());
    }
  }

  /**
   * Exit the application
   */
  public static void exit() {
    System.out.println("quitte l’application");
  }

}
