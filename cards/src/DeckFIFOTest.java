import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckFIFOTest {
    CardGame game;
    Deck deck;

    @BeforeEach
    void setUp() throws Exception{
        game = new CardGame();
        deck = new Deck(1);
    }

    @Test 
    void cannotPickFromEmptyDeck() throws Exception{
        assertThrows(NullPointerException.class, () -> deck.pickCard());
    }

    @Test
    void firstCardLeavesFirst() throws Exception{
        Card card1 = new Card(0, 1);
        Card card2 = new Card(1, 2);

        deck.addToDeck(card1);
        deck.addToDeck(card2);

        assertTrue(deck.pickCard() == card1);
        
    }
}
