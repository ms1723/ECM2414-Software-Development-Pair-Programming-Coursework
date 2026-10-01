//Must be executable

public class CardGame{
    //Get input for n players
    //Get input for name of text file

    //Loop through n times to create n players
    //Use current instantiation (this is i of the loop) to pass to parameter of initialisation of player
    //Create n decks as well

    //Create pack - 8n cards
    //Looping 8n times
    //Random number 0-length of list inclusive
    //Write index[random] to txt file on new line
    //Remove index[random] from initial list
    //If initial list is empty (should be declared before writing anything) replenish list first


    //Distribute from pack 4 card to each player
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
    //For each player, check if all cards are the same number for player n (call checkWinCondition)
    //If it is, declare winner and end game
    //If it isn't, next player
    //If end of list, move on to draw and discard

    //Loop n players
    //For each player, draw card from deck n, discard to deck n+1
    //Expection - if player == n, discard to 1

    //Write to each player's individual output file:
    //Draw
    //Discard
    //Current hand

    //After draw and discard have finished for a given player, that player is checked again

    //End game
    //For each deck, write deck contents to individual deck output files

    
}
