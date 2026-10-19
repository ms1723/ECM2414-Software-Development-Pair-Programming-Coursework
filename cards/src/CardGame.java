//Must be executable

import exceptions.HandEmptyException;
import exceptions.PlayernotFoundException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
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
    private int playerCount;

    //Methods
    public String getUserInput(){
        // Ask number of players
        String playerNumber = "0";
        String fileName = "";

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
            // Ask file name
            System.out.println("What is the file name for the deck of cards? (Including file type .txt)");
            fileName = numberOfPlayers.nextLine();  // Read user input
            
        }

        // Ask file name
        try{
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
        this.pack = new ArrayList<>();

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
        this.players = new ArrayList<>();

        // Loop for each player
        for (int i = 1; i <= this.playerCount; i++){
            // Add the new object for the player
            players.add(new Player(i));
        }
    }

    // Creating the deck objects
    public void initializeDecks(){
        this.decks = new ArrayList<>();

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

        // Create all the player's files.txt
        for (int i = 1; i <= playerCount; i++) {
            Player currentPlayer = this.players.get(i-1);
            try {
                File currentPlayerFile = new File("Player"+(i)+"_output.txt");
                if (currentPlayerFile.createNewFile()) {           // Try to create the file
                    System.out.println("File created: " + currentPlayerFile.getName());
                    try ( // Write to all the player's files their initial hand
                        FileWriter currentPlayerFileWriter = new FileWriter(currentPlayerFile.getName(), true)) {
                        currentPlayerFileWriter.write("player " + (i) + "'s initial hand - " + currentPlayer.getHand());
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

        // Create all the decks's files.txt
        for (int i = 1; i <= decks.size(); i++) {
            try {
                File currentDeckFile = new File("Deck"+(i)+"_output.txt");
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
        int currentPlayerID;
        Deck drawDeck;
        Deck discardDeck;
        Card drawCard;
        Card discardCard;

        // Assign the draw and discard decks,
        public void findDecks() {
            // If the last player
            if (currentPlayerID == players.size()) {
                drawDeck = decks.get(currentPlayerID - 1);
                discardDeck = decks.get(0);
            // If the player number is not the last player number
            } else {
                drawDeck = decks.get(currentPlayerID - 1);
                discardDeck = decks.get(currentPlayerID);
            }
        } 

        // Constructor
        public DrawDiscardThread (int i){
            this.currentPlayer = players.get(i);
            currentPlayerID = currentPlayer.getPlayerNo();
        }

        @Override 
        public void run() {
            try {
                // Execption check that the player exists
                if (!players.contains(currentPlayer)) {
                    throw new PlayernotFoundException("Player not found");
                } 
            
                try {
                    while(!isInterrupted()){
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
                        //Write the draw to the player's text file
                        try (FileWriter currentPlayerFileWriter = new FileWriter("Player"+(currentPlayerID)+"_output.txt", true)){
                            currentPlayerFileWriter.write("\nplayer " + (currentPlayerID) 
                                                            + " draws a " + drawCard.getCardValue()
                                                            + " from deck " + drawDeck.getDeckNo());
                            currentPlayerFileWriter.close();  // must close manually
                            System.out.println("Successfully wrote to Player" +(currentPlayerID)+"_output.txt.");
                        } catch (IOException fileDoestExist) {
                            System.out.println("An error occurred.");
                            fileDoestExist.printStackTrace();
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
                        //Write the discard to the output file
                        try (FileWriter currentPlayerFileWriter = new FileWriter("Player"+(currentPlayerID)+"_output.txt", true)){
                            currentPlayerFileWriter.write("\nplayer " + (currentPlayerID) 
                                                            + " discards a " + discardCard.getCardValue()
                                                            + " to deck " + discardDeck.getDeckNo());
                            currentPlayerFileWriter.close();  // must close manually
                            System.out.println("Successfully wrote to Player" +(currentPlayerID)+"_output.txt.");
                        } catch (IOException fileDoestExist) {
                            System.out.println("An error occurred.");
                            fileDoestExist.printStackTrace();
                        }

                        // Add the discarded card to the discard deck
                        discardDeck.addToDeck(discardCard);
                        
                        // Add the draw card to the hand
                        try {
                            currentPlayer.addToHand(drawCard);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Write the player's new hand to the output file
                        try (FileWriter currentPlayerFileWriter = new FileWriter("Player"+(currentPlayerID)+"_output.txt", true)) {
                            currentPlayerFileWriter.write("\nplayer " + (currentPlayerID) + "'s current hand - " + currentPlayer.getHand());
                            System.out.println("Successfully wrote to Player" +(currentPlayerID)+"_output.txt.");
                            currentPlayerFileWriter.close();  // must close manually
                        } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace(); // Print error details
                        }
                    }
                // Catch statement for when a player wins
                } catch (InterruptedException e) {
                    // Player has won so interrupt all threads
                    InterruptAllThreads();

                    // Write to terminal currentPlayer wins
                    System.out.println("player " + (currentPlayerID) + " wins");

                    // Write to the winner's file:
                    try (FileWriter currentPlayerFileWriter = new FileWriter("Player"+currentPlayerID+"_output.txt", true)){
                        currentPlayerFileWriter.write("\nplayer " + (currentPlayerID) + " wins");
                        currentPlayerFileWriter.write("\nplayer " + (currentPlayerID) + " exits");
                        currentPlayerFileWriter.write("\nplayer " + (currentPlayerID) + " final hand: " + currentPlayer.getHand());
                        currentPlayerFileWriter.close();  // must close manually
                        System.out.println("Successfully wrote to Player" +(currentPlayerID)+"_output.txt.");
                    } catch (IOException fileDoestExist) {
                        System.out.println("An error occurred.");
                        fileDoestExist.printStackTrace();
                    }
                            
                    // Loop through players to notify them all that someone has won
                    for (Player iteratePlayer : players) {
                        int iteratePlayerID = iteratePlayer.getPlayerNo();
                        // Don't notify the winner that they've won
                        if (currentPlayer != iteratePlayer){
                            try (FileWriter currentPlayerFileWriter = new FileWriter("Player"+iteratePlayerID+"_output.txt", true)){
                                currentPlayerFileWriter.write(
                                    "\nplayer " + 
                                    (currentPlayerID) + 
                                    " has informed player " + 
                                    (iteratePlayerID) + 
                                    " that player " + 
                                    (currentPlayerID) + 
                                    " has won"
                                );
                                currentPlayerFileWriter.write("\nplayer " + (iteratePlayerID) + " exits");
                                currentPlayerFileWriter.write("\nplayer " + (iteratePlayerID) + " final hand: " + iteratePlayer.getHand());
                                currentPlayerFileWriter.close();  // must close manually
                                System.out.println("Successfully wrote to Player" +(iteratePlayerID)+"_output.txt.");
                            } catch (IOException fileDoestExist) {
                                System.out.println("An error occurred.");
                                fileDoestExist.printStackTrace();
                            }
                        }
                    }
                    //Write final deck contents
                    for (Deck deck : decks) {
                        int deckID = deck.getDeckNo();
                        // Don't notify the winner that they've won
                        try (FileWriter currentDeckFileWriter = new FileWriter("Deck"+deckID+"_output.txt", true)){
                            currentDeckFileWriter.write("deck" + deckID + "contents " + deck.getDeckContents());
                            currentDeckFileWriter.close();  // must close manually
                            System.out.println("Successfully wrote to Deck" +(deckID)+"_output.txt.");
                        } catch (IOException fileDoestExist) {
                            System.out.println("An error occurred.");
                            fileDoestExist.printStackTrace();
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
        for (int i = 0; i < playerCount; i++) {
            // Create all the threads and store them in threadList
            threadList[i] = new DrawDiscardThread(i);
            threadList[i].findDecks();
            threadList[i].start();
        }
        for (int j = 0; j < playerCount; j++) {
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
        this.playerCount = numberOfPlayers;

        this.initializePlayers();
        this.initializeDecks();
    }

    //Accessor methods
    public int getPlayerCount(){
        return this.playerCount;
    }

    public Player getPlayer(int index){
        return this.players.get(index);
    }

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        String fileName = cardGame.getUserInput();

        cardGame.initializePlayers();
        cardGame.initializeDecks();

        try {
            cardGame.validateAndLoadPack(cardGame.getPlayerCount(), fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            cardGame.distributeCards();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        cardGame.makeThreads();
    }
}

