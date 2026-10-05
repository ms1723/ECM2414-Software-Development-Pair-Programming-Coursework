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
    public Card(int cardID, int value, String suit){
        //Must set cardid, cardvalue and cardsuit
        //Can set basevalue and base suit Null
        this.cardID = cardID;

        //Must be greater than 0, raise exception
        this.cardValue = value;

        //Must be a valid suit
        this.cardSuit = suit;
    }

    //Getters
    public int getCardID(){
        //return cardID
        return this.cardID;
    }

    public int getCardValue(){
        return this.cardValue;
    }
    
    public String getCardSuit(){
        return this.cardSuit;
    }

    //Setters
    public void setCardValue(int newValue){
        //Must be greater than 0
        this.cardValue = newValue;
    }

    public void setCardSuit(String newSuit){
        //Must be a valid suit
        this.cardSuit = newSuit;
    }

    //Other Methods

    
}