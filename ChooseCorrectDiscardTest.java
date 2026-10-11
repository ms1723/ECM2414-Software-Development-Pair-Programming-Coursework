import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChooseCorrectDiscardTest {
    CardGame game;
    Player player;
    Card card1;
    Card card2;
    Card card3;
    Card card4;

    @BeforeEach
    void setUp() throws Exception{
        game = new CardGame();
        player = new Player(1);
    }

    @Test
    void doesNotDiscardTrio() throws Exception{
        card1 = new Card(1, 2);
        card2 = new Card(2, 2);
        card3 = new Card(3, 2);
        card4 = new Card(4, 3);

        player.addToHand(card1);
        player.addToHand(card2);
        player.addToHand(card3);
        player.addToHand(card4);

        assertTrue(player.chooseDiscard(4) == card4);
    }

    @Test
    void doesNotDiscardPair() throws Exception{
        card1 = new Card(1, 1);
        card2 = new Card(2, 2);
        card3 = new Card(3, 2);
        card4 = new Card(4, 3);

        player.addToHand(card1);
        player.addToHand(card2);
        player.addToHand(card3);
        player.addToHand(card4);

        assertTrue(player.chooseDiscard(4) == card4);
    }

    @Test
    void packLoads() throws IOException{
        game.quickInitialize(4);
        assertTrue(game.validateAndLoadPack(game.getPlayerCount(), "cardPackFile4.txt"));
    }
}