package com.example.project;
import java.util.ArrayList;

public class Game {
    /**
     * Determines the winner of the game based on hand rankings.
     * If both players have the same hand rank, it falls back to high-card comparisons.
     * 
     * @param p1 Player 1
     * @param p2 Player 2
     * @param p1Hand Player 1's evaluated hand ranking
     * @param p2Hand Player 2's evaluated hand ranking
     * @param communityCards The shared community cards
     * @return A string indicating the winner or if it's a tie
     */
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand, ArrayList<Card> communityCards) {
        int p1Rank = Utility.getHandRanking(p1Hand);
        int p2Rank = Utility.getHandRanking(p2Hand);
        
        if (p1Rank > p2Rank) {
            return "Player 1 wins!";
        } else if (p2Rank > p1Rank) {
            return "Player 2 wins!";
        } else {
            return compareHighCards(p1, p2);
        }
    }

    /**
     * Compares the highest-ranking cards between two players to break ties.
     * Sorts each player's hand in descending order and compares the highest card first.
     * 
     * @param p1 Player 1
     * @param p2 Player 2
     * @return A string indicating the winner or if it's a tie
     */
    private static String compareHighCards(Player p1, Player p2) {
        ArrayList<Card> p1Cards = new ArrayList<>(p1.getHand());
        ArrayList<Card> p2Cards = new ArrayList<>(p2.getHand());

        p1Cards.sort((c1, c2) -> Integer.compare(Utility.getRankValue(c2.getRank()), Utility.getRankValue(c1.getRank())));
        p2Cards.sort((c1, c2) -> Integer.compare(Utility.getRankValue(c2.getRank()), Utility.getRankValue(c1.getRank())));

        for (int i = 0; i < Math.min(p1Cards.size(), p2Cards.size()); i++) {
            int p1Value = Utility.getRankValue(p1Cards.get(i).getRank());
            int p2Value = Utility.getRankValue(p2Cards.get(i).getRank());
            
            if (p1Value > p2Value) return "Player 1 wins!";
            if (p2Value > p1Value) return "Player 2 wins!";
        }
        return "Tie!";
    }

    /**
     * Simulates a round of poker, dealing hands to two players and determining the winner.
     * Creates a deck, deals two hole cards to each player, and five community cards.
     * Evaluates hands and prints the winner.
     */
    public static void play() {
        Deck deck = new Deck();
        Player p1 = new Player();
        Player p2 = new Player();
        ArrayList<Card> communityCards = new ArrayList<>();
        
        // Deal two hole cards to each player
        for (int i = 0; i < 2; i++) {
            p1.addCard(deck.drawCard());
            p2.addCard(deck.drawCard());
        }
        
        // Deal five community cards
        for (int i = 0; i < 5; i++) {
            communityCards.add(deck.drawCard());
        }
        
        // Determine the best hand for each player
        String p1Hand = p1.playHand(communityCards);
        String p2Hand = p2.playHand(communityCards);
        
        // Print the winner
        System.out.println(determineWinner(p1, p2, p1Hand, p2Hand, communityCards));
    }
}
