package org.example.model;

import org.example.enums.Status;

import javax.lang.model.type.ErrorType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Forum {
    List<User> userList = new ArrayList<>();
    List<Article> articleList = new ArrayList<>();

    public Forum() {
        /*EMPTY*/
    }

    public Forum(List<User> userList, List<Article> articleList) {
        this.userList = userList;
        this.articleList = filterAllArticles(articleList);
    }

    private List<Article> filterAllArticles(List<Article> articleList){
        List<Article> checkedArticleList = new ArrayList<>();
        for(Article article : articleList){
            int userIdFromArticle = article.getIdUser();
            userList.stream().filter(user1 -> {
                return user1.getId().equals(userIdFromArticle);
            }).findFirst().ifPresent(user -> checkedArticleList.add(article));
        }
        return checkedArticleList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public User getUserFromUsername(String username){
        return userList.stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst().orElse(null);
    }

    public List<Article> getAllPendingArticles() {
        return articleList.stream()
                .filter(article -> article.status.equals(Status.PENDING)).toList();
    }

    public Article getArticleWithId(int id){
        return articleList.stream()
                .filter(article -> article.getIdArticle().equals(id)).findFirst().orElse(null);
    }


    public User getUserFromId(int id){
        return userList.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Article> getAllApprovedArticles(){
        return articleList.stream()
                .filter(article -> article.status.equals(Status.APPROVED)).toList();
    }

    public List<Article> getAllArticlesForUser(int id){
        return articleList.stream()
                .filter(article -> article.getIdUser().equals(id)).toList();
    }

    public Integer getIdForNewArticle(){
        return articleList.stream()
                .max(Comparator.comparingInt(Article::getIdArticle))
                .map(article -> article.getIdArticle() + 1)
                .orElse(0);
    }

    public void deleteArticleWithId(Integer id){
        articleList.stream()
                .filter(article -> article.getIdArticle().equals(id))
                .findFirst().ifPresent(articleToBeDeleted -> articleList.remove(articleToBeDeleted));
    }

    public void addArticle(Article article){
        this.articleList.add(article);
    }

    @Override
    public String toString() {
        return "Forum{" +
                "userList=" + userList +
                ", articleList=" + articleList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forum forum = (Forum) o;
        return Objects.equals(userList, forum.userList) && Objects.equals(articleList, forum.articleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userList, articleList);
    }
}
