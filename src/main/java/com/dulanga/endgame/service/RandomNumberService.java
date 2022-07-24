package com.dulanga.endgame.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomNumberService {
    private Random r = new Random();

    public int getNextValue(){
        return r.nextInt(100);
    }
}
