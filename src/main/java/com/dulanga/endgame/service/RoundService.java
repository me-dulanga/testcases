package com.dulanga.endgame.service;

import com.dulanga.endgame.model.Round;
import com.dulanga.endgame.model.User;
import com.dulanga.endgame.repository.RoundRepository;
import com.dulanga.endgame.repository.UserRepository;
import com.dulanga.endgame.util.ResultType;
import com.dulanga.endgame.util.WinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class RoundService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private RandomNumberService randomNumberService;

    public Round play(String username, double bet){
        Optional<User> userOp = userRepository.findByUsername(username);
        Round round = null;
        if(userOp.isPresent()){
            User user = userOp.get();
            round = new Round();
            round.setUser(user);
            round.setBet(bet);
            round.setUserStartBalance(user.getBalance());

            startPlay(round);

            if(round.getResult() == ResultType.Win){
                double wining = 0;
                switch (round.getWinType()){
                    case SMALL -> wining = 3 * bet;
                    case MEDIUM -> wining = 5 * bet;
                    case LARGE -> wining = 10 * bet;
                    default -> wining = bet;
                }
                user.setBalance(user.getBalance() + wining);
            } else if (round.getResult() == ResultType.Loose){
                if(!user.isHasFreeRound()){
                    user.setBalance(user.getBalance() - bet);
                }
            }
            user.setHasFreeRound(round.isWonFreeRound());
            round.setUserEndBalance(user.getBalance());
            userRepository.save(user);
            roundRepository.save(round);
        }
        return round;
    }

    private void startPlay(Round round){
        int resultValue = randomNumberService.getNextValue();

        if(resultValue < 30){ //30% win chance
            round.setResult(ResultType.Win);
            if(resultValue > 10){
                round.setWinType(WinType.SMALL);
            } else if( resultValue > 2) {
                round.setWinType(WinType.MEDIUM);
            } else {
                round.setWinType(WinType.LARGE);
            }
        } else {
            round.setResult(ResultType.Loose);
            round.setWinType(WinType.NA);
        }

        round.setWonFreeRound(resultValue % 10 == 0); //10% free round win
    }
}
