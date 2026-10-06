import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

package tests;

public class PlayerMaintains4CardsTest {
    CardGame game;

    @BeforeEach
    void setUp() throws Exception{
        game = new CardGame();
    }
}
