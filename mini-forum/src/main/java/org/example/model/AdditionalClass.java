package org.example.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AdditionalClass {
    public void read(){
        List<User> users;
        users = readUserFromFile();
        if(users != null && users.isEmpty()) {
            System.out.println("EROARE LA CITIRE: USERS");
            return;
        }

        List<Article> articles;
        articles = readArticlesFromFile();
        if(articles != null && articles.isEmpty()){
            System.out.println("EROARE LA CITIRE: ARTICLES");
            return;
        }

        Forum forum = new Forum(users, articles);

        User currentUser = readUserFromConsole(forum);

        if(currentUser==null){
            System.out.println("EROARE LA CITIRE INPUT DIN CONSOLA: INVALID ID/USERNAME");
            return;
        }

        System.out.println("BINE AI VENIT, " +  currentUser.getRole() + ": " + currentUser.getUsername());
        System.out.println();

        Menu menu = new Menu(forum, currentUser);
        menu.showMenu();
        writeArticlesToFile(menu);
    }

    private List<User> readUserFromFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    new File("src/main/resources/users"),
                    new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Article> readArticlesFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    new File("src/main/resources/articles"),
                    new TypeReference<List<Article>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeArticlesToFile(Menu menu){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(menu.getForum().getArticleList());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/articles"))) {
                writer.write(json);
            }
            System.out.println("Datele au fost salvate cu succes!");
        } catch (Exception e) {
            System.out.println("Datele nu au putut fi salvate!");
            e.printStackTrace();
        }
    }

    public User readUserFromConsole(Forum forum){
        User currentUser;
        Scanner scanner = new Scanner(System.in);

        System.out.println("DACA DORESTI SA TE LOGHEZI CU ID, INTRODU 1. \nDACA DORESTI SA TE LOGHEZI CU USERNAME, INTRODU 2");
        int option = scanner.nextInt();
        scanner.nextLine();
        if(option==1){
            System.out.println("VA ROG SA INTRODUCETI ID-UL DUMNEAVOASTRA: ");
            int id = scanner.nextInt();
            currentUser = forum.getUserFromId(id);
        }
        else {
            System.out.println("VA ROG SA INTRODUCETI USERNAME-UL DUMNEAVOASTRA: ");
            String username = scanner.nextLine();
            currentUser = forum.getUserFromUsername(username);
        }
        return currentUser;
    }
}
