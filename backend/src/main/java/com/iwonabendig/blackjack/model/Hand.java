package com.iwonabendig.blackjack.model;

import com.iwonabendig.blackjack.model.enums.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private static final int ACE_VALUE_DIFFERENCE = 10;

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateTotal() {
        int totalValue = 0;
        int acesCount = 0;
        for(Card card : cards) {
            totalValue += card.getBlackjackValue();
            if(card.getRank().equals(Rank.ACE)) acesCount++;
        }

        for (int i = 0; i < acesCount; i++) {
            if (totalValue <= 21) break;
            totalValue -= ACE_VALUE_DIFFERENCE;
        }

        return totalValue;
    }

    public boolean isBust() {
        return calculateTotal() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculateTotal() == 21;
    }
}
