//
//FIFO (first in first out)
//I.e cards must be drawn from the front and dumped to the back


public class Deck {
    //Attributes
    
    // Change to list as not gonna be fixed size
    private Integer[] deckContents;
    private int deckNo;

    
    //Constructors
    //Parameters - running count of decks = deckNo
    public Deck(){
        //Must set deck number and initialized contents
    }

    //Getters
    public int getDeckNo(){
        //Exception: Deck must have a number
    }
    //Setters
    //Parameters - takes in number to add from
    public void addToDeck(){

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

    // May not need but it's here
    public void sortDeck(){
        // The deck is a queue
    }
}
