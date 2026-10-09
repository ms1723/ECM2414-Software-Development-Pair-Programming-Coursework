//Must be executable

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//Works with threads
//Needs a runnable method?
//Threads to draw, discard and check?
public class CardGame{
    //Attributes
    private ArrayList<Integer> pack;
    private ArrayList<Player> players;
    private ArrayList<Deck> decks;
    private ArrayList<String> fileNames;
    private int playerCount;

    public class DrawDiscardThread extends Thread{
        //Attributes
        int playerNo;
    }
    //Methods
    public void getUserInput(){
        // Ask number of players
        String playerNumber = "0";

        // Must be a non negative non 0 integer, so keep asking 
        while (Integer.parseInt(playerNumber) <= 0){
            Scanner numberOfPlayers = new Scanner(System.in);  // Create a Scanner object
            System.out.println("How many player are there going to be?");

            playerNumber = numberOfPlayers.nextLine();  // Read user input
            System.out.println("You have picked" + playerNumber);  // Output user input
            // Send to class attribute to be used by other methods,
            this.playerCount = Integer.parseInt(playerNumber);

            // Send error message if input doesnt reach requirements
            if (Integer.parseInt(playerNumber) <= 0){
                System.err.println("Must be a positive integer, re-enter number");
            }
        }

        // Ask file name
        Scanner fileNameScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("What is the file name for the deck of cards? (Including file type .txt)");

        String fileName = fileNameScanner.nextLine();  // Read user input
        System.out.println("Loading" + fileName);  // Output user input
    }
    //Parameters - will take in a data structure which contains all the cards in a pack
    public boolean validateAndLoadPack(int playerCount, String packName) throws FileNotFoundException {
        //Validate that a pack contains a combination of cards in order to win
        try (BufferedReader br = new BufferedReader(new FileReader(packName))) {
            // List which holds the count for each occurance of a number
            int[] cardItemized = new int[playerCount];  

            String line;
            while ((line = br.readLine()) != null) {
                // Convert the line to an integer
                int lineAsNumber = Integer.parseInt(line);
                // Load each item into the pack ArrayList as you go through the file
                this.pack.add(lineAsNumber);
                // Mark the occurance of that number 
                cardItemized[lineAsNumber]++;
            }
            // Check if there is any value that has 4 or more cards
            for (int count : cardItemized) {
                if (count >= 4){
                    // The pack is valid so return true
                    return true;
                }
            }

            // If not finding 4 or more cards of at least 1 value
            return false;

        } catch (IOException e) {
            System.out.println("Error reading file.");
            return false;
        }
    }

    public void writePlayerFile(int playerNumber, String message){
        
    }

    public void writeDeckFile(int deckNumber, String message){

    }

    // Creating the player objects
    public void initializePlayers(){
        // Loop for each player
        for (int i = 1; i <= this.playerCount; i++){
            // Add the new object for the player
            players.add(new Player(i));
        }
    }

    // Creating the deck objects
    public void initializeDecks(){
        // Loop for each player (will be 1 deck per player)
        for (int i = 1; i <= this.playerCount; i++){
            // Add the new object for the deck
            decks.add(new Deck(i));
        }
    }

    // Distributing the cards
    public void distributeCards() throws Exception {
        // Tally for number of cards, in order to correctly distribute cards + cardIDs
        int cardCount = 0;

        // Looping 4 times to give 4 cards to each player
        for (int j = 1; j <= 4; j++) {
            // Loop through players to distribute to the players
            for (int i = 0; i < this.playerCount; i++){
                Card currentCard = new Card(cardCount, this.pack.get(cardCount));
                // Get the current player
                Player currentPlayer = this.players.get(i);

                // Add the current card to the current player's hand
                currentPlayer.addToHand(currentCard);

                // Increment cards grabs
                cardCount++;
            }
        }

        // Looping 4 times to give 4 cards to each deck
        for (int j = 1; j <= 4; j++) {
            // Loop through players to distribute to the decks
            for (int i = 0; i < this.playerCount; i++){
                Card currentCard = new Card(cardCount, this.pack.get(cardCount));
                // Get the deck
                Deck currentDeck = this.decks.get(i);

                // Add the current card to the current player's hand
                currentDeck.addToDeck(currentCard);

                // Increment cards grabs
                cardCount++;
            }
        }
    }

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
