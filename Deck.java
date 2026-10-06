//
//FIFO (first in first out)
//I.e cards must be drawn from the front and dumped to the back

import java.util.ArrayList;

public class Deck {
    //Attributes
    
    //Deck contents must contain cards and be a variable size (does not have an upper limit for its size)
    private ArrayList<Card> deckContents;
    private int deckNo;

    
    //Constructors
    //Parameters - running count of decks = deckNo
    public Deck(int deckNo){
        //Must set deck number and initialized contents
        this.deckNo = deckNo;
        this.deckContents = new ArrayList<>();

    }

    //Getters
    public int getDeckNo(){
        //Exception: Deck must have a number
        return this.deckNo;
    }
    //Setters
    //Parameters - takes in a new card to add to the pile
    public void addToDeck(Card cardToAdd){

        //Append number passed in to deck
        //add() automatically appends to the back
        this.deckContents.add(cardToAdd);
    }
    
    //Methods
    public Card pickCard() throws NullPointerException{
        Card cardToPick = null;
        //Check if deck is empty
        if (!this.deckContents.isEmpty()){
            //Store card in local variable
            cardToPick = this.deckContents.get(0);
        }

        
        //Must take item at front of deckContents (decks work with FIFO)
        
        //Loop through deckContents
        //If current item == pick number, remove from deckContents
        this.deckContents.remove(0);
        //return picked number (return type Card)
        if (cardToPick == null){
            throw new NullPointerException("No card was picked");
        }
        return cardToPick;
    }

    // May not need but it's here
    public void sortDeck(){
        // The deck is a queue
    }
}
