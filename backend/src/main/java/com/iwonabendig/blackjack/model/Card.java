package com.iwonabendig.blackjack.model;

import com.iwonabendig.blackjack.model.enums.Rank;
import com.iwonabendig.blackjack.model.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Card {
    private Suit suit;
    private Rank rank;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getSuit() == card.getSuit() && getRank() == card.getRank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSuit(), getRank());
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public int getBlackjackValue() {
        return rank.getValue();
    }
}
