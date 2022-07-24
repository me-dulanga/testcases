package com.dulanga.endgame.model;

import com.dulanga.endgame.util.ResultType;
import com.dulanga.endgame.util.WinType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Round {
    @Id @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    private Double bet;
    private Double userStartBalance;
    private Double userEndBalance;

    private boolean wonFreeRound = false;

    private WinType winType;

    private ResultType result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBet() {
        return bet;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public Double getUserStartBalance() {
        return userStartBalance;
    }

    public void setUserStartBalance(Double userStartBalance) {
        this.userStartBalance = userStartBalance;
    }

    public Double getUserEndBalance() {
        return userEndBalance;
    }

    public void setUserEndBalance(Double userEndBalance) {
        this.userEndBalance = userEndBalance;
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public boolean isWonFreeRound() {
        return wonFreeRound;
    }

    public void setWonFreeRound(boolean wonFreeRound) {
        this.wonFreeRound = wonFreeRound;
    }

    public WinType getWinType() {
        return winType;
    }

    public void setWinType(WinType winType) {
        this.winType = winType;
    }
}
