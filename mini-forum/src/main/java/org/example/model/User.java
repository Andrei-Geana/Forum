package org.example.model;

import org.example.enums.Role;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    Integer id;
    String username;
    Role role;

    public User(Integer id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role);
    }
}
