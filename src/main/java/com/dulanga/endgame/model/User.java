package com.dulanga.endgame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_tbl")
public class User {
    @Id @GeneratedValue
    private Long id;

    private String username;
    @JsonIgnore
    private String password;
    private Double balance;
    private boolean hasFreeRound = false;

    @JsonIgnore
    @ManyToMany
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Round> rounds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isHasFreeRound() {
        return hasFreeRound;
    }

    public void setHasFreeRound(boolean hasFreeRound) {
        this.hasFreeRound = hasFreeRound;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
