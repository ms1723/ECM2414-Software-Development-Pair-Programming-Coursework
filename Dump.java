// Any unused methods or class structures, that changed from our plan


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
}
