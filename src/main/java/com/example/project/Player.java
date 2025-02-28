package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      
        return "Nothing";
    }

    public void sortAllCards(){
        allCards.sort(null);
    }

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<String> rank = new ArrayList<String>();
        ArrayList<Integer> freq = new ArrayList<Integer>();
        rank.addAll(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));
        for (int i = 0; i < rank.size(); i++) {
            freq.add(i, Collections.frequency(allCards, rank.get(i)));
        }
        return freq; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<String> suit = new ArrayList<String>();
        ArrayList<Integer> freq = new ArrayList<Integer>();
        suit.addAll(Arrays.asList("♠", "♥", "♣", "♦"));
        for (int i = 0; i < suit.size(); i++) {
            freq.add(i, Collections.frequency(allCards, suit.get(i)));
        }
        return freq;  
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
