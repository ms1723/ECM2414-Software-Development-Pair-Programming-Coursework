import org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DeckFIFOTest {
    CardGame game;

    @BeforeEach
    void setUp() throws Exception{
        game = new CardGame();
    }
}
