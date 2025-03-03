package com.example.project;

public class Card {
    /**
     * The rank of the card.
     */
    private String rank;
    
    /**
     * The suit of the card.
     */
    private String suit;

    /**
     * Constructs a new Card with the specified rank and suit.
     * 
     * @param rank The rank of the card.
     * @param suit The suit of the card.
     */
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Retrieves the rank of the card.
     * 
     * @return The rank as a string.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Retrieves the suit of the card.
     * 
     * @return The suit as a string.
     */
    public String getSuit() {
        return suit;
    }
    
    /**
     * Returns a string representation of the card, including its rank and suit.
     * 
     * @return A formatted string representing the card.
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
