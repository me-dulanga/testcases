package com.dulanga.endgame.controller;

import com.dulanga.endgame.model.Round;
import com.dulanga.endgame.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class RoundController {

    @Autowired
    private RoundService roundService;

    @GetMapping("/play")
    public Round playGame(Principal principal, @RequestParam Double bet){
        return roundService.play(principal.getName(), bet);
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello World";
    }
}
