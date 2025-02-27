package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.example.project.Deck;


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
        
    }

    public String playHand(ArrayList<Card> communityCards){      
        return "Nothing";
    }

    public void SortCards(){
        ArrayList<Card> c = new ArrayList<Card>();
        c.addAll(allCards);
        c.addAll(hand);
        Collections.sort(c, null);;
    } 

    public ArrayList<Integer> findRankingFrequency(){
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){
        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
