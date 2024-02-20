package org.example.model;

import org.example.enums.Role;
import org.example.enums.Status;

import java.util.List;
import java.util.Scanner;

public class Menu {
    Forum forum;
    User user;

    public Menu(Forum forum, User user) {
        this.forum = forum;
        this.user = user;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showMenu(){
        if(user.role.equals(Role.ADMIN)){
            adminMenu();
            return;
        }
        userMenu();
    }

    private void printOptionsForUser(){
        System.out.println();
        System.out.println("OPTIUNILE SUNT: ");
        System.out.println("1. Vezi articolele de pe forum.");
        System.out.println("2. Vezi toate articolele tale");
        System.out.println("3. Introdu un nou articol");
        System.out.println("0. Iesi din meniu");
        System.out.println("\nTE ROG SA INTRODUCI OPTIUNEA TA, NUMERIC: ");
    }
    private void userMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while(option!=0){
            printOptionsForUser();
            option = scanner.nextInt();
            switch(option){
                case 1: {
                    List<Article> listOfApprovedArticles = forum.getAllApprovedArticles();
                    if (listOfApprovedArticles.isEmpty()) {
                        System.out.println("MOMENTAN, NU SUNT ARTICOLE APROBATE. REVINO ALTA DATA.");
                    } else {
                        System.out.println(listOfApprovedArticles);
                    }
                    break;
                }
                case 2:{
                    List<Article> listOfArticles = forum.getAllArticlesForUser(user.id);
                    if (listOfArticles.isEmpty()) {
                        System.out.println("MOMENTAN, NU AI PUBLICAT ARTICOLE. REVINO ALTA DATA.");
                    } else {
                        System.out.println(listOfArticles);
                    }
                    break;
                }
                case 3:{
                    System.out.println("INTRODU MESAJUL DIN ARTICOL: ");
                    scanner.nextLine();
                    String message = scanner.nextLine();
                    forum.addArticle(new Article(user.getId(), forum.getIdForNewArticle(), message, Status.PENDING));
                    System.out.println("FELICITARI! TOCMAI AI TRANSMIS UN NOU ARTICOL SPRE VERIFICARE");
                    break;
                }
                case 0:{
                    System.out.println("AI ALES SA IESI DIN MENIU.");
                    break;
                }
                default:{
                    System.out.println("OPTIUNE INVALIDA. TE ROG SA INTRODUCI ALTA OPTIUNE.");
                    break;
                }
            }}
    }

    private void printOptionsForAdmin(){
        System.out.println();
        System.out.println("OPTIUNILE SUNT: ");
        System.out.println("1. Vezi toate articolele aprobate");
        System.out.println("2. Vezi toate articolele tale");
        System.out.println("3. Vezi toate articolele neaprobate");
        System.out.println("4. Aproba un articol");
        System.out.println("5. Sterge un articol");
        System.out.println("0. Iesi din meniu");
        System.out.println("\nTE ROG SA INTRODUCI OPTIUNEA TA, NUMERIC: ");
    }
    private void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while(option!=0){
            printOptionsForAdmin();
            option= scanner.nextInt();
            switch(option){
                case 1: {
                    List<Article> listOfApprovedArticles = forum.getAllApprovedArticles();
                    if (listOfApprovedArticles.isEmpty()) {
                        System.out.println("MOMENTAN, NU SUNT ARTICOLE APROBATE. REVINO ALTA DATA.");
                    } else {
                        System.out.println(listOfApprovedArticles);
                    }
                    break;
                }
                case 2:{
                    List<Article> listOfArticles = forum.getAllArticlesForUser(user.id);
                    if (listOfArticles.isEmpty()) {
                        System.out.println("MOMENTAN, NU AI PUBLICAT ARTICOLE. REVINO ALTA DATA.");
                    } else {
                        System.out.println(listOfArticles);
                    }
                    break;
                }
                case 3:{
                    List<Article> listOfArticles = forum.getAllPendingArticles();
                    if (listOfArticles.isEmpty()) {
                        System.out.println("NU EXISTA ARTICOLE NEAPROBATE.");
                    } else {
                        System.out.println(listOfArticles);
                    }
                    break;
                }
                case 4:{
                    System.out.println("TE ROG SA INTRODUCI ID-UL ARTICOLULUI PE CARE DORESTI SA-L APROBI: ");
                    int id = scanner.nextInt();
                    Article article = forum.getArticleWithId(id);
                    article.approveArticle();
                    System.out.println("ARTICOLUL A FOST APROBAT CU SUCCES!");
                    break;
                }
                case 5:{
                    System.out.println("TE ROG SA INTRODUCI ID-UL ARTICOLULUI PE CARE DORESTI SA-L STERGI: ");
                    int id = scanner.nextInt();
                    forum.deleteArticleWithId(id);
                    System.out.println("OPERATIA S-A REALIZAT CU SUCCES!");
                    break;
                }
                case 10:{
                    System.out.println("AI ALES SA IESI DIN MENIU.");
                    break;
                }
                default:{
                    System.out.println("OPTIUNE INVALIDA. TE ROG SA INTRODUCI ALTA OPTIUNE.");
                    break;
                }
            }}
    }

    @Override
    public String toString() {
        return "Menu{" +
                "forum=" + forum +
                ", user=" + user +
                '}';
    }
}
