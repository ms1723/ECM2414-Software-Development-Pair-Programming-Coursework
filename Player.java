//Must be thread safe
//Player must hold exactly 4 cards

public class Player {
    //Attributes
    //Deck to take is playerNo, deck to dump is playerNo + 1
    //Exception 
    //If playerNo == n, dump to 1

    private int playerNo;
    private Integer[] hand;
    final private int HAND_SIZE;

    
    //Constructors
    //Parameters - running count of players = playerNo
    public Player(){
        //Must set player number
    }

    //Getters
    public int getPlayerNo(){
    }
    //Setters
    //Parameters - takes in number to add from
    public void addToHand(){
        //Check if hand is full

        //Append number passed in to hand

    }
    
    //Methods
    public chooseDiscard(){
        //Check if hand is empty
        //Check if hand has any of playerNo, if so discard random that is not playerNo

        //Never discard playerNo even if repeated numbers that are not playerNo

        //Loop through 4 cards
        //If the card number == playerNo, ignore
        //Check for repeated numbers
        //If player has 2 pairs, randomly pick number to discard
        //If player has trio, discard number that is not in trio
        //If no pairs, no trios, pick random from numbers that are not playerNo

        //Weighting system to pick random number, higher chances for cards held onto for longer
        
        //Loop through hand
        //If current item == discard number, remove from hand

        //Whenever a slot changes, reset time that slot has been held
        //+1 to all times, starts from 0

        //return discard number (return type integer)
    }

    public boolean checkWinCondition(){
        //Check if all 4 cards in hand are the same number

        //If so, return true

        //Else return false
    }
}
