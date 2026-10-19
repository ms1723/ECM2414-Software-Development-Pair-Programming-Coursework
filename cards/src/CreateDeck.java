// Purely for our own testing
import java.io.File;       // Import the File class
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CreateDeck {
    // Constructor
    public CreateDeck(int cardNumberInput){
        // Create the list
        ArrayList<Integer> cardPack = new ArrayList<>();
        // Go through each number (will be number of players)
        // Needs to repeat twice to get 8n cards
        for (int k = 0; k <= 1; k++){
            for (int i = 1; i <= cardNumberInput; i++) {
                // Append 4 of the current number
                for (int j = 0; j < 4; j++) {
                    cardPack.add(i);
                }
            }
        }

        // Shuffle it
        Collections.shuffle(cardPack);
        // Create the shuffled pack
        try {
            File cardPackFile = new File("cardPackFile4.txt"); 
            if (cardPackFile.createNewFile()) {           // Try to create the file
                System.out.println("File created: " + cardPackFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }
        try (FileWriter shufflePackFileWriter = new FileWriter("cardPackFile4.txt");){
            // Loop for each line in the list
            for (int i = 0; i < cardPack.size(); i++) {
                // If it's the first line don't start on a new line
                if (i == 0){
                    shufflePackFileWriter.write(""+cardPack.get(i));
                } else{
                    shufflePackFileWriter.write("\n"+cardPack.get(i));
                }
            }
        // Catch error
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        CreateDeck Pack1 = new CreateDeck(4);
    }
}
