package com.iwonabendig.blackjack.mapper;

import com.iwonabendig.blackjack.model.Card;
import com.iwonabendig.blackjack.model.GameState;
import com.iwonabendig.blackjack.model.Hand;
import com.iwonabendig.blackjack.model.dto.GameStateDTO;
import com.iwonabendig.blackjack.model.dto.HandDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameMapper {
    public GameStateDTO toDTO(GameState gameState, boolean hideDealerCard) {
        // map player hand to HandDTO
        HandDTO playerHandDTO = mapHandToDTO(gameState.getPlayerHand(), false);

        // map dealer hand to HandDTO
        HandDTO dealerHandDTO = mapHandToDTO(gameState.getDealerHand(), hideDealerCard);

        return new GameStateDTO(playerHandDTO, dealerHandDTO, gameState.getGameStatus());
    }

    public HandDTO mapHandToDTO(Hand hand, boolean hideDealerCard) {
        List<String> cards = new ArrayList<>();
        int total;

        if(hideDealerCard) {
            cards.add(hand.getCards().getFirst().toString());
            cards.add("HIDDEN");
            total = hand.getCards().getFirst().getBlackjackValue();
        } else {
            total = hand.calculateTotal();
            for (Card card : hand.getCards()) {
                cards.add(card.toString());
            }
        }

        return new HandDTO(cards, total);
    }
}
