//Must be executable

public class CardGame{
    //Get input for n players
    //Get input for name of text file (not creating anything, already made text file, the name will be loaded from this)
    //Exception: file not found
    //Console input for both of them
    //Exception: Player n has to be a non negative integer and also not 0

    //Loop through n times to create n players
    //Use current instantiation (this is i of the loop) to pass to parameter of initialisation of player
    //Create n decks as well

    //Distribute from pack 4 cards to each player
    //Loop of n times
    //Each loop take bottom written line from txt file, add to player n
    //Remove bottom written line from txt file
    //(could be a method which performs distribution for players and decks, called for each one)

    //4 passes, each pass distributes 1 card to each player, starting with 1 and ending with n

    //Remaining cards distributed to n equal sized decks
    //4 passes, 4 cards per deck
    //Loop of n times
    //Each loop take bottom written line from txt file, add to deck n
    //Remove bottom written line from txt file
    //(could be a method which performs distribution for players and decks, called for each one)

    //Draw and discard must be done sequentially and must happen (either both or none happen)
    //Loop through n players
    //Write to initial hand (flag)
    //Exception: Player must exist
    //For each player, check if all cards are the same number for player n (call checkWinCondition)
    //If it is, declare winner and end game
    //If it isn't, next player
    //If end of list, move on to draw and discard

    //Loop n players
    //For each player, draw card from deck n, discard to deck n+1
    //Expection - if player == n, discard to 1

    //Write to each player's individual output file:
    //Exception: must be a positive non 0 integer
    //Draw
    //Exeption: There must be a card in the draw pile
    //Draw deck must exist
    //Discard
    //Exception: discard deck must exist
    //Current hand
    //Exception: Player must exist

    //After draw and discard have finished for a given player, that player is checked again

    //All threads should run in parallel (must use synchronise())
    //If a thread is faster, and empties a draw deck, then it has to wait until the deck isn't empty
    //(this is starvation mention that the doc)

    //End game
    //For each deck, write deck contents to individual deck output files
    //Exception: Deck must exist

    
}
