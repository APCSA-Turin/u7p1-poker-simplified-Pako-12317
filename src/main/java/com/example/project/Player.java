package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards;
    private String[] suits = Utility.getSuits();
    private String[] ranks = Utility.getRanks();

    public Player() {
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand() { return hand; }
    public ArrayList<Card> getAllCards() { return allCards; }

    public void addCard(Card c) {
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards) {
        allCards.clear();
        allCards.addAll(hand);
        allCards.addAll(communityCards);
        sortAllCards();

        if (isRoyalFlush()) return "Royal Flush";
        if (isStraightFlush()) return "Straight Flush";
        if (isFourOfAKind()) return "Four of a Kind";
        if (isFullHouse()) return "Full House";
        if (isFlush()) return "Flush";
        if (isStraight()) return "Straight";
        if (isThreeOfAKind()) return "Three of a Kind";
        if (isTwoPair()) return "Two Pair";
        if (isPair()) return "A Pair";
        
        Card highestCard = allCards.get(allCards.size() - 1);
        for (Card card : hand) {
            if (card.getRank().equals(highestCard.getRank())) {
                return "High Card";
            }
        }
        return "Nothing";
    }

    public void sortAllCards() {
        allCards.sort((c1, c2) -> Integer.compare(Utility.getRankValue(c1.getRank()), Utility.getRankValue(c2.getRank())));
    }

    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> freq = new ArrayList<>(Collections.nCopies(13, 0));
        for (Card card : allCards) {
            int index = Arrays.asList(ranks).indexOf(card.getRank());
            freq.set(index, freq.get(index) + 1);
        }
        return freq;
    }

    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> freq = new ArrayList<>(Collections.nCopies(4, 0));
        for (Card card : allCards) {
            int index = Arrays.asList(suits).indexOf(card.getSuit());
            freq.set(index, freq.get(index) + 1);
        }
        return freq;
    }

    private boolean isFlush() {
        return findSuitFrequency().contains(5);
    }

    private boolean isStraight() {
        ArrayList<Integer> freq = findRankingFrequency();
        int count = 0;
        for (int f : freq) {
            if (f > 0) {
                count++;
                if (count == 5) return true;
            } else {
                count = 0;
            }
        }
        return false;
    }

    private boolean isRoyalFlush() {
        return isFlush() && isStraight() && allCards.get(allCards.size() - 1).getRank().equals("A");
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    private boolean isFourOfAKind() {
        return findRankingFrequency().contains(4);
    }

    private boolean isFullHouse() {
        ArrayList<Integer> freq = findRankingFrequency();
        return Collections.frequency(freq, 3) == 1 && Collections.frequency(freq, 2) == 1;
    }

    private boolean isThreeOfAKind() {
        return findRankingFrequency().contains(3);
    }

    private boolean isTwoPair() {
        return findRankingFrequency().stream().filter(f -> f == 2).count() == 2;
    }

    private boolean isPair() {
        return findRankingFrequency().contains(2);
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
