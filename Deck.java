//
//FIFO (first in first out)
//I.e cards must be drawn from the front and dumped to the back


public class Deck {
    //Attributes

    final private int SIZE;
    
    private Integer[] deckContents;
    private int deckNo;

    
    //Constructors
    //Parameters - running count of decks = deckNo
    public Deck(){
        //Must set deck number
    }

    //Getters
    public int getDeckNo(){
    }
    //Setters
    //Parameters - takes in number to add from
    public void addToDeck(){
        //Check if deck is full

        //Append number passed in to deck
    }
    
    //Methods
    public int pickCard(){
        //Check if deck is empty


        //Pick random number from deckContents
        
        //Loop through deckContents
        //If current item == pick number, remove from deckContents

        //return picked number (return type integer)
    }
}
