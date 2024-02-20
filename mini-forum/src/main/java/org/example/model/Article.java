package org.example.model;

import org.example.enums.Status;

import java.util.Objects;

public class Article {
    Integer idUser;
    Integer idArticle;
    String value;
    Status status;

    public Article() {
    }

    public Article(Integer idUser, Integer idArticle, String value, Status status) {
        this.idUser = idUser;
        this.idArticle = idArticle;
        this.value = value;
        this.status = status;
    }

    public void approveArticle(){
        status=Status.APPROVED;
    }

    public void disapproveArticle() {
        status=Status.PENDING;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(idUser, article.idUser) && Objects.equals(idArticle, article.idArticle) && Objects.equals(value, article.value) && status == article.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idArticle, value, status);
    }

    @Override
    public String toString() {
        return "Article{" +
                "idUser=" + idUser +
                ", idArticle=" + idArticle +
                ", value='" + value + '\'' +
                ", status=" + status +
                '}';
    }
}
