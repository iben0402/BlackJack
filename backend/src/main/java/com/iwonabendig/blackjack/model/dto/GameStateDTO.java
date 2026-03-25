package com.iwonabendig.blackjack.model.dto;

import  com.iwonabendig.blackjack.model.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameStateDTO {
    private HandDTO playerHand;
    private HandDTO dealerHand;
    private GameStatus gameStatus;
}
