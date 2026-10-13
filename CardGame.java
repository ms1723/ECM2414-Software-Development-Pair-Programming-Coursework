//Must be executable


import exceptions.PlayernotFoundException;
import java.io.BufferedReader;
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
    private static int playerCount;

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
    public boolean validateAndLoadPack(int playerCount, String packName) throws IOException {
        //Validate that a pack contains a combination of cards in order to win
        try (BufferedReader br = new BufferedReader(new FileReader(packName))) {
            // List which holds the count for each occurance of a number
            //Pack holds 8n cards
            int[] cardItemized = new int[8*playerCount];  

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

    public class DrawDiscardThread extends Thread {
        //Attributes
        Player currentPlayer;

        @Override 
        public void run() {
            try {
                // Execption check that the player exists
                if (!players.contains(currentPlayer)) {
                    throw new PlayernotFoundException("Player not found");
                } 
                
                try {
                    if (true) {
                        throw new InterruptedException("A player has won");
                    }
                // All of this needs to be in an overriden run method

                // Check player is a winner
                // Interrupt all threads if they do - needs to use notify
                // Draw new card from draw pile - store card drawn in temporary variable
                // Discard pile n+1 except if player number is n, then discard to 1
                // Discard Card from hand - store card discarded in temporary variable
                // Add discard card to discard pile
                // Add drawn card to hand
                // Make sure this is done atomically 

               
                
                } catch (InterruptedException e) {
                    // Threads need to be able to handle case where another player has won
                    // All actions need to stop
                    // Write to output file line of who is notifying the win
            }
                
            } catch (PlayernotFoundException e) {
            }
        }

    }

    public static void main(String[] args) {
        // Loop for each player 
        for (int i = 1; i <= playerCount; i++) {
            // Run draw and discard thread
            
        }
    }


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

    public void quickInitialize(int numberOfPlayers){
        this.playerCount = numberOfPlayers;
        this.players = new ArrayList<>();
        this.decks = new ArrayList<>();
        this.pack = new ArrayList<>();

        this.initializePlayers();
        this.initializeDecks();
    }

    //Accessor methods
    public int getPlayerCount(){
        return this.playerCount;
    }
}
