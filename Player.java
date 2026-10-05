//Must be thread safe
//Player must hold exactly 4 cards

import java.util.ArrayList;

import exceptions.HandEmptyException;

public class Player {
    //Attributes
    //Deck to take is playerNo, deck to dump is playerNo + 1
    //Exception 
    //If playerNo == n, dump to 1

    private int playerNo;
    private ArrayList<Card> hand;
    private Integer[] weighting;
    final private int HAND_SIZE = 4;

    
    //Constructors
    //Parameters - running count of players = playerNo
    public Player(int playerNo){
        //Must set player number and initializes the hand

        //Player number must be > 0
        this.playerNo = playerNo;

        this.hand = new ArrayList<>();
        this.weighting = new ArrayList<>();
    }

    //Getters
    public int getPlayerNo(){
        //Exception: Check player exists
        return this.playerNo;
    }
    //Setters
    //Parameters - takes in number to add from
    public void addToHand(Card cardToAdd) throws Exception{
        //Check if hand is full
        //Exeption is hand is full
        if (this.hand.size() == HAND_SIZE) {
            throw new Exception("Player " + playerNo + "'s hand is full");
        }

        //Append number passed in to hand
        this.hand.add(cardToAdd);

        //Reset the weighting of that slot
        this.weighting[this.hand.indexOf(cardToAdd)] = 0;
    }
    
    //Methods
    public Card chooseDiscard(int n) throws HandEmptyException, NullPointerException{
        //Local variable initialisation
        Card cardToDiscard = null;
        ArrayList<Card> discardList = this.hand;
        Integer[] occurrences = new Integer[n];
        ArrayList<Integer> pairs = new ArrayList<>();
        int trio = 0;
        //Exception: (interrupt maybe??) Check if hand is empty
        //Check if hand has any of playerNo, if so discard random that is not playerNo
        if (this.hand.isEmpty()){
            throw new HandEmptyException("Hand is empty");
        }

        //Never discard playerNo even if repeated numbers that are not playerNo

        //Loop through 4 cards
        //If the card number == playerNo, ignore
        for (Card card : this.hand){
            if (card.getCardValue() != this.playerNo){
                discardList.add(card);
            }
        }

        //Check for repeated numbers
        for (Card card : discardList){
            occurrences[card.getCardValue()]++;
        }
        //If player has trio, discard number that is not in trio
        //If no pairs, no trios, pick random from numbers that are not playerNo
        for (int i = 0; i < occurrences.length; i++) {
            if (occurrences[i] == 3){
                trio = i;
            }
            else if (occurrences[i] == 2){
                pairs.add(i);
            }
        }
        
        int maxWeight = 0;
        int discardIndex = 0;
        //Weighting system to pick random number, higher chances for cards held onto for longer
        if (discardList.size() == 4){
            for (Card card : discardList){
                if (trio != 0){
                    if (card.getCardValue() != trio){
                        cardToDiscard = card;
                    }
                }
                //If two pairs, pick the card that has been in the hand the longest
                if (pairs.size() == 2){
                    //Check through weightings and log which card has been in the hand the longest
                    for (int i = 0; i < n; i++) {
                        if (weighting[i] > maxWeight){
                            maxWeight = weighting[i];
                            discardIndex = i;
                        }
                    }
                }
            }
        }        

        //Loop through hand
        //If current item == discard number, remove from hand

        //discardList should always hold the correct card to discard
        //Holds regardless of the structure of the hand
        cardToDiscard = discardList.get(discardtIndex)
        //Whenever a slot changes, reset time that slot has been held
        //+1 to all times, starts from 0
        for (int weight : this.weighting){
            weight++;
        }

        //return card to discard (returns a card)
        if (cardToDiscard == null){
            throw new NullPointerException("No card was found to discard");
        }
        return cardToDiscard;
    }

    public boolean checkWinCondition(){
        //Check if all 4 cards in hand are the same number#
        int currentCardNumber = this.hand.get(0).getCardValue();
        for (Card card : this.hand){
            //If there is any difference in cardValue then the hand does not win
            if (card.getCardValue() != currentCardNumber){
                return false;
            }
        }
        //All cards have been checked as the same number, therefore this hand wins
        return true;
    }
}
