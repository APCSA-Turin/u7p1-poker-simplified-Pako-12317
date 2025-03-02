package com.example.project;
import java.util.ArrayList;

public class Game {
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

    public static void play() {
        Deck deck = new Deck();
        Player p1 = new Player();
        Player p2 = new Player();
        ArrayList<Card> communityCards = new ArrayList<>();
        
        for (int i = 0; i < 2; i++) {
            p1.addCard(deck.drawCard());
            p2.addCard(deck.drawCard());
        }
        
        for (int i = 0; i < 5; i++) {
            communityCards.add(deck.drawCard());
        }
        
        String p1Hand = p1.playHand(communityCards);
        String p2Hand = p2.playHand(communityCards);
        
        System.out.println(determineWinner(p1, p2, p1Hand, p2Hand, communityCards));
    }
}
