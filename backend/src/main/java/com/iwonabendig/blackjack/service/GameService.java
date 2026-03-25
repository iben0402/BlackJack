package com.iwonabendig.blackjack.service;

import com.iwonabendig.blackjack.exception.GameAlreadyOverException;
import com.iwonabendig.blackjack.exception.GameNotInitializedException;
import com.iwonabendig.blackjack.model.Deck;
import com.iwonabendig.blackjack.model.GameState;
import com.iwonabendig.blackjack.model.enums.GameStatus;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameState currentGame;

    public GameState deal() {
        Deck deck = new Deck();
        deck.shuffle();
        currentGame = new GameState(deck);

        // deal 2 cards to player and 2 cards to dealer
        currentGame.getPlayerHand().addCard(currentGame.dealCard());
        currentGame.getDealerHand().addCard(currentGame.dealCard());

        currentGame.getPlayerHand().addCard(currentGame.dealCard());
        currentGame.getDealerHand().addCard(currentGame.dealCard());

        // check if player has black jack
        if (currentGame.getDealerHand().isBlackjack() && currentGame.getPlayerHand().isBlackjack()) {
            currentGame.setGameStatus(GameStatus.PUSH);
        } else if (currentGame.getPlayerHand().isBlackjack()) {
            currentGame.setGameStatus(GameStatus.PLAYER_WIN);
        } else if (currentGame.getDealerHand().isBlackjack()) {
            currentGame.setGameStatus(GameStatus.DEALER_WIN);
        }

        return currentGame;
    }

    public GameState hit() {
        if (currentGame == null) {
            throw new GameNotInitializedException("Game hasn't been initialized");
        }
        if (currentGame.getGameStatus() != GameStatus.PLAYING) {
            throw new GameAlreadyOverException("Can't hit when game is over");
        }

        currentGame.getPlayerHand().addCard(currentGame.dealCard());
        if (currentGame.getPlayerHand().isBust()) {
            currentGame.setGameStatus(GameStatus.DEALER_WIN);
        }

        return currentGame;
    }

    public GameState stand() {
        if (currentGame == null) {
            throw new GameNotInitializedException("Game hasn't been initialized");
        }
        if (currentGame.getGameStatus() != GameStatus.PLAYING) {
            throw new GameAlreadyOverException("Can't stand when game is over");
        }

        while (currentGame.getDealerHand().calculateTotal() < 17) {
            currentGame.getDealerHand().addCard(currentGame.dealCard());
        }

        int playerTotal = currentGame.getPlayerHand().calculateTotal();
        int dealerTotal = currentGame.getDealerHand().calculateTotal();

        if (currentGame.getDealerHand().isBust()) {
            // dealer busts -> player wins
            currentGame.setGameStatus(GameStatus.PLAYER_WIN);
        } else if (playerTotal > dealerTotal) {
            // player total > dealer total -> player wins
            currentGame.setGameStatus(GameStatus.PLAYER_WIN);
        } else if (playerTotal < dealerTotal) {
            // player total < dealer total -> dealer wins
            currentGame.setGameStatus(GameStatus.DEALER_WIN);
        } else {
            // player total = dealer total -> push (tie)
            currentGame.setGameStatus(GameStatus.PUSH);
        }
        return currentGame;
    }
}
