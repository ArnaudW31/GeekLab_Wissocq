package com.geeklab.api.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "_user")
public class User {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private Date lastConnection;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Team> teams;

  public User() {}

  public User(String _username, String _password, String _email, Date _lastConnection) {

    this.username = _username;
    this.password = _password;
    this.email = _email;
    this.lastConnection = _lastConnection;
    this.teams = new ArrayList<>();
  }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getLastConnection() {
        return this.lastConnection;
    }

    public List<Team> getTeams() { return teams; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String _username) {
        this.username = _username;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }

    public void setLastConnection(Date _lastConnection) {
        this.lastConnection = _lastConnection;
    }

    public void setTeams(List<Team> teams) { this.teams = teams; }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id) && Objects.equals(this.username, user.username)
            && Objects.equals(this.password, user.password) && Objects.equals(this.email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.password, this.email, this.lastConnection);
    }

    @Override
    public String toString() {
        return "Utilisateur {" + "id=" + this.id + ", name='" + this.username + '\'' + '}';
    }
}
