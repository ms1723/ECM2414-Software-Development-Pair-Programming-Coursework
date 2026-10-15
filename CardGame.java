//Must be executable


import exceptions.HandEmptyException;
import exceptions.PlayernotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    private DrawDiscardThread[] threadList;
    private static int playerCount;

    //Methods
    public String getUserInput(){
        // Ask number of players
        String playerNumber = "0";

        // Must be a non negative non 0 integer, so keep asking 
        while (Integer.parseInt(playerNumber) <= 0){
            Scanner numberOfPlayers = new Scanner(System.in);  // Create a Scanner object
            System.out.println("How many player are there going to be?");

            playerNumber = numberOfPlayers.nextLine();  // Read user input
            System.out.println("You have picked" + playerNumber);  // Output user input
            // Send to class attribute to be used by other methods,
            CardGame.playerCount = Integer.parseInt(playerNumber);

            // Send error message if input doesnt reach requirements
            if (Integer.parseInt(playerNumber) <= 0){
                System.err.println("Must be a positive integer, re-enter number");
            } else {
                numberOfPlayers.close();
            }
        }

        // Ask file name
        System.out.println("What is the file name for the deck of cards? (Including file type .txt)");
        try (Scanner fileNameScanner = new Scanner(System.in) // Create a Scanner object
        ) {
            String fileName = fileNameScanner.nextLine();  // Read user input
            File doesFileExist = new File(fileName);
            if (doesFileExist.exists()){
                System.out.println("Loading" + fileName);  // Output user input
            } else {
                throw new FileNotFoundException();
            }

            return fileName;
            
        } catch (FileNotFoundException e) {
            System.out.println("File Doesn't Exist");
            e.printStackTrace();
            return "An Error Occured";
        }
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

    // Creating the player objects
    public void initializePlayers(){
        // Loop for each player
        for (int i = 1; i <= CardGame.playerCount; i++){
            // Add the new object for the player
            players.add(new Player(i));
        }
    }

    // Creating the deck objects
    public void initializeDecks(){
        // Loop for each player (will be 1 deck per player)
        for (int i = 1; i <= CardGame.playerCount; i++){
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

        // Create all the player's files.txt
        for (int i = 0; i <= playerCount; i++) {
            Player currentPlayer = this.players.get(i);
            try {
                File currentPlayerFile = new File("Player"+i+"file.txt");
                if (currentPlayerFile.createNewFile()) {           // Try to create the file
                    System.out.println("File created: " + currentPlayerFile.getName());
                    try ( // Write to all the player's files their initial hand
                        FileWriter currentPlayerFileWriter = new FileWriter(currentPlayerFile.getName())) {
                        currentPlayerFileWriter.write("player " + i + " " + currentPlayer.getHand());
                        System.out.println("Successfully wrote to the file.");
                    }
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace(); // Print error details
            }
        }

        

        // Looping 4 times to give 4 cards to each deck
        for (int j = 1; j <= 4; j++) {
            // Loop through players to distribute to the decks
            for (int i = 0; i < CardGame.playerCount; i++){
                Card currentCard = new Card(cardCount, this.pack.get(cardCount));
                // Get the deck
                Deck currentDeck = this.decks.get(i);

                // Add the current card to the current player's hand
                currentDeck.addToDeck(currentCard);

                // Increment cards grabs
                cardCount++;
            }
        }

        // Create all the decks's files.txt
        for (int i = 0; i <= decks.size(); i++) {
            try {
                File currentDeckFile = new File("Deck"+i+"file.txt");
                if (currentDeckFile.createNewFile()) {           // Try to create the file
                    System.out.println("File created: " + currentDeckFile.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace(); // Print error details
            }
        }
    }

    public class DrawDiscardThread extends Thread {
        //Attributes
        Player currentPlayer;
        int currentPlayerID = currentPlayer.getPlayerNo();
        Deck drawDeck;
        Deck discardDeck;
        Card drawCard;
        Card discardCard;

        // Assign the draw and discard decks,
        public void findDecks() {
                // If the player number is not the first or last player number
                if (currentPlayerID != 1 && currentPlayerID != players.size()) {
                    drawDeck = decks.get(currentPlayerID - 1);
                    discardDeck = decks.get(currentPlayerID + 1);
                // If the first player
                } else if (currentPlayerID == 1) {
                    drawDeck = decks.get(players.size());
                    discardDeck = decks.get(currentPlayerID + 1);
                // If the last player
                } else if (currentPlayerID == players.size());
                    drawDeck = decks.get(currentPlayerID - 1);
                    discardDeck = decks.get(1);
        } 


        // Constructor
        public DrawDiscardThread (int i){
            this.currentPlayer = players.get(i);
            
        }

        @Override 
        public void run() {
            try {
                // Execption check that the player exists
                if (!players.contains(currentPlayer)) {
                    throw new PlayernotFoundException("Player not found");
                } 
                
                try {
                    // Check if there's a winner, notify all threads if there is one
                    if (currentPlayer.checkWinCondition()) {
                        throw new InterruptedException("A player has won");
                    }

                    // Draw a card
                    try {
                        drawCard = drawDeck.pickCard();
                    } catch (NullPointerException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    

                    // Discard the card
                    try {
                        discardCard = currentPlayer.chooseDiscard(players.size());
                    } catch (HandEmptyException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                    // Add the discarded card to the discard deck
                    discardDeck.addToDeck(discardCard);
                    
                    // Add the draw card to the hand
                    try {
                        currentPlayer.addToHand(drawCard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                
                // Catch statement for when a player wins
                } catch (InterruptedException e) {
                    // Player has won so interrupt all threads
                    InterruptAllThreads();

                    // Write to terminal currentPlayer wins
                    System.out.println("player" + currentPlayerID + "wins");

                    // Write to the winner's file:
                    try (FileWriter currentPlayerFileWriter = new FileWriter("Player"+currentPlayerID+"file.txt")){
                        currentPlayerFileWriter.write("player " + currentPlayerID + " wins");
                        currentPlayerFileWriter.write("player " + currentPlayerID + " exits");
                        currentPlayerFileWriter.write("player " + currentPlayerID + " final hand: " + currentPlayer.getHand());
                        currentPlayerFileWriter.close();  // must close manually
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException fileDoestExist) {
                        System.out.println("An error occurred.");
                        fileDoestExist.printStackTrace();
                    }
                    
                    // Loop through players to notify them all that someone has won
                    for (Player iteratePlayer : players) {
                        int itereratePlayerID = iteratePlayer.getPlayerNo();
                        // Don't notify the winner that they've won
                        if (currentPlayer != iteratePlayer){
                            try (FileWriter currentPlayerFileWriter = new FileWriter("Player"+itereratePlayerID+"file.txt")){
                                currentPlayerFileWriter.write(
                                    "player " + 
                                    currentPlayerID + 
                                    " has informed " + 
                                    itereratePlayerID + 
                                    " that " + 
                                    currentPlayerID + 
                                    " has won"
                                );
                                currentPlayerFileWriter.close();  // must close manually
                                System.out.println("Successfully wrote to the file.");
                            } catch (IOException fileDoestExist) {
                                System.out.println("An error occurred.");
                                fileDoestExist.printStackTrace();
                            }
                        }
                    }
            }
                
            } catch (PlayernotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    }

    // Method to create the list of threads, so all active threads can be easily accessed
    public DrawDiscardThread[] makeThreads() {
        // Initialize list of threads
        threadList = new DrawDiscardThread[playerCount];


        // Loop for each player 
        for (int i = 1; i <= playerCount; i++) {
            // Create all the threads and store them in threadList
            threadList[i] = new DrawDiscardThread(i);
            threadList[i].start();
        }
        for (int j = 1; j <= playerCount; j++) {
            try {
                threadList[j].join(); 
            } catch (InterruptedException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        return threadList;
    }

    // Method to interrupt all the threads, used when a winner is found
    public void InterruptAllThreads() {
        // Interrupt all threads
        for (DrawDiscardThread currentThread : threadList) {
            currentThread.interrupt();
        }
    }

    // public static void main(String[] args){
    // }

    public void quickInitialize(int numberOfPlayers){
        CardGame.playerCount = numberOfPlayers;
        this.players = new ArrayList<>();
        this.decks = new ArrayList<>();
        this.pack = new ArrayList<>();

        this.initializePlayers();
        this.initializeDecks();
    }

    //Accessor methods
    public int getPlayerCount(){
        return CardGame.playerCount;
    }
}
