package com.denys;

import com.denys.classes.BaseDeNews;
import com.denys.classes.News;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * The main class of program
 */
public class MesNews {

    /**
     * Our stockage
     */
    private static BaseDeNews baseDeNews;

    /**
     * The entry point of program
     *
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        baseDeNews = new BaseDeNews();
        baseDeNews.initialize();
        baseDeNews.add(new News("Denys", LocalDate.parse("2019-02-02"), "Author", new URL("https://google.com")));
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
    }

    /**
     * Save our news to file
     */
    public static void save() {
        System.out.println("sauvegarde la base courante sur le disque dur de l’ordinateur");
    }

    /**
     * Insert the ne item in the news storage
     */
    public static void insert() {
        System.out.println("ins`ere une nouvelle actualit´e dans la base (statut d’un ami, article de presse, photo. . . ).");

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter a title: ");
            String title = scanner.nextLine();

            System.out.println("Enter the date: ");
            String date = scanner.nextLine();
            LocalDate localDate = LocalDate.parse(date);

            System.out.println("Enter author: ");
            String author = scanner.nextLine();

            System.out.println("Enter URL: ");
            String url = scanner.nextLine();
            URL source = new URL(url);
            News news = new News(title, localDate, author, source);
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
    }

    /**
     * Exit the application
     */
    public static void exit() {
        System.out.println("quitte l’application");
    }

}
