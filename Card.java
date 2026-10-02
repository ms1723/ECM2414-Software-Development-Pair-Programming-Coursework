//Must be thread-safe
//Only here to check off a box, it'll be used when possible
//Card object is an active card and is either in a deck of in a players hand, 
//so it's not an object until it is in one of those positions

public class Card{
    //Attributes
    private int cardID;
    private int cardValue;
    private String cardSuit;
    //Constructors
    public Card(){
        //Must set cardid, cardvalue and cardsuit
        //Can set basevalue and base suit Null
    }

    //Getters
    public int getCardID(){
        //return cardID
    }

    public int getCardValue(){

    }
    
    public String getCardSuit(){

    }

    //Setters
    public void setCardValue(){

    }

    public void setCardSuit(){
        
    }

    //Other Methods

    
}