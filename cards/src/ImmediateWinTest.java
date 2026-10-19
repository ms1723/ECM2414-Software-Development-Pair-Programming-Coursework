import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImmediateWinTest {
    CardGame game;

    @BeforeEach
    void setUp() throws Exception{
        game = new CardGame();
    }

    @Test
    void generatesCorrectOutputFiles() throws Exception{
    }

    @Test
    void packLoads() throws IOException{
        game.quickInitialize(4);
        assertTrue(game.validateAndLoadPack(game.getPlayerCount(), "cardPackFile4.txt"));
    }
}
