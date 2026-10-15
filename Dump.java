// Any unused methods or class structures, that changed from our plan

import java.util.ArrayList;

public class Dump {

    // Comes from CreateDeck.java
    //The highest number there can be in a pack is n, 
    //After 4 of each number is in the pack, 
    //it repeats until you reach 8n cards
    //Will provide advantage to lower number players, doesn't matter just for testing
    //Randomise order of each line, otherwise you'd all start with 4 of the same number


    // Comes from CardGame.java
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

    // All of this needs to be in an overriden run method

    // Threads need to be able to handle case where another player has won
    // All actions need to stop
    // Write to output file line of who is notifying the win

    // Write to everyone elses file (Each player's file only receives their one notify message)
    // For each player (if currentPlayer = n, then inform player 1)
    // Write to file "currentPlayer has informed currentPlayer+1 that currentPlayer has won"

     // Check player is a winner
     // Interrupt all threads if they do - needs to use notify
    // Draw new card from draw pile - store card drawn in temporary variable
    // Discard pile n+1 except if player number is n, then discard to 1
    // Discard Card from hand - store card discarded in temporary variable
    // Add discard card to discard pile
    // Add drawn card to hand
    // Make sure this is done atomically 
    
    // Then for interrupts iterate through the list to interrupt them all

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
