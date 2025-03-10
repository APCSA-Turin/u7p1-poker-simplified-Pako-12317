package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Player {
    /**
     * Represents the player's personal hand of cards.
     */
    private ArrayList<Card> hand;
    
    /**
     * Stores both the player's hand and community cards for evaluation.
     */
    private ArrayList<Card> allCards;
    
    private String[] suits = Utility.getSuits();
    private String[] ranks = Utility.getRanks();

    /**
     * Initializes a new player with an empty hand.
     */
    public Player() {
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand() { return hand; }
    public ArrayList<Card> getAllCards() { return allCards; }

    /**
     * Adds a card to the player's hand.
     * 
     * @param c The card to be added
     */
    public void addCard(Card c) {
        hand.add(c);
    }

    /**
     * Determines the best poker hand the player can make using their hand and community cards.
     * 
     * @param communityCards The shared community cards
     * @return A string representing the highest-ranked poker hand
     */
    public String playHand(ArrayList<Card> communityCards) {
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        sortAllCards();

        if (isRoyalFlush()) {
            return "Royal Flush";
        }
        if (isStraightFlush()) {
            return "Straight Flush";
        }
        if (isFourOfAKind()) {
            return "Four of a Kind";
        }
        if (isFullHouse()) {
            return "Full House";
        }
        if (isFlush()) {
            return "Flush";
        }
        if (isStraight()) {
            return "Straight";
        }
        if (isThreeOfAKind()) {
            return "Three of a Kind";
        }
        if (isTwoPair()) {
            return "Two Pair";
        }
        if (isPair()) {
            return "A Pair";
        }
        
        Card highestCard = allCards.get(allCards.size() - 1);
        for (Card card : hand) {
            if (card.getRank().equals(highestCard.getRank())) {
                return "High Card";
            }
        }
        return "Nothing";
    }

    /**
     * Sorts all the player's cards in ascending order by rank.
     */
    public void sortAllCards() {
        for (int i = 0; i < allCards.size() - 1; i++) {
            for (int j = 0; j < allCards.size() - i - 1; j++) {
                int rankValue1 = Utility.getRankValue(allCards.get(j).getRank());
                int rankValue2 = Utility.getRankValue(allCards.get(j + 1).getRank());
                if (rankValue1 > rankValue2) {
                    Card temp = allCards.get(j);
                    allCards.set(j, allCards.get(j + 1));
                    allCards.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Finds the frequency of each rank in the player's combined hand.
     * 
     * @return A list of integers representing the frequency of each rank
     */
    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> freq = new ArrayList<>(Collections.nCopies(13, 0));
        for (Card card : allCards) {
            int index = Arrays.asList(ranks).indexOf(card.getRank());
            freq.set(index, freq.get(index) + 1);
        }
        return freq;
    }

    /**
     * Finds the frequency of each suit in the player's combined hand.
     * 
     * @return A list of integers representing the frequency of each suit
     */
    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> freq = new ArrayList<>(Collections.nCopies(4, 0));
        for (Card card : allCards) {
            int index = Arrays.asList(suits).indexOf(card.getSuit());
            freq.set(index, freq.get(index) + 1);
        }
        return freq;
    }

    /**
     * Checks if the player's hand contains a flush (five cards of the same suit).
     */
    private boolean isFlush() {
        ArrayList<Integer> freq = findSuitFrequency();
        for (int f : freq) {
            if (f == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player's hand contains a straight (five consecutive ranks).
     */
    private boolean isStraight() {
        ArrayList<Integer> freq = findRankingFrequency();
        int count = 0;
        for (int f : freq) {
            if (f > 0) {
                count++;
                if (count == 5) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    /**
     * Checks if the player's hand contains a Royal Flush (A, K, Q, J, 10 of the same suit).
     */
    private boolean isRoyalFlush() {
        return isFlush() && isStraight() && allCards.get(allCards.size() - 1).getRank().equals("A");
    }

    /**
     * Checks if the player's hand contains a Straight Flush.
     */
    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    /**
     * Checks if the player's hand contains Four of a Kind.
     */
    private boolean isFourOfAKind() {
        ArrayList<Integer> freq = findRankingFrequency();
        for (int f : freq) {
            if (f == 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player's hand contains a Full House.
     */
    private boolean isFullHouse() {
        ArrayList<Integer> freq = findRankingFrequency();
        boolean three = false;
        boolean two = false;
        for (int f : freq) {
            if (f == 3) {
                three = true;
            } else if (f == 2) {
                two = true;
            }
        }
        return three && two;
    }

    /**
     * Checks if the player's hand contains Three of a Kind.
     */
    private boolean isThreeOfAKind() {
        ArrayList<Integer> freq = findRankingFrequency();
        for (int f : freq) {
            if (f == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player's hand contains Two Pair.
     */
    private boolean isTwoPair() {
        ArrayList<Integer> freq = findRankingFrequency();
        int count = 0;
        for (int f : freq) {
            if (f == 2) {
                count++;
            }
        }
        if (count == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the player's hand contains a single pair.
     */
    private boolean isPair() {
        ArrayList<Integer> freq = findRankingFrequency();
        for (int f : freq) {
            if (f == 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
