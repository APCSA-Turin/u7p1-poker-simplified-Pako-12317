package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){ //hint.. use the utility class
        for(String suit : Utility.getSuits()){
            for(String rank : Utility.getRanks()){
                cards.add(new Card(rank, suit));
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public  Card drawCard(){
        if(!isEmpty()){
            Card card = cards.get(0);
            cards.remove(0);
            return new Card(card.getRank(), card.getSuit());
        }
       return null;
    }

    public  boolean isEmpty(){
        return cards.isEmpty();
    }

   


}