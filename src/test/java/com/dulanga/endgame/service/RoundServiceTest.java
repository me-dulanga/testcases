package com.dulanga.endgame.service;

import com.dulanga.endgame.model.Round;
import com.dulanga.endgame.model.User;
import com.dulanga.endgame.repository.RoundRepository;
import com.dulanga.endgame.repository.UserRepository;
import com.dulanga.endgame.util.ResultType;
import com.dulanga.endgame.util.WinType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RoundServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoundRepository roundRepository;

    @Mock
    private RandomNumberService randomNumberService;

    @InjectMocks
    private RoundService roundService;

    public void getUser(String username, double balance){

//        userRepository = Mockito.mock(UserRepository.class);
        User user = new User();
        user.setUsername(username);
        user.setBalance(balance);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
    }

    @Test
    public void winTest(){
        String username = "user";
        double balance = 5000.0;
        double bet = 10.0;

        getUser(username, balance);

        Mockito.when(randomNumberService.getNextValue()).thenReturn(9);

        Round round = roundService.play(username, bet);

        assertEquals(ResultType.Win, round.getResult());
        assertEquals(WinType.MEDIUM, round.getWinType());
        assertEquals(5000.0, round.getUserStartBalance());
        assertEquals(5050.0, round.getUserEndBalance());
    }

    @Test
    public void looseTest(){
        String username = "user";
        double balance = 5000.0;
        double bet = 10.0;

        getUser(username, balance);

        Mockito.when(randomNumberService.getNextValue()).thenReturn(52);

        Round round = roundService.play(username, bet);

        assertEquals(ResultType.Loose, round.getResult());
        assertEquals(WinType.NA, round.getWinType());
        assertEquals(5000.0, round.getUserStartBalance());
        assertEquals(4990.0, round.getUserEndBalance());
    }

    @Test
    public void freeRoundWithWinTest(){
        String username = "user";
        double balance = 5000.0;
        double bet = 10.0;

        getUser(username, balance);

        Mockito.when(randomNumberService.getNextValue()).thenReturn(20);

        Round round = roundService.play(username, bet);

        assertEquals(ResultType.Win, round.getResult());
        assertEquals(true, round.isWonFreeRound());
    }

    @Test
    public void freeRoundWithLooseTest(){
        String username = "user";
        double balance = 5000.0;
        double bet = 10.0;

        getUser(username, balance);

        Mockito.when(randomNumberService.getNextValue()).thenReturn(50);

        Round round = roundService.play(username, bet);

        assertEquals(ResultType.Loose, round.getResult());
        assertEquals(true, round.isWonFreeRound());
    }
}