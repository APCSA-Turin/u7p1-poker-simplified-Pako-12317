package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    /**
     * Represents a deck of playing cards.
     */
    private ArrayList<Card> cards;

    /**
     * Constructs a new deck, initializes it with 52 cards, and shuffles it.
     */
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    /**
     * Retrieves the current deck of cards.
     * 
     * @return An ArrayList of Card objects representing the deck.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Initializes the deck by generating all possible card combinations of ranks and suits.
     */
    public void initializeDeck() {
        for (String suit : Utility.getSuits()) {
            for (String rank : Utility.getRanks()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Shuffles the deck randomly to ensure fair play.
     */
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    /**
     * Draws the top card from the deck and removes it.
     * 
     * @return The drawn Card object or null if the deck is empty.
     */
    public Card drawCard() {
        if (!isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    /**
     * Checks if the deck is empty.
     * 
     * @return True if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
