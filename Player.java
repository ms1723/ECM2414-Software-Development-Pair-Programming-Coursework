//Must be thread safe
//Player must hold exactly 4 cards

import exceptions.HandEmptyException;
import java.util.ArrayList;

public class Player {
    //Attributes
    //Deck to take is playerNo, deck to dump is playerNo + 1
    //Exception 
    //If playerNo == n, dump to 1

    private int playerNo;
    private ArrayList<Card> hand;
    private ArrayList<Integer> weighting;
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

    public String getHand(){
        return (
            hand.get(0).getCardValue() + " " + 
            hand.get(1).getCardValue() + " " + 
            hand.get(2).getCardValue() + " " + 
            hand.get(3).getCardValue()
        );
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
        this.weighting.add(0);
    }
    
    //Methods
    public Card chooseDiscard(int n) throws HandEmptyException, NullPointerException{
        //Local variable initialisation
        Card cardToDiscard = null;
        ArrayList<Card> discardList = new ArrayList<>();
        Integer[] occurrences = new Integer[n+1];
        ArrayList<Integer> pairs = new ArrayList<>();
        int trio = 0;
        //Exception: (interrupt maybe??) Check if hand is empty
        //Check if hand has any of playerNo, if so discard random that is not playerNo
        if (this.hand.isEmpty()){
            throw new HandEmptyException("Hand is empty");
        }


        //Initialise occurrences
        for(int i = 1; i <= n; i++){
            occurrences[i] = 0;
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
        for (int i = 1; i < occurrences.length; i++) {
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
        //If there is a trio, discardList.size() must be > 3
        if (trio != 0){
            if (discardList.size() == 4){
                for (int i = 0; i < discardList.size(); i++){
                    if (discardList.get(i).getCardValue() != trio){
                        discardIndex = i;
                    }
                }
            //Case where one of the trio of items must be considered for discard
            } else {
                for (int j = 0; j < weighting.size(); j++) {
                    if (weighting.get(j) > maxWeight){
                        maxWeight = weighting.get(j);
                        discardIndex = j;
                    }
                }
            }
        //If two pairs, pick the card that has been in the hand the longest
        } else if (pairs.size() == 2){
            //No need to check size of discard list, as for two pairs all cards must be valid discards
            //Check through weightings and log which card has been in the hand the longest
            for (int j = 0; j < this.weighting.size(); j++) {
                if (weighting.get(j) > maxWeight){
                    maxWeight = weighting.get(j);
                    discardIndex = j;
                }
            }
        //For a single pair, do not remove the values (unless the other values are preferred)
        } else if (pairs.size() == 1){
            //Check weightings of other values
            //If a single pair are the only valid discards, pick card with highest weighting
            if (discardList.size() == 2){
                for (int j = 0; j < this.weighting.size(); j++) {
                    if ((weighting.get(j) > maxWeight)){
                        maxWeight = weighting.get(j);
                        discardIndex = j;
                        }
                }
            //Case where pair is preferred over other non-preferred values
            } else{
                if (discardList.size() == 4){
                    for (int j = 0; j < this.weighting.size(); j++) {
                        if (!pairs.contains(this.hand.get(j).getCardValue())){
                            if (weighting.get(j) > maxWeight){
                                maxWeight = weighting.get(j);
                                discardIndex = j;
                            }
                        }
                    }
                //Case where there is a single preferred card and a pair
                } else {
                    for (int j = 0; j < discardList.size(); j++){
                        if (discardList.get(j).getCardValue() != pairs.get(0)){
                            discardIndex = j;
                        }
                    }
                }
            } 
        //Checking case where there are no pairs or trios
        } else {
            for (int j = 0; j < this.weighting.size(); j++) {
                if (weighting.get(j) > maxWeight){
                    maxWeight = weighting.get(j);
                    discardIndex = j;
                }
            }
        }

        //discardList should always hold the correct card to discard
        //Holds regardless of the structure of the hand
        cardToDiscard = discardList.get(discardIndex);
        //Whenever a slot changes, reset time that slot has been held
        //+1 to all times, starts from 0
        for (int i = 0; i < this.weighting.size(); i++){
            int currentElement = this.weighting.get(i);
            this.weighting.set(i, currentElement + 1);
        }
        //Reset weight index for value
        this.weighting.remove(discardIndex);
        //return card to discard (returns a card)
        if (cardToDiscard == null){
            throw new NullPointerException("No card was found to discard");
        }
        this.hand.remove(cardToDiscard);
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
