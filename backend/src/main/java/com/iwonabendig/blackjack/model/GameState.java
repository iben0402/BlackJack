package com.iwonabendig.blackjack.model;

import com.iwonabendig.blackjack.model.enums.GameStatus;
import lombok.Getter;
import lombok.Setter;

public class GameState {
    @Getter
    private Hand playerHand;
    @Getter
    private Hand dealerHand;
    private Deck deck;
    @Getter
    @Setter
    private GameStatus gameStatus;

    public GameState(Deck deck) {
        playerHand = new Hand();
        dealerHand = new Hand();
        this.deck = deck;
        gameStatus = GameStatus.PLAYING;
    }

    public Card dealCard() {
        return deck.dealCard();
    }
}
